package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class ProfileBuild extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_build);
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
        EditText yoeGrab = (EditText)findViewById(R.id.yoeInput);
        EditText descGrab = (EditText)findViewById(R.id.shortDesc);

        String hometown = hometownGrab.getText().toString();
        String yoe = yoeGrab.getText().toString();
        String desc = descGrab.getText().toString();
        String age = ageGrab.getText().toString();

        intent.putExtra("age", age);
        intent.putExtra("hometown", hometown);
        intent.putExtra("yoe", yoe);
        intent.putExtra("desc", desc);

        startActivity(intent);
    }

}
