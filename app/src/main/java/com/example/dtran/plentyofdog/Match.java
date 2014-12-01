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
    int matched;
    String dateMatched;

}
