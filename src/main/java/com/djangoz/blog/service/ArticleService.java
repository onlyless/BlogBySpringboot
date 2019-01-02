package com.djangoz.blog.service;

import com.djangoz.blog.DAO.ArticleDao;
import com.djangoz.blog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    private static List<Article> cacheArticle = new ArrayList<>();

    public Article getById(Long id){
        Article article = articleDao.findById(id).get();
        System.out.println("对"+ article.getTitle() + "做了数据缓存++++++++++++++++=");
        return article;
    }

    public List<Article> getAllArticle(){
        if(cacheArticle.size()!=0){
            return cacheArticle;
        }
        cacheArticle = articleDao.findAll();
        return cacheArticle;
    }

    public List<Article> getByCategoryName(String categoryName){
        return articleDao.findAllByCategory_Name(categoryName);
    }

    public void deleteById(Long id){
        cacheArticle.clear();
        articleDao.deleteById(id);
    }

    public void save(Article article){
        cacheArticle.clear();
        articleDao.save(article);
        System.out.println("对"+ article.getTitle() + "做了数据缓存===============");
    }

    public List<Article> search(String key){
        return articleDao.findByTitleContaining(key);
    }
}
