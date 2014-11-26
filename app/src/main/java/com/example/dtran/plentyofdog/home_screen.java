package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class home_screen extends Activity {
    Intent userIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        userIntent = getIntent();
        Log.d("intent Dogfilter", " " + userIntent.getStringExtra("username"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
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

    public void dogBuild(View view){
        Intent dogIntent = new Intent(this, dog_builder.class);
        dogIntent.putExtra("username", userIntent.getStringExtra("username"));
        startActivity(dogIntent);
    }
    public void setPreference(View view){
        Intent intent = new Intent(this, DogFilter.class);
        intent.putExtra("username", userIntent.getStringExtra("username"));
        startActivity(intent);
    }
    public void changeProfile(View view){
        Intent intent = new Intent(this, ProfileChange.class);
        intent.putExtra("username", userIntent.getStringExtra("username"));
        startActivity(intent);
    }

    public void myDogs(View view){
        Intent home = new Intent(this, MyDogs.class);
        home.putExtra("username", userIntent.getStringExtra("username"));
        startActivity(home);
    }

    public void test(View view){
        Intent home = new Intent(this, dog_builder.class);
        home.putExtra("username", userIntent.getStringExtra("username"));
        startActivity(home);
    }
}
