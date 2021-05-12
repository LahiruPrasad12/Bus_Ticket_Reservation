package com.example.busticketreservation.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.busticketreservation.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewRoute extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button editBtn, deleteBtn;
    private DrawerLayout drawer;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_route);
//        TextView twViewRoute = findViewById(R.id.twViewRoute);
        TextView tvRouteNo = findViewById(R.id.tv_routeNo);
        TextView tvFrom = findViewById(R.id.tv_from);
        TextView tvTo = findViewById(R.id.tv_to);
        TextView tvStops = findViewById(R.id.tv_stops);
        TextView tvFullPrice = findViewById(R.id.tv_fullPrice);

//        //Initializing string variables to get from intent
//        String routNo = "";
//        String from = "";
//        String to = "";
//        Integer price = 0;

        //Getting Intent with values
        Intent i =getIntent();
        Routes route = (Routes)i.getSerializableExtra("routeObj");

//        //assigning intent values to variables
//        routNo = route.getRouteNo();
//        from = route.getFrom();
//        to = route.getTo();
//        price = route.getPrice();

        //changing text of text views according to the variables
        tvRouteNo.setText(route.getRouteNo());
        tvFrom.setText(route.getFrom());
        tvTo.setText(route.getTo());
        tvStops.setText(String.valueOf(route.getNoOfStops()));
        tvFullPrice.setText(String.valueOf(route.getFullRoutePrice()));


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


        //on click listner for delete button
        deleteBtn = (Button) findViewById(R.id.admin_btn_deleteRoute);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Routes_Admin").child("-M_85giM4xbv-yEzoPOR");
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(), "Successfully delted", Toast.LENGTH_SHORT).show();
            }
        });



        //      using the toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.hash_toolbar);
        setSupportActionBar(toolbar);

//        getting the drawer layout
        drawer = findViewById(R.id.hash_drawer_layout);

        //        listen to click events of the navigation view
        NavigationView navigationView = findViewById(R.id.hash_nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        get the menu button in the top left corner
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    //    passing menu item to setNavigationItemSelectedListener
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_all_routes:
                startActivity(new Intent(getApplicationContext(), AllRoutes.class));
                break;
            case R.id.nav_add_routes:
                startActivity(new Intent(getApplicationContext(), AddRoutes.class));
                break;
            case R.id.nav_all_users:
                startActivity(new Intent(getApplicationContext(), AllUsers.class));
                break;
            case R.id.nav_add_users:
                startActivity(new Intent(getApplicationContext(), AddUsers.class));
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
//    close navigation bar when back button is clicked
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }


}







