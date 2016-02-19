package com.LAW.Lift.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.LAW.Lift.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends Activity {

    String months;
    @Override
        protected void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splash);
            try {

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent splash = new Intent(SplashScreen.this, MainActivity.class);

                        startActivity(splash);
                        SplashScreen.this.finish();
                    }
                }, 2000);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            // Tracking the screen view

        }

        @Override
        protected void onPause() {
            super.onPause();
            finish();
        }
    }

