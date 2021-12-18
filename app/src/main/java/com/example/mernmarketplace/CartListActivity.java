package com.example.mernmarketplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mernmarketplace.Adapters.CartAdapter;
import com.example.mernmarketplace.Adapters.ProductAdapter;
import com.example.mernmarketplace.conf.APIs;
import com.example.mernmarketplace.conf.AppConstants;
import com.example.mernmarketplace.conf.AppUtils;
import com.example.mernmarketplace.conf.DBManager;
import com.example.mernmarketplace.conf.NetworkClient;
import com.example.mernmarketplace.models.Product;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartListActivity extends AppCompatActivity {
ImageView homeIcon;
TextView totalText;
RecyclerView recyclerView;
Button checkOut;
int gTotal=0;
DBManager dbManager;
boolean isSeller;

List<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        checkOut = findViewById(R.id.saveShop2);
        homeIcon = findViewById(R.id.imageView10);
        totalText = findViewById(R.id.textView9);
        dbManager = new DBManager(CartListActivity.this);
        dbManager.open();
        recyclerView = findViewById(R.id.shoplist);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartListActivity.this,CheckOutActivity.class);
                startActivity(i);
                finish();
            }
        });
     products = dbManager.getProducts();
     for (Product p : products){
         gTotal +=p.getPrice();

     }
     totalText.setText("Subtotal is :"+gTotal+" /PKR");
        AppUtils.setTotalSP(CartListActivity.this, AppConstants.total,""+totalText.getText().toString());


     if(products.size()!=0) {

         CartAdapter productAdapter = new CartAdapter(products);
         recyclerView.setHasFixedSize(true);
         RecyclerView.LayoutManager catLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
         recyclerView.setLayoutManager(catLayoutManager);
         recyclerView.setAdapter(productAdapter);

     }
    }
}