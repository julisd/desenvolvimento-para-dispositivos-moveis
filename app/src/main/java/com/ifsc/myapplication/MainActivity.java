package com.ifsc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextPeso, editTextAltura;
    TextView textViewResultado;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPeso=findViewById(R.id.edPeso);
        editTextAltura=findViewById(R.id.edAltura);
        imageView=findViewById(R.id.imageView);
        textViewResultado=findViewById(R.id.tvResultado);

    }

    public void calcularIMC(View v){
        double peso, altura, imc;

        peso=Double.parseDouble(editTextPeso.getText().toString());
        altura=Double.parseDouble(editTextAltura.getText().toString());

        imc = peso/(altura*altura);

        textViewResultado.setText(Double.toString(imc));

        if(imc >25){
            imageView.setImageResource(R.drawable.obesidade1);
        }


    }
}