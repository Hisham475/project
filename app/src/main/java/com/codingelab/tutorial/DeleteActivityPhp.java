package com.codingelab.tutorial;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.codingelab.tutorial.Local.DeleteActivityLocal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class DeleteActivityPhp extends AppCompatActivity {
    EditText id;
    Button delete;
    ListView listViewDP;
    private Syn syn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Delete Online");
        this.syn=new Syn();
        getJSON(syn.URL+"mysql_read.php");
        setContentView(R.layout.activity_delete_php);
        delete =(Button)findViewById(R.id.bttnDeleteP);
        id =(EditText)findViewById(R.id.editFindP);


        this.syn=new Syn();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEmpty(id)) {
                    id.setError("Enter Corrcet ID");
                }  else {
                    String msg=syn.doInBackground("delete",id.getText().toString());
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
                    id.setText("");
                    getJSON(syn.URL+"mysql_read.php");
                }
            }
        });
    }
        boolean isEmpty(EditText text){
            CharSequence str = text.getText().toString();
            return TextUtils.isEmpty(str);
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

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
    private void loadIntoListView(final String json) throws JSONException {
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
        }
        listViewDP=(ListView)findViewById(R.id.listViewDP);
        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_rows,new String[] { "id", "name", "phone", "email"},
                new int[] {R.id.idText, R.id.NameText, R.id.PhoneText, R.id.EmailText});
        listViewDP.setAdapter(myadapter);
        listViewDP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String sid = ((TextView)view.findViewById(R.id.idText)).getText().toString();

                AlertDialog.Builder alert = new AlertDialog.Builder(DeleteActivityPhp.this);
                alert.setTitle("Delete!");
                alert.setMessage("Do you Sure want delete ?");
                alert.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                syn.doInBackground("delete",sid);
                                getJSON(syn.URL+"mysql_read.php");
                            }
                        });

                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DeleteActivityPhp.this, "No", Toast.LENGTH_SHORT).show();
                            }
                        });
                alert.show();
            }
        });
    }
}
