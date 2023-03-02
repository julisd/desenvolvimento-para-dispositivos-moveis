package com.ifsc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int contador;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contador=0;
        textView=findViewById(R.id.textView);
        textView.setText(Integer.toString(contador));
    }

    public void contaClick(View view){
        contador++;
        textView.setText(Integer.toString(contador));


    }
}