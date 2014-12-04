package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-11-22.
 */
public class Preference {
    String username;
    String size;
    String temperament;
    String hairtype;
    int id;
    public Preference(){

    }

    public Preference(String size, String hairtype , String temperament, String username) {
        this.username = username;
        this.size = size;
        this.temperament = temperament;
        this.hairtype = hairtype;
    }
    public Preference(int id, String size, String hairtype , String temperament, String username) {
        this.id = id;
        this.username = username;
        this.size = size;
        this.temperament = temperament;
        this.hairtype = hairtype;
    }

}
