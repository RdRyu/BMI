package com.example.bmi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyThread2 extends Thread implements Runnable{
    Handler handler;

    public void setHandler(Handler handler){
        this.handler=handler;
    }

    public  void run() {
        ArrayList<HashMap<String, String>> listItems = new ArrayList<HashMap<String, String>>();
        Document document= null;
        try {
            Log.i("run", "run: ");
            document = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/").get();
            Elements tables=document.getElementsByTag("table");
            Element table= tables.get(1);
            Elements table1=table.select("tr");
            table1.remove(0);
            for (Element el:table1){
                HashMap<String,String> map=new HashMap<String,String>();
                Log.i("el", "run: "+el);
                map.put("item_tittle",el.getElementsByTag("td").get(0).text());
                map.put("item_1",el.getElementsByTag("td").get(5).text());
                listItems.add(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage();
        msg.obj=listItems;
        msg.what=1;
        handler.sendMessage(msg);
    }

}
