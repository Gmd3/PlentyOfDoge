package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class dog_builder extends Activity {

    private DogHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_builder);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dog_builder, menu);
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

    public void dogPicker(View v){

        Intent WelcomeScreenintent = getIntent();

        Intent intent = new Intent(this, home_screen.class);

        EditText ageGrab = (EditText)findViewById(R.id.ageInput);
        EditText hometownGrab = (EditText)findViewById(R.id.hometownInput);
        EditText descGrab = (EditText)findViewById(R.id.shortDesc);
        EditText genderGrab = (EditText)findViewById(R.id.genderInput);
        EditText emailGrab = (EditText)findViewById(R.id.emailInput);
        EditText phoneGrab = (EditText)findViewById(R.id.phoneInput);
        Spinner yoeGrab = (Spinner)findViewById(R.id.yoeInput);

        String hometown = hometownGrab.getText().toString();
        String desc = descGrab.getText().toString();
        String age = ageGrab.getText().toString();
        String gender = genderGrab.getText().toString();
        String email = emailGrab.getText().toString();
        String phone = phoneGrab.getText().toString();
        String xp = yoeGrab.getSelectedItem().toString();
        String firstname = WelcomeScreenintent.getStringExtra("WelcomeStringsFName");
        String lastname  = WelcomeScreenintent.getStringExtra("WelcomeStringsLName");

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


        startActivity(intent);
    }
}
