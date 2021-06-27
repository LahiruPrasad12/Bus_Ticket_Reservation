package com.example.busticketreservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RouteViewAdapter extends RecyclerView.Adapter<RouteViewAdapter.ViewHolder>{

    private ArrayList<String> RouteNo = new ArrayList<>();
    private ArrayList<String> sLocation = new ArrayList<>();
    private ArrayList<String> eLocation = new ArrayList<>();
    private Context mContext;

    public RouteViewAdapter(ArrayList<String> routeNo, ArrayList<String> sLocation, ArrayList<String> eLocation, Context mContext) {
        RouteNo = routeNo;
        this.sLocation = sLocation;
        this.eLocation = eLocation;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_route,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.routeNo.setText(RouteNo.get(position));
        holder.sLocation.setText(sLocation.get(position));
        holder.eLocation.setText(eLocation.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "OK", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return RouteNo.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView routeNo,sLocation,eLocation;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            routeNo = itemView.findViewById(R.id.textView28);
            sLocation = itemView.findViewById(R.id.textView29);
            eLocation = itemView.findViewById(R.id.textView30);
            parentLayout = itemView.findViewById(R.id.parent);
        }
    }
}
