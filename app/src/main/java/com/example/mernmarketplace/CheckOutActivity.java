package com.example.mernmarketplace;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mernmarketplace.conf.APIs;
import com.example.mernmarketplace.conf.AppConstants;
import com.example.mernmarketplace.conf.AppUtils;
import com.example.mernmarketplace.conf.DBManager;
import com.example.mernmarketplace.conf.NetworkClient;
import com.example.mernmarketplace.models.DeliveryAddress;
import com.example.mernmarketplace.models.Order;
import com.example.mernmarketplace.models.OrderResponse;
import com.example.mernmarketplace.models.Product;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutActivity extends AppCompatActivity {
    private EditText street, city, state, zip;
    private TextView totalText;
    private Button proceed;
    String gTotal;
    Product[] products, p1;
    List<Product> productList = new ArrayList<>();
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        dbManager = new DBManager(CheckOutActivity.this);
        dbManager.open();
        productList = dbManager.getProducts();
        products = new Product[productList.size()];
        p1 = new Product[productList.size()];
        p1 = productList.toArray(products);
        gTotal = AppUtils.getTotalSP(CheckOutActivity.this, AppConstants.total);
        street = findViewById(R.id.streetText);
        city = findViewById(R.id.cityText);
        state = findViewById(R.id.stateText);
        zip = findViewById(R.id.zipcodeText);
        totalText = findViewById(R.id.totalText);
        if (gTotal != "" && gTotal != null)
            totalText.setText(gTotal);
        proceed = findViewById(R.id.checkoutButton);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (city.getText().toString() != "" && state.getText().toString() != "" && street.getText().toString() != "" && zip.getText().toString() != "" && totalText.getText().toString() != "") {
                    if (p1.length > 0) {
                        DeliveryAddress address = new DeliveryAddress(street.getText().toString(), city.getText().toString(), state.getText().toString(), zip.getText().toString());
                        String token = "Bearer " + AppUtils.getUserTokenSharedPreference(CheckOutActivity.this, AppConstants.token);
                        Order order1 = new Order(productList, AppUtils.getUserNameDSharedPreference(CheckOutActivity.this, AppConstants.userName), AppUtils.getUserEmailDSharedPreference(CheckOutActivity.this, AppConstants.userEmail), address);
                        APIs apiService = NetworkClient.getRetrofit().create(APIs.class);
                        RequestBody order = RequestBody.create(MediaType.parse("application/json"), String.valueOf(order1));

                        Call<OrderResponse> call = apiService.createOrder(token,AppUtils.getUserIDSharedPreference(CheckOutActivity.this,AppConstants.userID),order1);
                        call.enqueue(new Callback<OrderResponse>() {
                            @Override
                            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                                Log.d("Server Response:", "onResponse: "+response.code());
                                dbManager.delete();
                                if (response.body() != null && response.code() == 200) {
                                    dbManager.delete();
                                    Toast.makeText(CheckOutActivity.this,"Order Placed Successfully!",Toast.LENGTH_LONG).show();
                                    }
                                }


                            @Override
                            public void onFailure(Call<OrderResponse> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Could not place order", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        AppUtils.showCancelAlert(CheckOutActivity.this, "The passwords you've entered don't match");
                    }
                } else {
                    AppUtils.showCancelAlert(CheckOutActivity.this, "One or more fields is missing");
                }
            }
        });
    }
}