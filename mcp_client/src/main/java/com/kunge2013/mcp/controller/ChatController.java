package com.kunge2013.mcp.controller;

/**
 * @Author kunge2013
 * @Description TODO
 * @Date 2025/4/8 21:46
 * @Version 1.0
 */

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.mcp.spring.McpFunctionCallback;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author jianzhang
 * 2025/03/12/下午2:05
 */
@RestController
@RequestMapping("/dashscope/chat-client")
public class ChatController {

    private final ChatClient chatClient;

    private final ChatMemory chatMemory = new InMemoryChatMemory();

    public ChatController(ChatClient.Builder chatClientBuilder, List<McpFunctionCallback> functionCallbacks) {
        this.chatClient = chatClientBuilder
                .defaultFunctions(functionCallbacks.toArray(new McpFunctionCallback[0]))
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .defaultOptions(DashScopeChatOptions.builder().withTopP(0.7).build())
                .build();
    }


    @RequestMapping(value = "/generate_stream", method = RequestMethod.GET)
    public Flux<ChatResponse> generateStream(HttpServletResponse response, @RequestParam("id") String id, @RequestParam("prompt") String prompt) {
        response.setCharacterEncoding("UTF-8");
        var messageChatMemoryAdvisor = new MessageChatMemoryAdvisor(chatMemory, id, 10);
        return this.chatClient.prompt(prompt)
                .advisors(messageChatMemoryAdvisor).stream().chatResponse();
    }


    @GetMapping("/advisor/chat/{id}/{prompt}")
    public Flux<String> advisorChat(
            HttpServletResponse response,
            @PathVariable String id,
            @PathVariable String prompt) {

        response.setCharacterEncoding("UTF-8");
        var messageChatMemoryAdvisor = new MessageChatMemoryAdvisor(chatMemory, id, 10);
        return this.chatClient.prompt(prompt)
                .advisors(messageChatMemoryAdvisor).stream().content();
    }


}