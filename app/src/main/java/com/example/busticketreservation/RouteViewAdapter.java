package com.example.busticketreservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busticketreservation.Admin.Routes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RouteViewAdapter extends RecyclerView.Adapter<RouteViewAdapter.ViewHolder> implements Filterable {


//    private ArrayList<String> RouteNo = new ArrayList<>();
//    private ArrayList<String> sLocation = new ArrayList<>();
//    private ArrayList<String> eLocation = new ArrayList<>();
    private ArrayList<Routes> routes = new ArrayList<>();
    private ArrayList<Routes> routesfull = new ArrayList<>();
    private Context mContext;

//    private ArrayList<String> sLocationfull = new ArrayList<>();
//    private ArrayList<String> eLocationfull = new ArrayList<>();
//    private ArrayList<String> RouteNoFull = new ArrayList<>();

    public RouteViewAdapter(ArrayList<Routes> routes, Context mContext) {
        this.routes = routes;
        this.mContext = mContext;
        routesfull = new ArrayList<>(routes);
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
        Routes route =routes.get(position);
        holder.routeNo.setText(route.getRouteNo());
        holder.sLocation.setText(route.getFrom());
        holder.eLocation.setText(route.getTo());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "OK", Toast.LENGTH_SHORT).show();
            }
        });

//        holder.routeNo.setText(RouteNo.get(position));
//        holder.sLocation.setText(sLocation.get(position));
//        holder.eLocation.setText(eLocation.get(position));
//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "OK", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    @Override
    public Filter getFilter() {
        return FilterUser;
    }

    private Filter FilterUser = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String searchtext = charSequence.toString().toLowerCase();
            List<Routes>temp = new ArrayList<>();


            if(searchtext.isEmpty()){
                temp.addAll(routesfull);

            }else {
                for (Routes item:routesfull) {
                    if(item.getFrom().toLowerCase().contains(searchtext.toLowerCase()) || item.getTo().toLowerCase().contains(searchtext.toLowerCase())){
                        temp.add(item);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = temp;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            routes.clear();
            routes.addAll((Collection<? extends Routes>) filterResults.values);
            notifyDataSetChanged();
        }
    };


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
