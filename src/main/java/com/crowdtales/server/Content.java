package com.crowdtales.server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private int userid;
    private String username;
    private String title;
    private String text;
    private Date date;
    private String storyid;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserid() { return userid; }
    public void setUserid(int userid) { this.userid = userid; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getStoryid() { return storyid; }
    public void setStoryid(String storyid) { this.storyid = storyid; }
}
