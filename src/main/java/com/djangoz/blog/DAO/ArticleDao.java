package com.djangoz.blog.DAO;

import com.djangoz.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article,Long> {
    List<Article> findAllByCategory_Name(String categoryName);

    List<Article> findByTitleContaining(String title);
}
