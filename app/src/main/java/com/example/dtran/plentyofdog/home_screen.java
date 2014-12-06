package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class home_screen extends Activity {
    Intent userIntent;
    DogHelper dogHelper;
    DogOwnerHelper dogOwnerHelper;
    UserHelper userHelper;
    MatchHelper matchHelper;
    OwnerHelper ownerHelper;

    List<Dog> myDogs;
    List<Owner> myOwners;
    MatchList adapter;
    ListView list;
    List<Match> matchlist;
    ArrayList<Owner> tempOwners;

    List<Match> ApprovedMatchesList;
    ArrayList<Dog> matchedDogs;
    ApprovedMatchList adapter2;
    ListView list2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_screen);
        userIntent = getIntent();
        matchedDogs = new ArrayList<Dog>();
        new LoadMyMatches().execute("");
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
    public void dogBrowse(View view){
        Intent intent = new Intent(this, DogSelection.class);
        intent.putExtra("username", userIntent.getStringExtra("username"));
        startActivity(intent);
    }
    public void test(View view){
        Intent home = new Intent(this, dog_builder.class);
        home.putExtra("username", userIntent.getStringExtra("username"));
        home.putExtra("new", false);
        startActivity(home);
    }
    private class LoadMyMatches extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {

            try {
                runOnUiThread(new Runnable() {
                    public void run() {
                        dogHelper = new DogHelper(getApplicationContext());
                        dogOwnerHelper = new DogOwnerHelper(getApplicationContext());
                        userHelper = new UserHelper(getApplicationContext());
                        matchHelper = new MatchHelper(getApplicationContext());
                        ownerHelper = new OwnerHelper(getApplicationContext());

                        Intent intent = getIntent();
                        int ownerID = userHelper.getOwnerID(intent.getStringExtra("username"));

                        //Left List
                        ArrayList<Integer> DogIDs = dogOwnerHelper.getMyDogs(ownerID);
                        ArrayList<Dog> tempDogs = new ArrayList<Dog>();
                        tempOwners = new ArrayList<Owner>();
                        matchlist = new ArrayList<Match>();
                        //End Left List
                        for(int i = 0; i < DogIDs.size(); i++){
                            matchlist = matchHelper.getAllMatchesDogID(DogIDs.get(i));
                            for(Match m : matchlist){
                                tempDogs.add(dogHelper.getDog(DogIDs.get(i)));
                                tempOwners.add(ownerHelper.getOwner(m.userID));
                            }

                        }
                        myOwners = tempOwners;

                        myDogs = tempDogs;

                        if (myDogs != null){
                            adapter = new MatchList(home_screen.this,  myDogs, myOwners);
                            list = (ListView) findViewById(R.id.list);
                            list.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Intent dogIntent = new Intent(home_screen.this, view_profile.class);
                                    dogIntent.putExtra("ownerID", tempOwners.get(position).id);
                                    dogIntent.putExtra("dogID", myDogs.get(position).id);
                                    dogIntent.putExtra("username", userIntent.getStringExtra("username"));
                                    dogIntent.putExtra("listFrom", 1);
                                    startActivity(dogIntent);
                                }
                            });
                            resp = "All dogs loaded!";
                        } //End Left List

                        //Start Right List
                        ApprovedMatchesList = matchHelper.getAllMatches(ownerID);


                        if (ApprovedMatchesList != null){
                            Log.d("Size of ApprovedMAtchesList ", "" + ApprovedMatchesList.size());
                            for (Match m : ApprovedMatchesList){
                                Dog tempDog = dogHelper.getDog(m.dogID);
                                matchedDogs.add(tempDog);
                            }
                            adapter2 = new ApprovedMatchList(home_screen.this, ApprovedMatchesList, matchedDogs);
                            list2 = (ListView) findViewById(R.id.list2);
                            list2.setAdapter(adapter2);
                            adapter2.notifyDataSetChanged();

                            list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    int myOwnerID = dogOwnerHelper.getDogOwnerFromDogID(ApprovedMatchesList.get(position).dogID) .ownerID;

                                    Intent ownerIntent = new Intent(home_screen.this, view_profile.class);
                                    ownerIntent.putExtra("ownerID", myOwnerID);
                                    ownerIntent.putExtra("listFrom", 2);
                                    ownerIntent.putExtra("username", userIntent.getStringExtra("username"));
                                    startActivity(ownerIntent);

                                }
                            });

                            resp += ", All matches loaded!";
                        }


                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
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
}
