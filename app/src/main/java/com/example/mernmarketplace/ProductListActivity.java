package com.example.mernmarketplace;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mernmarketplace.Adapters.SellerProductAdapter;
import com.example.mernmarketplace.Adapters.ShopAdapter;
import com.example.mernmarketplace.conf.APIs;
import com.example.mernmarketplace.conf.AppConstants;
import com.example.mernmarketplace.conf.AppUtils;
import com.example.mernmarketplace.conf.NetworkClient;
import com.example.mernmarketplace.models.ProductCreationResponse;
import com.example.mernmarketplace.models.ShopCreationResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {
    ImageView homeImage;
    RecyclerView recyclerView;

    List<ProductCreationResponse> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
      String token = "Bearer "+getIntent().getStringExtra("token");
      String id = getIntent().getStringExtra("id");
        homeImage = findViewById(R.id.imageView10);
        recyclerView = findViewById(R.id.productlist);
        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        APIs apiService = NetworkClient.getRetrofit().create(APIs.class);
        Call<List<ProductCreationResponse> >call = apiService.getProductsForSeller(token,id);
        call.enqueue(new Callback<List<ProductCreationResponse>>() {

            @Override
            public void onResponse(Call<List<ProductCreationResponse>> call, Response<List<ProductCreationResponse>> response) {
                Log.d("Server code",""+response.code());
                if (response.body() != null && response.code()==200) {
                    products = response.body();
                    for(ProductCreationResponse p : products){
                        p.setImage(AppConstants.BASE_URL+"api/product/image/"+p.getId());
                    }
                    //converting the string to json array object
//                    JSONArray array = new JSONArray(response.body());
//
//                    //traversing through all the object
//                    for (int i = 0; i < array.length(); i++) {
//
//                        //getting product object from json array
//                        JSONObject jsonData = array.getJSONObject(i);
//                        shops.add(new Shop(
//                                jsonData.getString("name"),
//                                jsonData.getString("description")));
//
//
//                    }
                    SellerProductAdapter productAdapterAdapter = new SellerProductAdapter(products);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager catLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(catLayoutManager);
                    recyclerView.setAdapter(productAdapterAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ProductCreationResponse>> call, Throwable t) {

            }
        });
    }
}