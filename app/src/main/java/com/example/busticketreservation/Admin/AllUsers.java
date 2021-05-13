package com.example.busticketreservation.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
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

import java.util.ArrayList;

public class AllUsers extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    private DrawerLayout drawer;
    private String userType;
    Button btnSearch, btnDelete;
    EditText edTxtMail;
    TextView roll, name, phone, email;
    private DatabaseReference dbRef;
    ArrayList<User> list;
    private String userKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

//      nav and toolbar
        Toolbar toolbar = findViewById(R.id.hash_toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.hash_drawer_layout);
        NavigationView navigationView = findViewById(R.id.hash_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        edTxtMail = findViewById(R.id.edTxtMail);
        btnSearch = findViewById(R.id.btnSearch);
        btnDelete = findViewById(R.id.admin_btn_deleteUser);
        roll = findViewById(R.id.tv_roll);
        name = findViewById(R.id.tv_name);
        phone = findViewById(R.id.tv_phone);
        email = findViewById(R.id.tv_email);
        System.out.println("Ed: "+edTxtMail);


        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                userKey = searchUser();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog diaBox = AskOption();
                diaBox.show();
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

    // methods for spinner item selected
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        userType = adapterView.getItemAtPosition(pos).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        userType = null;
    }

    //method to search user by email address
    public String searchUser(){
        String search = edTxtMail.getText().toString();


        // connecting to the database and referring users table
        try{
            Query query = FirebaseDatabase.getInstance().getReference().child("Users")
                    .orderByChild("Mail")
                    .equalTo(search);
            query.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        //getting key for deleting purpose
                        userKey = dataSnapshot.getKey();

                        //creating user object with retrieved data
                        User user = dataSnapshot.getValue(User.class);



                        roll.setText(user.getRoll());
                        name.setText(user.getName());
                        phone.setText(user.getPhone());
                        email.setText(user.getMail());
                    }

                    if(userKey==null){
                        Toast.makeText(getApplicationContext(), "No user with"+search+"email", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "User fetched successfully", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "No user"+search+"email", Toast.LENGTH_SHORT).show();
                }
            });


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
        }
        return userKey;
    }

    public void deleteUSer(){
        dbRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userKey);
        dbRef.removeValue();
        Toast.makeText(getApplicationContext(), "User Deleted Successfully ", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), AddUsers.class));
    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Delete User")
                .setMessage("Are you sure you wanna delete?")

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int Yes) {
                        deleteUSer();

                        dialog.dismiss();
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int No) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }


}