package com.example.busticketreservation.Admin;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

        //Getting Intent with values
        Intent i =getIntent();
        Routes route = (Routes)i.getSerializableExtra("routeObj");

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

                //getting key of the route
                try{
                    Query query = FirebaseDatabase.getInstance().getReference().child("Routes_Admin")
                            .orderByChild("routeNo")
                            .equalTo(route.getRouteNo());
                    System.out.println(route.getRouteNo());
                    query.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                                String routeKey = dataSnapshot.getKey();
                                updateRoute(routeKey);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "Could not delete route", Toast.LENGTH_SHORT).show();
                        }
                    });


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
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
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    //update route when key is give
    public void updateRoute(String key){
        dbRef = FirebaseDatabase.getInstance().getReference();

        //getting inputs into int fields
        stops = Integer.parseInt(stopsE.getText().toString().trim());
        bPrice = Integer.parseInt(bPriceE.getText().toString().trim());
        sPrice = Integer.parseInt(sPriceE.getText().toString().trim());

        //calculation
        fullRoutePrice = bPrice + (stops-1) * sPrice;
        try {

            dbRef.child("Routes_Admin").child(key).child("routeNo").setValue(routeNOE.getText().toString().trim());
            dbRef.child("Routes_Admin").child(key).child("from").setValue(fromE.getText().toString().trim());
            dbRef.child("Routes_Admin").child(key).child("to").setValue(toE.getText().toString().trim());
            dbRef.child("Routes_Admin").child(key).child("noOfStops").setValue(stops);
            dbRef.child("Routes_Admin").child(key).child("basePrice").setValue(bPrice);
            dbRef.child("Routes_Admin").child(key).child("stopPrice").setValue(sPrice);
            dbRef.child("Routes_Admin").child(key).child("fullRoutePrice").setValue(fullRoutePrice);

            Toast.makeText(getApplicationContext(), "update success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), AllRoutes.class));

        } catch (NumberFormatException nfe) {
            Toast.makeText(getApplicationContext(), "Invalid Price", Toast.LENGTH_SHORT).show();

        }
    }


}