package com.brankein13.mjm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "172.30.1.2";
    private static String TAG = "Kaistian";

    private EditText mEditTextage;
    private EditText mEditTextflight_hour;

    public RadioGroup Radiogroup_Sex;
    public RadioGroup Radiogroup_Tem;
    public RadioGroup Radiogroup_RH;

    public String Sex;
    public String Age;
    public int Tem;
    public int RH;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_page_v2);

        TextView version = (TextView) findViewById(R.id.version);
        version.setText(GetVersion());


        mEditTextflight_hour = (EditText) findViewById(R.id.editText_main_flight_hour);

        Radiogroup_Sex = (RadioGroup) findViewById(R.id.radioGroup_Sex);
        Radiogroup_Tem = (RadioGroup) findViewById(R.id.radioGroup_Tem);
        Radiogroup_RH = (RadioGroup) findViewById(R.id.radioGroup_RH);


        Button buttonInsert = (Button) findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String flight_hour = mEditTextflight_hour.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/MJM_insert.php", Age, Sex, flight_hour, Integer.toString(Tem-22), Integer.toString(RH-40));


                mEditTextage.setText("");
                mEditTextflight_hour.setText("");

                Radiogroup_Tem.clearCheck();
                Radiogroup_RH.clearCheck();
                Radiogroup_Sex.clearCheck();

            }
        });

        Spinner spinner = (Spinner)findViewById(R.id.survey_page_age_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Age = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent backpage = new Intent(MainActivity.this, Content.class);
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

    public void Male(View view){
        Sex = "M";
    }
    public void Female(View view){
        Sex = "F";
    }

    public void Tem_1(View view){
        Tem = 2;
    }
    public void Tem_2(View view){
        Tem = 1;
    }
    public void Tem_3(View view){
        Tem = 0;
    }
    public void Tem_4(View view){
        Tem = -1;
    }
    public void Tem_5(View view){
        Tem = -2;
    }

    public void RH_1(View view){
        RH = -10;
    }
    public void RH_2(View view){
        RH = -5;
    }
    public void RH_3(View view){
        RH = 0;
    }
    public void RH_4(View view){
        RH = 5;
    }
    public void RH_5(View view){
        RH = 10;
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //progressDialog = ProgressDialog.show(MainActivity.this, "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {


            String age = (String) params[1];
            String sex = (String) params[2];
            String flight_hour = (String) params[3];
            String hope_tem = (String) params[4];
            String hope_rh = (String) params[5];

            String serverURL = (String) params[0];
            String postParameters = "age=" + age + "&sex=" + sex + "&flight_hour=" + flight_hour + "&hope_tem=" + hope_tem + "&hope_rh=" + hope_rh;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();

                while (responseStatusCode != HttpURLConnection.HTTP_OK) {
                    responseStatusCode = httpURLConnection.getResponseCode();
                    Log.d(TAG, "Response :::::::::::::::::::::" + responseStatusCode);

                }
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Insert Error: " + e.getMessage());
            }

        }
    }

}