package com.example.busticketreservation.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.busticketreservation.LoginActivity;
import com.example.busticketreservation.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class AddRoutes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //    creating objects to get reference from xml file
    EditText routeNO, from, to, noOfStops, basePrice, stopPrice;
    int stops, bPrice, sPrice, fullRoutePrice;
    Button addRouteBtn;
    DatabaseReference dbRef;
    Routes routes;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routes);

       //nav and tool bar
        Toolbar toolbar = findViewById(R.id.hash_toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.hash_drawer_layout);
        NavigationView navigationView = findViewById(R.id.hash_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //getting reference from xml elements
        routeNO = findViewById(R.id.admin_edit_routeno);
        from = findViewById(R.id.admin_edit_from);
        to = findViewById(R.id.admin_edit_to);
        noOfStops = findViewById(R.id.admin_edit_noOfStops);
        basePrice = findViewById(R.id.admin_edit_basePrice);
        stopPrice = findViewById(R.id.admin_edit_stopPrice);

        addRouteBtn = findViewById(R.id.admin_btn_addRoute);
        routes = new Routes();

        // add route button onClickListener
        addRouteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData(view);
            }
        });
    }


    //method to clear text values when data is inserted
    protected void clearControls() {
        routeNO.setText("");
        from.setText("");
        to.setText("");
        noOfStops.setText("");
        basePrice.setText("");
        stopPrice.setText("");
    }


    //method to calculate fullRoutePrice
    public int calFullRoutePrice(int stops, int bPrice, int sPrice) {

        return bPrice + (stops - 1) * sPrice;

    }


    //method to insert route data
    public void insertData(View view) {

        // connecting to the database and referring Routes_Admin table
        dbRef = FirebaseDatabase.getInstance().getReference().child("Routes_Admin");

        //validating input fields
        try {
            if (TextUtils.isEmpty(routeNO.getText().toString()))
                Toast.makeText(getApplicationContext(), "Empty Route No", Toast.LENGTH_SHORT).show();

            else if (TextUtils.isEmpty(from.getText().toString()))
                Toast.makeText(getApplicationContext(), "Empty From", Toast.LENGTH_SHORT).show();

            else if (TextUtils.isEmpty(to.getText().toString()))
                Toast.makeText(getApplicationContext(), "Empty To", Toast.LENGTH_SHORT).show();

            else if (TextUtils.isEmpty(noOfStops.getText().toString()))
                Toast.makeText(getApplicationContext(), "Empty noOfStops", Toast.LENGTH_SHORT).show();

            else if (TextUtils.isEmpty(basePrice.getText().toString()))
                Toast.makeText(getApplicationContext(), "Empty basePrice", Toast.LENGTH_SHORT).show();

            else if (TextUtils.isEmpty(stopPrice.getText().toString()))
                Toast.makeText(getApplicationContext(), "Empty stopPrice", Toast.LENGTH_SHORT).show();


            else {

                //getting inputs into int fields
                stops = Integer.parseInt(noOfStops.getText().toString().trim());
                bPrice = Integer.parseInt(basePrice.getText().toString().trim());
                sPrice = Integer.parseInt(stopPrice.getText().toString().trim());

                //calling method for calculation
                fullRoutePrice = calFullRoutePrice(stops, bPrice, sPrice);


                //setting values to routes object
                routes.setRouteNo(routeNO.getText().toString().trim());
                routes.setFrom(from.getText().toString().trim());
                routes.setTo(to.getText().toString().trim());
                routes.setNoOfStops(stops);
                routes.setBasePrice(bPrice);
                routes.setStopPrice(sPrice);
                routes.setFullRoutePrice(fullRoutePrice);

                //pushing routes object into Routes_Admin table
                dbRef.push().setValue(routes);
                Toast.makeText(getApplicationContext(), "Route added Successfully", Toast.LENGTH_SHORT).show();
                clearControls();
            }
        } catch (NumberFormatException nfe) {
            Toast.makeText(getApplicationContext(), "Stops and Prices Should be numbers", Toast.LENGTH_SHORT).show();
        }
    }

//        passing menu item to setNavigationItemSelectedListener
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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
            case R.id.logOut:
                Toast.makeText(this, "logging out", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
//    close navigation bar when back button is clicked
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}