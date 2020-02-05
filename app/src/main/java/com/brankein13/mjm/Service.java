package com.brankein13.mjm;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service extends AppCompatActivity {

    public NumberPicker agePicker, hourPicker;

    public Button Applybtn;

    public List<String> displayValues_age, displayValues_hour;

    public ListView AI_Console;
    public ArrayList<String> consoles;
    public ArrayAdapter adapter;

    public int Sex = -1;
    public int Age, Hour;

    private Classifier Temclassifier, RHclassifier;
    public int numTreads = 2;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_page);

        agePicker = (NumberPicker) findViewById(R.id.picker_age);
        hourPicker = (NumberPicker) findViewById(R.id.picker_hour);

        displayValues_age = new ArrayList<String>();
        displayValues_hour = new ArrayList<String>();

        agePicker.setMaxValue(4);
        agePicker.setMinValue(0);
        hourPicker.setMaxValue(3);
        hourPicker.setMinValue(0);

        //for (int i = 0; i < 5; i += 1){ displayValues_age.add(String.format("%d ~ %d", i * 10 + 20, i * 10 + 30)); }
        //for (int i = 0; i < 4; i += 1){ displayValues_hour.add(String.format("%d ~ %d (hour)", i * 5, i * 5 + 5)); }

        //agePicker.setDisplayedValues(displayValues_age.toArray(new String[0]));
        //hourPicker.setDisplayedValues(displayValues_hour.toArray(new String[0]));

        agePicker.setWrapSelectorWheel(false);
        hourPicker.setWrapSelectorWheel(false);

        TextView version = (TextView) findViewById(R.id.version);
        version.setText(GetVersion());

        AI_Console = (ListView) findViewById(R.id.AIPrompt);
        consoles = new ArrayList<String>();
        consoles.add(getString(R.string.console1));
        adapter = new ArrayAdapter(this, R.layout.ai_prompt, consoles);

        Applybtn = (Button) findViewById(R.id.applybtn);
        Applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Age = agePicker.getValue();
                Hour = hourPicker.getValue();
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

    public String GetVersion(){
        PackageInfo pinfo = null;
        try{
            pinfo = getPackageManager().getPackageInfo("com.brankein13.mjm", PackageManager.GET_META_DATA);
        }
        catch (Exception e){}
        return pinfo.versionName;
    }

    private void Prompt_Animation_Start() {
        consoles.add(getString(R.string.console2));
        adapter.notifyDataSetChanged();

        if(Sex == -1){
            consoles.add("Error!");
            adapter.notifyDataSetChanged();
            return;
        }

        int[] UserArr = new int[3];
        UserArr[0] = Sex;
        UserArr[1] = Age;
        UserArr[2] = Hour;

        consoles.add(getString(R.string.console4));
        adapter.notifyDataSetChanged();

        if(Temclassifier == null){
            consoles.add(getString(R.string.console3_1_1));
            adapter.notifyDataSetChanged();
            Temclassifier = CreateClassifier("Tem.tflite", Temclassifier, numTreads);

            consoles.add(getString(R.string.console5_1));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_1_1));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_2));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_3));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_4));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_5));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_6));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_7));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_8));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_9));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_10));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_11));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_12));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_13));
            adapter.notifyDataSetChanged();
        }

        consoles.add(" ");
        adapter.notifyDataSetChanged();

        consoles.add(getString(R.string.console3_1_2));
        adapter.notifyDataSetChanged();

        consoles.add(getString(R.string.console7_1_1));
        adapter.notifyDataSetChanged();
        List<Classifier.Recognition> Tem_results = Temclassifier.Service(UserArr);

        consoles.add(getString(R.string.console7_2_1));
        adapter.notifyDataSetChanged();

        consoles.add(" ");
        adapter.notifyDataSetChanged();

        if(RHclassifier == null) {
            consoles.add(getString(R.string.console3_2_1));
            adapter.notifyDataSetChanged();
            RHclassifier = CreateClassifier("RH.tflite", RHclassifier, numTreads);

            consoles.add(getString(R.string.console5_2));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_1_2));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_2));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_3));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_4));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_5));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_6));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_7));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_8));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_9));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_10));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_11));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_12));
            adapter.notifyDataSetChanged();

            consoles.add(getString(R.string.console6_13));
            adapter.notifyDataSetChanged();
        }

        consoles.add(" ");
        adapter.notifyDataSetChanged();

        consoles.add(getString(R.string.console3_2_2));
        adapter.notifyDataSetChanged();

        consoles.add(getString(R.string.console7_1_2));
        adapter.notifyDataSetChanged();
        List<Classifier.Recognition> RH_results = RHclassifier.Service(UserArr);

        consoles.add(getString(R.string.console7_2_2));
        adapter.notifyDataSetChanged();

        consoles.add(getString(R.string.console8));
        adapter.notifyDataSetChanged();

        consoles.add(showResults(Tem_results, "Tem"));
        adapter.notifyDataSetChanged();

        consoles.add(showResults(RH_results, "RH"));
        adapter.notifyDataSetChanged();

        consoles.add(getString(R.string.console1));
        adapter.notifyDataSetChanged();
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
