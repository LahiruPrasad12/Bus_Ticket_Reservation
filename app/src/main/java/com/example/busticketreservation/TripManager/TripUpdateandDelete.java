package com.example.busticketreservation.TripManager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.busticketreservation.R;
import com.google.firebase.database.DatabaseReference;

public class TripUpdateandDelete extends AppCompatActivity {

    TextView trip_id, route_id, date , dep_time, ar_time;
    Button btnupdate, btndelete;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_updateand_delete);

        trip_id = findViewById(R.id.add_date_up);
        route_id = findViewById(R.id.addrouteid_up);
        date = findViewById(R.id.add_date_up);
        dep_time = findViewById(R.id.txt_dep_time_up);
        ar_time = findViewById(R.id.txt_ar_time_up);

        btnupdate = findViewById(R.id.button_updte_trip_up);
        btndelete = findViewById(R.id.btn_delete_trip_up);




        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}