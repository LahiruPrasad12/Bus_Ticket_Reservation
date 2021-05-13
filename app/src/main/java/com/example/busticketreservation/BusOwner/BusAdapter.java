package com.example.busticketreservation.BusOwner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busticketreservation.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        View v = LayoutInflater.from(context).inflate(R.layout.busitem,parent,false);
        return new MyviewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        BusList busList = list.get(position);




        //delete
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                alertDlg.setMessage("Are you sure?");
                alertDlg.setCancelable(false);
                alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Bus").child("-M_XAtYd4u7yxCWH_Qej");
                        databaseReference.removeValue();
                        Toast.makeText(context,"DELETED",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(v.getContext(),ViewAllBus.class);
                        v.getContext().startActivity(i);
                    }
                });
                alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDlg.create().show();

            }


        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{

        TextView busID, routeID, licenseNo, numSeats;
        Button del;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            busID = itemView.findViewById(R.id.tvbusId);
            routeID = itemView.findViewById(R.id.rnumber);
            licenseNo = itemView.findViewById(R.id.lNumber);
            numSeats = itemView.findViewById(R.id.sets);
            del = itemView.findViewById(R.id.delBtn);
        }
    }
}




