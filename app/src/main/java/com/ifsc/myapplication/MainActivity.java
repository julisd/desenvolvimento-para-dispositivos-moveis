package com.ifsc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

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
        editTextPeso.setText(getIntent().getExtras().getString("editTextPeso"));
    }

    public void calcularIMC(View v){
        double peso, altura, imc;

        peso=Double.parseDouble(editTextPeso.getText().toString());
        altura=Double.parseDouble(editTextAltura.getText().toString());

        imc = peso/(altura*altura);

        textViewResultado.setText(Double.toString(imc));


        if(imc >= 16 && imc <= 18.49 )
            imageView.setImageResource(R.drawable.abaixopeso);
        if (imc >= 18.5 && imc <= 24.9)
            imageView.setImageResource(R.drawable.normal);
        else if (imc >= 25 && imc <= 29.9)
            imageView.setImageResource(R.drawable.sobrepeso);
        else if (imc >= 30 && imc <= 34.9)
            imageView.setImageResource(R.drawable.obesidade1);
        else if (imc >= 35 && imc <= 39.9)
            imageView.setImageResource(R.drawable.obesidade2);
        else if (imc >= 40)
            imageView.setImageResource(R.drawable.obesidade3);

    }
}