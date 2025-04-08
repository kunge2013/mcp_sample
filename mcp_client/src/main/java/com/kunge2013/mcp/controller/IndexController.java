package com.kunge2013.mcp.controller;

/**
 * @Author kunge2013
 * @Description TODO
 * @Date 2025/4/8 21:47
 * @Version 1.0
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jianzhang
 * 2025/03/11/上午10:51
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String chat(Model model) {
        //model.addAttribute("name", "User");
        // 返回视图名称，对应 templates/index.html
        return "index";
    }

}