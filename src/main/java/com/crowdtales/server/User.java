package com.crowdtales.server;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String password2;
    private String email;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPassword2() { return password2; }
    public void setPassword2(String password2) { this.password2 = password2; }
    public void setPassword2AsNull() { this.password2 = null; };

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
