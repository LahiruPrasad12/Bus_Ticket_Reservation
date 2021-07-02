package com.example.busticketreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewSavedPlace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_saved_place);

        getSupportActionBar().setTitle("Choose Saved Place");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void gotoAddSavedPlace(View view){
        startActivity(new Intent(this,AddSavedPlace.class));
    }
}