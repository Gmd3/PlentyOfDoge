package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ProfileChange extends Activity {
    Intent userIntent;
    OwnerHelper ownerdb;
    Owner owner;
    UserHelper userDB;
    String oldEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile_change);
        //load the current users data
        userIntent = getIntent();
        String username = userIntent.getStringExtra("username");
        ownerdb = new OwnerHelper(this);
        ownerdb.getWritableDatabase();
        userDB = new UserHelper(getApplicationContext());
        userDB.getWritableDatabase();
        Log.d("DB",""+ownerdb.getOwnerCount());
        Log.d("ProfileChange username","" + username);


        //DEBUG SECTION
        List<Owner> owners = ownerdb.getAllOwner();
        for(Owner o : owners) {
            Log.d("OwnerDB", "OwnerID :" + o.id);
            Log.d("OwnerDB", "Email :" + o.email);
            Log.d("OwnerDB", "___________________ :");

        }

        Log.d("OwnerDB", "@@@@@@@@@@@@@@@@@@@@@@@@@ :");
        List<User> users = userDB.getAllUser();
        for(User u : users) {
            Log.d("OwnerDB", "UserID :" + u.id);
            Log.d("OwnerDB", "Email :" + u.username);
            Log.d("OwnerDB", "OwnerID :" + u.ownerId);
            Log.d("OwnerDB", "___________________ :");

        }
        //DEBUG


        owner = ownerdb.getOwner(username);
        EditText age = (EditText)findViewById(R.id.ageInput);
        TextView email = (TextView)findViewById(R.id.emailInput);
        Spinner experience = (Spinner)findViewById(R.id.yoeInput);
        EditText gender = (EditText)findViewById(R.id.genderInput);
        EditText phone = (EditText)findViewById(R.id.phoneInput);
        EditText area = (EditText)findViewById(R.id.hometownInput);
        EditText firstName = (EditText)findViewById(R.id.firstNameInput);
        EditText lastName = (EditText)findViewById(R.id.lasttNameInput);

        oldEmail = owner.email;
        email.setText(owner.email);
        age.setText(""+ owner.age);
        area.setText(owner.area);
        ArrayAdapter myadap = (ArrayAdapter)experience.getAdapter();
        int position = myadap.getPosition(owner.experience);
        experience.setSelection(position);
        gender.setText(owner.gender);
        phone.setText(""+owner.phone);
        firstName.setText(owner.firstName);
        lastName.setText(owner.lastName);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_change, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void back(View view){
        Intent intent = new Intent(this, home_screen.class);
        intent.putExtra("username", userIntent.getStringExtra("username"));
        startActivity(intent);
    }
    public void update(View view){
        int errors = 0;
        Intent intent = new Intent(this, home_screen.class);
        EditText age = (EditText)findViewById(R.id.ageInput);
        TextView email = (TextView)findViewById(R.id.emailInput);
        Spinner experience = (Spinner)findViewById(R.id.yoeInput);
        EditText gender = (EditText)findViewById(R.id.genderInput);
        EditText phone = (EditText)findViewById(R.id.phoneInput);
        EditText area = (EditText)findViewById(R.id.hometownInput);
        EditText firstName = (EditText)findViewById(R.id.firstNameInput);
        EditText lastName = (EditText)findViewById(R.id.lasttNameInput);
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyy");
        String date = s.format(new Date());

        //password section
        EditText passwordgrab = (EditText)findViewById(R.id.lblpasswordinput);
        EditText confirmgrab = (EditText)findViewById(R.id.lblpasswordinput2);
        String password = passwordgrab.getText().toString();
        String confirm = confirmgrab.getText().toString();
        if((!password.equals("") && !confirm.equals(""))) {
            if (!password.equals(confirm)) {
                errors++;
            }
            else {
                PreferenceHelper prefDB = new PreferenceHelper(getApplicationContext());
                if(prefDB.preferenceExist(oldEmail)) {
                    Preference prevPref = prefDB.getPreference(oldEmail);
                    Log.d("Old username ProfileChange :", prevPref.username);
                    prevPref.username = email.getText().toString();

                    Log.d("Official Size : ", "" + prevPref.size);
                    Log.d("Official HairType : ", "" + prevPref.hairtype);
                    Log.d("Official Temperament : ", "" + prevPref.temperament);
                    Log.d("Official username : ", "" + prevPref.username);

                    prefDB.updatePreference(prevPref);

                    Log.d("New username ProfileChange :", prevPref.username);
                }
                else
                {
                    Log.d("Preference doens't exist", "");
                }

                Log.d("PASSWORDS ARE THE ", "SAME");

                Log.d("old email ", "" + oldEmail);
                UserHelper db = new UserHelper(this);
                User userTempEmail = db.getUser(oldEmail);
                Log.d("New email ", "" + email.getText().toString());

                User user = new User(email.getText().toString(),
                        password, userTempEmail.ownerId);

                Log.d("User email : user.email", user.username);
                db.updateUser(user);
            }
        }
        if(!password.equals("") && confirm.equals(""))
            errors++;
        if(!confirm.equals("") && password.equals(""))
            errors++;
        if(confirm.equals("") && password.equals("") && !email.getText().toString().equals(""))
            errors++;

        if(errors == 0) {

            Owner owner = ownerdb.getOwner(oldEmail);

            owner.firstName = firstName.getText().toString();
            owner.lastName = lastName.getText().toString();
            owner.experience = experience.getSelectedItem().toString();
            owner.age = Integer.parseInt(age.getText().toString());
            owner.gender =  gender.getText().toString();
            owner.email = email.getText().toString();
            owner.phone = phone.getText().toString();
            owner.area =  area.getText().toString();
            owner.lastEdited = date;

            //update preferences

            //Updating Owner
            Log.d("Owner email: owner.email", owner.email);
            ownerdb.updateOwner(owner);

            Log.d("DB UPDATE SUCCESS ? : ", "" + ownerdb.updateOwner(owner));
            intent.putExtra("username",email.getText().toString());
            startActivity(intent);
        } else {
            passwordgrab.setText("");
            confirmgrab.setText("");
        }

    }
}
