package com.djangoz.blog.controller;

import com.djangoz.blog.entity.Article;
import com.djangoz.blog.entity.Category;
import com.djangoz.blog.entity.User;
import com.djangoz.blog.service.ArticleService;
import com.djangoz.blog.service.CategoryService;
import com.djangoz.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @RequestMapping("")
    public String Index(Model model){
        List<Article> articles = articleService.getAllArticle();
        model.addAttribute("articles",articles);
        return "admin/index";
    }

    @RequestMapping("/login")
    public String Login(){
        return "admin/login";
    }

    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    public String CheckLogin(HttpServletResponse response, HttpSession session, User user, Model model){
        if(userService.login(user.getUsername(),user.getPassword())){
            Cookie cookie = new Cookie("user",user.toString());
            response.addCookie(cookie);
            session.setAttribute("user",user);
            System.out.println(user.getUsername()+"====="+user.getPassword());

            return "admin/";
        }else{
            model.addAttribute("error","用户名或者密码错误");
            return "admin/login";
        }
    }

    @RequestMapping(value = "/logout")
    public String SignOut(HttpSession session){
        session.removeAttribute("user");
        return "index";
    }

    @RequestMapping("/write")
    public String write(Model model){
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories",categories);
        model.addAttribute("article",new Article());
        return "admin/write";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id")Long id){
        articleService.deleteByid(id);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Article article){
        String name = article.getCategory().getName();
        Category category = categoryService.getByName(name);
        article.setCategory(category);
        if(article.getContent().length() > 40){
            article.setSummary(article.getContent().substring(0,40));
        }else{
            article.setSummary(article.getContent());
        }
        article.setDate(dateFormat.format(new Date()));
        articleService.save(article);
        return "redirect:/admin/";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id")Long id,Model model){
        Article article = articleService.getById(id);
        model.addAttribute("target",article);
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories",categories);
        model.addAttribute("article",article);
        return "admin/mange";
    }
}
