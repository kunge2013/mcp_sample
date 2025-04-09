package com.kunge2013.mcp;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.spec.McpSchema;

import java.util.Map;

/**
 * @Author kunge2013
 * @Description TODO
 * @Date 2025/4/9 13:43
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        var stdioParams = ServerParameters.builder("java")
                .args("-jar", "D:\\github.io\\mcp_sample\\mcp_weather_server\\target\\mcp-weather-server-0.0.1-SNAPSHOT.jar")
                .build();

        var stdioTransport = new StdioClientTransport(stdioParams);

        var mcpClient = McpClient.sync(stdioTransport).build();

        mcpClient.initialize();

        McpSchema.ListToolsResult toolsList = mcpClient.listTools();

        McpSchema.CallToolResult weather = mcpClient.callTool(
                new McpSchema.CallToolRequest("getWeatherForecastByLocation",
                        Map.of("latitude", "47.6062", "longitude", "-122.3321")));

        McpSchema.CallToolResult alert = mcpClient.callTool(
                new McpSchema.CallToolRequest("getAlerts", Map.of("state", "NY")));

        mcpClient.closeGracefully();
    }
}
