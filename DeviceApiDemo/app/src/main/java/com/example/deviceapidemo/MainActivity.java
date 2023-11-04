package com.example.deviceapidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSensors(View view) {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for ( Sensor s : sensors){
            Toast.makeText(this, s.getName(), Toast.LENGTH_SHORT).show();
        }

        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null){
            sensorManager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float x, y, z;
                    x = event.values[0];
                    y = event.values[1];
                    z = event.values[2];
                    TextView sensorTextView = findViewById(R.id.sensorTextView);
                    sensorTextView.setText(String.format("X: %.2f Y: %.2f Z: %.2f", x, y, z));

                }
                //8:00 rec
                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            }, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void startGPS(View view) {
    }
}