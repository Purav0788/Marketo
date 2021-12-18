package com.example.mernmarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mernmarketplace.conf.APIs;
import com.example.mernmarketplace.conf.AppUtils;
import com.example.mernmarketplace.conf.NetworkClient;
import com.example.mernmarketplace.models.SignUpObj;
import com.example.mernmarketplace.models.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    private EditText nameText,emailText, passwordText, confirmPasswordText;
    private Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpButton = findViewById(R.id.signUpButton);
        nameText = findViewById(R.id.signUpUserName);
        emailText = findViewById(R.id.signUpEmail);
        passwordText = findViewById(R.id.signupPassword);
        confirmPasswordText = findViewById(R.id.signupPassword2);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameText.getText().toString()!=""&& emailText.getText().toString()!="" && passwordText.getText().toString()!="" ){
                    if(passwordText.getText().toString().equals(confirmPasswordText.getText().toString())){
                        SignUpObj signUpObj = new SignUpObj(nameText.getText().toString(),emailText.getText().toString(),passwordText.getText().toString());
                        APIs apiService = NetworkClient.getRetrofit().create(APIs.class);
                        Call<SignUpResponse> call = apiService.signUp(signUpObj);
                        call.enqueue(new Callback<SignUpResponse>() {
                            @Override
                            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                                SignUpResponse signUpResponse;
                                if (response.body() != null && response.code()==200) {
                                    signUpResponse = response.body();
                                    if(signUpResponse.getMessage().contains("Successfully")) {
                                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),"Could not sign in",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else{
                        AppUtils.showCancelAlert(SignUp.this,"The passwords you've entered don't match");
                    }
                }
                else{
                    AppUtils.showCancelAlert(SignUp.this,"One or more fields is missing");
                }
            }
        });
    }
}