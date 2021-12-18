package com.example.mernmarketplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.mernmarketplace.Adapters.ProductAdapter;
import com.example.mernmarketplace.conf.APIs;
import com.example.mernmarketplace.conf.AppConstants;
import com.example.mernmarketplace.conf.AppUtils;
import com.example.mernmarketplace.conf.NetworkClient;
import com.example.mernmarketplace.models.Product;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends AppCompatActivity {
    DrawerLayout drawer;
Spinner spinner;
ImageView profileIcon, menuButton,cartButton;
RecyclerView recyclerView;

private NavigationView navigationView;
String token,email,name;
boolean isSeller;

List<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout_main);
        spinner = findViewById(R.id.filterSpinner);
        drawer = (DrawerLayout) findViewById(R.id.menu_layout);
        cartButton = findViewById(R.id.profileImage3);
        menuButton = findViewById(R.id.logOutButton);
        recyclerView = findViewById(R.id.productList);
        token = getIntent().getStringExtra("token");
        email = getIntent().getStringExtra("email");
        AppUtils.setUserTokenSharedPreference(HomeScreen.this, AppConstants.token,token);
        name = getIntent().getStringExtra("name");
        isSeller = getIntent().getBooleanExtra("isSeller",false);

        List<String> list = new ArrayList<>();
        list.add("Default");
        list.add("By Name");
        list.add("Price: Low to High");
        list.add("Price: High to Low");
        list.add("By Category");
        spinner.setAdapter(new ArrayAdapter<String>(this,
                R.layout.spinner_item,list));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override

                public void onClick(View v) {
                setItemInNavigation();
                    drawer.openDrawer(Gravity.LEFT, true);
                
            }
        });

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(HomeScreen.this,CartListActivity.class);
                startActivity(i);
            }
        });


        APIs apiService = NetworkClient.getRetrofit().create(APIs.class);
        Call<List<Product>> call = apiService.getProducts();
        call.enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() != null && response.code()==200) {
                    products = response.body();
                    for(Product p : products){
                        p.setImage("https://fyp-1.herokuapp.com/api/product/image/"+p.get_id());
                    }
                    ProductAdapter productAdapter = new ProductAdapter(products);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager catLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(catLayoutManager);
                    recyclerView.setAdapter(productAdapter);
//                    if(spinner.getSelectedItem().toString().equalsIgnoreCase("Price: Low to High"))
//                    {
//                        double tempPrice = 0;
//                        for(Product p : products){
//                            if(p.getPrice()>tempPrice)
//                            {
//                                tempPrice = p.getPrice();
//                                products.
//                            }
//                        }
//                    }

                    }


                }


            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
    private void setItemInNavigation() {

        Menu nav_Menu = navigationView.getMenu();

        nav_Menu.setGroupVisible(R.id.grpLogout, true);

        nav_Menu.setGroupVisible(R.id.grpProf, true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                int id = item.getItemId();
                switch (id) {


                    case R.id.nav_logout:
                        drawer.closeDrawer(Gravity.LEFT);

                        Intent i = new Intent(HomeScreen.this, LoginActivity.class);
                        finish();
                        startActivity(i);

                        break;


                    case R.id.nav_profile:
                        Intent i1 = new Intent(HomeScreen.this, Profile.class);
                        i1.putExtra("token", token);
                        i1.putExtra("name", name);
                        i1.putExtra("email", email);
                        i1.putExtra("isSeller", isSeller);
                        startActivity(i1);
                        break;
                }
                return true;
            }


        });
    }

}