package com.example.busticketreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class FindBus extends AppCompatActivity {

    EditText routeNumber;
    String name = "lahiru";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_bus);

        routeNumber = findViewById(R.id.routeNumber);
        routeNumber.setText(name);
    }
}