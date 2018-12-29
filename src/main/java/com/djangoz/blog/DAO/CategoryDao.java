package com.djangoz.blog.DAO;

import com.djangoz.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Long> {
    Category findByName(String name);
}
