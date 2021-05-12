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

        addRout routes = new addRout();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Routes");

        routes.setBus_No("UP8756");
        routes.setDeparture_Time("12:00 PM");
        routes.setArrival_Time("04:00 PM");
        routes.setFrom("Kottava");
        routes.setTo("Matara");
        routes.setPrice("700");

        databaseReference.push().setValue(routes);
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

    }
}