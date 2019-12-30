package com.codingelab.tutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codingelab.tutorial.Local.DBHelper;
import com.codingelab.tutorial.Local.Update1ActivityLocal;
import com.codingelab.tutorial.Local.UpdateActivityLoca;

public class Update1ActivityPhp extends AppCompatActivity {
    EditText Name,Phone,Email;
    TextView ID;
    Syn syn;
    Button update1P;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update1_php);
        setTitle("Update Data Online");
        ID=(TextView) findViewById(R.id.TextIDUpP);
        Name=(EditText)findViewById(R.id.editTextNameUpP);
        Phone=(EditText)findViewById(R.id.editTextPhoneUpP);
        Email=(EditText)findViewById(R.id.editTextEmailUpP);
        update1P=(Button)findViewById(R.id.bttnUpdatePhp);
        this.syn=new Syn();
        final String IDD= String.valueOf(UpdateActivityPhp.upid);
        ID.setText(IDD);
        Name.setText(UpdateActivityPhp.upname);
        Phone.setText(UpdateActivityPhp.upphone);
        Email.setText(UpdateActivityPhp.upemail);

        update1P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=syn.doInBackground("update",IDD,Name.getText().toString(),Phone.getText().toString(),Email.getText().toString());
                Toast.makeText(getBaseContext(),msg, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
