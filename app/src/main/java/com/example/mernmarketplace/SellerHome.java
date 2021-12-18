package com.example.mernmarketplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mernmarketplace.conf.AppConstants;
import com.example.mernmarketplace.conf.AppUtils;

public class SellerHome extends AppCompatActivity {
    ImageView profileButton,logOutButton;
    Button createShop, myShops;
    String token, email, name;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);
        profileButton = findViewById(R.id.profileImage2);
        logOutButton = findViewById(R.id.logOutButton);
        token = getIntent().getStringExtra("token");
        email = getIntent().getStringExtra("email");
        name = getIntent().getStringExtra("name");

        createShop = findViewById(R.id.createButton);
        AppUtils.setUserTokenSharedPreference(SellerHome.this, AppConstants.token,token);
        myShops = findViewById(R.id.shopsButton);
        verifyStoragePermissions(SellerHome.this);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellerHome.this,LoginActivity.class);
                finish();
                startActivity(i);

            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellerHome.this, Profile.class);
                i.putExtra("token", token);
                i.putExtra("email", email);
                i.putExtra("name", name);
                i.putExtra("isSeller", true);
                startActivity(i);
            }
        });
        createShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellerHome.this, CreateShop.class);
                i.putExtra("token", token);
                startActivity(i);

            }
        });

        myShops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellerHome.this, ShopListActivity.class);
                i.putExtra("token", token);
                startActivity(i);
            }
        });

    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}