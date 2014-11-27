package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MyDogs extends Activity {

    ListView list;
    SimpleDogList adapter;
    String[] web = {
            "Google Plus","asd"
    } ;
    Integer[] imageId = {
            R.drawable.ic_launcher,  R.drawable.ic_launcher, R.drawable.ic_launcher,  R.drawable.ic_launcher, R.drawable.ic_launcher,  R.drawable.ic_launcher, R.drawable.ic_launcher,  R.drawable.ic_launcher,  R.drawable.ic_launcher, R.drawable.ic_launcher,  R.drawable.ic_launcher, R.drawable.ic_launcher,  R.drawable.ic_launcher, R.drawable.ic_launcher,  R.drawable.ic_launcher,  R.drawable.ic_launcher, R.drawable.ic_launcher,  R.drawable.ic_launcher, R.drawable.ic_launcher,  R.drawable.ic_launcher, R.drawable.ic_launcher,  R.drawable.ic_launcher
    };

    List<Dog> myDogs;
    DogHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dogs);

        new LoadMyDogs().execute("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_dogs, menu);



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



    public void newDog(View view){
        Intent dogIntent = new Intent(this, dog_builder.class);
        dogIntent.putExtra("username", getIntent().getStringExtra("username"));
        dogIntent.putExtra("new", true);
        startActivity(dogIntent);
    }

    public void backHome(View view){
        Intent home = new Intent(this, home_screen.class);
        startActivity(home);
    }

    private class LoadMyDogs extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {

            try {
                runOnUiThread(new Runnable() {
                    public void run() {
                        db = new DogHelper(getApplicationContext());
                        myDogs = db.getAllDog();
                        if (myDogs != null){
                            adapter = new SimpleDogList(MyDogs.this, imageId, myDogs);
                            list = (ListView) findViewById(R.id.list);
                            list.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Toast.makeText(MyDogs.this, "You Clicked at " + myDogs.get(position).activitylevel, Toast.LENGTH_SHORT).show();
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
