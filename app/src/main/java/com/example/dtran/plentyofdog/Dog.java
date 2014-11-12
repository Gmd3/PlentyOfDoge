package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-11-11.
 */
public class Dog {
    String name;
    int age;
    String size;

    public int getKidsafe() {
        return kidsafe;
    }

    public void setKidsafe(int kidsafe) {
        this.kidsafe = kidsafe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getActivitylevel() {
        return activitylevel;
    }

    public void setActivitylevel(String activitylevel) {
        this.activitylevel = activitylevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCatsafe() {
        return catsafe;
    }

    public void setCatsafe(int catsafe) {
        this.catsafe = catsafe;
    }

    public int getDogparksafe() {
        return dogparksafe;
    }

    public void setDogparksafe(int dogparksafe) {
        this.dogparksafe = dogparksafe;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public String getLasteditted() {
        return lasteditted;
    }

    public void setLasteditted(String lasteditted) {
        this.lasteditted = lasteditted;
    }

    String training;
    String activitylevel;
    String description;
    int kidsafe;
    int catsafe;
    int dogparksafe;
    String area;
    String datecreated;
    String lasteditted;
    int id;
public Dog(    String training,
        String activitylevel,
        String description,
        int kidsafe,
        int catsafe,
        int dogparksafe,
        String area,
        String datecreated,
        String lasteditted){
    this.training = training;
    this.activitylevel = activitylevel;
    this.description = description;
    this.kidsafe = kidsafe;
    this.catsafe = catsafe;
    this.dogparksafe = dogparksafe;
    this.area = area;
    this.datecreated = datecreated;
    this.lasteditted = "";
}

}
