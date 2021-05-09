package com.example.busticketreservation.TripManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.busticketreservation.R;

public class AddTrips extends AppCompatActivity {

    Button btcancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trips);

        /*navigate to select route */
        btcancel = findViewById(R.id.btncancel);
        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SelectRoutesTm.class));
            }
        });

    }
}