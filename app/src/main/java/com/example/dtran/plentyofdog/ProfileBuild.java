package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class ProfileBuild extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_build);

        Spinner xp = (Spinner)findViewById(R.id.yoeInput);
        xp.setOnItemSelectedListener(new ExperienceListener());

    }

    public class ExperienceListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView parent, View view, int pos, long id) {

        Toast.makeText(parent.getContext(), "Experience level: " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
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


    public void dogPicker(View v){
        Intent intent = new Intent(this, DogFilter.class);

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

        intent.putExtra("age", age);
        intent.putExtra("hometown", hometown);
        intent.putExtra("desc", desc);
        intent.putExtra("xp", xp);
        intent.putExtra("gender", gender);
        intent.putExtra("email", email);
        intent.putExtra("phone", phone);

        startActivity(intent);
    }

}
