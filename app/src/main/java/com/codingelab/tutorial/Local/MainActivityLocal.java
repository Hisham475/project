package com.codingelab.tutorial.Local;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.codingelab.tutorial.R;
import com.codingelab.tutorial.StartActivity;
import com.codingelab.tutorial.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivityLocal extends AppCompatActivity {
    DBHelper mydb;
    Button ToAddL,ToUpdateL,ToDeleteL,ToSearchL;
    public static int del;
    ListView lview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_local);
        setTitle("Local Database");
        mydb = new DBHelper(this);reload();
        lview = (ListView) findViewById(R.id.listViewL);
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
        lview = (ListView) findViewById(R.id.listViewL);
        List<Student> data = mydb.getAllcontactDetails();
        for (Student val :data)
        {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", String.valueOf(val.getId()));
            map.put("name", val.getName());
            map.put("phone", val.getPhone());
            map.put("email", val.getEmail());
            Items.add(map);
        }

        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_rows,new String[] { "id", "name", "phone", "email"},
                new int[] {R.id.idText, R.id.NameText, R.id.PhoneText, R.id.EmailText});

        lview.setAdapter(myadapter);
        ToAddL =(Button)findViewById(R.id.bttnToAddL);
        ToUpdateL =(Button)findViewById(R.id.bttnToUpdateL);
        ToDeleteL =(Button)findViewById(R.id.bttnToDeleteL);
        ToSearchL =(Button)findViewById(R.id.bttnToSearchL);
        ToAddL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityLocal.this, InsertActivityLocal.class);
                startActivity(updateStudent);
            }
        });

        ToUpdateL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityLocal.this, UpdateActivityLoca.class);
                startActivity(updateStudent);
            }
        });

        ToDeleteL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityLocal.this, DeleteActivityLocal.class);
                startActivity(updateStudent);
            }
        });

        ToSearchL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityLocal.this, SearchActivityLocal.class);
                startActivity(updateStudent);
            }
        });
        reload();
    }
    void reload(){
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
        lview = (ListView) findViewById(R.id.listViewL);
        List<Student> data = mydb.getAllcontactDetails();
        for (Student val :data)
        {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", String.valueOf(val.getId()));
            map.put("name", val.getName());
            map.put("phone", val.getPhone());
            map.put("email", val.getEmail());
            Items.add(map);
        }

        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_rows,new String[] { "id", "name", "phone", "email"},
                new int[] {R.id.idText, R.id.NameText, R.id.PhoneText, R.id.EmailText});

        lview.setAdapter(myadapter);
    }
}
