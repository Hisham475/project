package com.codingelab.tutorial.Local;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.codingelab.tutorial.R;
import com.codingelab.tutorial.Student;
import com.codingelab.tutorial.listViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdateActivityLoca extends AppCompatActivity {
    DBHelper mydb;
    public static int del;
    private ArrayList<Student> studentlist;
    ListView lview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_loca);
        setTitle("Update Data Local");
        mydb = new DBHelper(this);
        studentlist = new ArrayList<Student>();
        lview = (ListView) findViewById(R.id.listViewUpL);
        load();
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String sid = ((TextView)view.findViewById(R.id.idText)).getText().toString();
                String name = ((TextView)view.findViewById(R.id.NameText)).getText().toString();
                String phone = ((TextView)view.findViewById(R.id.PhoneText)).getText().toString();
                String email = ((TextView)view.findViewById(R.id.EmailText)).getText().toString();
                del = Integer.parseInt(sid);
                Intent searchStudent = new Intent(UpdateActivityLoca.this, Update1ActivityLocal.class);
                startActivity(searchStudent);
            }
        });
    }
    void load(){
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
        lview = (ListView) findViewById(R.id.listViewUpL);
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
