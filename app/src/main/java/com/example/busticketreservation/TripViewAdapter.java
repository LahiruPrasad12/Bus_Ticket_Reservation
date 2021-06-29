package com.example.busticketreservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.busticketreservation.Admin.Routes;
import java.util.ArrayList;

public class TripViewAdapter extends RecyclerView.Adapter<TripViewAdapter.ViewHolder>{
    private ArrayList<Trips> trips = new ArrayList<>();
    private String routes;
    private Context mContext;

    public TripViewAdapter(ArrayList<Trips> trips, String routes, Context mContext) {
        this.trips = trips;
        this.routes = routes;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_trip,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Trips trip = trips.get(position);
        holder.deTime.setText(trip.getAr_time()+" drop-off");
        holder.arTime.setText(trip.getDep_time()+" drop-on");
        holder.price.setText("LKR "+routes);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, routes, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView arTime,deTime,price;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            arTime = itemView.findViewById(R.id.arTime);
            deTime = itemView.findViewById(R.id.deTime);
            price = itemView.findViewById(R.id.price);
            parentLayout = itemView.findViewById(R.id.Parent);
        }
    }
}
