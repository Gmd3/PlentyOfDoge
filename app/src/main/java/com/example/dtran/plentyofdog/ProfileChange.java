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

/**
 * This is the controller that will change the current users profile, only the use that is logged in
 * not the user they are viewing
 */
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


        owner = ownerdb.getOwner(username);
        EditText age = (EditText)findViewById(R.id.ageInput);
        TextView email = (TextView)findViewById(R.id.emailInput);
        Spinner experience = (Spinner)findViewById(R.id.yoeInput);
        Spinner gender = (Spinner)findViewById(R.id.genderInput);
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
        ArrayAdapter genadap = (ArrayAdapter) gender.getAdapter();
        int genpos = genadap.getPosition(owner.gender);
        gender.setSelection(genpos);
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
        Spinner gender = (Spinner)findViewById(R.id.genderInput);
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
                UserHelper db = new UserHelper(this);
                User userTempEmail = db.getUser(oldEmail);

                User user = new User(email.getText().toString(),
                        password, userTempEmail.ownerId);

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
            owner.gender =  gender.getSelectedItem().toString();
            owner.email = email.getText().toString();
            owner.phone = phone.getText().toString();
            owner.area =  area.getText().toString();
            owner.lastEdited = date;


            //Updating Owner
            ownerdb.updateOwner(owner);

            intent.putExtra("username",email.getText().toString());
            startActivity(intent);
        } else {
            passwordgrab.setText("");
            confirmgrab.setText("");
        }

    }
}
