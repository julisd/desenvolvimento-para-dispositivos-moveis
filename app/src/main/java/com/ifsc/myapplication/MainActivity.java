package com.ifsc.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Fragment fragment;
    Button buttonA, buttonB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonA.setOnClickListener(this::onClick);
        buttonB.setOnClickListener(this::onClick);
    }

    public void abreFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonA:
                fragment = new FragmentoA();
                break;
            case R.id.buttonB:
                fragment = new FragmentoB();
                break;
        }
        abreFragment();
    }
}