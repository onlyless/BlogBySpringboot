package com.djangoz.blog.controller;

import com.djangoz.blog.entity.Article;
import com.djangoz.blog.service.ArticleService;
import com.youbenzi.mdtool.tool.MDTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("")
    public String getAllArticles(Model model){
        List<Article> articles = articleService.getAllArticle();
        model.addAttribute("articles",articles);
        return "index";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id")Long id,Model model){
        Article article = articleService.getById(id);
        article.setContent(MDTool.markdown2Html(article.getContent()));
        model.addAttribute("article",article);
        return "front/detail";
    }

}
