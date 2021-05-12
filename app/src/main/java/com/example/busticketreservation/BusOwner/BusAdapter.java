package com.example.busticketreservation.BusOwner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busticketreservation.R;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.MyviewHolder> {

    Context context;

    ArrayList<BusList> list;

    public BusAdapter(Context context, ArrayList<BusList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyviewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        BusList busList = list.get(position);
        holder.BusID.setText(busList.getBusId());
        holder.RouteID.setText(busList.getRouteNumbr());
        holder.LicenseNo.setText(busList.getLicseNumbr());
        holder.NumSeats.setText(busList.getNumsets());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{

        TextView BusID, RouteID, LicenseNo, NumSeats;



        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            BusID = itemView.findViewById(R.id.tvbusId);
            RouteID = itemView.findViewById(R.id.rnumber);
            LicenseNo = itemView.findViewById(R.id.lNumber);
            NumSeats = itemView.findViewById(R.id.sets);


        }
    }
}
