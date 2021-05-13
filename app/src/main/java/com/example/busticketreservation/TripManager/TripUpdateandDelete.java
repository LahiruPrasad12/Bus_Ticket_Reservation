package com.example.busticketreservation.TripManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.busticketreservation.Admin.Routes;
import com.example.busticketreservation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TripUpdateandDelete extends AppCompatActivity {

    TextView trip_id, route_id, date , dep_time, ar_time;
    Button btnupdate, btndelete;
    FirebaseFirestore fStore;
    private String tripId,arTime;

    DatabaseReference dbRef;
    Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_updateand_delete);

        trip_id = findViewById(R.id.addtripid_up);
        route_id = findViewById(R.id.addrouteid_up);
        date = findViewById(R.id.add_date_up);
        dep_time = findViewById(R.id.txt_dep_time_up);
        ar_time = findViewById(R.id.txt_ar_time_up);


        fStore = FirebaseFirestore.getInstance();
        btnupdate = findViewById(R.id.button_updte_trip_up);
        btndelete = findViewById(R.id.btn_delete_trip_up);


        //getting intent with values
        Intent i =getIntent();
        tripId = i.getStringExtra("TripId");
      Trip trip = (Trip) i.getSerializableExtra("tripObj");
//
//
//        //changing text views according to the variables
//        trip_id.setText(trip.getTrip_id());;
//        route_id.setText(trip.getRoute_id());;
//        date.setText(trip.getDate());
//        dep_time.setText(trip.getDep_time());
//        ar_time.setText(trip.getAr_time());

        trip_id.setText(tripId);


        Query query = FirebaseDatabase.getInstance().getReference("Trip").orderByChild("trip_id").equalTo(tripId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ar_time.setText(dataSnapshot.child("ar_time").getValue().toString());
                    dep_time.setText(dataSnapshot.child("dep_time").getValue().toString());
                    date.setText(dataSnapshot.child("date").getValue().toString());
                    route_id.setText(dataSnapshot.child("route_id").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

    public void update(View view){
        String id="-M_VIu1Q3QV_nvlX_U-_";
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Trip").child(id);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arTime = ar_time.getText().toString();
                Map<String,Object> trip = new HashMap<>();
                trip.put("ar_time",arTime);

                databaseReference.updateChildren(trip);
                Toast.makeText(TripUpdateandDelete.this, "Success ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}