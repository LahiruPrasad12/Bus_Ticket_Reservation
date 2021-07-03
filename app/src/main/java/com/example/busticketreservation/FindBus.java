package com.example.busticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.busticketreservation.Admin.Routes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FindBus extends AppCompatActivity {

    EditText routeNumber;
    FirebaseAuth frbAuth;
    FirebaseFirestore fStore;
    ProgressBar progressBar;


    private ArrayList<Routes> routes = new ArrayList<>();
    private ArrayList<Trips> trips = new ArrayList<>();
    private String price = "0";
    private String RouteNo;
    private String enterRoute;
    private int x =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_bus);

        routeNumber = findViewById(R.id.routeNumber);
        Intent intent = getIntent();
        RouteNo = intent.getStringExtra("routeNo");

        if(RouteNo.length()>2){
            selectFromTable();
        }


    }


    //If user Select Route Number From Table Row this method call automatically
    public void selectFromTable(){
        routeNumber.setText(RouteNo);
        frbAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar5);
        progressBar.setVisibility(View.VISIBLE);


        Query query1 = FirebaseDatabase.getInstance().getReference("Trips").orderByChild("route_id").equalTo(RouteNo);
        Query query2 = FirebaseDatabase.getInstance().getReference("Routes_Admin").orderByChild("routeNo").equalTo(RouteNo);

        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        price = snapshot1.child("fullRoutePrice").getValue().toString();
                    }
                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(FindBus.this, "Invalid route number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FindBus.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(FindBus.this, "Data Loaded", Toast.LENGTH_SHORT).show();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Trips trip = dataSnapshot.getValue(Trips.class);
                        if(trip.getStatus().equals("Active")) {
                            x++;
                            trips.add(trip);
                            initRecyclerView();
                        }
                    }

                    if(x==0){
                        Toast.makeText(FindBus.this, "Not Available Buses at this time", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(FindBus.this, "Invalid route number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(FindBus.this, "Error!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        TripViewAdapter tripViewAdapter = new TripViewAdapter(trips,price,this);
        recyclerView.setAdapter(tripViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    //If user known and try to entered route number this function will call
    public void EnteredRoute(View view){
        enterRoute = routeNumber.getText().toString();


        frbAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar5);

        Query query1 = FirebaseDatabase.getInstance().getReference("Trips").orderByChild("route_id").equalTo(enterRoute);
        Query query2 = FirebaseDatabase.getInstance().getReference("Routes_Admin").orderByChild("routeNo").equalTo(enterRoute);

        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        price = snapshot1.child("fullRoutePrice").getValue().toString();
                    }
                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(FindBus.this, "Invalid route number", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FindBus.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(FindBus.this, "Data Loaded", Toast.LENGTH_SHORT).show();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Trips trip = dataSnapshot.getValue(Trips.class);
                        trips.add(trip);
                        initRecyclerView();
                    }
                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(FindBus.this, "Invalid route number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(FindBus.this, "Error!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goBack(View view){
        startActivity(new Intent(this,FindRouteNumber.class));
    }
    public void goChoosePlace(View view){
        startActivity(new Intent(this,ViewSavedPlace.class));
    }
}