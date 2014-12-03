package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class dog_builder extends Activity {

    private DogHelper db;
    private DogOwnerHelper db2;
    private UserHelper db3;
    private OwnerHelper db4;
    private MatchHelper db5;

    Uri uri = null;
    int dogID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent typeIntent = getIntent();
        dogID = typeIntent.getIntExtra("dogID", -0);

        db = new DogHelper(getApplicationContext());
        db2 = new DogOwnerHelper(getApplicationContext());
        db3 = new UserHelper(getApplicationContext());
        db4 = new OwnerHelper(getApplicationContext());
        db5 = new MatchHelper(getApplicationContext());

        setContentView(R.layout.activity_dog_builder);

        Log.d("dogID: ", ""+dogID);
        if (dogID != -0){
            Dog dog = db.getDog(dogID);

            EditText nameGrab = (EditText)findViewById(R.id.nameInput);
            EditText ageGrab = (EditText)findViewById(R.id.ageInput);
            Spinner genderGrab = (Spinner)findViewById(R.id.genderInput);
            EditText trainingGrab = (EditText)findViewById(R.id.trainingInput);
            Spinner sizeGrab = (Spinner)findViewById(R.id.spinner1);
            Spinner activityGrab = (Spinner)findViewById(R.id.spinner2);
            Spinner breedGrab = (Spinner)findViewById(R.id.breedInput);
            EditText descGrab = (EditText)findViewById(R.id.dogDesc);
            ImageView imageGrab = (ImageView)findViewById(R.id.image);

            nameGrab.setText(dog.name);
            ageGrab.setText(""+dog.age);
            ArrayAdapter genadap = (ArrayAdapter)genderGrab.getAdapter();
            int genpos = genadap.getPosition(dog.age);
            genderGrab.setSelection(genpos);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private static final int READ_REQUEST_CODE = 42;
    public void uploader(View v){

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("image/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().

            if (resultData != null) {
                uri = resultData.getData();
                ImageView image = (ImageView)findViewById(R.id.image);

                image.setImageURI(uri);
            }
        }
    }
    public void submit(View v){

        Intent UserIntent = getIntent();

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
        EditText descGrab = (EditText)findViewById(R.id.dogDesc);
        ImageView imageGrab = (ImageView)findViewById(R.id.image);

        String userName = UserIntent.getStringExtra("username");
        int ownerID = db3.getOwnerID(userName);
        String name = nameGrab.getText().toString();
        String breed = breedGrab.getSelectedItem().toString();
        String ageString = ageGrab.getText().toString();
        String gender = genderGrab.getSelectedItem().toString();
        String size = sizeGrab.getSelectedItem().toString();
        String training = trainingGrab.getText().toString();
        String activity = activityGrab.getSelectedItem().toString();
        String desc = descGrab.getText().toString();
        Integer age = Integer.valueOf(ageString);

        int errors = 0;
        ArrayList<String> errorMsgs = new ArrayList<String>();

        if(name.length() < 1) {
            errorMsgs.add("Enter the name");
            errors++;
        }

        if(age < 0 || age > 13) {
            errorMsgs.add("A dog can only live for 13 years!");
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

        if(errors == 0) {
            Owner o = db4.getOwner(ownerID);

            Dog dog = new Dog(name, breed, age, gender, size, training,
                    activity, desc, o.area, "today", "today", uri.toString());
            Dog d;
            if (dogID != -0) {
                Dog dogToUpdate = db.getDog(dogID);
                dogToUpdate.name = name;
                dogToUpdate.breed = breed;
                dogToUpdate.age = age;
                dogToUpdate.gender = gender;
                dogToUpdate.size = size;
                dogToUpdate.training = training;
                dogToUpdate.activitylevel = activity;
                dogToUpdate.description = desc;
                dogToUpdate.image = uri.toString();

                db.updateDog(dogToUpdate);
                d = db.getLastDog();
                DogOwner oldDogOwner = db2.getDogOwnerFromDogID(dogID);
                db2.updateDogOwner(oldDogOwner);

            } else {
                db.addDog(dog);
                d = db.getLastDog();
                DogOwner newDogOwner = new DogOwner(d.id,
                        ownerID,
                        "today", "today", "Active");
                db2.addDogOwner(newDogOwner);

            }

            Toast toast = Toast.makeText(getApplicationContext(), "Dog ID: " + d.id, Toast.LENGTH_SHORT);
            toast.show();

            startActivity(intent);
        } else {
            Toast.makeText(this, errorMsgs.get(0), Toast.LENGTH_LONG).show();
        }
    }
    public void delete(View view){
        db.deleteDog(db.getDog(dogID));
        db2.deleteDogOwner(dogID);
        db5.deleteMatch(dogID);

        Intent UserIntent = getIntent();

        Intent intent = new Intent(this, home_screen.class);
        intent.putExtra("username", UserIntent.getStringExtra("username"));
        intent.putExtra("new", false);
        startActivity(intent);
    }
}
