package com.example.busticketreservation.Admin;

import com.example.busticketreservation.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Routes> list;

    //for onclick card
    //had to make this static
    private static RecyclerViewClickListener listener;

    public MyAdapter(Context context, ArrayList<Routes> list, RecyclerViewClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Routes routes =list.get(position);
        holder.routeNo.setText(routes.getRouteNo());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

         TextView routeNo,Description;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            routeNo = itemView.findViewById(R.id.tvRouteNo);
            itemView.setOnClickListener(this);

        }
        //for onclick card
        @Override
        public void onClick(View view) {
            //had to make listner static in the declaration
            listener.onClick(view, getAdapterPosition());
        }
    }

    //implementation for card onclick listener
    public interface RecyclerViewClickListener{
            void onClick(View v, int position);
    }
}
