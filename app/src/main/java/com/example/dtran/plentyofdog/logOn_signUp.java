package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class logOn_signUp extends Activity {
    public UserHelper userdb;
    public DatabaseHelper db;
    public SQLiteDatabase sqlitedb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_log_on_sign_up);
        db = new DatabaseHelper(this);

        db.getWritableDatabase();
        userdb = new UserHelper(this);
        userdb.getWritableDatabase();
        loadData();

        Log.d("@@@@@@@@@@@@@  ----  DB", "" + userdb.getUserCount());
    }

    private void loadData() {
        //adding fake people and dogs
        //Owner
        OwnerHelper ownerDB = new OwnerHelper(getApplicationContext());
        Owner newOwner = new Owner("Danny", "Tran", "None", 20, "Male", "dannyhaitran@outlook.com", "1233123131", "Burnaby", "today", "today");
        Owner newOwner2 = new Owner("Geronimo", "Delarosa", "None", 20, "Male", "gmgdelarosa@gmail.com", "1233123131", "Burnaby", "today", "today");
        Owner newOwner3 = new Owner("a", "a", "None", 20, "Male", "a", "1233123131", "Burnaby", "today", "today");
        Owner newOwner4 = new Owner("b", "b", "None", 20, "Male", "b", "1233123131", "Burnaby", "today", "today");
        ownerDB.addOwner(newOwner);
        ownerDB.addOwner(newOwner2);
        ownerDB.addOwner(newOwner3);
        ownerDB.addOwner(newOwner4);
        //DEBUG SECTION
        List<Owner> owners = ownerDB.getAllOwner();
        for(Owner o : owners) {
            Log.d("OwnerDB", "OwnerID :" + o.id);
            Log.d("OwnerDB", "Email :" + o.email);
            Log.d("OwnerDB", "___________________ :");

        }
        //User

        User user = new User("dannyhaitran@outlook.com", "d", 1);
        User user2 = new User("gmgdelarosa@gmail.com", "d", 2);
        User user3 = new User("a", "a", 3);
        User user4 = new User("b", "b", 4);
        userdb.addUser(user);
        userdb.addUser(user2);
        userdb.addUser(user3);
        userdb.addUser(user4);

        //Preferences
        PreferenceHelper prefDB = new PreferenceHelper(getApplicationContext());
        Preference pref = new Preference("noSize", "noHair","noTemperament","dannyhaitran@outlook.com");
        Preference pref2 = new Preference("noSize", "noHair","noTemperament","gmgdelarosa@gmail.com");
        Preference pref3 = new Preference("noSize", "noHair","noTemperament","a");
        Preference pref4 = new Preference("noSize", "noHair","noTemperament","b");
        prefDB.addPreference(pref);
        prefDB.addPreference(pref2);
        prefDB.addPreference(pref3);
        prefDB.addPreference(pref4);
        //Dogs
        DogHelper dogdb = new DogHelper(getApplicationContext());


        Uri uri = Uri.parse("android.resource://com.example.dtran.plentyofdog/drawable/dog1");
        Uri uri2 = Uri.parse("android.resource://com.example.dtran.plentyofdog/drawable/dog2");
        Uri uri3 = Uri.parse("android.resource://com.example.dtran.plentyofdog/drawable/dog3");
        Uri uri4 = Uri.parse("android.resource://com.example.dtran.plentyofdog/drawable/dog4");
        Uri uri5 = Uri.parse("android.resource://com.example.dtran.plentyofdog/drawable/dog5");
        Uri uri6 = Uri.parse("android.resource://com.example.dtran.plentyofdog/drawable/dog6");
        Uri uri7 = Uri.parse("android.resource://com.example.dtran.plentyofdog/drawable/dog7");

        Dog dog = new Dog("Charlie", "Corgi", 2, "Male", "Small", "None", "Hyper", "This dog is so cute", "Burnaby", "today", "today", uri.toString());
        Dog dog2 = new Dog("Daphne", "Retriever", 2, "Male", "Small", "None", "Hyper", "This dog is so cute", "Burnaby", "today", "today", uri2.toString());
        Dog dog3 = new Dog("Childish", "Shiba", 2, "Male", "Large", "None", "Hyper", "This dog is so cute", "Burnaby", "today", "today", uri3.toString());
        Dog dog4 = new Dog("Gambino", "Samoyed", 2, "Male", "Large", "None", "Hyper", "This dog is so cute", "Burnaby", "today", "today", uri4.toString());
        Dog dog5 = new Dog("Germaine", "Rottweiler", 2, "Male", "Large", "None", "Hyper", "This dog is so cute", "Burnaby", "today", "today", uri5.toString());
        Dog dog6 = new Dog("Danny", "Samoyed", 2, "Male", "Small", "None", "Hyper", "This dog is so cute", "Burnaby", "today", "today", uri6.toString());
        Dog dog7 = new Dog("Mark", "Chow", 2, "Male", "Small", "None", "Hyper", "This dog is so cute", "Burnaby", "today", "today", uri7.toString());
        dogdb.addDog(dog);
        dogdb.addDog(dog2);
        dogdb.addDog(dog3);
        dogdb.addDog(dog4);
        dogdb.addDog(dog5);
        dogdb.addDog(dog6);
        dogdb.addDog(dog7);
        //DogOwner
        DogOwnerHelper dogOwnerdb = new DogOwnerHelper(getApplicationContext());

        DogOwner dogOwner = new DogOwner(1, 1, "today", "today", "Active");
        DogOwner dogOwner2 = new DogOwner(2, 1, "today", "today", "Active");
        DogOwner dogOwner3 = new DogOwner(3, 1, "today", "today", "Active");
        DogOwner dogOwner4 = new DogOwner(4, 1, "today", "today", "Active");
        DogOwner dogOwner5 = new DogOwner(5, 1, "today", "today", "Active");
        DogOwner dogOwner6 = new DogOwner(6, 1, "today", "today", "Active");
        DogOwner dogOwner7 = new DogOwner(7, 1, "today", "today", "Active");
        dogOwnerdb.addDogOwner(dogOwner);
        dogOwnerdb.addDogOwner(dogOwner2);
        dogOwnerdb.addDogOwner(dogOwner3);
        dogOwnerdb.addDogOwner(dogOwner4);
        dogOwnerdb.addDogOwner(dogOwner5);
        dogOwnerdb.addDogOwner(dogOwner6);
        dogOwnerdb.addDogOwner(dogOwner7);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.log_on_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void signUpClick(View v){
        Intent intent = new Intent(this, WelcomeScreen.class);
        startActivity(intent);
    }
    public void loginClick(View v){
        Intent intent = new Intent(this, home_screen.class);
        EditText usernameGrab = (EditText)findViewById(R.id.usernameInput);
        EditText passwordGrab = (EditText)findViewById(R.id.passwordInput);

        userdb.getReadableDatabase();
        String username = usernameGrab.getText().toString();
        String password = passwordGrab.getText().toString();



        if(userdb.userExist(username) && password.equals(userdb.getUser(username).password)){
            Log.d("userdb.getUser(username)", " " + userdb.getUser(username).username);


                Log.d("LOGIN" , " PASSED");
                intent.putExtra("username", username);
                startActivity(intent);
                finish();

        } else {


            passwordGrab.setText("");
            Toast.makeText(getApplicationContext(), "Password or Username is incorrect", Toast.LENGTH_SHORT).show();
            Log.d("LOGIN", " FAILED");
        }

    }
}
