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

    private int id;
    private int userid;
    private Date date;
    private String genre;
    private String title;
    private String text;
    private String status;
    private String currentcontrib;
    private String username;

    public Story() {
        status = "in prompt";
        currentcontrib = "";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserid() { return userid; }
    public void setUserid(int userid) {this.userid = userid; }

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

    public String getCurrentcontrib() { return currentcontrib; }
    public void setCurrentcontrib(String currentcontrib) { this.currentcontrib = currentcontrib; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
