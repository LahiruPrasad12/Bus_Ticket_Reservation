package com.example.busticketreservation.Admin;

import androidx.appcompat.app.AppCompatActivity;
import com.example.busticketreservation.R;

import android.os.Bundle;
import android.widget.TextView;

public class ViewRoute extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_route);
        TextView twViewRoute = findViewById(R.id.twViewRoute);

        String routNo = "Route No not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            routNo = extras.getString("routeNo");
        }
        twViewRoute.setText(routNo);
    }
}