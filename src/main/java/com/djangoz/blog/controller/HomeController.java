package com.djangoz.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping(value = "")
    public String index(@RequestParam(defaultValue = "1")int page, Model model){
        page = page < 1 ? 0 : page-1;

        model.addAttribute("page",page+1);
        return "index";
    }
}
