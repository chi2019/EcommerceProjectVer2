package com.example.chanakya.ecommerceprojectver2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.chanakya.ecommerceprojectver2.R;
import com.example.chanakya.ecommerceprojectver2.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanakya on 2/18/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Item> countries;

   // Communicate communicator;

    Item country;

    public MyAdapter(Context context, ArrayList<Item> countries) {
        this.context = context;
        this.countries = countries;
 //       this.communicator = communicator;

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View v = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        country = countries.get(position);

       // holder.descrition.setText(item.getDescription());
        holder.title.setText(country.getCategoryName());


       if (country.getCategoryImage().isEmpty()) {
            holder.image.setImageResource(R.drawable.ic_launcher_background);
        } else{
            Picasso.with(context).load(country.getCategoryImage()).placeholder(R.drawable.ic_launcher_background).into( holder.image);

        }

      /*  holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                communicator.show(item);
            }
        });*/




    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;

        public MyViewHolder(View v){
            super(v);

            this.title = v.findViewById(R.id.textViewTitle);
            this.image = v.findViewById(R.id.imageViewCountryImage);

        }

    }


}

