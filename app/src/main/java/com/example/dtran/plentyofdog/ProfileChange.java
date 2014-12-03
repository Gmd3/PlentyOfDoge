package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ProfileChange extends Activity {
    Intent userIntent;
    OwnerHelper ownerdb;
    Owner owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_change);
        //load the current users data
        userIntent = getIntent();
        String username = userIntent.getStringExtra("username");
        ownerdb = new OwnerHelper(this);
        ownerdb.getWritableDatabase();
        Log.d("DB",""+ownerdb.getOwnerCount());

        owner = ownerdb.getOwner(username);
        EditText age = (EditText)findViewById(R.id.ageInput);
        EditText email = (EditText)findViewById(R.id.emailInput);
        Spinner experience = (Spinner)findViewById(R.id.yoeInput);
        Spinner gender = (Spinner)findViewById(R.id.genderInput);
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
        ArrayAdapter genadap = (ArrayAdapter)gender.getAdapter();
        int genpos = genadap.getPosition(owner.gender);
        gender.setSelection(genpos);
        phone.setText(""+owner.phone);
        firstName.setText(owner.firstName);
        lastName.setText(owner.lastName);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_change, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void back(View view){
        Intent intent = new Intent(this, home_screen.class);
        intent.putExtra("username", userIntent.getStringExtra("username"));
        startActivity(intent);
    }
    public void update(View view){
        UserHelper db = new UserHelper(this);
        int errors = 0;
        ArrayList<String> errorMsg = new ArrayList<String>();
        Intent intent = new Intent(this, home_screen.class);
        EditText age = (EditText)findViewById(R.id.ageInput);
        EditText email = (EditText)findViewById(R.id.emailInput);
        Spinner experience = (Spinner)findViewById(R.id.yoeInput);
        Spinner gender = (Spinner)findViewById(R.id.genderInput);
        EditText phone = (EditText)findViewById(R.id.phoneInput);
        EditText area = (EditText)findViewById(R.id.hometownInput);
        EditText firstName = (EditText)findViewById(R.id.firstNameInput);
        EditText lastName = (EditText)findViewById(R.id.lasttNameInput);
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyy");
        String date = s.format(new Date());

        String strEmail =  email.getText().toString();
        String strFName = firstName.getText().toString();
        String strLName = lastName.getText().toString();
        String strHome = area.getText().toString();

        //password section
        EditText passwordgrab = (EditText)findViewById(R.id.lblpasswordinput);
        EditText confirmgrab = (EditText)findViewById(R.id.lblpasswordinput2);
        String password = passwordgrab.getText().toString();
        String confirm = confirmgrab.getText().toString();
        if((!password.equals("") && !confirm.equals(""))) {
            if (!password.equals(confirm)) {
                errors++;
            }
            else {
                Log.d("PASSWORDS ARE THE ", "SAME");

                User userTempEmail = db.getUser(strEmail);

                User user = new User(strEmail,
                        password, userTempEmail.ownerId);
                db.updateUser(user);
            }
        }
        if(!password.equals("") && confirm.equals(""))
            errors++;
        if(!confirm.equals("") && password.equals(""))
            errors++;

//        if(db.userExist(strEmail)) {
//            errorMsg.add(strEmail + " already exists!");
//            errors++;
//        }

        if(strEmail.length() < 1) {
            errorMsg.add("Email cannot be empty!");
            errors++;
        }

        if(strFName.length() < 1) {
            errorMsg.add("First name cannot be empty!");
            errors++;
        }

        if(strLName.length() < 1) {
            errorMsg.add("Last name cannot be empty!");
            errors++;
        }

        if(strHome.length() < 1) {
            errorMsg.add("Hometown cannot be empty!");
            errors++;
        }

        if(phone.getText().length() != 10) {
            errorMsg.add("Invalid phone number");
            errors++;
        }

        if(errors == 0) {
            Owner ownerTemp = new Owner(
                    firstName.getText().toString(),
                    lastName.getText().toString(),
                    experience.getSelectedItem().toString(),
                    Integer.parseInt(age.getText().toString())
                    , gender.getSelectedItem().toString(),
                    email.getText().toString(),
                    phone.getText().toString()
                    , area.getText().toString(),
                    owner.dateCreated,
                    date);
            Log.d("DB UPDATE SUCCESS ? : ", "" + ownerdb.updateOwner(ownerTemp));
            intent.putExtra("username",email.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, errorMsg.get(0), Toast.LENGTH_LONG).show();
            passwordgrab.setText("");
            confirmgrab.setText("");
        }

    }
}
