package com.example.mernmarketplace;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mernmarketplace.conf.APIs;
import com.example.mernmarketplace.conf.AppConstants;
import com.example.mernmarketplace.conf.AppUtils;
import com.example.mernmarketplace.conf.NetworkClient;
import com.example.mernmarketplace.models.ProductCreationResponse;
import com.squareup.picasso.Picasso;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProduct extends AppCompatActivity {
    private EditText nameText,descriptionText, categoryText, priceText;
    private TextView quantityText;
    private ImageView image,home;
    private Button saveProductButton,addButton,subButton;


    String shopID,productID,pic;
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        home = findViewById(R.id.imageView3);
        nameText = findViewById(R.id.productNameText);
        descriptionText = findViewById(R.id.productDescription);
        categoryText = findViewById(R.id.productCategory);
        priceText = findViewById(R.id.productPrice);
        pic = getIntent().getStringExtra("image");
        image = findViewById(R.id.prodImg);
        saveProductButton = findViewById(R.id.saveButton);
        quantityText = findViewById(R.id.productQuantity);
        Picasso.with(EditProduct.this).load(pic).into(image);
        addButton = findViewById(R.id.addButton);
        productID = getIntent().getStringExtra("id");
        subButton = findViewById(R.id.subButton);
        quantityText.setText(getIntent().getStringExtra("quantity"));
        quantity = Integer.parseInt(getIntent().getStringExtra("quantity"));
        priceText.setText(getIntent().getStringExtra("price"));
        categoryText.setText(getIntent().getStringExtra("category"));
        descriptionText.setText(getIntent().getStringExtra("description"));
        nameText.setText(getIntent().getStringExtra("title"));
        shopID = AppUtils.getShopIdSharedPreference(EditProduct.this,AppConstants.shopID);
        String  token = "Bearer "+AppUtils.getUserTokenSharedPreference(EditProduct.this, AppConstants.token);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity += 1;
                quantityText.setText(""+quantity);
            }
        });
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = quantity -1;
                if(quantity<0)
                    quantity = 0;
                quantityText.setText(""+quantity);
            }
        });
        saveProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameText.getText().toString()!=""&& descriptionText.getText().toString()!="" && categoryText.getText().toString()!=""&& quantity!=0 && priceText.getText().toString()!="" ) {
                    RequestBody name = RequestBody.create(MediaType.parse("text/plain"), nameText.getText().toString());
                    RequestBody description = RequestBody.create(MediaType.parse("text/plain"), descriptionText.getText().toString());
                    RequestBody category = RequestBody.create(MediaType.parse("text/plain"), categoryText.getText().toString());
                    RequestBody quantity = RequestBody.create(MediaType.parse("text/plain"), quantityText.getText().toString());
                    RequestBody price = RequestBody.create(MediaType.parse("text/plain"), priceText.getText().toString());


                    APIs apiService = NetworkClient.getRetrofit().create(APIs.class);
                    Call<ProductCreationResponse> call = apiService.editProduct(token,shopID,productID,name,description,category,quantity,price);
                    call.enqueue(new Callback<ProductCreationResponse>() {
                        @Override
                        public void onResponse(Call<ProductCreationResponse> call, Response<ProductCreationResponse> response) {
                            Log.d("Server Response", "Response: "+response.code());
                            if(response.body()!=null && response.code()==200){
                                Toast.makeText(EditProduct.this,"Product updated Successfully",Toast.LENGTH_LONG).show();
                                finish();
                                Intent i = new Intent(EditProduct.this,ProductListActivity.class);
                                startActivity(i);
                            }
                        }

                        @Override
                        public void onFailure(Call<ProductCreationResponse> call, Throwable t) {
                            Toast.makeText(EditProduct.this,"Could not edit product",Toast.LENGTH_LONG).show();

                        }
                    });
                }
    }
});
    }
}