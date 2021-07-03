package com.example.busticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    TextView From,To,tx3;
    String from,to,depTime,arTime;
    FirebaseAuth frbAuth;
    FirebaseFirestore fStore;
    String useId ;
    public static final String TAG = "TAG";
    String busNo;
    ProgressBar progressBar;
    private int count = 0;
    private int numRetrieve = 0;

    private DatabaseReference databaseReference;
    private ArrayList<String> BusNo = new ArrayList<>();
    private ArrayList<String> Location = new ArrayList<>();
    private ArrayList<String> Time = new ArrayList<>();
    private Context mContext;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        drawerLayout=findViewById(R.id.tx1111);
        toolbar=findViewById(R.id.toolBar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//        navigationView=findViewById(R.id.nav_view);


        //Navigation  Bar
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                Fragment fragment=null;
                switch (id)
                {
                    case R.id.Trips:

                        break;
                    case R.id.wallet:

                        break;
                    case R.id.Helps:

                        break;

                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext(),ViewUserProfile.class));

                        break;
                    case R.id.logOut:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }
}
