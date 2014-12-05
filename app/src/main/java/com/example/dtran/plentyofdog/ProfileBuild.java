package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The controller that will build the user to sign in with.
 */

public class ProfileBuild extends Activity {
    private static final String DB_NAME = "plentyofdoge_db";
    private static final String TABLE_NAME = "Owner";
    private static final String OWNER_ID = "_id";
    private SQLiteDatabase database;
    private ListView listView;
    private ArrayList friends;
    public OwnerHelper db;
    public UserHelper userdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile_build);

        Log.d("DB ", "----------------------CREATING DB------------------------------");
        db = new OwnerHelper(this);
        db.getWritableDatabase();
        userdb = new UserHelper(this);
        userdb.getWritableDatabase();
        Log.d("DB ", "----------------------FINISH CREATING DB------------------------------");
        Spinner xp = (Spinner)findViewById(R.id.yoeInput);
        xp.setOnItemSelectedListener(new ExperienceListener());

    }
    public class ExperienceListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView parent, View view, int pos, long id) {


        }

        @Override
        public void onNothingSelected(AdapterView parent) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_build, menu);
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

    public void dogPicker(View v) {
        int errors = 0;
        Intent WelcomeScreenintent = getIntent();

        Intent intent = new Intent(this, DogFilter.class);

        EditText ageGrab = (EditText) findViewById(R.id.ageInput);
        EditText hometownGrab = (EditText) findViewById(R.id.hometownInput);
        EditText descGrab = (EditText) findViewById(R.id.shortDesc);
        Spinner genderGrab = (Spinner) findViewById(R.id.genderInput);
        EditText emailGrab = (EditText) findViewById(R.id.emailInput);
        EditText phoneGrab = (EditText) findViewById(R.id.phoneInput);
        EditText passwordGrab = (EditText) findViewById(R.id.lblpasswordinput);
        EditText confirmGrab = (EditText) findViewById(R.id.lblpasswordinput2);
        Spinner yoeGrab = (Spinner) findViewById(R.id.yoeInput);

        String hometown = hometownGrab.getText().toString();
        String desc = descGrab.getText().toString();
        String age = ageGrab.getText().toString();
        String gender = genderGrab.getSelectedItem().toString();
        String email = emailGrab.getText().toString();
        String phone = phoneGrab.getText().toString();
        String xp = yoeGrab.getSelectedItem().toString();
        String firstname = WelcomeScreenintent.getStringExtra("WelcomeStringsFName");
        String lastname = WelcomeScreenintent.getStringExtra("WelcomeStringsLName");
        String password = passwordGrab.getText().toString();
        String confirm = confirmGrab.getText().toString();


        ArrayList<String> errorMsg = new ArrayList<String>();


        if (firstname.equals("")) {
            errorMsg.add("Enter a first name");
            errors++;
        }
        if (lastname.equals("")) {
            errorMsg.add("Enter a last name");
            errors++;
        }
        if (email.equals("")) {
            errorMsg.add("Enter an email");
            errors++;
        }
        //check if email already exist
        if (userdb.userExist(email)){
            errorMsg.add("Email already exist");
            errors++;
        }
        if(!password.equals(confirm)) {
            errorMsg.add("Enter a password");
            errors++;
        }
        if(age.equals("")) {
            errorMsg.add("Enter an age");
            errors++;
        }
        if(hometown.equals("")) {
            errorMsg.add("Enter a hometown");
            errors++;
        }
        if(phone.length() != 10) {
            errorMsg.add("Enter a phone number");
            errors++;
        }
        if(desc.equals("")) {
            errorMsg.add("Enter a description");
            errors++;
        }

        if(errors == 0) {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            intent.putExtra("age", age);
            intent.putExtra("hometown", hometown);
            intent.putExtra("desc", desc);
            intent.putExtra("xp", xp);
            intent.putExtra("gender", gender);
            intent.putExtra("email", email);
            intent.putExtra("phone", phone);
            SimpleDateFormat s = new SimpleDateFormat("ddMMyyyy");
            String date = s.format(new Date());
            Owner newOwner = new Owner(firstname, lastname, xp, Integer.parseInt(age), gender, email, phone, hometown, date, "");
            db.addOwner(newOwner);
            userdb.addUser(new User(email, password, db.getLastOwnerID()));
            Toast toast = Toast.makeText(getApplicationContext(), "owner ID: " + db.getLastOwnerID(), Toast.LENGTH_SHORT);
            toast.show();
            Log.d("DB", "Inserted Owner");

            List<Owner> owners = db.getAllOwner();

            for (Owner o : owners) {
                String log = "ID: " + o.id + "\n";
                Log.d("LOG id : ", log);
            }
            intent.putExtra("username", email);

            Log.d("Leaving ", "Activity");

            startActivity(intent);
        } else
        {
            Toast.makeText(this, errorMsg.get(0), Toast.LENGTH_LONG).show();
        }
    }

}
