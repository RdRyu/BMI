package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class MyListActivity extends ListActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Handler handler=new Handler(){
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==1){
                    ArrayList<String> list=(ArrayList<String>) msg.obj;
                    Log.i("taglist", "handleMessage: "+list.get(0));
                    ListAdapter adapter=new ArrayAdapter<String>(MyListActivity.this,android.R.layout.simple_expandable_list_item_1,list);
                    setListAdapter(adapter);
                }
            }
        };
        MyThread myThread=new MyThread();
        myThread.setHandler(handler);
        myThread.start();

        List<String> list1 =new ArrayList<String>();
        for (int i=0;i<100;i++){
            list1.add("item"+i);
        }
        String []list_data={"1","2","3","4"};
        //ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list1);
        //setListAdapter(adapter);
    }



}
