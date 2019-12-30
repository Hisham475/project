package com.codingelab.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {
    private Button bttnToAddP,bttnToUpP,bttnToDelP,bttnToSrchP;
    private Syn syn;
    private ListView listViewP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Online Database");
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        this.syn=new Syn();
        listViewP=(ListView)findViewById(R.id.listViewP);
        getJSON(syn.URL+"mysql_read.php");

        this.bttnToAddP=(Button)findViewById(R.id.bttnToAddP);
        bttnToAddP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivity.this, InsertActivityPhp.class);
                startActivity(updateStudent);
            }
        });

        this.bttnToUpP=(Button)findViewById(R.id.bttnToUpdateP);
        bttnToUpP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivity.this, UpdateActivityPhp.class);
                startActivity(updateStudent);
            }
        });

        this.bttnToDelP=(Button)findViewById(R.id.bttnToDeleteP);
        bttnToDelP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivity.this, DeleteActivityPhp.class);
                startActivity(updateStudent);
            }
        });

        this.bttnToSrchP=(Button)findViewById(R.id.bttnToSearchP);
        bttnToSrchP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivity.this, SearchActivityPhp.class);
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
                try {
                    loadIntoListView(s);
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
    ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
    ArrayList<String> test = new ArrayList<String>();
    private void loadIntoListView(String json) throws JSONException {
        Items.clear();
        JSONArray jsonArray = new JSONArray(json);
        String[] heroes = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            JSONObject obj = jsonArray.getJSONObject(i);
            String idd = heroes[i] = obj.getString("id");
            map.put("id",idd);
            String name = heroes[i] = obj.getString("name");
            map.put("name",name);
            String phone = heroes[i] = obj.getString("phone");
            map.put("phone",phone);
            String email = heroes[i] = obj.getString("email");
            map.put("email",email);
            Items.add(map);
            test.add(map.toString());
        }
        System.out.println(test);

        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_rows,new String[] { "id", "name", "phone", "email"},
                new int[] {R.id.idText, R.id.NameText, R.id.PhoneText, R.id.EmailText});
        listViewP.setAdapter(myadapter);


    }
}

