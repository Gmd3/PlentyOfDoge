package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-11-11.
 */
public class Match {
    public Match(int userID, int dogID, int matched, String dateMatched) {
        this.userID = userID;
        this.dogID = dogID;
        this.matched = matched;
        this.dateMatched = dateMatched;
    }
    int id;
    int userID;
    int dogID;

    public int getMatched() {
        return matched;
    }

    public void setMatched(int matched) {
        this.matched = matched;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getDogID() {
        return dogID;
    }

    public void setDogID(int dogID) {
        this.dogID = dogID;
    }

    public String getDateMatched() {
        return dateMatched;
    }

    public void setDateMatched(String dateMatched) {
        this.dateMatched = dateMatched;
    }

    int matched;
    String dateMatched;

}
