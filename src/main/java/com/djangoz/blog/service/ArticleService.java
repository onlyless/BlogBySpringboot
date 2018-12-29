package com.djangoz.blog.service;

import com.djangoz.blog.DAO.ArticleDao;
import com.djangoz.blog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public Article getById(Long id){
        Article article = articleDao.findById(id).get();
        return article;
    }

    public List<Article> getAllArticle(){
        List<Article> articles = articleDao.findAll();
        return articles;
    }

    public List<Article> getByCategoryName(String categoryName){
        return articleDao.findAllByCategory_Name(categoryName);
    }

    public void deleteByid(Long id){
        articleDao.deleteById(id);
    }

    public void save(Article article){
        articleDao.save(article);
    }

    public List<Article> search(String key){
        return articleDao.findByTitleContaining(key);
    }
}
