package com.brankein13.mjm;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class InfoUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infous_page);

        TextView URL = (TextView) findViewById(R.id.airporturl);
        URL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent urlintent = new Intent(Intent.ACTION_VIEW);
                urlintent.setData(Uri.parse("https://www.airport.kr/ap/ko/index.do"));
                startActivity(urlintent);
            }
        });

        TextView version = (TextView) findViewById(R.id.version);
        version.setText(GetVersion());
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent backpage = new Intent(InfoUs.this, Content.class);
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
}
