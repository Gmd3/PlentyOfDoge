package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-11-11.
 */
public class Dog {
    public static final String COLUMN_NAME_DOG_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LASTNAME = "breed";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_TRAINING = "training";
    public static final String COLUMN_ACTIVITYLEVEL = "activitylevel";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_AREA = "area";
    public static final String COLUMN_DATE_CREATED = "datecreated";
    public static final String COLUMN_LAST_EDITTED = "lasteddited";

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
    byte[] image;

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
            byte[] image){
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
        this.lasteditted = " ";
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
            byte[] image){
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
        this.lasteditted = " ";
        this.image = image;
    }

}
