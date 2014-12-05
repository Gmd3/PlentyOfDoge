package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 2014-10-17.
 */
public final class DogOwner {

    public DogOwner(int id,int dogID, int ownerID, String dateCreated, String lastEdited, String status) {
        this.id = id;
        this.ownerID = ownerID;
        this.dateCreated = dateCreated;
        this.lastEditted = lastEdited;
        this.status = status;
        this.dogID = dogID;
    }

    public DogOwner(int dogID, int ownerID, String dateCreated, String lastEdited, String status) {
        this.ownerID = ownerID;
        this.dateCreated = dateCreated;
        this.lastEditted = lastEdited;
        this.status = status;
        this.dogID = dogID;
    }

    int id;
    int ownerID;
    String dateCreated;
    String lastEditted;
    String status;
    int dogID;

}