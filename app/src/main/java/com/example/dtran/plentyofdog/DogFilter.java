package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;


public class DogFilter extends Activity {
    Preference preference;
    PreferenceHelper db;
    Intent userIntent;
    String username;

    /**
     * When the view loads, the dog filter will display the users preferences
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_filter);
        userIntent = getIntent();
        db = new PreferenceHelper(this);
        db.getWritableDatabase();
        preference = new Preference();

        username = userIntent.getStringExtra("username");

        List<Preference> preflists = db.getAllPreferences();
        for(Preference p : preflists) {

        }

        if(db.preferenceExist(username)) {
            Preference prevPref = db.getPreference(username);
            setPref(prevPref);

        }


    }

    /**
     * Takes in the preference and updates the database whether the user is new or existing
     * @param pref
     */
    private void setPref(Preference pref){
        Button noSize = (Button)findViewById(R.id.noSizePreference);
        Button small = (Button)findViewById(R.id.small);
        Button medium = (Button)findViewById(R.id.medium);
        Button large = (Button)findViewById(R.id.large);

        Button noTemperament = (Button)findViewById(R.id.noTempermentPreference);
        Button mellow = (Button)findViewById(R.id.mellow);
        Button hyper = (Button)findViewById(R.id.hyper);

        Button noHair = (Button)findViewById(R.id.noHairTypePreference);
        Button shorthaired = (Button)findViewById(R.id.shortHaired);
        Button longhaired = (Button)findViewById(R.id.longHaired);

        if(pref.hairtype.equals("noHair")) {
            noHair.setBackgroundColor(0xff57adff);
            noHair.setTextColor(0xFEFEFEFE);
            preference.hairtype = "noHair";
        } else if(pref.hairtype.equals("shortHaired")) {
            shorthaired.setBackgroundColor(0xff57adff);
            shorthaired.setTextColor(0xFEFEFEFE);
            preference.hairtype = "shortHaired";
        } else if(pref.hairtype.equals("longHaired")) {
            longhaired.setBackgroundColor(0xff57adff);
            longhaired.setTextColor(0xFEFEFEFE);
            preference.hairtype = "longHaired";
        }

        if(pref.size.equals("Small")) {
            small.setBackgroundColor(0xff57adff);
            small.setTextColor(0xFEFEFEFE);
            preference.size = "Small";
        } else if(pref.size.equals("Medium")) {
            medium.setBackgroundColor(0xff57adff);
            medium.setTextColor(0xFEFEFEFE);
            preference.size = "Medium";
        } else if(pref.size.equals("Large")) {
            large.setBackgroundColor(0xff57adff);
            large.setTextColor(0xFEFEFEFE);
            preference.size = "Large";
        } else if(pref.size.equals("noSize")) {
            noSize.setBackgroundColor(0xff57adff);
            noSize.setTextColor(0xFEFEFEFE);
            preference.size = "noSize";
        }

        if(pref.temperament.equals("noTemperament")) {
            noTemperament.setBackgroundColor(0xff57adff);
            noTemperament.setTextColor(0xFEFEFEFE);
            preference.temperament = "noTemperament";
        } else if(pref.temperament.equals("Mellow")) {
            mellow.setBackgroundColor(0xff57adff);
            mellow.setTextColor(0xFEFEFEFE);
            preference.temperament = "Mellow";
        } else if(pref.temperament.equals("Hyper")) {
            hyper.setBackgroundColor(0xff57adff);
            hyper.setTextColor(0xFEFEFEFE);
            preference.temperament = "Hyper";
        }


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

    /**
     * Very self explanatory, saves the dog preferences in the database
     * @param v
     */
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


        if(!db.preferenceExist(userIntent.getStringExtra("username"))) {
            db.addPreference(preference);

        }else {
            db.updatePreference(preference) ;
        }
        intent.putExtra("username", userIntent.getStringExtra("username"));
        startActivity(intent);
    }

    /**
     * Depending on the object clicked the button will react to the selection
     * @param view
     */
    public void onSizeClick(View view) {
        Button noSize = (Button)findViewById(R.id.noSizePreference);
        Button small = (Button)findViewById(R.id.small);
        Button medium = (Button)findViewById(R.id.medium);
        Button large = (Button)findViewById(R.id.large);
        switch (view.getId()) {
            case (R.id.noSizePreference):
                //do something
                preference.size = "noSize";

                small.setBackgroundColor(0xffc1e9fb);
                small.setTextColor(0xff57adff);
                medium.setBackgroundColor(0xffc1e9fb);
                medium.setTextColor(0xff57adff);
                large.setBackgroundColor(0xffc1e9fb);
                large.setTextColor(0xff57adff);


                noSize.setBackgroundColor(0xff57adff);
                noSize.setTextColor(0xffc1e9fb);
                break;
            case (R.id.small):
                //do something

                preference.size = "Small";
                noSize.setBackgroundColor(0xffc1e9fb);
                noSize.setTextColor(0xff57adff);
                medium.setBackgroundColor(0xffc1e9fb);
                medium.setTextColor(0xff57adff);
                large.setBackgroundColor(0xffc1e9fb);
                large.setTextColor(0xff57adff);


                small.setBackgroundColor(0xff57adff);
                small.setTextColor(0xffc1e9fb);
                break;
            case (R.id.medium):
                //do something

                preference.size = "Medium";
                noSize.setBackgroundColor(0xffc1e9fb);
                noSize.setTextColor(0xff57adff);
                small.setBackgroundColor(0xffc1e9fb);
                small.setTextColor(0xff57adff);
                large.setBackgroundColor(0xffc1e9fb);
                large.setTextColor(0xff57adff);


                medium.setBackgroundColor(0xff57adff);
                medium.setTextColor(0xffc1e9fb);
                break;
            case (R.id.large):
                //do something

                preference.size = "Large";
                small.setBackgroundColor(0xffc1e9fb);
                small.setTextColor(0xff57adff);
                medium.setBackgroundColor(0xffc1e9fb);
                medium.setTextColor(0xff57adff);
                noSize.setBackgroundColor(0xffc1e9fb);
                noSize.setTextColor(0xff57adff);


                large.setBackgroundColor(0xff57adff);
                large.setTextColor(0xffc1e9fb);
                break;
            default:
                //do something
                break;


        }
    }

    /**
     * Depending on the object clicked the button will react to the selection
     * @param view
     */
    public void onHairTypeClick(View view) {
        Button noHair = (Button)findViewById(R.id.noHairTypePreference);
        Button shorthaired = (Button)findViewById(R.id.shortHaired);
        Button longhaired = (Button)findViewById(R.id.longHaired);
        switch (view.getId()) {
            case (R.id.noHairTypePreference):
                //do something
                preference.hairtype = "noHair";

                shorthaired.setBackgroundColor(0xffc1e9fb);
                shorthaired.setTextColor(0xff57adff);
                longhaired.setBackgroundColor(0xffc1e9fb);
                longhaired.setTextColor(0xff57adff);

                noHair.setBackgroundColor(0xff57adff);
                noHair.setTextColor(0xffc1e9fb);
                break;
            case (R.id.shortHaired):
                //do something

                preference.hairtype = "shortHaired";
                noHair.setBackgroundColor(0xffc1e9fb);
                noHair.setTextColor(0xff57adff);
                longhaired.setBackgroundColor(0xffc1e9fb);
                longhaired.setTextColor(0xff57adff);

                shorthaired.setBackgroundColor(0xff57adff);
                shorthaired.setTextColor(0xffc1e9fb);
                break;
            case (R.id.longHaired):
                //do something

                preference.hairtype = "longHaired";
                shorthaired.setBackgroundColor(0xffc1e9fb);
                shorthaired.setTextColor(0xff57adff);
                noHair.setBackgroundColor(0xffc1e9fb);
                noHair.setTextColor(0xff57adff);

                longhaired.setBackgroundColor(0xff57adff);
                longhaired.setTextColor(0xffc1e9fb);
                break;
            default:
                //do something
                break;


        }
    }

    /**
     * Depending on the object clicked the button will react to the selection
     * @param view
     */
    public void onTemperamentClick(View view) {
        Button noTemperament = (Button)findViewById(R.id.noTempermentPreference);
        Button mellow = (Button)findViewById(R.id.mellow);
        Button hyper = (Button)findViewById(R.id.hyper);
        switch (view.getId()) {
            case (R.id.noTempermentPreference):
                //do something
                preference.temperament = "noTemperament";
                mellow.setBackgroundColor(0xffc1e9fb);
                mellow.setTextColor(0xff57adff);
                hyper.setBackgroundColor(0xffc1e9fb);
                hyper.setTextColor(0xff57adff);

                noTemperament.setBackgroundColor(0xff57adff);
                noTemperament.setTextColor(0xffc1e9fb);
                break;
            case (R.id.mellow):
                //do something

                preference.temperament = "Mellow";
                noTemperament.setBackgroundColor(0xffc1e9fb);
                noTemperament.setTextColor(0xff57adff);
                hyper.setBackgroundColor(0xffc1e9fb);
                hyper.setTextColor(0xff57adff);

                mellow.setBackgroundColor(0xff57adff);
                mellow.setTextColor(0xffc1e9fb);
                break;
            case (R.id.hyper):
                //do something

                preference.temperament = "Hyper";
                noTemperament.setBackgroundColor(0xffc1e9fb);
                noTemperament.setTextColor(0xff57adff);
                mellow.setBackgroundColor(0xffc1e9fb);
                mellow.setTextColor(0xff57adff);

                hyper.setBackgroundColor(0xff57adff);
                hyper.setTextColor(0xffc1e9fb);
                break;
            default:
                //do something
                break;


        }
    }

    /**
     * This will act as a back button to the home screen
     * @param view
     */
    public void skip(View view){
        Intent intent = new Intent(this, home_screen.class);
        intent.putExtra("username", userIntent.getStringExtra("username"));

        startActivity(intent);
    }
}
