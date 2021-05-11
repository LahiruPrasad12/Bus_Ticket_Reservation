package com.example.busticketreservation.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.busticketreservation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddUsers extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    private DrawerLayout drawer;
    private String userType;
    EditText userName, contact, email, pwd;
    String un, con, mail, pass;
    Button submit;
    DatabaseReference dbRef;
    FirebaseAuth frb;
    String userId;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users);

//      using the toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.hash_toolbar);
        setSupportActionBar(toolbar);

//        getting the drawer layout
        drawer = findViewById(R.id.hash_drawer_layout);

        //       listen to click events of the navigation view
        NavigationView navigationView = findViewById(R.id.hash_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        get the menu button in the top left corner
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.user_type);
        //create a list of items for the spinner.
        String[] items = new String[]{"Bus Owner", "Bus Driver", "Trip Manager"};
        //create an adapter to describe how the items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);


        //getting clicked item of drop down lis
        Spinner spinner = (Spinner) findViewById(R.id.user_type);
        spinner.setOnItemSelectedListener(this);

        //setting edit text values
        userName = findViewById(R.id.user_name);
        contact = findViewById(R.id.contact_no);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        frb = FirebaseAuth.getInstance();

        submit = findViewById(R.id.admin_btn_addUser);



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

    // methods for spinner item selected
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        userType = adapterView.getItemAtPosition(pos).toString();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                un = userName.getText().toString();
                con = contact.getText().toString();
                mail = email.getText().toString();
                pass = pwd.getText().toString();

               // connecting to the database and referring users table
                dbRef = FirebaseDatabase.getInstance().getReference().child("Employees");

                //validating input fields
                if (TextUtils.isEmpty(userType))
                    Toast.makeText(getApplicationContext(), "Please Select A User Type", Toast.LENGTH_SHORT).show();

                else if (TextUtils.isEmpty(un))
                    Toast.makeText(getApplicationContext(), "Please Enter User Name", Toast.LENGTH_SHORT).show();

                else if (TextUtils.isEmpty(con))
                    Toast.makeText(getApplicationContext(), "Please Enter Contact Number", Toast.LENGTH_SHORT).show();

                else if (TextUtils.isEmpty(mail))
                    Toast.makeText(getApplicationContext(), "Please Enter User Email Address", Toast.LENGTH_SHORT).show();

                else if (TextUtils.isEmpty(pass))
                        Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();

                else {
                    try{
                        frb.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "User Created Successfully!", Toast.LENGTH_SHORT).show();
                                    userId = frb.getCurrentUser().getUid();

//                                    DocumentReference documentReference = fStore.collection("Users").document(userId);
                                    dbRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("Name", un);
                                    user.put("Mail", mail);
                                    user.put("Roll", userType);
                                    user.put("Phone", con);

                                    dbRef.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            clearControls();
                                        }
                                    });
                                }else{
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), (CharSequence) e, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        userType = null;
    }

    //method to clear text values when data is inserted
    protected void clearControls() {
        userName.setText("");
        contact.setText("");
        email.setText("");
        pwd.setText("");

    }

}