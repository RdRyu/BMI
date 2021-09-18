package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=findViewById(R.id.button9);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input_weight = findViewById(R.id.editTextTextPersonName);
                EditText input_height = findViewById(R.id.editTextTextPersonName2);
                if (!input_height.getText().toString().equals("")&&!input_weight.getText().toString().equals("")){
                    double weight=Double.parseDouble(input_weight.getText().toString());
                    double height=Double.parseDouble(input_height.getText().toString());
                    if(weight>=1000){weight=weight/1000;}
                    if(height>=100){height=height/100;}
                    double bmi=weight/(height*height);
                    String output=null;
                    String sbmi=String.valueOf(bmi);
                    sbmi=String.format("%.2f",bmi);
                    bmi=Double.parseDouble(sbmi);
                    if(bmi<18.5){
                        output="bmi为"+bmi+"\n体重过轻";
                    }
                    else if(bmi<25&&bmi>=18.5){
                        output="bmi为"+bmi+"\n体重正常";
                    }
                    else{
                        output="bmi为"+bmi+"\n体重超重";
                    }
                    TextView tv=findViewById(R.id.textView2);
                    tv.setText(output);
                }
                else{
                    Toast.makeText(MainActivity.this,"请完整输入",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}