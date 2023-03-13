package com.ifsc.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrincipalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void chamaCalculadoraIMC(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("editTextPeso", 100.2);

        startActivity(intent);
    }

    public void abreSlideShow(View view) {
        Intent intent = new Intent(this, SlideShow.class);
        startActivity(intent);
    }
}