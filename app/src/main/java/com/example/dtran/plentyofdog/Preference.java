package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-11-22.
 */
public class Preference {
    String username;
    String size;
    String temperament;
    String hairtype;
    public Preference(){

    }

    public Preference(String username, String size, String hairtype, String temperament) {
        this.username = username;
        this.size = size;
        this.temperament = temperament;
        this.hairtype = hairtype;
    }

}
