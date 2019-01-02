package com.djangoz.blog.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="article")
public class Article implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",columnDefinition = "int")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content",columnDefinition = "text")
    private String content;

    @ManyToOne
    private Category category;

    @Column(name = "summary",columnDefinition = "text")
    private String summary;

    @Column(name = "date",columnDefinition = "datetime")
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(Object o) {
        return ((Article)o).getDate().compareTo(this.getDate());
    }
}
