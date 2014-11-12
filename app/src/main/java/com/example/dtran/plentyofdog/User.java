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
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    String username;
    String password;
    int userId;
    public User(){

    }
    public User(String username, String password, int userID){
        this.userId = userID;
        this.username = username;
        this.password = password;

    }
}
