package com.example.busticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
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
import java.util.Timer;
import java.util.TimerTask;

public class FindRouteNumber extends AppCompatActivity {

    SearchView sLocation;
     FirebaseAuth frbAuth;
     FirebaseFirestore fStore;
     ProgressBar progressBar;
    RouteViewAdapter routeViewAdapter;
    private String ser = "panni";

    private ArrayList<Routes> arrayList = new ArrayList<>();
    private ArrayList<String> RouteNo = new ArrayList<>();
    private ArrayList<String> startLocation = new ArrayList<>();
    private ArrayList<String> EndLocation = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_route_number);
//        sLocation = findViewById(R.id.startLocation);

        getAllRoutes();
    }

    public void getAllRoutes(){
        frbAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar4);
        progressBar.setVisibility(View.VISIBLE);

        Query query = FirebaseDatabase.getInstance().getReference("Routes_Admin");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(FindRouteNumber.this, "Data Loaded", Toast.LENGTH_SHORT).show();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Routes routes = dataSnapshot.getValue(Routes.class);
                        arrayList.add(routes);
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
        routeViewAdapter = new RouteViewAdapter(arrayList,this);
        recyclerView.setAdapter(routeViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void back(View view){
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manus,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);

        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                routeViewAdapter.getFilter().filter(s.toString());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                routeViewAdapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}