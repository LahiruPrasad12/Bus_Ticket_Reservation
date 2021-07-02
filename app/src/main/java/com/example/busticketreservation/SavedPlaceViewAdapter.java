package com.example.busticketreservation;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SavedPlaceViewAdapter {


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView arTime;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
