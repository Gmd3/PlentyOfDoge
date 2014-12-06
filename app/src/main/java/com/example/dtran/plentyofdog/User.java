package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-11-11.
 * The User class
 */
public class User {
    int id;


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
    public User(int id, String username, String password, int OwnerId){
        this.id = id;
        this.ownerId = OwnerId;
        this.username = username;
        this.password = password;

    }
}
