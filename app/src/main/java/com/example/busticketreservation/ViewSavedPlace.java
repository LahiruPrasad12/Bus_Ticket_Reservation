package com.example.busticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class ViewSavedPlace extends AppCompatActivity {

    FirebaseAuth frbAuth;
    FirebaseFirestore fStore;
    ProgressBar progressBar;
    String userId;
    SavedPlaceViewAdapter savedPlaceViewAdapter;

    private ArrayList<SavedPlace> savedPlaces = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_saved_place);

        getSupportActionBar().setTitle("Choose Saved Place");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        frbAuth = FirebaseAuth.getInstance();
        userId = frbAuth.getCurrentUser().getUid();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);


        Query query1 = FirebaseDatabase.getInstance().getReference("Saved_Place").orderByChild("userId").equalTo(userId);

        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(ViewSavedPlace.this, "Data Loaded", Toast.LENGTH_SHORT).show();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        SavedPlace savedPlace = dataSnapshot.getValue(SavedPlace.class);
                        savedPlaces.add(savedPlace);
                        initRecyclerView();
                    }
                }else {
                    Toast.makeText(ViewSavedPlace.this, "You haven't any saved places", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewSavedPlace.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        SavedPlaceViewAdapter savedPlaceViewAdapter = new SavedPlaceViewAdapter(savedPlaces,this);
        recyclerView.setAdapter(savedPlaceViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void gotoAddSavedPlace(View view){
        startActivity(new Intent(this,AddSavedPlace.class));
    }
}