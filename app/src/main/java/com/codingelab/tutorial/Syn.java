package com.codingelab.tutorial;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by xbcis on 22/05/16.
 */
public class Syn extends AsyncTask<String,Void,String> {
    public String URL="http://192.168.60.90/sqli/";
    public String phpPageULR=URL+"mysql_write.php";

    @Override
    protected String doInBackground(String ... params) {
        if(params.length>0){
            if(params[0].equalsIgnoreCase("syn")){
                onSyn();
            }else if(params[0].equalsIgnoreCase("insert")){
               return onInsert(params);
            }else if(params[0].equalsIgnoreCase("delete")){
                return onDelete(params);
            }else if(params[0].equalsIgnoreCase("update")){
                return onUpdate(params);
            }else if(params[0].equalsIgnoreCase("Truncate")){
                return onTruncate(params);
            }
        }
        return null;
    }
    private String onInsert(String ... params){
        try {
            URL url=new URL(phpPageULR);
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            OutputStream subChannel=channel.getOutputStream();
            OutputStreamWriter pen=new OutputStreamWriter(subChannel,"UTF-8");
            BufferedWriter student =new BufferedWriter(pen);
            String name = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String phone = URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
            String email = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            String information=name+"&"+phone+"&"+email;
            student.write(information);
            student.flush();
            student.close();
            System.out.println(params[1]);
            System.out.println(params[2]);
            System.out.println(params[3]);
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Insert data to MySQL";
    }
    private String onDelete(String ... params){
        try {
            URL url=new URL(phpPageULR);
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            OutputStream subChannel=channel.getOutputStream();
            OutputStreamWriter pen=new OutputStreamWriter(subChannel,"UTF-8");
            BufferedWriter student =new BufferedWriter(pen);
            String delete = URLEncoder.encode("delete", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String information=delete;
            student.write(information);
            student.flush();
            student.close();
            System.out.println("--ID-->"+params[1]);
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Delete data from MySQL";
    }
    private String onUpdate(String ... params){
        try {
            URL url=new URL(phpPageULR);
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            OutputStream subChannel=channel.getOutputStream();
            OutputStreamWriter pen=new OutputStreamWriter(subChannel,"UTF-8");
            BufferedWriter student =new BufferedWriter(pen);
            String id = URLEncoder.encode("upid", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String name = URLEncoder.encode("upname", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
            String phone = URLEncoder.encode("upphone", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            String email = URLEncoder.encode("upemail", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8");
            String information=id+"&"+name+"&"+phone+"&"+email;
            student.write(information);
            student.flush();
            student.close();
            System.out.println(params[1]);
            System.out.println(params[2]);
            System.out.println(params[3]);
            System.out.println(params[4]);
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Update data to MySQL";
    }
    private String onTruncate(String ... params){
        try {
            URL url=new URL(URL+"mysql_truncate.php");
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            channel.setDoOutput(true);
            OutputStream subChannel=channel.getOutputStream();
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Update data to MySQL";
    }

    private void onSyn(){
        System.out.println(" INSERTING DATA insertin data");

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}

