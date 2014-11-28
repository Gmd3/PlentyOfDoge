package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class DogSelection extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_selection);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dog_selection, menu);
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
    int getDrawablesCount() {
        return R.drawable.class.getFields().length;
    }
    public void random(){
        ImageView image = (ImageView)findViewById(R.id.dogImage);

        int n = getDrawablesCount();

        ImageView imgView = new ImageView(this);
        DogHelper dogdb = new DogHelper(this);
        Dog dog = dogdb.getRandomDog();

        String variableValue = dog.activitylevel;
        image.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));

    }
    public void yes(View view){
        //add to list of matches
        random();
    }
    public void no(View view){
        //randomly choose another dog to view
        random();
    }
}
