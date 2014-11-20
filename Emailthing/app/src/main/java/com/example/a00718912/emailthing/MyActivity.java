package com.example.a00718912.emailthing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    public void send(View view) {
        EditText toEmail = (EditText) findViewById(R.id.toEmail);
        EditText emailSubject = (EditText) findViewById(R.id.subject);
        EditText emailBody = (EditText) findViewById(R.id.emailBody);

        String to = toEmail.getText().toString();
        String subject = emailSubject.getText().toString();
        String message = emailBody.getText().toString();

        message += "\nAPI Level: " + Integer.valueOf(Build.VERSION.SDK_INT);

        SensorManager sMgr;
        String temp;
        sMgr = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        if (sMgr.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null){
            temp = "Supported";
        }
        else {
            temp = "Not supported";
        }


        message += "\nThermometer is: " + temp.toString();

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Select Email client"));
    }
}
