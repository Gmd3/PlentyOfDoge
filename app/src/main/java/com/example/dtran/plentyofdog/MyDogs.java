package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MyDogs extends Activity {

    ListView list;
    SimpleDogList adapter;

    List<Dog> myDogs;
    DogHelper dogHelper;
    DogOwnerHelper dogOwnerHelper;
    UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_dogs);

        new LoadMyDogs().execute("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_dogs, menu);



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



    public void newDog(View view){
        Intent dogIntent = new Intent(this, dog_builder.class);
        dogIntent.putExtra("username", getIntent().getStringExtra("username"));
        dogIntent.putExtra("dogID", "");
        startActivity(dogIntent);
    }

    public void backHome(View view){
        Intent home = new Intent(this, home_screen.class);
        home.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(home);
    }

    private class LoadMyDogs extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {

            try {
                runOnUiThread(new Runnable() {
                    public void run() {
                        dogHelper = new DogHelper(getApplicationContext());
                        dogOwnerHelper = new DogOwnerHelper(getApplicationContext());
                        userHelper = new UserHelper(getApplicationContext());

                        Intent intent = getIntent();
                        int ownerID = userHelper.getOwnerID(intent.getStringExtra("username"));
                        ArrayList<Integer> DogIDs = dogOwnerHelper.getMyDogs(ownerID);

                        ArrayList<Dog> temp = new ArrayList<Dog>();


                        for(int i = 0; i < DogIDs.size(); i++){
                            temp.add(dogHelper.getDog(DogIDs.get(i)));
                        }

                        myDogs = temp;

                        if (myDogs != null){
                            adapter = new SimpleDogList(MyDogs.this,  myDogs);
                            list = (ListView) findViewById(R.id.list);
                            list.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Toast.makeText(MyDogs.this, "You Clicked at " + myDogs.get(position).id, Toast.LENGTH_SHORT).show();
                                    Intent dogIntent = new Intent(MyDogs.this, dog_builder.class);
                                    dogIntent.putExtra("username", getIntent().getStringExtra("username"));
                                    dogIntent.putExtra("dogID", myDogs.get(position).id);
                                    startActivity(dogIntent);
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
