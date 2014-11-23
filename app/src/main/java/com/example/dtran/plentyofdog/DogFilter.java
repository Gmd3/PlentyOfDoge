package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class DogFilter extends Activity {
    Preference preference;
    PreferenceHelper db;
    Intent userIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_filter);
        userIntent = getIntent();
        Log.d("intent Dogfilter", " " + userIntent.getStringExtra("username"));
        db = new PreferenceHelper(this);
        db.getWritableDatabase();
        preference = new Preference();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dog_filter, menu);
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
    public void save(View v){
        Intent intent = new Intent(this, home_screen.class);
        //check for the parameters in preferences

        if(preference.hairtype == null)
            preference.hairtype = "noHair";
        if(preference.size== null)
            preference.size = "noSize";
        if(preference.temperament== null)
            preference.temperament = "noTemperament";
        preference.username = userIntent.getStringExtra("username");


        if(!db.preferenceExist(userIntent.getStringExtra("username")))
            db.addPreference(preference);
        else {
            db.updatePreference(preference);
        }
        Log.d("count: ", "" + db.countPreference());
        intent.putExtra("username", userIntent.getStringExtra("username"));
        startActivity(intent);
    }

    public void onSizeClick(View view) {
        Button noSize = (Button)findViewById(R.id.noSizePreference);
        Button small = (Button)findViewById(R.id.small);
        Button medium = (Button)findViewById(R.id.medium);
        Button large = (Button)findViewById(R.id.large);
        switch (view.getId()) {
            case (R.id.noSizePreference):
                //do something
                preference.size = "noSize";

                small.setBackgroundColor(0xfffff3a3);
                small.setTextColor(0xff57adff);
                medium.setBackgroundColor(0xfffff3a3);
                medium.setTextColor(0xff57adff);
                large.setBackgroundColor(0xfffff3a3);
                large.setTextColor(0xff57adff);


                noSize.setBackgroundColor(0xff57adff);
                noSize.setTextColor(0xfffff3a3);
                break;
            case (R.id.small):
                //do something

                preference.size = "small";
                noSize.setBackgroundColor(0xfffff3a3);
                noSize.setTextColor(0xff57adff);
                medium.setBackgroundColor(0xfffff3a3);
                medium.setTextColor(0xff57adff);
                large.setBackgroundColor(0xfffff3a3);
                large.setTextColor(0xff57adff);


                small.setBackgroundColor(0xff57adff);
                small.setTextColor(0xfffff3a3);
                break;
            case (R.id.medium):
                //do something

                preference.size = "medium";
                noSize.setBackgroundColor(0xfffff3a3);
                noSize.setTextColor(0xff57adff);
                small.setBackgroundColor(0xfffff3a3);
                small.setTextColor(0xff57adff);
                large.setBackgroundColor(0xfffff3a3);
                large.setTextColor(0xff57adff);


                medium.setBackgroundColor(0xff57adff);
                medium.setTextColor(0xfffff3a3);
                break;
            case (R.id.large):
                //do something

                preference.size = "large";
                small.setBackgroundColor(0xfffff3a3);
                small.setTextColor(0xff57adff);
                medium.setBackgroundColor(0xfffff3a3);
                medium.setTextColor(0xff57adff);
                noSize.setBackgroundColor(0xfffff3a3);
                noSize.setTextColor(0xff57adff);


                large.setBackgroundColor(0xff57adff);
                large.setTextColor(0xfffff3a3);
                break;
            default:
                //do something
                break;


        }
    }
    public void onHairTypeClick(View view) {
        Button noHair = (Button)findViewById(R.id.noHairTypePreference);
        Button shorthaired = (Button)findViewById(R.id.shortHaired);
        Button longhaired = (Button)findViewById(R.id.longHaired);
        switch (view.getId()) {
            case (R.id.noHairTypePreference):
                //do something
                preference.hairtype = "noHair";


                shorthaired.setBackgroundColor(0xfffff3a3);
                shorthaired.setTextColor(0xff57adff);
                longhaired.setBackgroundColor(0xfffff3a3);
                longhaired.setTextColor(0xff57adff);

                noHair.setBackgroundColor(0xff57adff);
                noHair.setTextColor(0xfffff3a3);
                break;
            case (R.id.shortHaired):
                //do something

                preference.hairtype = "shortHaired";
                noHair.setBackgroundColor(0xfffff3a3);
                noHair.setTextColor(0xff57adff);
                longhaired.setBackgroundColor(0xfffff3a3);
                longhaired.setTextColor(0xff57adff);

                shorthaired.setBackgroundColor(0xff57adff);
                shorthaired.setTextColor(0xfffff3a3);
                break;
            case (R.id.longHaired):
                //do something

                preference.hairtype = "longHaired";
                shorthaired.setBackgroundColor(0xfffff3a3);
                shorthaired.setTextColor(0xff57adff);
                noHair.setBackgroundColor(0xfffff3a3);
                noHair.setTextColor(0xff57adff);

                longhaired.setBackgroundColor(0xff57adff);
                longhaired.setTextColor(0xfffff3a3);
                break;
            default:
                //do something
                break;


        }
    }
    public void onTemperamentClick(View view) {
        Button noTemperament = (Button)findViewById(R.id.noTempermentPreference);
        Button mellow = (Button)findViewById(R.id.mellow);
        Button hyper = (Button)findViewById(R.id.hyper);
        switch (view.getId()) {
            case (R.id.noTempermentPreference):
                //do something
                preference.temperament = "noTemperament";
                mellow.setBackgroundColor(0xfffff3a3);
                mellow.setTextColor(0xff57adff);
                hyper.setBackgroundColor(0xfffff3a3);
                hyper.setTextColor(0xff57adff);

                noTemperament.setBackgroundColor(0xff57adff);
                noTemperament.setTextColor(0xfffff3a3);
                break;
            case (R.id.mellow):
                //do something

                preference.temperament = "mellow";
                noTemperament.setBackgroundColor(0xfffff3a3);
                noTemperament.setTextColor(0xff57adff);
                hyper.setBackgroundColor(0xfffff3a3);
                hyper.setTextColor(0xff57adff);

                mellow.setBackgroundColor(0xff57adff);
                mellow.setTextColor(0xfffff3a3);
                break;
            case (R.id.hyper):
                //do something

                preference.temperament = "hyper";
                noTemperament.setBackgroundColor(0xfffff3a3);
                noTemperament.setTextColor(0xff57adff);
                mellow.setBackgroundColor(0xfffff3a3);
                mellow.setTextColor(0xff57adff);

                hyper.setBackgroundColor(0xff57adff);
                hyper.setTextColor(0xfffff3a3);
                break;
            default:
                //do something
                break;


        }
    }
    public void skip(View view){
        Intent intent = new Intent(this, home_screen.class);


        startActivity(intent);
    }
}
