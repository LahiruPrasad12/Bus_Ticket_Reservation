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
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateRoute extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    EditText routeNOE, fromE, toE, stopsE, bPriceE, sPriceE;
    int stops, bPrice, sPrice, fullRoutePrice;
    private DrawerLayout drawer;
    Button updateButton;
    DatabaseReference dbRef;
    Routes routes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_route);

        //setting current text into edit text fields
        routeNOE = findViewById(R.id.admin_edit_routeno);
        fromE = findViewById(R.id.admin_edit_from);
        toE = findViewById(R.id.admin_edit_to);
        stopsE = findViewById(R.id.admin_edit_noOfStops);
        bPriceE = findViewById(R.id.admin_edit_basePrice);
        sPriceE = findViewById(R.id.admin_edit_stopPrice);
        updateButton = findViewById(R.id.admin_btn_updateRoute);

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
        routeNOE.setText(route.getRouteNo());
        fromE.setText(route.getFrom());
        toE.setText(route.getTo());
        stopsE.setText(String.valueOf(route.getNoOfStops()));
        bPriceE.setText(String.valueOf(route.getBasePrice()));
        sPriceE.setText(String.valueOf(route.getStopPrice()));


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


//        update route button onClickListener
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference();

                //getting inputs into int fields
                stops = Integer.parseInt(stopsE.getText().toString().trim());
                bPrice = Integer.parseInt(bPriceE.getText().toString().trim());
                sPrice = Integer.parseInt(sPriceE.getText().toString().trim());

                //calculation
                fullRoutePrice = bPrice + (stops-1) * sPrice;
                try {

                    dbRef.child("Routes_Admin").child("-M_85giM4xbv-yEzoPOR").child("routeNo").setValue(routeNOE.getText().toString().trim());
                    dbRef.child("Routes_Admin").child("-M_85giM4xbv-yEzoPOR").child("from").setValue(fromE.getText().toString().trim());
                    dbRef.child("Routes_Admin").child("-M_85giM4xbv-yEzoPOR").child("to").setValue(toE.getText().toString().trim());
                    dbRef.child("Routes_Admin").child("-M_85giM4xbv-yEzoPOR").child("noOfStops").setValue(stops);
                    dbRef.child("Routes_Admin").child("-M_85giM4xbv-yEzoPOR").child("basePrice").setValue(bPrice);
                    dbRef.child("Routes_Admin").child("-M_85giM4xbv-yEzoPOR").child("stopPrice").setValue(sPrice);
                    dbRef.child("Routes_Admin").child("-M_85giM4xbv-yEzoPOR").child("fullRoutePrice").setValue(fullRoutePrice);

                    Toast.makeText(getApplicationContext(), "update success", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException nfe) {
                    Toast.makeText(getApplicationContext(), "Invalid Price", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
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