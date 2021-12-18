package com.example.mernmarketplace.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.mernmarketplace.BidProduct;
import com.example.mernmarketplace.ProductActivityCustomer;
import com.example.mernmarketplace.R;
import com.example.mernmarketplace.conf.AppUtils;
import com.example.mernmarketplace.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    private Context context;
    private List<Product> products;
    public ProductAdapter(List<Product> productList){
        products = productList;
    }
    @Override
    public ProductAdapter.ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_entry, parent, false);
        context = parent.getContext();
        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder( ProductAdapter.ProductHolder holder, int position) {
        final Product product = products.get(position);
        Picasso.with(context).load(product.getImage()).into(holder.productImage);
        holder.productTitle.setText(product.getName());
        //holder.productDescription.setText(product.getDescription());
        holder.productPrice.setText((int)product.getPrice()+" /PKR");
        holder.sellerName.setText(product.getShop().getName());
        holder.Category.setText(product.getCategory());

        holder.cartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderStatusIntent = new Intent(context, ProductActivityCustomer.class);
               orderStatusIntent.putExtra("image",product.getImage());
                orderStatusIntent.putExtra("title", product.getName());
                orderStatusIntent.putExtra("description", product.getDescription());
                orderStatusIntent.putExtra("price", (int)product.getPrice()+" /PKR");
                orderStatusIntent.putExtra("total",product.getPrice());
                context.startActivity(orderStatusIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        public ImageView productImage,auctionImage,cartImage;
        public TextView productTitle;
       // public TextView productDescription;
        public TextView productPrice;
        public TextView sellerName;
        public TextView Category;
        public ProductHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productTitle = itemView.findViewById(R.id.productName);
          // productDescription = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice);
            sellerName = itemView.findViewById(R.id.sellerText);
            Category = itemView.findViewById(R.id.CategoryText);

            cartImage = itemView.findViewById(R.id.imageView13);

        }
    }
}
