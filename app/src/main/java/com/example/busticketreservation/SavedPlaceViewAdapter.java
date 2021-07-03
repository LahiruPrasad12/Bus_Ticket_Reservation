package com.example.busticketreservation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SavedPlaceViewAdapter extends RecyclerView.Adapter<SavedPlaceViewAdapter.ViewHolder> {

    ArrayList<SavedPlace> savedPlaces = new ArrayList<>();
    private Context mContext;

    public SavedPlaceViewAdapter(ArrayList<SavedPlace> savedPlaces, Context mContext) {
        this.savedPlaces = savedPlaces;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_saved_place,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SavedPlace savedPlace = savedPlaces.get(position);
        holder.name.setText(savedPlace.getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Saved_Place").child(savedPlace.getName());
                databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //
                    }
                });

            }
        });
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,FindBus.class);
                intent.putExtra("routeNo",savedPlace.getRouteNumber());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
       return savedPlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        RelativeLayout parentLayout;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Name);
            parentLayout = itemView.findViewById(R.id.parentL);
            imageView = itemView.findViewById(R.id.imageView2);

        }
    }

}
