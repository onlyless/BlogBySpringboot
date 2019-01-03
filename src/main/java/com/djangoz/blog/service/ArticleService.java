package com.djangoz.blog.service;

import com.djangoz.blog.DAO.ArticleDao;
import com.djangoz.blog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    private static List<Article> cacheArticles = new ArrayList<>();
    private static Map<Long,Article> cacheArticle = new HashMap<>();

    public Article getById(Long id){
        if(cacheArticle.containsKey(id)){
            return cacheArticle.get(id);
        }
        Article article = articleDao.findById(id).get();
        cacheArticle.put(id,article);
        System.out.println("对"+ article.getTitle() + "做了数据缓存++++++++++++++++=");
        return article;
    }

    public List<Article> getAllArticle(){
        if(cacheArticles.size()!=0){
            return cacheArticles;
        }
        cacheArticles = articleDao.findAll();
        return cacheArticles;
    }

    public List<Article> getByCategoryName(String categoryName){
        return articleDao.findAllByCategory_Name(categoryName);
    }

    public void deleteById(Long id){
        cacheArticles.clear();
        cacheArticle.remove(id);
        articleDao.deleteById(id);
    }

    public void save(Article article){
        cacheArticles.clear();
        cacheArticle.put(article.getId(),article);
        articleDao.save(article);
        System.out.println("对"+ article.getTitle() + "做了数据缓存===============");
    }

    public List<Article> search(String key){
        return articleDao.findByTitleContaining(key);
    }
}
