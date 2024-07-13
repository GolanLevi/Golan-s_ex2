package com.example.golans_ex2.Utilities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.golans_ex2.Interfaces.MoveCallBack;

public class MoveDetector {
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    private int moveCountX = 0;
    private int moveCountY = 0;
    private long timestamp = 0l;

    public int getMoveCountX() {
        return moveCountX;
    }

    public int getMoveCountY() {
        return moveCountY;
    }

    private MoveCallBack moveCallBack;

    public MoveDetector(Context context, MoveCallBack moveCallBack) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.moveCallBack = moveCallBack;
        initEventListener();
    }

    private void initEventListener() {
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                calculateMove(x, y);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

//    private void calculateMove(float x, float y) {
//        if(System.currentTimeMillis() - timestamp > 500){
//            timestamp = System.currentTimeMillis();
//            if (x > 6.0){
//               moveCountX++;
//               if (moveCallBack != null){
//                   moveCallBack.moveX();
//               }
//            }
//
//            if (y > 6.0){
//               moveCountY++;
//               if (moveCallBack != null){
//                   moveCallBack.moveY();
//               }
//            }
//        }
//    }

    private void calculateMove(float x, float y) {
        if (System.currentTimeMillis() - timestamp > 500) {
            timestamp = System.currentTimeMillis();
            if (moveCallBack != null) {
                moveCallBack.moveX(x);
                moveCallBack.moveY(y);
            }
        }
    }

    public void start(){
        sensorManager.registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    public void stop(){
        sensorManager.unregisterListener(
                sensorEventListener,
                sensor
        );
    }
}
