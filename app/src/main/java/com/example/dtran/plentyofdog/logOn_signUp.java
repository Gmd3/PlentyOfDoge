package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class logOn_signUp extends Activity {
    public UserHelper userdb;
    public DatabaseHelper db;
    public SQLiteDatabase sqlitedb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on_sign_up);
        db = new DatabaseHelper(this);
        db.getWritableDatabase();
        userdb = new UserHelper(this);
        userdb.getWritableDatabase();


        Log.d("@@@@@@@@@@@@@  ----  DB", "" + userdb.getUserCount());

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
        startActivity(intent);
    }
    public void loginClick(View v){
        Intent intent = new Intent(this, home_screen.class);
        EditText usernameGrab = (EditText)findViewById(R.id.usernameInput);
        EditText passwordGrab = (EditText)findViewById(R.id.passwordInput);

        userdb.getReadableDatabase();
        String username = usernameGrab.getText().toString();
        String password = passwordGrab.getText().toString();
        if(userdb.userExist(username) && password.equals(userdb.getUser(username).password)){
            Log.d("userdb.getUser(username)", " " + userdb.getUser(username).username);


                Log.d("LOGIN" , " PASSED");
                intent.putExtra("username", username);
                startActivity(intent);
                finish();

        } else {


            passwordGrab.setText("");
            Toast.makeText(getApplicationContext(), "Password or Username is incorrect", Toast.LENGTH_SHORT).show();
            Log.d("LOGIN", " FAILED");
        }

    }
}
