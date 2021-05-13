package com.example.busticketreservation.TripManager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busticketreservation.R;

import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter <TripAdapter.TripViewHolder> {

    ArrayList<TripModel> mList;
    Context context;

    public TripAdapter(Context context, ArrayList<TripModel> mList){

        this.mList = mList;
        this.context = context;
    }



    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.trip_item,parent,false);
        return new TripViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        TripModel model = mList.get(position);
        holder.t_id.setText(model.getTrip_id());
        holder.r_id.setText(model.getRoute_id());
        holder.date.setText(model.getDate());
        holder.dep_t.setText(model.getDep_time());
        holder.ar_t.setText(model.getAr_time());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,TripUpdateandDelete.class);
                intent.putExtra("TripId",model.getTrip_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class TripViewHolder extends RecyclerView.ViewHolder{

        TextView t_id , r_id , date, dep_t, ar_t;
        LinearLayout linearLayout;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.layout1);
            t_id = itemView.findViewById(R.id.trip_idtxt);
            r_id = itemView.findViewById(R.id.route_idtxt);
            date = itemView.findViewById(R.id.date_txt);
            dep_t = itemView.findViewById(R.id.dep_t_txt);
            ar_t = itemView.findViewById(R.id.ar_t_txt);

        }
    }

    /////////////////////////////////////////////////////////////
    //implementation for card onclick listener
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

}
