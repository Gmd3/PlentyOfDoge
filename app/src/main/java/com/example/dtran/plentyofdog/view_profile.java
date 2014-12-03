package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class view_profile extends Activity {
    MatchHelper matchDB;
    UserHelper userDB;

    Intent matchIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        matchDB = new MatchHelper(getApplicationContext());
        userDB = new UserHelper(getApplicationContext());
        matchIntent = getIntent();

        OwnerHelper ownerdb = new OwnerHelper(this);
        ownerdb.getWritableDatabase();
        Log.d("DB", "" + ownerdb.getOwnerCount());
        int ownerID = matchIntent.getIntExtra("ownerID", 0);
        Owner owner = ownerdb.getOwner(ownerID);

        EditText age = (EditText)findViewById(R.id.ageInput);
        EditText email = (EditText)findViewById(R.id.emailInput);
        Spinner experience = (Spinner)findViewById(R.id.yoeInput);
        EditText gender = (EditText)findViewById(R.id.genderInput);
        EditText phone = (EditText)findViewById(R.id.phoneInput);
        EditText area = (EditText)findViewById(R.id.hometownInput);
        EditText firstName = (EditText)findViewById(R.id.firstNameInput);
        EditText lastName = (EditText)findViewById(R.id.lasttNameInput);

        email.setText(owner.email);
        age.setText(""+ owner.age);
        area.setText(owner.area);
        ArrayAdapter myadap = (ArrayAdapter)experience.getAdapter();
        int position = myadap.getPosition(owner.experience);
        experience.setSelection(position);
        gender.setText(owner.gender);
        phone.setText(""+owner.phone);
        firstName.setText(owner.firstName);
        lastName.setText(owner.lastName);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_profile, menu);
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

    public void YES(View view){

        int ownerID = matchIntent.getIntExtra("ownerID", 0);

        int dogID =  matchIntent.getIntExtra("dogID", 0);

        Match match = matchDB.getMatch(ownerID,dogID);

        if(match.matched==0)
        {
            Button yesbutton = (Button)findViewById(R.id.btnYESYESYES);
            yesbutton.setEnabled(false);
        } else {
            match.matched = 1;
            matchDB.updateMatch(match);
        }
        Intent intent = new Intent(this, home_screen.class);
        intent.putExtra("username" ,matchIntent.getStringExtra("username"));
        startActivity(intent);
    }
    public void reject(View view){
        int matchID = Integer.parseInt(matchIntent.getStringExtra("matchID"));
        matchDB.deleteMatch(matchID);
        Intent intent = new Intent(this, home_screen.class);
        startActivity(intent);
    }
}
