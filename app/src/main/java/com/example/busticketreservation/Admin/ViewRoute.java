package com.example.busticketreservation.Admin;

import androidx.appcompat.app.AppCompatActivity;
import com.example.busticketreservation.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewRoute extends AppCompatActivity {

    Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_route);
        TextView twViewRoute = findViewById(R.id.twViewRoute);
        TextView tvRouteNo = findViewById(R.id.tv_routeNo);
        TextView tvFrom = findViewById(R.id.tv_from);
        TextView tvTo = findViewById(R.id.tv_to);
        TextView tvPrice = findViewById(R.id.tv_price);

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
        tvRouteNo.setText(route.getRouteNo());
        tvFrom.setText(route.getFrom());
        tvTo.setText(route.getTo());
        tvPrice.setText(String.valueOf(route.getPrice()));


        //redirect to update route activity when edit button is clicked
        editBtn = (Button) findViewById(R.id.admin_btn_editRoute);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("edit button clicked");
                Intent intent = new Intent(getApplicationContext(), UpdateRoute.class);
                intent.putExtra("routeObj", route);
                startActivity(intent);
            }
        });


    }
}