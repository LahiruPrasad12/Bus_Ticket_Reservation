package com.example.busticketreservation.TripManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busticketreservation.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<TripsModelClass> tripList;

    public Adapter (List<TripsModelClass> tripList){ this .tripList=tripList;}

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_design_recycler,parent,false);
         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        int t_id = tripList.get(position).getTrip_id();
        String date = tripList.get(position).getTrip_date();
        String a_time = tripList.get(position).getArrival_time();
        String d_time = tripList.get(position).getDepature_time();
        String d_id = tripList.get(position).getBus_id();

        holder.setData(t_id,date,a_time,d_time,d_id);


    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView trip_id;
        private TextView trip_date;
        private TextView arrival_time;
        private TextView depature_time;
        private TextView bus_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            trip_id =itemView.findViewById(R.id.trip_id);
            trip_date = itemView.findViewById(R.id.date);
            arrival_time = itemView.findViewById(R.id.arrival_time);
            depature_time = itemView.findViewById(R.id.depature_time);
            bus_id = itemView.findViewById(R.id.bus_id);


        }

        public void setData(int t_id, String date, String a_time, String d_time, String d_id) {
            trip_id.setText(t_id);
            trip_date.setText(date);
            arrival_time.setText(a_time);
            depature_time.setText(d_time);
            bus_id.setText(d_id);
        }
    }
}
