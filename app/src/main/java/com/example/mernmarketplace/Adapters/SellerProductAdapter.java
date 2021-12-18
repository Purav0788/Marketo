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
import com.example.mernmarketplace.EditProduct;
import com.example.mernmarketplace.ProductActivityCustomer;
import com.example.mernmarketplace.R;
import com.example.mernmarketplace.models.Product;
import com.example.mernmarketplace.models.ProductCreationResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SellerProductAdapter extends RecyclerView.Adapter<SellerProductAdapter.ProductHolder> {
    private Context context;
    private List<ProductCreationResponse> products;
    public SellerProductAdapter(List<ProductCreationResponse> productList){
        products = productList;
    }
    @Override
    public SellerProductAdapter.ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_entry_seller, parent, false);
        context = parent.getContext();
        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SellerProductAdapter.ProductHolder holder, int position) {
        final ProductCreationResponse product = products.get(position);
        if(product.getImage()!=null)
        Picasso.with(context).load(product.getImage()).into(holder.productImage);
        holder.productTitle.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        holder.productPrice.setText((int)product.getPrice()+" /PKR");
       //+ holder.sellerName.setText(product.getShop().getName());
        holder.Category.setText(product.getCategory());
        holder.productQuantity.setText("Available Units: "+product.getQuantity());
        holder.editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderStatusIntent = new Intent(context, EditProduct.class);
               //orderStatusIntent.putExtra("image",product.getImage());
                orderStatusIntent.putExtra("title", product.getName());
                orderStatusIntent.putExtra("description", product.getDescription());
                orderStatusIntent.putExtra("price", ""+product.getPrice());
                orderStatusIntent.putExtra("quantity", ""+product.getQuantity());
                orderStatusIntent.putExtra("category", ""+product.getCategory());
                orderStatusIntent.putExtra("id", ""+product.getId());
                orderStatusIntent.putExtra("image", product.getImage());

                context.startActivity(orderStatusIntent);
            }
        });
        holder.productBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderStatusIntent = new Intent(context, BidProduct.class);
                orderStatusIntent.putExtra("title", product.getName());
                orderStatusIntent.putExtra("price",product.getPrice());
                orderStatusIntent.putExtra("image",product.getImage());
                context.startActivity(orderStatusIntent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        public ImageView productImage,productBid,editProduct;
        public TextView productTitle;
        public TextView productDescription;
        public TextView productPrice;
        public TextView productQuantity;
        public TextView Category;
        public ProductHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imageView5);
            productTitle = itemView.findViewById(R.id.productName);
            productDescription = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice);
            productQuantity = itemView.findViewById(R.id.textView12);
            Category = itemView.findViewById(R.id.productCategory);
    productBid = itemView.findViewById(R.id.imageView15);
    editProduct = itemView.findViewById(R.id.editIcon);
        }
    }
}
