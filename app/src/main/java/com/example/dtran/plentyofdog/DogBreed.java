package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-11-11.
 */
public class DogBreed {
    String breedname;

    public DogBreed(String breedname, String hairtype, String temperment, String shedding, String size) {
        this.breedname = breedname;
        this.hairtype = hairtype;
        this.temperment = temperment;
        this.shedding = shedding;
        this.size = size;
    }

    public String getBreedname() {
        return breedname;
    }

    public void setBreedname(String breedname) {
        this.breedname = breedname;
    }

    public String getHairtype() {
        return hairtype;
    }

    public void setHairtype(String hairtype) {
        this.hairtype = hairtype;
    }

    public String getTemperment() {
        return temperment;
    }

    public void setTemperment(String temperment) {
        this.temperment = temperment;
    }

    public String getShedding() {
        return shedding;
    }

    public void setShedding(String shedding) {
        this.shedding = shedding;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    String hairtype;
    String temperment;
    String shedding;
    String size;
    int id;
}
