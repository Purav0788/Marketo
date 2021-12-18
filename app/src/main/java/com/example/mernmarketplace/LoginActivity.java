package com.example.mernmarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mernmarketplace.conf.APIs;
import com.example.mernmarketplace.conf.AppConstants;
import com.example.mernmarketplace.conf.AppUtils;
import com.example.mernmarketplace.conf.NetworkClient;
import com.example.mernmarketplace.models.LoginResponse;
import com.example.mernmarketplace.models.SignUpObj;
import com.example.mernmarketplace.models.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText emailText, passwordText;
    TextView signUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailText = findViewById(R.id.loginUserName);
        passwordText = findViewById(R.id.loginPassword);
        signUpText = findViewById(R.id.sign_up_text);
        login = findViewById(R.id.signUpButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailText.getText().toString() != "" && passwordText.getText().toString() != "") {
                    SignUpObj signUpObj = new SignUpObj(emailText.getText().toString(), passwordText.getText().toString());
                    APIs apiService = NetworkClient.getRetrofit().create(APIs.class);
                    Call<LoginResponse> call = apiService.signIN(signUpObj);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            LoginResponse loginResponse;
                            if (response.body() != null) {
                                loginResponse = response.body();
                                if (loginResponse.getUser().getEmail().equalsIgnoreCase(emailText.getText().toString())) {
                                    Intent i = new Intent(getApplicationContext(), HomeScreen.class);

                                   AppUtils.setUserIDSharedPreference(getApplicationContext(), AppConstants.userID,loginResponse.getUser().get_id());
                                   if(!loginResponse.getUser().isSeller()) {
                                       i.putExtra("token",loginResponse.getToken());
                                       i.putExtra("email",loginResponse.getUser().getEmail());
                                       i.putExtra("isSeller",loginResponse.getUser().isSeller());
                                       i.putExtra("name",loginResponse.getUser().getName());
                                       startActivity(i);
                                   }
                                   else{
                                       Intent i1 = new Intent(getApplicationContext(), SellerHome.class);
                                       AppUtils.setUserEmailSharedPreference(LoginActivity.this,AppConstants.userEmail,loginResponse.getUser().getEmail());
                                       AppUtils.setUserNameSharedPreference(LoginActivity.this,AppConstants.userName,loginResponse.getUser().getName());
                                       i1.putExtra("token",loginResponse.getToken());
                                       i1.putExtra("email",loginResponse.getUser().getEmail());
                                       i1.putExtra("isSeller",loginResponse.getUser().isSeller());
                                       i1.putExtra("name",loginResponse.getUser().getName());
                                       startActivity(i1);

                                   }
                                    finish();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                          AppUtils.showCancelAlert(LoginActivity.this,"Could not sign in");
                        }
                    });

                }
            }
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);
                finish();
            }
        });
    }
}