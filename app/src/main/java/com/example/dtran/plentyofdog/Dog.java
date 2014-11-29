package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-11-11.
 */
public class Dog {


    int id;
    String name;
    String breed;
    int age;
    String gender;
    String size;
    String training;
    String activitylevel;
    String description;
    String area;
    String datecreated;
    String lasteditted;
    String image;

    public Dog(
            String name,
            String breed,
            int age,
            String gender,
            String size,
            String training,
            String activitylevel,
            String description,
            String area,
            String datecreated,
            String lastedited,
            String image){
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.size = size;
        this.training = training;
        this.activitylevel = activitylevel;
        this.description = description;
        this.area = area;
        this.datecreated = datecreated;
        this.lasteditted = lastedited;
        this.image = image;

    }

    public Dog(
            int id,
            String name,
            String breed,
            int age,
            String gender,
            String size,
            String training,
            String activitylevel,
            String description,
            String area,
            String datecreated,
            String lastedited,
            String image){
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.size = size;
        this.training = training;
        this.activitylevel = activitylevel;
        this.description = description;
        this.area = area;
        this.datecreated = datecreated;
        this.lasteditted = lastedited;
        this.image = image;
    }

}
