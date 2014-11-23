package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-11-22.
 */
public class Preference {
    int userid;
    String size;
    String temperament;
    String hairtype;
    public Preference(){

    }
    public Preference(int userid, String size, String temperament, String hairtype) {
        this.userid = userid;
        this.size = size;
        this.temperament = temperament;
        this.hairtype = hairtype;
    }

}
