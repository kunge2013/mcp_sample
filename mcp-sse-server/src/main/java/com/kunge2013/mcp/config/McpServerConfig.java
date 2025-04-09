package com.kunge2013.mcp.config;

/**
 * @Author kunge2013
 * @Description TODO
 * @Date 2025/4/8 21:37
 * @Version 1.0
 */

import com.kunge2013.mcp.service.DateTimeService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jianzhang
 * 2025/03/18/下午3:23
 */
@Configuration
public class McpServerConfig {
    @Bean
    public ToolCallbackProvider dateTimeTools(DateTimeService dateTimeService) {
        return MethodToolCallbackProvider.builder().toolObjects(dateTimeService).build();
    }
}