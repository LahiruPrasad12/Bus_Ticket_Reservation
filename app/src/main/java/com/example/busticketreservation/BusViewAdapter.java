package com.example.busticketreservation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BusViewAdapter extends RecyclerView.Adapter<BusViewAdapter.ViewHolder>{

    private ArrayList<String> BusNo = new ArrayList<>();
    private ArrayList<String> Location = new ArrayList<>();
    private ArrayList<String> Time = new ArrayList<>();
    private Context mContext;

    public BusViewAdapter(ArrayList<String> busNo, ArrayList<String> location, ArrayList<String> time, Context mContext) {
        BusNo = busNo;
        Location = location;
        Time = time;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_lists,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.busNo.setText(BusNo.get(position));
        holder.Location.setText(Location.get(position));
        holder.Time.setText(Time.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Book_Bus.class);
                intent.putExtra("BusNo",BusNo.get(position));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return BusNo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView busNo,Location,Time;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            busNo = itemView.findViewById(R.id.BusNo);
            Location = itemView.findViewById(R.id.location);
            Time = itemView.findViewById(R.id.time);
            parentLayout = itemView.findViewById(R.id.recycler_view);
        }
    }
}
