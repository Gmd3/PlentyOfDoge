package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class logOn_signUp extends Activity {
    public UserHelper userdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on_sign_up);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.log_on_sign_up, menu);
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
    public void signUpClick(View v){
        Intent intent = new Intent(this, WelcomeScreen.class);
        EditText fnameGrab = (EditText)findViewById(R.id.firstNameInput);
        EditText lnameGrab = (EditText)findViewById(R.id.lasttNameInput);


        String fname = fnameGrab.getText().toString();
        String lname = lnameGrab.getText().toString();


        intent.putExtra("WelcomeStringsFName", fname);
        intent.putExtra("WelcomeStringsLName", lname);
        startActivity(intent);
    }
    public void loginClick(View v){
        Intent intent = new Intent(this, home_screen.class);
        EditText usernameGrab = (EditText)findViewById(R.id.usernameInput);
        EditText passwordGrab = (EditText)findViewById(R.id.passwordInput);

        userdb.getReadableDatabase();
        String username = usernameGrab.getText().toString();
        String password = passwordGrab.getText().toString();

        if(userdb.userExist(username)){
            if(password.equals(userdb.getUser(username).password)){
                startActivity(intent);
            }
        }

    finish();
        startActivity(getIntent());

    }
}
