package com.brankein13.mjm;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Service extends AppCompatActivity {

    private NumberPicker agePicker;
    private NumberPicker hourPicker;

    private Button Applybtn;

    private List<String> displayValues_age;
    private List<String> displayValues_hour;

    public int Sex;
    public int Age;
    public int Hour;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_page);

        agePicker = (NumberPicker) findViewById(R.id.picker_age);
        hourPicker = (NumberPicker) findViewById(R.id.picker_hour);

        displayValues_age = new ArrayList<String>();
        displayValues_hour = new ArrayList<String>();

        for (int i = 20; i < 61; i += 1){ displayValues_age.add(String.format("%d", i)); }
        for (int i = 0; i < 21; i += 1){ displayValues_hour.add(String.format("%d", i)); }

        agePicker.setDisplayedValues(displayValues_age.toArray(new String[0]));
        hourPicker.setDisplayedValues(displayValues_hour.toArray(new String[0]));

        agePicker.setWrapSelectorWheel(true);
        hourPicker.setWrapSelectorWheel(true);

        TextView version = (TextView) findViewById(R.id.version);
        version.setText(GetVersion());

        Applybtn = (Button) findViewById(R.id.applybtn);
        Applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Prompt_Animation_Start();

            }
        });
    }

    public void Male(){
        Sex = 0;
    }
    public void Female(){
        Sex = 1;
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent backpage = new Intent(Service.this, Content.class);
        startActivity(backpage);
    }

    public String GetVersion(){
        PackageInfo pinfo = null;
        try{
            pinfo = getPackageManager().getPackageInfo("com.brankein13.mjm", PackageManager.GET_META_DATA);
        }
        catch (Exception e){}
        return pinfo.versionName;
    }

    private void Prompt_Animation_Start(){

    }
}
