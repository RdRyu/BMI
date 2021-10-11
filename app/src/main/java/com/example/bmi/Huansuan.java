package com.example.bmi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.DatagramSocketImpl;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.security.auth.login.LoginException;

public class Huansuan extends AppCompatActivity implements Runnable {
    float dollarhl=2;
    float eurohl=3;
    float wonhl=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huansuan);
        Button btn1 = findViewById(R.id.dollar);
        Button btn2 = findViewById(R.id.euro);
        Button btn3 = findViewById(R.id.won);
        EditText ed=findViewById(R.id.rmb);





        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ed.getText().toString().equals("")){
                    float f=Float.parseFloat(ed.getText().toString());
                    String out=String.valueOf((f*dollarhl));
                    TextView t=findViewById(R.id.huansuan);
                    t.setText(out);
                }
                else{
                    Toast.makeText(Huansuan.this,"Input completely",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ed.getText().toString().equals("")){
                    float f=Float.parseFloat(ed.getText().toString());
                    String out=String.valueOf((f* eurohl));
                    TextView t=findViewById(R.id.huansuan);
                    t.setText(out);
                }
                else{
                    Toast.makeText(Huansuan.this,"Input completely",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ed.getText().toString().equals("")){
                    float f=Float.parseFloat(ed.getText().toString());
                    String out=String.valueOf((f* wonhl));
                    TextView t=findViewById(R.id.huansuan);
                    t.setText(out);
                }
                else{
                    Toast.makeText(Huansuan.this,"Input completely",Toast.LENGTH_SHORT).show();
                }
            }
        });

        SharedPreferences sp=getSharedPreferences("update_2",MODE_PRIVATE);
        SharedPreferences.Editor uped=sp.edit();
        int state=sp.getInt("state", 0);
        long lastupdate=sp.getLong("time",0);
        Log.i("state", "onCreate: "+state);
        long nd = 1000 * 24 * 60 * 60;
        //获取当前时间
        long nowtime=System.currentTimeMillis();
        if(state==0){
            uped.putInt("state",1);
            uped.putLong("time",nowtime);
            uped.commit();
            Thread t=new Thread(this);
            t.start();
            Log.i("success", "onCreate: ");
        }
        else if(state==1){
            long uptime=sp.getLong("time",0);
            long diff=nowtime-uptime;
            if(diff/nd>1){
                uped.putLong("time",nowtime);
                uped.commit();
                Thread t=new Thread(this);
                t.start();
            }
        }

    }
    public  void fun(View v){
        Intent in=new Intent(this,huilv.class);
        SharedPreferences sp=getSharedPreferences("huilv",Huansuan.MODE_PRIVATE);
        SharedPreferences.Editor ed1=sp.edit();
        ed1.putFloat("dollar",dollarhl);
        ed1.putFloat("euro",eurohl);
        ed1.putFloat("won",wonhl);
        ed1.apply();
        startActivityForResult(in,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==2){
            dollarhl=Float.parseFloat(data.getStringExtra("dollarhl"));
            eurohl=Float.parseFloat(data.getStringExtra("eurohl"));
            wonhl=Float.parseFloat(data.getStringExtra("wonhl"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void run() {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        Log.i("tag", "run: running");
        try{
            URL url = new URL("https://usd-cny.com/");
            connection = (HttpURLConnection) url.openConnection();//打开openConnection链接
            connection.setRequestMethod("GET");//以GET方式发起请求（默认）
            InputStream inputStream = connection.getInputStream();//获得数据流
            String htmlcontent=inputStream2String(inputStream);
            //Log.i("taghttp", "run: "+htmlcontent);
            int index=0;
            index=htmlcontent.indexOf(">美元</a></td><td>");
            //Log.i("dollar", "run: "+htmlcontent.substring(index+16,index+21));
            /*
            dollarhl=100/Float.parseFloat(htmlcontent.substring(index+16,index+21));
            index=htmlcontent.indexOf(">欧元</a></td><td>");
            eurohl=100/Float.parseFloat(htmlcontent.substring(index+16,index+21));
            index=htmlcontent.indexOf(">韩币</a></td><td>");
            wonhl=100/Float.parseFloat(htmlcontent.substring(index+16,index+21));
            Log.i("won", "run: "+htmlcontent.substring(index+16,index+21));
            */
            Document document= Jsoup.connect("https://usd-cny.com/").get();
            Elements tables=document.select("table");
            Element table= tables.get(0);
            //Log.i("jsoup", "run: "+table);
            for (Element el:table.select("tr")){
                if(el.select("a").text().equals("美元")){
                    Log.i("tagtd", "run: "+el.select("td").get(1).text());
                    dollarhl=100/Float.parseFloat(el.select("td").get(1).text());
                };
                if(el.select("a").text().equals("欧元")){
                    Log.i("tagtd", "run: "+el.select("td").get(1).text());
                    eurohl=100/Float.parseFloat(el.select("td").get(1).text());
                };if(el.select("a").text().equals("韩币")){
                    Log.i("tagtd", "run: "+el.select("td").get(1).text());
                    wonhl=100/Float.parseFloat(el.select("td").get(1).text());
                };
            }


        }catch (Exception e){e.printStackTrace();}
        finally {
            if (reader != null){
                try{reader.close();}catch (Exception e){e.printStackTrace();}
            }
            if(connection != null){
                connection.disconnect();
            }
        }
    }
    private String inputStream2String(InputStream inputStream)throws IOException {
        final  int bufferSize=1024;
        final char[] buffer=new char[bufferSize];
        final StringBuilder out=new StringBuilder();
        Reader in=new InputStreamReader(inputStream,"gb2312");
        while(true){
            int rsz=in.read(buffer,0,buffer.length);
            if(rsz<0){
                break;
            }
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }
}