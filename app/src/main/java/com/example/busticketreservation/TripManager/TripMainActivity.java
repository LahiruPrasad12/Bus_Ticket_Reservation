package com.example.busticketreservation.TripManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.busticketreservation.MainActivity;
import com.example.busticketreservation.R;


public class TripMainActivity extends AppCompatActivity {

    Button viewSelectRoute;
    Button viewTrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_main);

        viewSelectRoute = findViewById(R.id.btn_add_trip);
        viewTrip = findViewById(R.id.btn_view_trips);

        /*navigate to other activities */
        viewSelectRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SelectRoutesTm.class));
            }
        });

        viewTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewTrips.class));
            }
        });




    }




}

