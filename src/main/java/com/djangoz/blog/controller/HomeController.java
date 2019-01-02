package com.djangoz.blog.controller;

import com.djangoz.blog.entity.Article;
import com.djangoz.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ArticleService articleService;

    @GetMapping(value = "")
    public String index(Model model){
        List<Article> articles = articleService.getAllArticle();
        Collections.sort(articles);
        model.addAttribute("articles",articles);
        return "index";
    }
}
