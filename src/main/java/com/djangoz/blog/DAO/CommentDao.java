package com.djangoz.blog.DAO;

import com.djangoz.blog.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comments,Long> {
}
