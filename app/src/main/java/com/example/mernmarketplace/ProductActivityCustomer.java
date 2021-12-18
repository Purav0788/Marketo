package com.example.mernmarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mernmarketplace.conf.AppConstants;
import com.example.mernmarketplace.conf.AppUtils;
import com.example.mernmarketplace.conf.DBManager;
import com.squareup.picasso.Picasso;

public class ProductActivityCustomer extends AppCompatActivity {
    TextView nameText,descText,priceText,qtyText;
    ImageView prodImage,homeImage;
    int quantity =1;
    double total;
    Button cartButton,add,sub;
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        String image = getIntent().getStringExtra("image");
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String price =  getIntent().getStringExtra("price");
       // String  token = "Bearer "+ AppUtils.getUserTokenSharedPreference(ProductActivityCustomer.this, AppConstants.token);
        cartButton = findViewById(R.id.button2);
        qtyText = findViewById(R.id.productQuantity2);
        add = findViewById(R.id.addButton2);
        sub = findViewById(R.id.subButton2);
        cartButton = findViewById(R.id.button2);
        nameText = findViewById(R.id.prodName);
        descText = findViewById(R.id.prodDesc);
        priceText = findViewById(R.id.prodPrice);
        prodImage = findViewById(R.id.prodImg);
        homeImage = findViewById(R.id.imageView3);
        nameText.setText(title);
        descText.setText(description);
        priceText.setText(price);
        total = getIntent().getDoubleExtra("total",0);
        Picasso.with(ProductActivityCustomer.this).load(image).into(prodImage);
        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity += 1;
                qtyText.setText(""+quantity);
                total = total*quantity;
                priceText.setText(total+" /PKR");
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = quantity -1;
                if(quantity<1)
                    quantity = 1;
                qtyText.setText(""+quantity);
                total = total*quantity;
                priceText.setText(total+" /PKR");
            }
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager = new DBManager(ProductActivityCustomer.this);
                dbManager.open();
                dbManager.insert(title,quantity+"",total+"");
                Toast.makeText(ProductActivityCustomer.this,"Added Item to Cart",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}