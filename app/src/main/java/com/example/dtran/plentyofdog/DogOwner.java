package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 2014-10-17.
 */
public final class DogOwner {

    public DogOwner(int id,int dogID, int ownerID, String dateCreated, String lastEditted, String status) {
        this.id = id;
        this.ownerID = ownerID;
        this.dateCreated = dateCreated;
        this.lastEditted = lastEditted;
        this.status = status;
        this.dogID = dogID;
    }
    int id;

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLastEditted() {
        return lastEditted;
    }

    public void setLastEditted(String lastEditted) {
        this.lastEditted = lastEditted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    int ownerID;
    String dateCreated;
    String lastEditted;
    String status;
    int dogID;

}