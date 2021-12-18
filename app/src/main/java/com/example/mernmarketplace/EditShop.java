package com.example.mernmarketplace;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mernmarketplace.conf.APIs;
import com.example.mernmarketplace.conf.AppConstants;
import com.example.mernmarketplace.conf.AppUtils;
import com.example.mernmarketplace.conf.NetworkClient;
import com.example.mernmarketplace.models.ShopCreationResponse;
import com.squareup.picasso.Picasso;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditShop extends AppCompatActivity {
public ImageView uploadImage;
EditText shopName,shopDescription;
Button createButton;
String token;
Bitmap bitmap;
Image img;
String userID,shopID,pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shop);
        pic = getIntent().getStringExtra("img");
    shopID = getIntent().getStringExtra("id");
        token = "Bearer "+AppUtils.getUserTokenSharedPreference(EditShop.this, AppConstants.token);
        userID = AppUtils.getUserIDSharedPreference(EditShop.this, AppConstants.userID);
        uploadImage = findViewById(R.id.shopImg);
        shopName = findViewById(R.id.shopNameText);
        shopDescription = findViewById(R.id.shopDescriptionText);
        createButton = findViewById(R.id.saveShop);
       shopName.setText(getIntent().getStringExtra("name"));
        shopDescription.setText(getIntent().getStringExtra("description"));
        Picasso.with(EditShop.this).load("https://fyp-1.herokuapp.com/"+"api/shops/logo/"+shopID).into(uploadImage);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopName.getText().toString() != "" && shopDescription.getText().toString() != "" ) {


                    APIs apIs = NetworkClient.getRetrofit().create(APIs.class);
                    RequestBody name = RequestBody.create(MediaType.parse("text/plain"), shopName.getText().toString());
                    RequestBody description = RequestBody.create(MediaType.parse("text/plain"), shopDescription.getText().toString());

                    ShopCreationResponse shop = new ShopCreationResponse(shopName.getText().toString(), shopDescription.getText().toString());
                    Call<ShopCreationResponse> call = apIs.editShop(token, shopID,name,description);

                    call.enqueue(new Callback<ShopCreationResponse>() {
                        @Override
                        public void onResponse(Call<ShopCreationResponse> call, Response<ShopCreationResponse> response) {
                            Log.d("server response", "" + response.code());
                            if (response.body() != null && response.code()==200) {
                                Toast.makeText(EditShop.this, "Your shop has been updated!", Toast.LENGTH_LONG).show();


                                Intent i = new Intent(EditShop.this, ShopListActivity.class);
                                finish();
                                startActivity(i);
                            }
                        }

                        @Override
                        public void onFailure(Call<ShopCreationResponse> call, Throwable t) {

                            AppUtils.showCancelAlert(EditShop.this, "Something went wrong :(");

                        }
                    });


                }

            }


    });
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != RESULT_CANCELED) {
//            switch (requestCode) {
//                case 0:
//                    if (resultCode == RESULT_OK && data != null) {
//                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
//                        uploadImage.setImageBitmap(selectedImage);
//                        bitmap = selectedImage;
//
//                    }
//
//                    break;
//                case 1:
//                    if (resultCode == RESULT_OK && data != null) {
//                        Uri selectedImage = data.getData();
//                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                        if (selectedImage != null) {
//                            Cursor cursor = getContentResolver().query(selectedImage,
//                                    filePathColumn, null, null, null);
//                            if (cursor != null) {
//                                cursor.moveToFirst();
//
//                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                                String picturePath = cursor.getString(columnIndex);
//                                uploadImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//                                bitmap = BitmapFactory.decodeFile(picturePath);
//                                cursor.close();
//                            }
//                        }
//
//                    }
//                    break;
//            }
//        }
    }

