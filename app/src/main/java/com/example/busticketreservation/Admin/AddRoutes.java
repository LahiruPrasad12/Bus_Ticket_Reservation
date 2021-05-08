package com.example.busticketreservation.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.busticketreservation.BusOwner.Bus;
import com.example.busticketreservation.R;
import com.example.busticketreservation.Student;
import com.example.busticketreservation.ViewUserProfile;
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

public class AddRoutes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    creating objects to get reference from xml file
    EditText routeNO, from, to, price;
    Button addRouteBtn;
    DatabaseReference dbRef;
    Routes routes;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routes);


        //        getting reference from xml elements
        routeNO = findViewById(R.id.admin_edit_routeno);
        from = findViewById(R.id.admin_edit_from);
        to = findViewById(R.id.admin_edit_to);
        price = findViewById(R.id.admin_edit_price);

        addRouteBtn = findViewById(R.id.admin_btn_addRoute);

        routes = new Routes();

        //        add route button onClickListner
        addRouteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Routes_Admin");

                try {
                    if (TextUtils.isEmpty(routeNO.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Empty Route No",Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(from.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Empty From",Toast.LENGTH_SHORT).show();

                    else  if (TextUtils.isEmpty(to.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Empty To",Toast.LENGTH_SHORT).show();

                    else   if(TextUtils.isEmpty(price.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Empty Price",Toast.LENGTH_SHORT).show();

                    else {
                        routes.setRouteNo(routeNO.getText().toString().trim());
                        routes.setFrom(from.getText().toString().trim());
                        routes.setTo(to.getText().toString().trim());
                        routes.setPrice(Integer.parseInt(price.getText().toString().trim()));

                        dbRef.push().setValue(routes);
                        Toast.makeText(getApplicationContext(),"Route added Successfully",Toast.LENGTH_SHORT).show();
                        clearControls();
                    }



                }
                catch (NumberFormatException nfe){
                    Toast.makeText(getApplicationContext(),"Invalid Price",Toast.LENGTH_SHORT).show();

                }

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
//method to clear text values when data is inserted
    private void clearControls(){
        routeNO.setText("");
        from.setText("");
        to.setText("");
        price.setText("");
    }
}