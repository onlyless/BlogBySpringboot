package com.djangoz.blog.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",columnDefinition = "int")
    private Long id;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name="comm_ip")
    private String commIP;

    @Column(name = "date",columnDefinition = "datetime")
    private String date;

    @Column(name = "comment",columnDefinition = "text")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getCommIP() {
        return commIP;
    }

    public void setCommIP(String commIP) {
        this.commIP = commIP;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
