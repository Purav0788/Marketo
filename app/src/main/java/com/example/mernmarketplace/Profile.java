package com.example.mernmarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mernmarketplace.conf.APIs;
import com.example.mernmarketplace.conf.AppConstants;
import com.example.mernmarketplace.conf.AppUtils;
import com.example.mernmarketplace.conf.NetworkClient;
import com.example.mernmarketplace.models.Product;
import com.example.mernmarketplace.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Header;

public class Profile extends AppCompatActivity {
EditText emailText,nameText;
Button updateButton;
Switch sellerSwitch;
boolean isSeller,isSellerIntent;
String token,email,name;
public static  String endPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = "Bearer "+getIntent().getStringExtra("token");
        email = getIntent().getStringExtra("email");
        name = getIntent().getStringExtra("name");
        isSellerIntent = getIntent().getBooleanExtra("isSeller",false);
        setContentView(R.layout.activity_profile);
        emailText = findViewById(R.id.Email);
        nameText = findViewById(R.id.updateUserName);
        sellerSwitch = findViewById(R.id.switch1);
        updateButton = findViewById(R.id.updateButton);
        emailText.setText(email);
        nameText.setText(name);
        if(isSellerIntent)
            sellerSwitch.setChecked(true);
        endPoint = AppUtils.getUserIDSharedPreference(getApplicationContext(), AppConstants.userID);
        sellerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(sellerSwitch.isChecked()){
                    isSeller = true;
                }
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(nameText.getText().toString()!="" && emailText.getText().toString()!=""){

                APIs apIs = NetworkClient.getRetrofit().create(APIs.class);

                User user = new User(nameText.getText().toString(), emailText.getText().toString(),isSeller);

                Call<User> call = apIs.editProfile(token, endPoint,user);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("server response", "" + response.code());
                        if (response.body() != null && response.code() == 200)
                        AppUtils.showCancelAlert(Profile.this,"Profile has been updated");
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        AppUtils.showCancelAlert(Profile.this,"Something went wrong :(");

                    }
                });



            }
            }
        });

    }
}