package com.codingelab.tutorial.Local;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codingelab.tutorial.R;

public class InsertActivityLocal extends AppCompatActivity {
    DBHelper mydb;
    EditText name,phone,email;
    Button insert,ShowLast;
    public static int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_local);
        setTitle("ADD Data In Local");
        mydb = new DBHelper(this);
        name =(EditText)findViewById(R.id.editTextNameL);
        phone =(EditText)findViewById(R.id.editTextPhoneL);
        email =(EditText)findViewById(R.id.editTextEmailL);
        insert =(Button)findViewById(R.id.bttnAddL);
        ShowLast =(Button)findViewById(R.id.bttnShowLastL);
        insert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String getName = name.getText().toString();
                String getPhone = phone.getText().toString();
                String getEmail = email.getText().toString();
                if (isEmpty(name)) {
                    name.setError("Enter Name");
                } else if (isEmpty(phone)) {
                    phone.setError("phone is requiard");
                } else if (isEmail(email) == false) {
                    email.setError("Enter Correct Email");
                } else {
                        if (mydb.insertContact(getName, getPhone, getEmail)) {
                            Log.v("georgeLog", "Successfully inserted record to db");
                            Toast.makeText(getApplicationContext(),
                                    "Inserted:" + getName + ", " + getPhone + "," + getEmail, Toast.LENGTH_SHORT).show();
                            name.setText("");
                            phone.setText("");
                            email.setText("");
                            //a=mydb.numberOfRows();
                        } else {
                            Toast.makeText(getApplicationContext(), "DID NOT insert to db :-(", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });
        ShowLast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("georgeLog", "clicked on fetch");
                Cursor getData=mydb.ShowLastRec(); //specific record (id=1)
                if (getData.moveToNext()) {// data?
                    Log.v("georgeLog", "data found in DB...");
                    String dName = getData.getString(getData.getColumnIndex("name"));
                    String dPhone = getData.getString(getData.getColumnIndex("phone"));
                    String dEmail = getData.getString(getData.getColumnIndex("email"));
                    Toast.makeText(getApplicationContext(),
                            "rec: " + dName + ", " + dPhone + ", " + dEmail, Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),
                            "did not get any data...:-(", Toast.LENGTH_LONG).show();
                getData.close();
            }
        });
    }

    boolean isEmail(EditText text){
        CharSequence email =text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}
