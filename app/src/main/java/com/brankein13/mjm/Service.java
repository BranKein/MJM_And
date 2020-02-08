package com.brankein13.mjm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service extends AppCompatActivity {

    public Button Applybtn;

    public ListView AI_Console;
    public ArrayList<String> consoles;
    public ArrayAdapter adapter;

    public RadioButton m, f;
    public Spinner spinner_age, spinner_hour;

    public Classifier Temclassifier;
    private Classifier RHclassifier;
    public int numTreads = 4;

    public int Sex = -1;
    public int Age = -1;
    public int Hour = -1;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_page);

        m = (RadioButton) findViewById(R.id.service_page_male_btn);
        f = (RadioButton) findViewById(R.id.service_page_female_btn);
        m.setChecked(true);
        f.setChecked(false);
        Sex = 0;


        spinner_age = (Spinner) findViewById(R.id.service_page_age_spinner);
        spinner_age.setSelection(0);
        Age = 0;
        spinner_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                Age = Integer.parseInt(parent.getItemAtPosition(position).toString()) / 10;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner_hour = (Spinner) findViewById(R.id.service_page_hour_spinner);
        spinner_hour.setSelection(0);
        Hour = 0;
        spinner_hour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                Hour = Integer.parseInt(parent.getItemAtPosition(position).toString()) / 5;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        consoles = new ArrayList<String>();
        consoles.add(getString(R.string.console1));
        adapter = new ArrayAdapter(this, R.layout.ai_prompt, consoles);
        AI_Console = (ListView) findViewById(R.id.AIPrompt);
        AI_Console.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        Applybtn = (Button) findViewById(R.id.applybtn);
        Applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prompt_Animation_Start();
            }
        });
    }

    public void Male_service(View view){
        Sex = 0;
    }
    public void Female_service(View view){
        Sex = 1;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent backpage = new Intent(Service.this, Content.class);
        startActivity(backpage);
    }

    public void Prompt_Animation_Start() {
        if(Sex != -1 || Age != -1 || Hour != -1){

            int[] UserArr = new int[3];
            UserArr[0] = Sex;
            UserArr[1] = Age;
            UserArr[2] = Hour;

            Handler delayHandler = new Handler();

            consoles.add(getString(R.string.console2));
            adapter.notifyDataSetChanged();

            Temclassifier = CreateClassifier("Tem.tflite", Temclassifier, numTreads);
            final List<Classifier.Recognition> Tem_results = Temclassifier.Service(UserArr);
            RHclassifier = CreateClassifier("RH.tflite", RHclassifier, numTreads);
            final List<Classifier.Recognition> RH_results = RHclassifier.Service(UserArr);

            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    consoles.add(getString(R.string.console4));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);
                }
            }, 2000);

            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    consoles.add(getString(R.string.console3_1_1));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);

                    consoles.add(getString(R.string.console5_1));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);

                    consoles.add(" ");
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);
                }
            }, 3000);

            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    consoles.add(getString(R.string.console6_1_1));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_3));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_5));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_7));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_9));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_11));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_12));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_13));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);
                }
            }, 4000);


            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    consoles.add(" ");
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console3_1_2));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console7_1_1));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);
                }
            }, 4500);


            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    consoles.add(getString(R.string.console7_2_1));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);

                    consoles.add(" ");
                    adapter.notifyDataSetChanged();
                }
            }, 6000);



            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    consoles.add(getString(R.string.console3_2_1));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);

                    consoles.add(getString(R.string.console5_2));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);

                    consoles.add(" ");
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);
                }
            }, 7000);



            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    consoles.add(getString(R.string.console6_1_2));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_3));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_5));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_7));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_9));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_11));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_12));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console6_13));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);
                }
            }, 8000);


            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    consoles.add(" ");
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console3_2_2));
                    adapter.notifyDataSetChanged();

                    consoles.add(getString(R.string.console7_1_2));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);
                }
            }, 8500);




            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    consoles.add(getString(R.string.console7_2_2));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);

                    consoles.add(" ");
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);

                    consoles.add(getString(R.string.console8));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);

                    consoles.add(showResults(Tem_results, "Tem"));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);

                    consoles.add(showResults(RH_results, "RH"));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);

                    consoles.add(getString(R.string.console1));
                    adapter.notifyDataSetChanged();
                    AI_Console.setSelection(adapter.getCount() - 1);
                }
            }, 10000);


        }
    }

    private Classifier CreateClassifier(String str, Classifier _classifier, int numThreads){
        if(_classifier != null){
            _classifier.close();
            _classifier = null;
        }
        try{
            _classifier = Classifier.create(this, str, numThreads);

        } catch (IOException e){

        }
        return _classifier;
    }

    protected String showResults(List<Classifier.Recognition> results, String mode){

        int recom = 0;
        float recog = 0.0F;

        if(results != null){
            Classifier.Recognition recognition = results.get(0);
            if(recognition != null){
                if(recognition.getTitle() != null) {
                    recom = Integer.parseInt(recognition.getTitle());
                    recog = recognition.getConfidence();
                }
            }
        }

        if(mode == "Tem"){
            recom = recom + 25;
        }
        else{
            recom = recom * 5 + 50;
        }

        return String.format("%s_model: I recommend you %d with %.2f%% of confidence.",mode, recom, recog * 100);
    }
}
