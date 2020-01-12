package com.brankein13.mjm;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_page);

        TextView version = (TextView) findViewById(R.id.version);
        version.setText(GetVersion());

        TextView title = (TextView) findViewById(R.id.infotitle);
        TextView text = (TextView) findViewById(R.id.infotext);
        title.setText(R.string.infotab1title);
        text.setText(R.string.infotab1text);

        Button tab1_button = (Button) findViewById(R.id.infotab1);
        tab1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tabchange(1);
            }
        });

        Button tab2_button = (Button) findViewById(R.id.infotab2);
        tab2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tabchange(2);
            }
        });

        Button tab3_button = (Button) findViewById(R.id.infotab3);
        tab3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tabchange(3);
            }
        });

        Button tab4_button = (Button) findViewById(R.id.infotab4);
        tab4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tabchange(4);
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent backpage = new Intent(Info.this, Content.class);
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

    public void Tabchange(int tab){
        Button tab1_button = (Button) findViewById(R.id.infotab1);
        Button tab2_button = (Button) findViewById(R.id.infotab2);
        Button tab3_button = (Button) findViewById(R.id.infotab3);
        Button tab4_button = (Button) findViewById(R.id.infotab4);

        TextView title = (TextView) findViewById(R.id.infotitle);
        TextView text = (TextView) findViewById(R.id.infotext);

        ScrollView view = (ScrollView) findViewById(R.id.infotab);

        switch(tab){
            case 1:
                title.setText(R.string.infotab1title);
                text.setText(R.string.infotab1text);
                title.setTextColor(Color.parseColor("#FFFFFF"));
                text.setTextColor(Color.parseColor("#FFFFFF"));
                tab1_button.setBackground(this.getResources().getDrawable(R.drawable.tab11));
                tab2_button.setBackground(this.getResources().getDrawable(R.drawable.tab22));
                tab3_button.setBackground(this.getResources().getDrawable(R.drawable.tab32));
                tab4_button.setBackground(this.getResources().getDrawable(R.drawable.tab42));
                view.setBackgroundColor(Color.parseColor("#464646"));
                break;
            case 2:
                title.setText(R.string.infotab2title);
                text.setText(R.string.infotab2text);
                title.setTextColor(Color.parseColor("#FFFFFF"));
                text.setTextColor(Color.parseColor("#FFFFFF"));
                tab1_button.setBackground(this.getResources().getDrawable(R.drawable.tab12));
                tab2_button.setBackground(this.getResources().getDrawable(R.drawable.tab21));
                tab3_button.setBackground(this.getResources().getDrawable(R.drawable.tab32));
                tab4_button.setBackground(this.getResources().getDrawable(R.drawable.tab42));
                view.setBackgroundColor(Color.parseColor("#5e5e5e"));
                break;
            case 3:
                title.setText(R.string.infotab3title);
                text.setText(R.string.infotab3text);
                title.setTextColor(Color.parseColor("#000000"));
                text.setTextColor(Color.parseColor("#000000"));
                tab1_button.setBackground(this.getResources().getDrawable(R.drawable.tab12));
                tab2_button.setBackground(this.getResources().getDrawable(R.drawable.tab23));
                tab3_button.setBackground(this.getResources().getDrawable(R.drawable.tab31));
                tab4_button.setBackground(this.getResources().getDrawable(R.drawable.tab42));
                view.setBackgroundColor(Color.parseColor("#878787"));
                break;
            case 4:
                title.setText(R.string.infotab4title);
                text.setText(R.string.infotab4text);
                title.setTextColor(Color.parseColor("#000000"));
                text.setTextColor(Color.parseColor("#000000"));
                tab1_button.setBackground(this.getResources().getDrawable(R.drawable.tab12));
                tab2_button.setBackground(this.getResources().getDrawable(R.drawable.tab23));
                tab3_button.setBackground(this.getResources().getDrawable(R.drawable.tab33));
                tab4_button.setBackground(this.getResources().getDrawable(R.drawable.tab41));
                view.setBackgroundColor(Color.parseColor("#bababa"));
                break;
        }

    }

}
