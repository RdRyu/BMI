package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Listactivity2 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity2);
        ListView lw=findViewById(R.id.mylist1);
        lw.setOnItemClickListener(this);
        @SuppressLint("HandlerLeak") Handler handler=new Handler(){
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==1){
                    ListView listView=findViewById(R.id.mylist1);
                    //List<String> list1 =new ArrayList<String>();
                    ArrayList<HashMap<String, String>> listItems = (ArrayList<HashMap<String, String>>) msg.obj;
                    MyAdapter myAdapter=new MyAdapter(Listactivity2.this,R.layout.list_item,listItems);
                    SimpleAdapter adapter=new SimpleAdapter(Listactivity2.this,listItems,R.layout.list_item,new String[]{"item_tittle","item_1"},new int[]{R.id.item_title,R.id.item_1});
                    listView.setAdapter(myAdapter);
                }
            }
        };
        MyThread2 myThread=new MyThread2();
        myThread.setHandler(handler);
        myThread.start();
       /*
        for (int i=0;i<10;i++){
            HashMap<String,String> map=new HashMap<String,String>();
            map.put("item_tittle","Rate"+i);
            map.put("item_1","Rate"+i);
            listItems.add(map);
        }
        //String []list_data={"1","2","3","4"};
        //ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list1);
        */
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ListView lw=findViewById(R.id.mylist1);
        Object itemAt=lw.getItemAtPosition(i);
        HashMap<String,String> map= (HashMap<String,String>) itemAt;
        String title=map.get("item_tittle");
        String detail=map.get("item_1");
        Log.i("click", "onItemClick: "+title+"\n"+detail);
        Intent in=new Intent(this,huilv2.class);
        SharedPreferences sp=getSharedPreferences("huilv2",Huansuan.MODE_PRIVATE);
        SharedPreferences.Editor ed1=sp.edit();
        ed1.putString("title",title);
        ed1.putString("detail",detail);
        ed1.commit();
        ed1.apply();
        startActivityForResult(in,1);
    }
}