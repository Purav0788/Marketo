package com.example.mernmarketplace;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.mernmarketplace.Adapters.ShopAdapter;
import com.example.mernmarketplace.conf.APIs;
import com.example.mernmarketplace.conf.AppConstants;
import com.example.mernmarketplace.conf.AppUtils;
import com.example.mernmarketplace.conf.NetworkClient;
import com.example.mernmarketplace.models.Product;
import com.example.mernmarketplace.models.Shop;
import com.example.mernmarketplace.models.ShopCreationResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopListActivity extends AppCompatActivity {
    ImageView homeImage;
    RecyclerView recyclerView;
  String token;
    List<ShopCreationResponse> shops = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
      token = "Bearer "+getIntent().getStringExtra("token");
        homeImage = findViewById(R.id.imageView10);
        recyclerView = findViewById(R.id.shoplist);
        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        APIs apiService = NetworkClient.getRetrofit().create(APIs.class);
        Call<List<ShopCreationResponse>> call = apiService.getShops(token,AppUtils.getUserIDSharedPreference(ShopListActivity.this, AppConstants.userID));
        call.enqueue(new Callback<List<ShopCreationResponse>>() {

            @Override
            public void onResponse(Call<List<ShopCreationResponse>> call, Response<List<ShopCreationResponse>> response) {
                Log.d("Server code",""+response.code());
                if (response.body() != null && response.code()==200) {
                    shops = response.body();
                        for(ShopCreationResponse p : shops){
                        p.setImage(AppConstants.BASE_URL+"api/shops/logo/"+p.getId());
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
                    ShopAdapter shopAdapter = new ShopAdapter(shops);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager catLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(catLayoutManager);
                    recyclerView.setAdapter(shopAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ShopCreationResponse>> call, Throwable t) {

            }
        });
    }
}