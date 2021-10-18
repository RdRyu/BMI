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
import java.util.List;

public class MyThread extends Thread implements Runnable{
    Handler handler;

    public void setHandler(Handler handler){
        this.handler=handler;
    }

    public  void run() {
        List<String> list=new ArrayList<String>();
        Document document= null;
        try {
            Log.i("run", "run: ");
            document = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/").get();
            Elements tables=document.getElementsByTag("table");
            Element table= tables.get(1);
            String line="";

            Elements table1=table.select("tr");
            table1.remove(0);
            for (Element el:table1){
                Log.i("el", "run: "+el);
                line+=el.getElementsByTag("td").get(0).text();
                line+="==================";
                line+=100/Float.parseFloat(el.select("td").get(5).text());
                list.add(line);
                Log.i("run", "run: "+line);
                line="";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage();
        msg.obj=list;
        msg.what=1;
        handler.sendMessage(msg);
    }

}
