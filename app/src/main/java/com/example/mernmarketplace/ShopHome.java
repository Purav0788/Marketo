package com.example.mernmarketplace;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.mernmarketplace.conf.AppConstants;
import com.example.mernmarketplace.conf.AppUtils;
import com.example.mernmarketplace.models.Shop;
import com.squareup.picasso.Picasso;

public class ShopHome extends AppCompatActivity {
   public  ImageView homeButton,shopImg;
    Button createProduct, myProducts;
    TextView nameText;
    private Context context;
    String token, email, name,id,pic;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        homeButton = findViewById(R.id.profileImage2);
        shopImg = findViewById(R.id.shopImg);
       nameText = findViewById(R.id.textView5);
       context = this;
       pic = getIntent().getStringExtra("img");
        Picasso.with(this).load(pic).into(shopImg);
        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");
        nameText.setText(name);
        createProduct = findViewById(R.id.createButton);
        //AppUtils.setUserTokenSharedPreference(ShopHome.this, AppConstants.token,token);
        myProducts = findViewById(R.id.shopsButton);
        AppUtils.setShopIdSharedPreference(ShopHome.this,AppConstants.shopID,id);
        verifyStoragePermissions(ShopHome.this);


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShopHome.this, SellerHome.class);
                i.putExtra("token", token);
                i.putExtra("email", email);
                i.putExtra("name", name);
                i.putExtra("isSeller", true);
                startActivity(i);
            }
        });
        createProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShopHome.this, AddProduct.class);
                i.putExtra("token", token);
                i.putExtra("id",id);
                startActivity(i);

            }
        });

        myProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShopHome.this, ProductListActivity.class);
                i.putExtra("token", token);
                i.putExtra("id",id);
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