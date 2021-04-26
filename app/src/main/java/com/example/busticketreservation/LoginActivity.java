package com.example.busticketreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth frb;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        frb = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.pBar);


    }

    public void register(View view){
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void signOut(View view){
        FirebaseAuth.getInstance().signOut();
    }
}