package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-11-11.
 */
public class User {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return ownerId;
    }

    public void setUserId(int userId) {
        this.ownerId = userId;
    }

    String username;
    String password;
    int ownerId;
    public User(){

    }
    public User(String username, String password, int OwnerId){
        this.ownerId = OwnerId;
        this.username = username;
        this.password = password;

    }
}
