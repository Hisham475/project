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
    DBHelper mydb;
    Cursor cursor;
    ImageView local,php;
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
}
