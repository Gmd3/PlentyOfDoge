package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * The filter controller for the dog view
 */
public class dog_builder extends Activity {

    private DogHelper dogHelper;
    private DogOwnerHelper dogOwnerHelper;
    private UserHelper userHelper;
    private OwnerHelper ownerHelper;
    private MatchHelper matchHelper;

    Uri uri = null;
    int dogID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent typeIntent = getIntent();
        dogID = typeIntent.getIntExtra("dogID", -0);

        dogHelper = new DogHelper(getApplicationContext());
        dogOwnerHelper = new DogOwnerHelper(getApplicationContext());
        userHelper = new UserHelper(getApplicationContext());
        ownerHelper = new OwnerHelper(getApplicationContext());
        matchHelper = new MatchHelper(getApplicationContext());
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dog_builder);

        if (dogID != -0){
            Dog dog = dogHelper.getDog(dogID);

            EditText nameGrab = (EditText)findViewById(R.id.nameInput);
            EditText ageGrab = (EditText)findViewById(R.id.ageInput);
            Spinner genderGrab = (Spinner)findViewById(R.id.genderInput);
            EditText trainingGrab = (EditText)findViewById(R.id.trainingInput);
            Spinner sizeGrab = (Spinner)findViewById(R.id.spinner1);
            Spinner activityGrab = (Spinner)findViewById(R.id.spinner2);
            Spinner breedGrab = (Spinner)findViewById(R.id.breedInput);
            TextView descGrab = (TextView)findViewById(R.id.dogDesc);
            ImageView imageGrab = (ImageView)findViewById(R.id.image);

            nameGrab.setText(dog.name);
            ageGrab.setText(""+dog.age);
            ArrayAdapter myadapDog = (ArrayAdapter)genderGrab.getAdapter();
            int positionDog = myadapDog.getPosition(dog.gender);
            genderGrab.setSelection(positionDog);
            trainingGrab.setText(dog.training);
            ArrayAdapter myadap = (ArrayAdapter)sizeGrab.getAdapter();
            int position = myadap.getPosition(dog.size);
            sizeGrab.setSelection(position);
            ArrayAdapter myadap2 = (ArrayAdapter)activityGrab.getAdapter();
            int position2 = myadap2.getPosition(dog.activitylevel);
            activityGrab.setSelection(position2);
            ArrayAdapter breedAdap = (ArrayAdapter)breedGrab.getAdapter();
            int position3 = breedAdap.getPosition(dog.size);
            breedGrab.setSelection(position3);
            descGrab.setText(dog.description);

            imageGrab.setScaleType(ImageView.ScaleType.CENTER);

            imageGrab.setImageURI(Uri.parse(dog.image));
            uri = Uri.parse(dog.image);

            Button b = (Button)findViewById(R.id.btnNext);
            b.setText("Submit");
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private static final int READ_REQUEST_CODE = 42;
    public void uploader(View v){


        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);


        intent.addCategory(Intent.CATEGORY_OPENABLE);


        intent.setType("image/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {


        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {


            if (resultData != null) {
                uri = resultData.getData();
                ImageView image = (ImageView)findViewById(R.id.image);

                image.setImageURI(uri);
            }
        }
    }
    public void submit(View v){
        Intent UserIntent = getIntent();
        int errors = 0;

        ArrayList<String> errorMsgs = new ArrayList<String>();

        Intent intent = new Intent(this, home_screen.class);
        intent.putExtra("username", UserIntent.getStringExtra("username"));
        intent.putExtra("new", false);

        EditText nameGrab = (EditText)findViewById(R.id.nameInput);
        EditText ageGrab = (EditText)findViewById(R.id.ageInput);
        Spinner genderGrab = (Spinner)findViewById(R.id.genderInput);
        EditText trainingGrab = (EditText)findViewById(R.id.trainingInput);
        Spinner sizeGrab = (Spinner)findViewById(R.id.spinner1);
        Spinner activityGrab = (Spinner)findViewById(R.id.spinner2);
        Spinner breedGrab = (Spinner)findViewById(R.id.breedInput);
        TextView descGrab = (TextView)findViewById(R.id.dogDesc);
        ImageView imageGrab = (ImageView)findViewById(R.id.image);


        String userName = UserIntent.getStringExtra("username");
        int ownerID = userHelper.getOwnerID(userName);
        String name = nameGrab.getText().toString();
        String breed = breedGrab.getSelectedItem().toString();
        String ageString = ageGrab.getText().toString();
        String gender = genderGrab.getSelectedItem().toString();
        String size = sizeGrab.getSelectedItem().toString();
        String training = trainingGrab.getText().toString();
        String activity = activityGrab.getSelectedItem().toString();
        String desc = descGrab.getText().toString();
        Integer age = null;
        if(ageGrab.getText().toString().equals("")) {
            errors++;
        } else {
            age = Integer.valueOf(ageString);
            if (age < 0 || age > 13) {
                errorMsgs.add("A dog can only live for 13 years!");
                errors++;
            }
        }
        if(userName.equalsIgnoreCase(""))
            errorMsgs.add("Name cannot be empty");

        if(name.length() < 1) {
            errorMsgs.add("Enter the name");
            errors++;
        }

        if(training.length() < 1) {
            errorMsgs.add("Enter a training");
            errors++;
        }

        if(desc.length() < 1) {
            errorMsgs.add("Enter a description");
            errors++;
        }
        Owner o = ownerHelper.getOwner(ownerID);


        Dog d;
        if(errors == 0) {
            Dog dog = new Dog(name, breed, age, gender, size, training,
                    activity, desc, o.area, "today","today", uri.toString());
            if (dogID != -0) {

                Dog dogToUpdate = dogHelper.getDog(dogID);
                dogToUpdate.name = name;
                dogToUpdate.breed = breed;
                dogToUpdate.age = age;
                dogToUpdate.gender = gender;
                dogToUpdate.size = size;
                dogToUpdate.training = training;
                dogToUpdate.activitylevel = activity;
                dogToUpdate.description = desc;
                dogToUpdate.image = uri.toString();

                dogHelper.updateDog(dogToUpdate);
                d = dogHelper.getLastDog();
                DogOwner oldDogOwner = dogOwnerHelper.getDogOwnerFromDogID(dogID);
                dogOwnerHelper.updateDogOwner(oldDogOwner);

            } else {
                dogHelper.addDog(dog);
                d = dogHelper.getLastDog();
                DogOwner newDogOwner = new DogOwner(d.id,
                        ownerID,
                        "today", "today", "Active");
                dogOwnerHelper.addDogOwner(newDogOwner);

            }
            startActivity(intent);
        } else
        {

            Toast.makeText(this, errorMsgs.get(0), Toast.LENGTH_LONG).show();
        }

    }
    public void delete(View view){
        dogHelper.deleteDog(dogHelper.getDog(dogID));
        dogOwnerHelper.deleteDogOwner(dogID);
        matchHelper.deleteMatch(dogID);

        Intent UserIntent = getIntent();

        Intent intent = new Intent(this, home_screen.class);
        intent.putExtra("username", UserIntent.getStringExtra("username"));
        intent.putExtra("new", false);
        startActivity(intent);
    }
}
