package com.example.mernmarketplace.conf;

import com.example.mernmarketplace.Profile;
import com.example.mernmarketplace.models.AuctionCreationResponse;
import com.example.mernmarketplace.models.LoginResponse;
import com.example.mernmarketplace.models.Order;
import com.example.mernmarketplace.models.OrderResponse;
import com.example.mernmarketplace.models.ProductCreationResponse;
import com.example.mernmarketplace.models.Shop;
import com.example.mernmarketplace.models.ShopCreationResponse;
import com.example.mernmarketplace.models.SignUpObj;
import com.example.mernmarketplace.models.Product;
import com.example.mernmarketplace.models.SignUpResponse;
import com.example.mernmarketplace.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

//test4api@test.com XD12345
public interface APIs {
    @GET("api/auction/{auctionId}")
    Call<AuctionCreationResponse> getAuction(@Path("auctionId") String userId);

    @POST("api/orders/{userId}")
    @Headers("Accept: */*")

    Call<OrderResponse> createOrder(@Header("Authorization") String Authorization, @Path("userId") String userId, @Body Order order);
    @POST("api/auctions/by/{userId}")
    @Headers("Accept: */*")
    @Multipart
    Call<AuctionCreationResponse> createAuction(@Header("Authorization") String Authorization, @Path("userId") String userId, @Part("itemName") RequestBody itemName, @Part("startingBid") RequestBody startingBid, @Part("bidStart") RequestBody bidStart,@Part("bidEnd") RequestBody bidEnd);
    @POST("api/users/")
    Call<SignUpResponse> signUp(@Body SignUpObj signUpObj);
    @POST("api/shops/by/{userId}")
    @Headers("Accept: */*")
    @Multipart
    Call<ShopCreationResponse> createShop(@Header("Authorization") String Authorization, @Path("userId") String userId,@Part("name") RequestBody name, @Part("description") RequestBody description );
    @PUT("api/shops/{shopId}")
    @Headers("Accept: */*")
    @Multipart
    Call<ShopCreationResponse> editShop(@Header("Authorization") String Authorization,@Path("shopId") String shopId,@Part("name") RequestBody name, @Part("description") RequestBody description);
    @GET("api/shops/by/{userId}")
    Call<List<ShopCreationResponse>> getShops(@Header("Authorization") String Authorization,@Path("userId") String userId);
    @GET("api/products/by/{shopId}")
    Call<List<ProductCreationResponse>> getProductsForSeller(@Header("Authorization") String Authorization,@Path("shopId") String shopId);
    @POST("api/products/by/{shopId}")
    @Headers("Accept: */*")
    @Multipart
    Call<ProductCreationResponse>  createProduct(@Header("Authorization") String Authorization, @Path("shopId") String shopId, @Part("name") RequestBody name, @Part("description") RequestBody description, @Part("category") RequestBody category, @Part("quantity") RequestBody quantity, @Part("price") RequestBody price);
    @PUT("api/product/{shopId}/{productId}")
    @Headers("Accept: */*")
    @Multipart
    Call<ProductCreationResponse>  editProduct(@Header("Authorization") String Authorization,@Path("shopId") String shopId,@Path("productId") String productId,@Part("name") RequestBody name, @Part("description") RequestBody description,@Part("category") RequestBody category, @Part("quantity") RequestBody quantity,@Part("price") RequestBody price);
    @POST("auth/signin")
    Call<LoginResponse> signIN(@Body SignUpObj signUpObj);

    @PUT("api/users/{token}")
    Call<User> editProfile(@Header("Authorization") String Authorization, @Path("token") String token, @Body User user);
    @GET("api/products")
    Call<List<Product>> getProducts();
}
