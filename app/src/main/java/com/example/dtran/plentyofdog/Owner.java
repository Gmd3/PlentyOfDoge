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





    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setOwnerID(int ownerID) {
        this.id = ownerID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setArea(String are) {
        this.area = area;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLastEdited(String lastEdited) {
        this.lastEdited = lastEdited;
    }

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
