package com.ifsc.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private GraphView graphView;
    private BarGraphSeries<DataPoint> series;
    private SensorManager sensorManager;
    private Sensor stepSensor;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphView = findViewById(R.id.graphView);

        series = new BarGraphSeries<>();
        graphView.addSeries(series);

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // Formatação da data
                    calendar.setTimeInMillis((long) value);
                    Date date = calendar.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM", Locale.getDefault());
                    return sdf.format(date);
                } else {
                    // Formatação da quantidade de passos
                    return super.formatLabel(value, isValueX);
                }
            }
        });

        graphView.getGridLabelRenderer().setNumHorizontalLabels(3); // Número de dias no eixo X
        graphView.getGridLabelRenderer().setNumVerticalLabels(4); // Número de valores no eixo Y

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                if (data.getY() >= 30) { // Limite de 30 passos
                    return Color.BLUE;
                } else {
                    return Color.GRAY;
                }
            }
        });

        series.setSpacing(50); // Espaçamento entre as barras do gráfico

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }

        if (stepSensor == null) {
            Toast.makeText(this, "Sensor de contador de passos não encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (stepSensor != null) {
            sensorManager.unregisterListener(this, stepSensor);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int steps = (int) event.values[0];
        updateGraph(steps);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void updateGraph(int steps) {
        DataPoint[] dataPoints = new DataPoint[3];
        calendar = Calendar.getInstance();

        for (int i = 0; i < 3; i++) {
            if (i != 0) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            Date date = calendar.getTime();
            DataPoint dataPoint = new DataPoint(date.getTime(), steps);
            dataPoints[i] = dataPoint;

            // Incrementa a quantidade de passos a cada 10
            steps += 10;
        }

        series.resetData(dataPoints);

        // Define a escala do eixo Y
        graphView.getViewport().setMinY(10); // Valor mínimo do eixo Y
        graphView.getViewport().setMaxY(40); // Valor máximo do eixo Y

        // Atualiza o gráfico
        graphView.onDataChanged(true, true);
    }

}
