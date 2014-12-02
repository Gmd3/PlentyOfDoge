package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DogSelection extends Activity {

    ListView list;
    FullDogList adapter;

    List<Dog> myDogs;
    DogHelper db;
    DogOwnerHelper db2;
    UserHelper db3;
    PreferenceHelper db4;
    MatchHelper db5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_selection);
        new LoadMyDogs().execute("");
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

    private class LoadMyDogs extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {

            try {
                runOnUiThread(new Runnable() {
                    public void run() {
                        db = new DogHelper(getApplicationContext());
                        db2 = new DogOwnerHelper(getApplicationContext());
                        db3 = new UserHelper(getApplicationContext());
                        db4 = new PreferenceHelper(getApplicationContext());
                        db5 = new MatchHelper(getApplicationContext());

                        Intent intent = getIntent();
                        int ownerID = db3.getOwnerID(intent.getStringExtra("username"));
                        Preference p = db4.getPreference(intent.getStringExtra("username"));

                        ArrayList<Integer> DogIDs = db2.getOtherDogs(ownerID);
                        ArrayList<Dog> temp = new ArrayList<Dog>();


                        Log.d("owner prefs: ", "" + p.size + ", "  + p.temperament +  ", "  + p.hairtype);
                        for(int i = 0; i < DogIDs.size(); i++){
                            Dog tempDog = db.getDog(DogIDs.get(i));
                            Log.d("tempDog stats: ", "" + tempDog.size + ", "  + tempDog.activitylevel +  ", "  + tempDog.breed);

                            if (rightSize(p.size, tempDog.size) &&
                                rightTemperment(p.temperament, tempDog.activitylevel) &&
                                rightBreed(tempDog.breed, p.hairtype) &&
                                    !alreadyMatched(DogIDs.get(i), ownerID)){
                                temp.add(tempDog);
                            }
                        }

                        myDogs = temp;

                        if (myDogs != null){
                            adapter = new FullDogList(DogSelection.this,  myDogs, ownerID);
                            list = (ListView) findViewById(R.id.list);
                            list.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Toast.makeText(DogSelection.this, "You Clicked at " + myDogs.get(position).area, Toast.LENGTH_SHORT).show();
                                }
                            });
                            resp = "All dogs loaded!";
                        } else resp = "No dogs found!";
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        protected boolean rightSize(String prefSize, String dogSize){
            if (prefSize.equals("noSize")) {
                return true;
            } else if (prefSize.equals(dogSize)){
                return true;
            }

            return false;
        }

        protected boolean rightTemperment(String temperament, String activityLevel){
            if (temperament.equals("noTemperament")) {
                return true;
            }else if (temperament.equals(activityLevel)){
                return true;
            }

            return false;
        }
        protected boolean alreadyMatched(int dogID, int userID){
            if(db5.existUserDogID(userID, dogID))
                return true;
            return false;
        }


        protected boolean rightBreed(String breed, String hairType){
            Log.d("Passed into rightBreed: ", breed + " , " + hairType);

            ArrayList<String> shortHair = new ArrayList<String>();
            shortHair.add("Corgi");
            shortHair.add("Retriever");
            shortHair.add("Shiba");
            shortHair.add("Samoyed");
            shortHair.add("Chow");

            ArrayList<String> longHair = new ArrayList<String>();
            longHair.add("Pug");
            longHair.add("Bulldog");
            longHair.add("Rottweiler");
            longHair.add("Husky");
            longHair.add("Terrier");

            if (hairType.equals("noHair")){
                Log.d("noHair", "");
                return true;
            }
            else if (hairType.equals("shortHaired")){
                Log.d("shortHaired", "");
                if (shortHair.contains(breed)) return true;
            } else if (hairType.equals("longHaired")){
                Log.d("longHaired", "");
                if (longHair.contains(breed)) return true;
            }

            Log.d("returning False", "");
            return false;
        }

        @Override
        protected void onPostExecute(String result) {
        }

        @Override
        protected void onPreExecute() {
        }


        @Override
        protected void onProgressUpdate(String... text) {
        }
    }

    public void yes(View view){
        //add to list of matches

    }
    public void no(View view){
        //randomly choose another dog to view
        
    }
}
