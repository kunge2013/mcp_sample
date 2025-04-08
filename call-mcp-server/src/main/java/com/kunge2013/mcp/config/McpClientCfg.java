package com.kunge2013.mcp.config.McpClientCfg;

/**
 * @Author kunge2013
 * @Description TODO
 * @Date 2025/4/8 21:55
 * @Version 1.0
 */
import io.modelcontextprotocol.client.McpClient;
import org.springframework.ai.mcp.customizer.McpSyncClientCustomizer;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author jianzhang
 * 2025/03/18/下午8:02
 */
@Configuration
public class McpClientCfg implements McpSyncClientCustomizer {


    @Override
    public void customize(String name, McpClient.SyncSpec spec) {
        // do nothing
        spec.requestTimeout(Duration.ofSeconds(30));
    }
}