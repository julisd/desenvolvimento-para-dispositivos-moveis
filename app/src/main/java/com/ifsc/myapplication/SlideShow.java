package com.ifsc.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class SlideShow extends AppCompatActivity {
    ImageView imageView;
    Integer[] imagens = {R.drawable.abaixopeso, R.drawable.normal, R.drawable.obesidade1};
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);
        imageView = findViewById(R.id.ivSlide);
        position = 0;
    }

    public void last(View view) {
        position--;
        imageView.setImageResource(imagens[position]);
    }
    public void home(View view) {
        position=0;
        imageView.setImageResource(imagens[position]);
    }
    public void next(View view) {
        position++;
        imageView.setImageResource(imagens[position]);
    }
}
