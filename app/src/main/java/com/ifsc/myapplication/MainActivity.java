package com.ifsc.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private XYPlot plot;
    private SensorManager sensorManager;
    private Sensor stepSensor;
    private List<Date> days;
    private List<Integer> steps;
    private XYSeries series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtém uma referência ao layout que contém o gráfico
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        LinearLayout chartLayout = findViewById(R.id.chartLayout);

        // Cria o objeto XYPlot
        plot = new XYPlot(this, "Contador de Passos");

        // Cria a lista de dados de dias e passos
        days = new ArrayList<>();
        steps = new ArrayList<>();

        // Configura o gerenciador do sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        // Cria a série XY
        series = new SimpleXYSeries(steps, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Passos");

        // Define as configurações da linha e ponto do gráfico
        LineAndPointFormatter seriesFormat = new LineAndPointFormatter(
                Color.BLUE,                           // cor da linha
                Color.RED,                            // cor dos pontos
                null,                                 // cor de preenchimento (não usado)
                null                                  // cor da borda (não usado)
        );

        // Adiciona a série formatada ao gráfico
        plot.addSeries(series, seriesFormat);

        // Define o formato do eixo X como datas
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(
                new SimpleDateFormat("dd/MM/yyyy"));

        // Adiciona o gráfico ao layout
        chartLayout.addView(plot);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Registra o SensorEventListener para o sensor de contador de passos
        sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Desregistra o SensorEventListener quando a atividade é pausada
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Método vazio, não utilizado neste exemplo
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            int stepCount = (int) event.values[0];

            // Obtém a data atual
            Date currentDate = new Date();

            days.add(currentDate);
            steps.add(stepCount);

            // Limita a quantidade de dados exibidos no gráfico
            if (days.size() > 10) {
                days.remove(0);
                steps.remove(0);
            }

            // Cria uma nova instância da série XY com os dados atualizados
            series = new SimpleXYSeries(steps, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Passos");

            // Remove a série antiga e adiciona a nova série ao gráfico
            plot.clear();
            plot.addSeries(series, new LineAndPointFormatter(Color.BLUE, Color.RED, null, null));

            // Redesenha o gráfico
            plot.redraw();
        }
    }
}