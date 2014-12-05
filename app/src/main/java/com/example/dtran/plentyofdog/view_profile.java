package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * The controller that will allow the user to view their profile or other profiles
 * This is powerful because it loads the current profile
 */

public class view_profile extends Activity {
    MatchHelper matchDB;
    UserHelper userDB;

    Intent matchIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_profile);
        matchDB = new MatchHelper(getApplicationContext());
        userDB = new UserHelper(getApplicationContext());
        matchIntent = getIntent();

        OwnerHelper ownerdb = new OwnerHelper(this);
        ownerdb.getWritableDatabase();
        Log.d("DB", "" + ownerdb.getOwnerCount());
        int ownerID = matchIntent.getIntExtra("ownerID", 0);
        int listFrom = matchIntent.getIntExtra("listFrom", 0);
        Owner owner = ownerdb.getOwner(ownerID);

        TextView age = (TextView)findViewById(R.id.ageInput);
        TextView email = (TextView)findViewById(R.id.emailInput);
        TextView experience = (TextView)findViewById(R.id.yoeInput);
        TextView gender = (TextView)findViewById(R.id.genderInput);
        TextView phone = (TextView)findViewById(R.id.phoneInput);
        TextView area = (TextView)findViewById(R.id.hometownInput);
        TextView firstName = (TextView)findViewById(R.id.firstNameInput);
        TextView lastName = (TextView)findViewById(R.id.lasttNameInput);
        Button approve = (Button)findViewById(R.id.approve);
        Button reject = (Button)findViewById(R.id.reject);

        email.setText(owner.email);
        age.setText(""+ owner.age);
        area.setText(owner.area);
        experience.setText(owner.experience);
        gender.setText(owner.gender);
        phone.setText(""+owner.phone);
        firstName.setText(owner.firstName);
        lastName.setText(owner.lastName);

        if (listFrom == 1){
            approve.setText("Approve");
            reject.setText("Reject");
        } else if (listFrom == 2){
            approve.setVisibility(View.GONE);
            reject.setText("Back to Home");
        }
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

        Log.d("Match status is: ", "" +  match.matched);

        if(match.matched == 0)
        {
            match.matched = 1;
            matchDB.updateMatch(match);
        } else {
            Button yesbutton = (Button)findViewById(R.id.approve);
            yesbutton.setEnabled(false);
        }

        Intent intent = new Intent(this, home_screen.class);
        intent.putExtra("username" ,matchIntent.getStringExtra("username"));
        startActivity(intent);
    }
    public void reject(View view){
        int listFrom = matchIntent.getIntExtra("listFrom", 0);

        if (listFrom == 1){
            int ownerID = matchIntent.getIntExtra("ownerID", 0);
            int dogID =  matchIntent.getIntExtra("dogID", 0);
            Match match = matchDB.getMatch(ownerID,dogID);
            matchDB.deleteMatch(match.dogID);
        } else if (listFrom == 2){

        }
        Intent intent = new Intent(this, home_screen.class);
        intent.putExtra("username" ,matchIntent.getStringExtra("username"));
        startActivity(intent);
    }
}
