package com.brankein13.mjm;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Content extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_page);

        Button info_button = (Button) findViewById(R.id.button_About);
        info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infointent = new Intent(Content.this, Info.class);
                startActivity(infointent);
            }
        });

        Button service_button = (Button) findViewById(R.id.button_Service);
        service_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceintent = new Intent(Content.this, Service.class);
                startActivity(serviceintent);
            }
        });

        Button feedback_button = (Button) findViewById(R.id.button_Feedback);
        feedback_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent surveyintent = new Intent(Content.this, MainActivity.class);
                startActivity(surveyintent);
            }
        });

        Button Us_button = (Button) findViewById(R.id.button_Us);
        Us_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Usintent = new Intent(Content.this, InfoUs.class);
                startActivity(Usintent);
            }
        });
    }

    long first_time;
    long second_time;
    @Override
    public void onBackPressed(){
        second_time = System.currentTimeMillis();
        Toast.makeText(Content.this, "App will be closed if you press one more time.", Toast.LENGTH_SHORT).show();
        if(second_time - first_time < 2000){
            super.onBackPressed();
            finishAffinity();
        }
        first_time = System.currentTimeMillis();
    }

}

