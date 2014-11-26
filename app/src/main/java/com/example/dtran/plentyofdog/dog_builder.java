package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;


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

    public void submit(View v){

        Intent UserIntent = getIntent();

        Intent intent = new Intent(this, home_screen.class);

        EditText nameGrab = (EditText)findViewById(R.id.nameInput);
        EditText ageGrab = (EditText)findViewById(R.id.ageInput);
        EditText genderGrab = (EditText)findViewById(R.id.genderInput);
        EditText trainingGrab = (EditText)findViewById(R.id.trainingInput);
        Spinner sizeGrab = (Spinner)findViewById(R.id.spinner1);
        EditText emailGrab = (EditText)findViewById(R.id.emailInput);
        Spinner activityGrab = (Spinner)findViewById(R.id.spinner2);
        Spinner breedGrab = (Spinner)findViewById(R.id.breedInput);
        EditText descGrab = (EditText)findViewById(R.id.dogDesc);

        int userID = UserIntent.getIntExtra("userID", 1);
        String name = nameGrab.getText().toString();
        String breed = breedGrab.getSelectedItem().toString();
        String ageString = ageGrab.getText().toString();
        String gender = genderGrab.getText().toString();
        String size = sizeGrab.getSelectedItem().toString();
        String training = trainingGrab.getText().toString();
        String activity = activityGrab.getSelectedItem().toString();
        String desc = descGrab.getText().toString();

        Integer age = Integer.valueOf(ageString);

        /*
        intent.putExtra("userID", userID);
        intent.putExtra("name", name);
        intent.putExtra("breed", breed);
        intent.putExtra("age", age);
        intent.putExtra("gender", gender);
        intent.putExtra("size", size);
        intent.putExtra("training", training);
        intent.putExtra("gender", gender);
        intent.putExtra("activity", activity);
        intent.putExtra("desc", desc);
        */

        Dog newDog = new Dog(name, breed, age, gender, size, training, activity, desc, "asd", "today");

        DogHelper db = new DogHelper(getApplicationContext());

        db.addDog(newDog);

        startActivity(intent);
    }
}
