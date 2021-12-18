package com.example.mernmarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LaunchActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(LaunchActivity.this, LoginActivity.class);
                LaunchActivity.this.startActivity(mainIntent);
                LaunchActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}