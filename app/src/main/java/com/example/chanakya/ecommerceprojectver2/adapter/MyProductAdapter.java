package com.example.chanakya.ecommerceprojectver2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chanakya.ecommerceprojectver2.Communicator;
import com.example.chanakya.ecommerceprojectver2.R;
import com.example.chanakya.ecommerceprojectver2.model.ProductItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chanakya on 2/25/18.
 */

public class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.MyViewHolder>{

    Context context;
    List<ProductItem> productItems;
   // String visibleFragment;

    //Communicator communicator;

    ProductItem productItem;

    public MyProductAdapter(Context context, List<ProductItem> productItems) {
        this.context = context;
        this.productItems = productItems;
       // this.visibleFragment = visibleFragment;
       // this.communicator = communicator;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.product_list_items,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        productItem = productItems.get(position);

        holder.title.setText(productItem.getName());
        holder.price.setText(productItem.getPrice());
        holder.quantity.setText(productItem.getQuantity());
        holder.description.setText(productItem.getDescription());

        if (productItem.getImage().isEmpty()) {
            holder.image.setImageResource(R.drawable.ic_launcher_background);
        } else{
            Picasso.with(context).load(productItem.getImage()).placeholder(R.drawable.ic_launcher_background).into( holder.image);

        }

    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView quantity;
        TextView price;
        TextView description;
        ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);

          this.title =itemView.findViewById(R.id.textViewTitle);
          this.quantity = itemView.findViewById(R.id.textViewQuantity);
          this.description = itemView.findViewById(R.id.textViewDescription);
          this.price = itemView.findViewById(R.id.textViewPrice);
          this.image = itemView.findViewById(R.id.imageViewProductImage);

        }
    }


}
