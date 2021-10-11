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

import com.google.android.material.tabs.TabLayout;

public class huilv extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        setContentView(R.layout.activity_huilv);
        Button btn=findViewById(R.id.button);
        EditText ed1=findViewById(R.id.dollarhl);
        EditText ed2=findViewById(R.id.eurohl);
        EditText ed3=findViewById(R.id.wonhl);
        SharedPreferences sp=getSharedPreferences("huilv",huilv.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);
        float dollarhl=sp.getFloat("dollar",0.0f);
        float eurohl=sp.getFloat("euro",0.0f);
        float wonhl=sp.getFloat("won",0.0f);
        ed1.setText(String.valueOf(dollarhl));
        ed2.setText(String.valueOf(eurohl));
        ed3.setText(String.valueOf(wonhl));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=getIntent();
                if(!ed1.getText().toString().equals(""))
                    in.putExtra("dollarhl",ed1.getText().toString());
                if(!ed2.getText().toString().equals(""))
                    in.putExtra("eurohl",ed2.getText().toString());
                if(!ed3.getText().toString().equals(""))
                    in.putExtra("wonhl",ed3.getText().toString());
                setResult(2,in);
                finish();
            }
        });
    }
}