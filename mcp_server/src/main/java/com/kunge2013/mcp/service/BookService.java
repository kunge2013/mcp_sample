package com.kunge2013.mcp.service;

/**
 * @Author kunge2013
 * @Description TODO
 * @Date 2025/4/8 21:40
 * @Version 1.0
 */
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    public record Book(List<String> isbn, String title, List<String> authorName) {
    }

    @Tool(description = "Get list of Books by title")
    public List<Book> getBooks(String title) {
        // 这里模拟查询DB操作
        return List.of(new Book(List.of("ISBN-88888888888"), "SpringAI教程", List.of("红专写的书")));
    }

    @Tool(description = "Get book titles by author")
    public List<String> getBookTitlesByAuthor(String authorName) {
        // 这里模拟查询DB操作
        return List.of(authorName+"SpringAI教程");
    }

}