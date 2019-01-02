package com.djangoz.blog;

import com.djangoz.blog.entity.Article;
import com.djangoz.blog.service.ArticleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    ArticleService articleService;

    @Test
    public void testCache(){
        List<Article> articles = articleService.getAllArticle();
        System.out.println(articles.size());
        Article article = articles.get(0);
        String date = article.getDate();
        article.setDate(dateFormat.format(new Date()));
        articleService.save(article);
        List<Article> articles1 = articleService.getAllArticle();
        System.out.println(articles1.size());
        Article article1 = articles1.get(0);
        Assert.assertEquals(date,article1.getDate());
    }

    @Test
    public void testCacheById(){
        Article article = articleService.getById((long) 5);
        String date = article.getDate();
        article.setDate(dateFormat.format(new Date()));
        articleService.save(article);
        Article article1 = articleService.getById((long) 5);
        Assert.assertEquals(date,article1.getDate());
    }

    @Test
    public void contextLoads() {
        long now1 = System.currentTimeMillis();
        long now2 = new Date().getTime();
        System.out.println(new Timestamp(now1));
        System.out.println(new Timestamp(now2));
    }

}

