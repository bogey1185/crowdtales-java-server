package com.crowdtales.server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int Id;
    private int user_id;
    private Date date;
    private String genre;
    private String title;
    private String text;
    private String status = "in prompt";
    private String currentContrib = "";

    public int getId() { return Id; }
    public void setId(int id) { Id = id; }

    public int getUser_id() { return user_id; }
    public void setUser_id(int user_id) {this.user_id = user_id; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCurrentContrib() { return currentContrib; }
    public void setCurrentContrib(String currentContrib) { this.currentContrib = currentContrib; }
}
