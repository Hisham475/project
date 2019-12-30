package com.codingelab.tutorial;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.codingelab.tutorial.Local.DBHelper;
import com.codingelab.tutorial.Local.MainActivityLocal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    //Button ToPhp,ToLocal,Syn;
    DBHelper mydb;
    Syn syn;
    Cursor cursor;
    ImageView local,php,Syn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setTitle("Welcome");
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mydb = new DBHelper(this);
        syn=new Syn();

        local=(ImageView)findViewById(R.id.local);
        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(StartActivity.this, MainActivityLocal.class);
                startActivity(updateStudent);
            }
        });

        php=(ImageView)findViewById(R.id.php);
        php.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(updateStudent);
            }
        });

    }


    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), "finish SYN", Toast.LENGTH_SHORT).show();
                try {
                    loadData2(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {



                try {
                    URL url = new URL(urlWebService);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    StringBuilder sb = new StringBuilder();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;

                    while ((json = bufferedReader.readLine()) != null) {

                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    private void loadData(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] heroes = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            int ii=0;
            System.out.println("ii="+ii);
            System.out.println("i="+i);
            String nameS = "";
            System.out.println("+"+nameS+"+");
            JSONObject obj = jsonArray.getJSONObject(i);
            String nameP=obj.get("name").toString();
            System.out.println("namePHP = "+nameP);
            List<Student> data = mydb.getAllcontactDetails();
            for (Student val : data) {
                String nameL=val.getName();
                System.out.println("nameLocal = "+nameL);
                System.out.println("===============");
                if(nameP.equals(nameL)){
                    System.out.println(" yeah ");
                    System.out.println(nameL+" "+nameP);
                    ii=0;
                }else{
                    ii=1;
                    System.out.println("no"+ii);
                    nameS=nameL;
                }
                if(ii==1){
                    System.out.println("'"+nameS+"'");
                    syn.doInBackground("insert",nameS,"te","te");
                    nameS="";
                    System.out.println("");
                    ii=0;

                }
            }
            System.out.println("=/=/=/=/=/=/=");
        }

    }
    private void loadData1(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] heroes = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            int ii=0;
            System.out.println("ii="+ii);
            System.out.println("i="+i);
            String nameS = "";
            System.out.println("+"+nameS+"+");
            JSONObject obj = jsonArray.getJSONObject(i);
            String nameP=obj.get("name").toString();
            System.out.println("namePHP = "+nameP);
            cursor=mydb.getAllData();
            if(cursor.moveToFirst())
            {

                do
                {
                String nameL=cursor.getString(1);
                System.out.println("nameLocal = "+nameL);
                System.out.println("===============");
                if(nameP.equals(nameL)){
                    System.out.println(" yeah ");
                    System.out.println(nameL+" "+nameP);
                    ii=0;
                }else{
                    ii=1;
                    System.out.println("no"+ii);
                    nameS=nameL;
                }
                if(ii==1){
                    System.out.println("'"+nameS+"'");
                    syn.doInBackground("insert",nameS,"te","te");
                    nameS="";
                    System.out.println("");
                    ii=0;

                }
            }while (cursor.moveToNext());


            System.out.println("=/=/=/=/=/=/=");
        }

    }}
    private void loadData2(String json) throws JSONException {
        //SQLiteDatabase db = mydb.getReadableDatabase();
        JSONArray jsonArray = new JSONArray(json); int ii=1; String nameL=""; String nameP="";String phoneP="";String emailP="";
        //String[] heroes = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
             nameP=obj.get("name").toString();
             phoneP=obj.get("phone").toString();
            emailP=obj.get("email").toString();
            System.out.println("ii="+ii);
            System.out.println("namePHP = "+nameP);
            cursor=mydb.getAllData();

            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                     nameL=cursor.getString(1);
                    System.out.println("nameLocal = "+nameL);
                    System.out.println("===============");
                    if(nameP.equals(nameL)){
                        System.out.println(" yeah ");
                        System.out.println(nameL+" "+nameP);
                        ii=0;
                    }
                cursor.moveToNext();
            }
            if(ii==1){
                System.out.println("'"+nameL+"'");
                mydb.insertContact(nameP,phoneP,emailP);
                System.out.println("");
            }

                ii = 1;
                System.out.println("=/=/=/=/=/=/=");
        }

    }
    private void syntoonline(){
        syn.doInBackground("Truncate");
        cursor=mydb.getAllData();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);
            syn.doInBackground("insert",name,phone,email);
            cursor.moveToNext();
        }
    }
}
