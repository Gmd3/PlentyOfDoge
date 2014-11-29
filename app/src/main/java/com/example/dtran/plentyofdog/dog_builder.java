package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class dog_builder extends Activity {

    private DogHelper db;
    private DogOwnerHelper db2;
    private UserHelper db3;
    private OwnerHelper db4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent typeIntent = getIntent();
        boolean isNew = typeIntent.getBooleanExtra("new", false);

        db = new DogHelper(getApplicationContext());
        db2 = new DogOwnerHelper(getApplicationContext());
        db3 = new UserHelper(getApplicationContext());
        db4 = new OwnerHelper(getApplicationContext());

        setContentView(R.layout.activity_dog_builder);
        if (!isNew){
            Button b = (Button)findViewById(R.id.btnNext);
            b.setText("Submit Changes");
        }
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
        boolean isNew = UserIntent.getBooleanExtra("new", false);


        Intent intent = new Intent(this, home_screen.class);
        intent.putExtra("username", UserIntent.getStringExtra("username"));
        intent.putExtra("new", false);

        EditText nameGrab = (EditText)findViewById(R.id.nameInput);
        EditText ageGrab = (EditText)findViewById(R.id.ageInput);
        EditText genderGrab = (EditText)findViewById(R.id.genderInput);
        EditText trainingGrab = (EditText)findViewById(R.id.trainingInput);
        Spinner sizeGrab = (Spinner)findViewById(R.id.spinner1);
        Spinner activityGrab = (Spinner)findViewById(R.id.spinner2);
        Spinner breedGrab = (Spinner)findViewById(R.id.breedInput);
        EditText descGrab = (EditText)findViewById(R.id.dogDesc);

        String userName = UserIntent.getStringExtra("username");
        int ownerID = db3.getOwnerID(userName);
        String name = nameGrab.getText().toString();
        String breed = breedGrab.getSelectedItem().toString();
        String ageString = ageGrab.getText().toString();
        String gender = genderGrab.getText().toString();
        String size = sizeGrab.getSelectedItem().toString();
        String training = trainingGrab.getText().toString();
        String activity = activityGrab.getSelectedItem().toString();
        String desc = descGrab.getText().toString();
        Integer age = Integer.valueOf(ageString);

        Owner o = db4.getOwner(ownerID);

        Dog newDog = new Dog(name, breed, age, gender, size, training, activity, desc, o.area, "today","today", "");
        db.addDog(newDog);
        Dog d = db.getLastDog();
        DogOwner newDogOwner = new DogOwner(d.id,
                ownerID,
                "today", "today", "Active");
        db2.addDogOwner(newDogOwner);

        Toast toast = Toast.makeText(getApplicationContext(), "Dog ID: " + d.id, Toast.LENGTH_SHORT);
        toast.show();

        startActivity(intent);
    }

}
