package com.example.busticketreservation.TripManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.busticketreservation.R;

public class SelectRoutesTm extends AppCompatActivity {

    ImageButton backtomain;
    RelativeLayout rel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_routes_tm);

        //navigate to home page
        backtomain = findViewById(R.id.backtohome);
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TripMainActivity.class));
            }
        });

        //navigate to add trips

        rel = findViewById(R.id.selectrel);
        rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddTrips.class));
            }
        });





    }


}