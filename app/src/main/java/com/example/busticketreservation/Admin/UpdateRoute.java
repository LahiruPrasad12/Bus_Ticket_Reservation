package com.example.busticketreservation.Admin;

import androidx.appcompat.app.AppCompatActivity;
import com.example.busticketreservation.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class UpdateRoute extends AppCompatActivity {

    EditText routeNOE, fromE, toE, priceE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_route);

        //setting current text into edit text fields
        routeNOE = findViewById(R.id.admin_edit_routeno);
        fromE = findViewById(R.id.admin_edit_from);
        toE = findViewById(R.id.admin_edit_to);
        priceE = findViewById(R.id.admin_edit_price);

        //Initializing string variables to get from intent
        String routNo = "";
        String from = "";
        String to = "";
        Integer price = 0;

        //Getting Intent with values
        Intent i =getIntent();
        Routes route = (Routes)i.getSerializableExtra("routeObj");

        //assigning intent values to variables
        routNo = route.getRouteNo();
        from = route.getFrom();
        to = route.getTo();
        price = route.getPrice();

        //changing text of text views according to the variables
        routeNOE.setText(route.getRouteNo());
        fromE.setText(route.getFrom());
        toE.setText(route.getTo());
        priceE.setText(String.valueOf(route.getPrice()));
    }
}