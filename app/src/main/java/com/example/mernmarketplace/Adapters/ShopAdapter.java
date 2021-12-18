package com.example.mernmarketplace.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mernmarketplace.EditShop;
import com.example.mernmarketplace.ProductActivityCustomer;
import com.example.mernmarketplace.R;
import com.example.mernmarketplace.ShopHome;
import com.example.mernmarketplace.ShopListActivity;
import com.example.mernmarketplace.models.Product;
import com.example.mernmarketplace.models.Shop;
import com.example.mernmarketplace.models.ShopCreationResponse;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopHolder> {
   private Context context;
    private List<ShopCreationResponse> shops;
    public ShopAdapter(List<ShopCreationResponse> shopList){
        shops = shopList;
    }

    @Override
    public ShopAdapter.ShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_entry, parent, false);
        context = parent.getContext();
        return new ShopHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShopAdapter.ShopHolder holder, int position) {
        final ShopCreationResponse shop = shops.get(position);
        Picasso.with(context).load(shop.getImage()).into(holder.shopImage);
        holder.shopName.setText(shop.getName());
        holder.shopDescription.setText(shop.getDescription());
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderStatusIntent = new Intent(context, EditShop.class);
                orderStatusIntent.putExtra("id",shop.getId());
                orderStatusIntent.putExtra("name",shop.getName());
               orderStatusIntent.putExtra("img",shop.getImage());

                orderStatusIntent.putExtra("description", shop.getDescription());

                context.startActivity(orderStatusIntent);

            }
        });
        holder.shopName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderStatusIntent = new Intent(context, ShopHome.class);
                orderStatusIntent.putExtra("name",shop.getName());
                orderStatusIntent.putExtra("img",shop.getImage());
                orderStatusIntent.putExtra("id",shop.getId());
                context.startActivity(orderStatusIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public class ShopHolder extends RecyclerView.ViewHolder {
        public ImageView shopImage;
        public ImageView editButton;
        public TextView shopName;
        public TextView shopDescription;

        public ShopHolder( View itemView) {
            super(itemView);
            shopImage = itemView.findViewById(R.id.imageView5);
            editButton = itemView.findViewById(R.id.imageView6);
            shopName = itemView.findViewById(R.id.textView6);
            shopDescription = itemView.findViewById(R.id.textView7);

        }
    }
}
