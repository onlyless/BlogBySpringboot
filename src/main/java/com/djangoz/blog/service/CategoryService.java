package com.djangoz.blog.service;

import com.djangoz.blog.DAO.CategoryDao;
import com.djangoz.blog.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public List<Category> getAllCategory(){
        List<Category> categories = categoryDao.findAll();
        return categories;
    }

    public Category getById(Long id){
        return categoryDao.findById(id).get();
    }

    public Category getByName(String name){
        return categoryDao.findByName(name);
    }

}
