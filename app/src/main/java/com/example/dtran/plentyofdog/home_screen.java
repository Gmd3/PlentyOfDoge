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


public class home_screen extends Activity {
    Intent userIntent;
    DogHelper db;
    DogOwnerHelper db2;
    UserHelper db3;
    MatchHelper db4;
    OwnerHelper db5;
    List<Dog> myDogs;
    List<Owner> myOwners;
    MatchList adapter;
    ListView list;
    List<Match> matchlist;


    List<Match> ApprovedMatchesList;
    ArrayList<Owner> ApprovedMatchedOwnersList;
    ArrayList<Dog> matchedDogs;
    ApprovedMatchList adapter2;
    ListView list2;

    ArrayList<Owner> temp3;
    ArrayList<Owner> temp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        userIntent = getIntent();
        Log.d("intent Dogfilter", " " + userIntent.getStringExtra("username"));
        matchedDogs = new ArrayList<Dog>();
        ApprovedMatchedOwnersList = new ArrayList<Owner>();
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
                        db = new DogHelper(getApplicationContext());
                        db2 = new DogOwnerHelper(getApplicationContext());
                        db3 = new UserHelper(getApplicationContext());
                        db4 = new MatchHelper(getApplicationContext());
                        db5 = new OwnerHelper(getApplicationContext());

                        Intent intent = getIntent();
                        int ownerID = db3.getOwnerID(intent.getStringExtra("username"));

                        //Left List
                        ArrayList<Integer> DogIDs = db2.getMyDogs(ownerID);
                        ArrayList<Dog> temp = new ArrayList<Dog>();
                        temp2 = new ArrayList<Owner>();
                        matchlist = new ArrayList<Match>();
                        //End Left List
                        for(int i = 0; i < DogIDs.size(); i++){
                            matchlist = db4.getAllMatchesDogID(DogIDs.get(i));
                            for(Match m : matchlist){
                                temp.add(db.getDog(DogIDs.get(i)));
                                Log.d("m.userID"  ,""+  m.userID);

                                Log.d("m.id"  ,""+  m.id);

                                temp2.add(db5.getOwner(m.userID));
                            }

                        }
                        myOwners = temp2;

                        myDogs = temp;

                        if (myDogs != null){
                            adapter = new MatchList(home_screen.this,  myDogs, myOwners);
                            list = (ListView) findViewById(R.id.list);
                            list.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Toast.makeText(home_screen.this, "You Clicked at " + myDogs.get(position).id, Toast.LENGTH_SHORT).show();
                                    Intent dogIntent = new Intent(home_screen.this, view_profile.class);
                                    dogIntent.putExtra("ownerID", temp2.get(position).id);
                                    dogIntent.putExtra("dogID", myDogs.get(position).id);
                                    dogIntent.putExtra("username", userIntent.getStringExtra("username"));
                                    startActivity(dogIntent);
                                }
                            });
                            resp = "All dogs loaded!";
                        } //End Left List

                        //Start Right List
                        ApprovedMatchesList = db4.getAllMatches(ownerID);


                        if (ApprovedMatchesList != null){
                            Log.d("Size of ApprovedMAtchesList ", "" + ApprovedMatchesList.size());
                            for (Match m : ApprovedMatchesList){
                                Dog tempDog = db.getDog(m.dogID);
                                Log.d("ownerID", ""+m.userID);
                                matchedDogs.add(tempDog);
                                ApprovedMatchedOwnersList.add(db5.getOwner(m.userID));
                            }
                            adapter2 = new ApprovedMatchList(home_screen.this, ApprovedMatchesList, ApprovedMatchedOwnersList, matchedDogs);
                            list2 = (ListView) findViewById(R.id.list2);
                            list2.setAdapter(adapter2);
                            adapter2.notifyDataSetChanged();
                            /*
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Toast.makeText(home_screen.this, "You Clicked at " + ApprovedMatchesList.get(position).dogID, Toast.LENGTH_SHORT).show();
                                    Intent dogIntent = new Intent(home_screen.this, view_profile.class);
                                    dogIntent.putExtra("ownerID", temp2.get(position).id);
                                    dogIntent.putExtra("dogID", myDogs.get(position).id);
                                    dogIntent.putExtra("username", userIntent.getStringExtra("username"));
                                    startActivity(dogIntent);
                                }
                            });
                            */
                            resp += "All matches loaded!";
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
