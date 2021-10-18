package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class huilv2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huilv2);
        Button back=findViewById(R.id.back1);
        TextView tv=findViewById(R.id.textView3);
        Intent in=getIntent();
        SharedPreferences sp=getSharedPreferences("huilv2",huilv.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);
        String text=new String();
        tv.setText(sp.getString("title",text));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button huansuan=findViewById(R.id.huansuan1);
        huansuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText=findViewById(R.id.editTextTextPersonName3);
                float hl=Float.parseFloat(sp.getString("detail",text));
                hl=Float.parseFloat(editText.getText().toString())*(100/hl);
                editText.setText(String.valueOf(hl));
                Log.i("hl", "onClick: "+sp.getString("detail",text));
            }
        });
    }

}