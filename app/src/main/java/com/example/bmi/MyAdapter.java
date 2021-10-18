package com.example.bmi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends ArrayAdapter {
    public MyAdapter(@NonNull Context context, int resource, ArrayList<HashMap<String, String>> itemlist) {
        super(context, resource,itemlist);
    }
    public View getView(int pos, View convertView, ViewGroup parent){
        View itemView=convertView;
        if(itemView==null){
            itemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Map<String,String> map= (Map<String, String>) getItem(pos);
        TextView tittle=itemView.findViewById(R.id.item_title);
        TextView item_1=itemView.findViewById(R.id.item_1);
        tittle.setText(map.get("item_tittle"));
        item_1.setText(map.get("item_1"));
        return itemView;
    }
}
