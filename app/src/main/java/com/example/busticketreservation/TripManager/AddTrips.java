package com.example.busticketreservation.TripManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.busticketreservation.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTrips extends AppCompatActivity {

    EditText trip_id, date , dep_time, ar_time;
    Button btcancel,btnaddtrip;
    Trip trp;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trips);

        trip_id = findViewById(R.id.addtripid);
        date = findViewById(R.id.add_date);
        dep_time = findViewById(R.id.txt_dep_time);
        ar_time = findViewById(R.id.txt_ar_time);

        btnaddtrip = findViewById(R.id.button_add_trip);



        /*navigate to select route */
        btcancel = findViewById(R.id.btncancel);
        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SelectRoutesTm.class));
            }
        });

        /*add data to the database */

        btnaddtrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Trip");

                try {
                    if (TextUtils.isEmpty(trip_id.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Empty ID",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(date.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Empty Date",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(dep_time.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Empty Depature Time",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(ar_time.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Empty Arrival Time",Toast.LENGTH_SHORT).show();
                    else {

                        trp = new Trip();
                        trp.setTrip_id(trip_id.getText().toString().trim());
                        trp.setDate(date.getText().toString().trim());
                        trp.setDep_time(dep_time.getText().toString().trim());
                        trp.setAr_time(ar_time.getText().toString().trim());
//                        dbref.child("Trip").setValue(trp);

                        dbref.push().setValue(trp);
                        Toast.makeText(getApplicationContext(),"Succesfully inserted",Toast.LENGTH_SHORT).show();
                        clearControls();

                    }
                }
                catch (NumberFormatException nfe){
                    Toast.makeText(getApplicationContext(),"Please Enter a Number In here",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }






    /*to clear text fields*/

    private void clearControls(){
        trip_id.setText("");
        date.setText("");
        dep_time.setText("");
        ar_time.setText("");
    }


}