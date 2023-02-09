package com.pj.shoppingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pj.shoppingapp.CartActivity;
import com.pj.shoppingapp.CartListener;
import com.pj.shoppingapp.ProductDetails;
import com.pj.shoppingapp.R;
import com.pj.shoppingapp.model.Cart;
import com.pj.shoppingapp.model.Colections;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ColectionViewHolder> {


    Context context;
    ArrayList<Cart> carts;
    int totalPrice;
    CartListener cartListener;
    ArrayList<String> positions = new ArrayList<String>();

    public CartAdapter(Context context, ArrayList<Cart> carts, CartListener cartListener) {
        this.context = context;
        this.carts = carts;
        this.cartListener = cartListener;
    }

    @NonNull
    @Override
    public ColectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.incart_item, parent, false);
        return new ColectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColectionViewHolder holder,final int position) {
        if (carts != null && carts.size()>0){
            holder.name.setText(carts.get(position).getName());
            holder.price.setText(carts.get(position).getPrice().toString());
            holder.size.setText(carts.get(position).getSize());
            holder.unit.setText(carts.get(position).getUnit());
            Glide.with(context).load(carts.get(position).getImageUrl()).into(holder.image);
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(holder.checkBox.isChecked()){
                        totalPrice += carts.get(position).getPrice();
                        positions.add(String.valueOf(position));
                    }else{
                        totalPrice -= carts.get(position).getPrice();
                        positions.remove(String.valueOf(position));
                    }
                    cartListener.onItemChange(totalPrice,positions);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public static class ColectionViewHolder extends  RecyclerView.ViewHolder{
        TextView name,price,size,unit;
        ImageView image;
        CheckBox checkBox;

        public ColectionViewHolder(@NonNull View itemView) {
            super(itemView);
            unit = itemView.findViewById(R.id.prod_unit);
            image = itemView.findViewById(R.id.itemImage);
            name = itemView.findViewById(R.id.nameSneakerCart);
            price = itemView.findViewById(R.id.priceSneakerCart);
            size = itemView.findViewById(R.id.sizeSneakerCart);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }


}
