package com.example.mernmarketplace.conf;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mernmarketplace.conf.AppConstants.BASE_URL;

public class NetworkClient {
    private static Retrofit retrofit = null;



     public static Retrofit getRetrofit() {
        OkHttpClient.Builder okHttpClientBuiler = new OkHttpClient.Builder();
        okHttpClientBuiler.connectTimeout(25, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES);
        OkHttpClient okHttpClient = okHttpClientBuiler.build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        }
        return retrofit;
    }

}
