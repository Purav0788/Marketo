package com.example.mernmarketplace.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mernmarketplace.ProductActivityCustomer;
import com.example.mernmarketplace.R;
import com.example.mernmarketplace.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProductHolder> {
    private Context context;
    private List<Product> products;
    public CartAdapter(List<Product> productList){
        products = productList;
    }
    @Override
    public CartAdapter.ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_entry, parent, false);
        context = parent.getContext();
        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartAdapter.ProductHolder holder, int position) {
        final Product product = products.get(position);

        holder.productTitle.setText(product.getName());
        holder.productQuantity.setText("Quantity: "+product.getQuantity());
        holder.productPrice.setText((int)product.getPrice()+" /PKR");





    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {

        public TextView productTitle;
        public TextView productQuantity;
        public TextView productPrice;

        public ProductHolder(View itemView) {
            super(itemView);
           ;
            productTitle = itemView.findViewById(R.id.productName);
            productQuantity = itemView.findViewById(R.id.textView12);
            productPrice = itemView.findViewById(R.id.productPrice);

        }
    }
}
