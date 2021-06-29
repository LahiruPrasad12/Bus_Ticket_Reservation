package com.example.busticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.busticketreservation.Admin.Routes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddRoute extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);
    }


    //create final bill
    public void addRoute(View view) {

        Trips routes = new Trips();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Trips");

        routes.setAr_time("10:20");
        routes.setDep_time("8:20");
        routes.setDate("2020/2/10");
        routes.setRoute_id("170");
        routes.setTrip_id("109");
        routes.setPrice(680);

        databaseReference.push().setValue(routes);
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

    }
}