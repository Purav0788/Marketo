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
import com.example.mernmarketplace.conf.NetworkClient;
import com.example.mernmarketplace.models.Product;
import com.example.mernmarketplace.models.ProductCreationResponse;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProduct extends AppCompatActivity {
    private EditText nameText,descriptionText, categoryText, priceText;
    private TextView quantityText;
    private Button saveProductButton,addButton,subButton;

    String shopID;
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        nameText = findViewById(R.id.productNameText);
        descriptionText = findViewById(R.id.productDescription);
        categoryText = findViewById(R.id.productCategory);
        priceText = findViewById(R.id.productPrice);
        saveProductButton = findViewById(R.id.saveButton);
        quantityText = findViewById(R.id.productQuantity);
        addButton = findViewById(R.id.addButton);
        shopID = getIntent().getStringExtra("id");
        subButton = findViewById(R.id.subButton);
      String  token = "Bearer "+AppUtils.getUserTokenSharedPreference(AddProduct.this, AppConstants.token);
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
                    Call<ProductCreationResponse> call = apiService.createProduct(token,shopID,name,description,category,quantity,price);
                    call.enqueue(new Callback<ProductCreationResponse>() {
                        @Override
                        public void onResponse(Call<ProductCreationResponse> call, Response<ProductCreationResponse> response) {
                            Log.d("Server Response", "Response: "+response.code());
                            if(response.body()!=null && response.code()==200){
                                Toast.makeText(AddProduct.this,"Product Added Successfully",Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ProductCreationResponse> call, Throwable t) {
                            Toast.makeText(AddProduct.this,"Could not add product",Toast.LENGTH_LONG).show();

                        }
                    });
                }
    }
});
    }
}