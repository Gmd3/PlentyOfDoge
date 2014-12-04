package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class WelcomeScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome_screen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome_screen, menu);
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
    public void buildProfile(View v){
        Intent intent = new Intent(this, ProfileBuild.class);
        EditText fnameGrab = (EditText)findViewById(R.id.firstNameInput);
        EditText lnameGrab = (EditText)findViewById(R.id.lasttNameInput);
        int errors = 0;
        String fname = fnameGrab.getText().toString();
        String lname = lnameGrab.getText().toString();

        ArrayList<String> errorMsg = new ArrayList<String>();

        if(fname.equalsIgnoreCase("")) {
            errors++;
            errorMsg.add("Please fill in the first name field");
        }
        if(lname.equalsIgnoreCase("")) {
            errors++;
            errorMsg.add("Please fill in the last name field");
        }

        if(errors == 0) {
            intent.putExtra("WelcomeStringsFName", fname);
            intent.putExtra("WelcomeStringsLName", lname);
            startActivity(intent);

        } else
        {
            Toast.makeText(this, errorMsg.get(0), Toast.LENGTH_LONG).show();

        }
    }
}
