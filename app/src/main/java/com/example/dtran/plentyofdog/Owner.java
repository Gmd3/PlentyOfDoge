package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 2014-10-17.
 * This file is creating the contact schema to create the SQLite3 database
 */
public class Owner {

    public static final String COLUMN_NAME_CONTACT_ID = "id";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_EXPERIENCE = "experience";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_AREA = "area";
    public static final String COLUMN_DATE_CREATED = "datecreated";
    public static final String COLUMN_LAST_EDITTED = "lasteddited";


    int id;
    String firstName;
    String lastName;
    String experience;
    int age;
    String gender;
    String email;
    String phone;
    String area;
    String dateCreated;
    String lastEdited;

    public Owner( String firstName,
                  String lastName,
                  String experience,
                  int age,
                  String gender,
                  String email,
                  String phone,
                  String area,
                  String dateCreated,
                  String lastEdited){
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.area = area;
        this.dateCreated = dateCreated;
        this.lastEdited = lastEdited;
    }
    public Owner( int id, String firstName,
                  String lastName,
                  String experience,
                  int age,
                  String gender,
                  String email,
                  String phone,
                  String area,
                  String dateCreated,
                  String lastEdited){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.area = area;
        this.dateCreated = dateCreated;
        this.lastEdited = lastEdited;
    }
    public Owner(){

    }

}
