package com.example.busticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FindRouteNumber extends AppCompatActivity {

     FirebaseAuth frbAuth;
     FirebaseFirestore fStore;
     ProgressBar progressBar;

    private ArrayList<String> RouteNo = new ArrayList<>();
    private ArrayList<String> startLocation = new ArrayList<>();
    private ArrayList<String> EndLocation = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_route_number);
        getAllRoutes();
    }

    public void getAllRoutes(){
        frbAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        Query query = FirebaseDatabase.getInstance().getReference("Routes_Admin");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(FindRouteNumber.this, "Success", Toast.LENGTH_SHORT).show();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String route = dataSnapshot.child("routeNo").getValue().toString();
                        String sLocation = dataSnapshot.child("from").getValue().toString();
                        String eLocation = dataSnapshot.child("to").getValue().toString();
                        RouteNo.add(route);
                        startLocation.add(sLocation);
                        EndLocation.add(eLocation);
                        initRecyclerView();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.INVISIBLE);

                Toast.makeText(FindRouteNumber.this,"Error!!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        RouteViewAdapter routeViewAdapter = new RouteViewAdapter(RouteNo,startLocation,EndLocation,this);
        recyclerView.setAdapter(routeViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}