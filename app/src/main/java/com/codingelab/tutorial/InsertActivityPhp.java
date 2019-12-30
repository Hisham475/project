package com.codingelab.tutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivityPhp extends AppCompatActivity {
    EditText name,phone,email;
    Button insert;
    private Syn syn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_php);
        setTitle("ADD Data In Online");
        this.syn=new Syn();
        insert=(Button)findViewById(R.id.bttnAddP);
        name=(EditText)findViewById(R.id.editTextNameP);
        phone=(EditText)findViewById(R.id.editTextPhoneP);
        email=(EditText)findViewById(R.id.editTextEmailP);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(name)) {
                    name.setError("Enter Name");
                } else if (isEmpty(phone)) {
                    phone.setError("phone is requiard");
                } else if (isEmail(email) == false) {
                    email.setError("Enter Correct Email");
                } else {
                    String msg = syn.doInBackground("insert", name.getText().toString(), phone.getText().toString(), email.getText().toString());
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
                    name.setText("");
                    phone.setText("");
                    email.setText("");
                }
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
