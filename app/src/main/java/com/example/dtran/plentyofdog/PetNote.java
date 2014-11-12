package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-11-11.
 */
public class PetNote {

    int id;
    int dogID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDogID() {
        return dogID;
    }

    public void setDogID(int dogID) {
        this.dogID = dogID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String title;
    String description;
    public PetNote(int dogID, String title, String description) {
//        this.id = id;
        this.dogID = dogID;
        this.title = title;
        this.description = description;
    }

}
