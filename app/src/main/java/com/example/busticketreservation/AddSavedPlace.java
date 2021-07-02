package com.example.busticketreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSavedPlace extends AppCompatActivity {

    EditText name,routeNumber;
    private String Name,userId,RouteNum;
    FirebaseAuth frbAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_saved_place);
        getSupportActionBar().setTitle("Add Saved Place");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        frbAuth = FirebaseAuth.getInstance();
        userId = frbAuth.getCurrentUser().getUid();
        name = findViewById(R.id.savedName);
        routeNumber = findViewById(R.id.SavedRouteNumber);
    }

    public void addSavedPlace(View view){
        Name = name.getText().toString();
        RouteNum = routeNumber.getText().toString();


        if(TextUtils.isEmpty(Name)){
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(RouteNum)){
            Toast.makeText(this, "Please enter route number", Toast.LENGTH_SHORT).show();
        }else {
            SavedPlace savedPlace = new SavedPlace();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Saved_Place");
            savedPlace.setName(Name);
            savedPlace.setRouteNumber(RouteNum);
            savedPlace.setUserId(userId);
            databaseReference.push().setValue(savedPlace);
            Toast.makeText(this, "Saved your favourite place successful", Toast.LENGTH_SHORT).show();
        }
    }
}