package com.example.busticketreservation.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.busticketreservation.R;
import com.google.android.material.navigation.NavigationView;

import android.os.Bundle;
import android.view.MenuItem;

public class AdminMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

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

//start all routes fragment immediately when main activity is started
        if(savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.hash_fragment_container,
                new AllRoutesFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_all_routes);}
    }

    @Override
//    passing menu item to setNavigationItemSelectedListener
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_all_routes:
                getSupportFragmentManager().beginTransaction().replace(R.id.hash_fragment_container,
                        new AllRoutesFragment()).commit();
                break;
            case R.id.nav_add_routes:
                getSupportFragmentManager().beginTransaction().replace(R.id.hash_fragment_container,
                        new AddRoutesFragment()).commit();
                break;
            case R.id.nav_all_users:
                getSupportFragmentManager().beginTransaction().replace(R.id.hash_fragment_container,
                        new AllUsersFragment()).commit();
                break;
            case R.id.nav_add_users:
                getSupportFragmentManager().beginTransaction().replace(R.id.hash_fragment_container,
                        new AddUsersFragment()).commit();
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