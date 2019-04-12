package com.example.hellosensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {
    private Sensor mAccelerometer;
    private SensorManager mSensorManager;
    TextView acc_x;
    TextView acc_y;
    TextView acc_z;
    int displayValue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        acc_x = (TextView) findViewById(R.id.acc_x);
        acc_y = (TextView) findViewById(R.id.acc_y);
        acc_z = (TextView) findViewById(R.id.acc_z);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this, mAccelerometer);
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
        displayValue = Math.round(event.values[0]);
        acc_x.setText(String.valueOf(displayValue));
        displayValue = Math.round(event.values[1]);
        acc_y.setText(String.valueOf(displayValue));
        displayValue = Math.round(event.values[2]);
        acc_z.setText(String.valueOf(displayValue));
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Will not be implemented in this project.
    }
}
