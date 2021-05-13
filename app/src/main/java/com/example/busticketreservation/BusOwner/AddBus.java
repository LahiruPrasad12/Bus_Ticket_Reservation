package com.example.busticketreservation.BusOwner;


import androidx.appcompat.app.AppCompatActivity;


import com.example.busticketreservation.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;



public class AddBus extends AppCompatActivity {

    EditText txtID,txtRoute,txtLicense,txtSeats;
    Button addBus;
    Button btnViewBus;
    DatabaseReference dbref;
    Bus bs;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        txtID = findViewById(R.id.pt1);
        txtRoute = findViewById(R.id.pt2);
        txtLicense = findViewById(R.id.pt3);
        txtSeats = findViewById(R.id.pt4);


        addBus = findViewById(R.id.bt1);
        bs = new Bus();




        btnViewBus = findViewById(R.id.btn3);
        btnViewBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ViewAllBus.class);
                startActivity(intent);
            }
        });


        addBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Db initializing
                dbref = FirebaseDatabase.getInstance().getReference().child("Bus");

                try {

                    // Validations
                    if (TextUtils.isEmpty(txtID.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter a valid ID",Toast.LENGTH_SHORT).show();

                   else if(TextUtils.isEmpty(txtRoute.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter a valid Route",Toast.LENGTH_SHORT).show();

                  else  if (TextUtils.isEmpty(txtLicense.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter a valid License Number",Toast.LENGTH_SHORT).show();

                 else   if(TextUtils.isEmpty(txtSeats.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter a valid Number of Seats",Toast.LENGTH_SHORT).show();



                    else {
                        bs.setID(txtID.getText().toString().trim());
                        bs.setRouteNo(txtRoute.getText().toString().trim());
                        bs.setLicenseNo(txtLicense.getText().toString().trim());
                        bs.setSeats(Integer.parseInt(txtSeats.getText().toString().trim()));


                        dbref.push().setValue(bs);
                        Toast.makeText(getApplicationContext(),"Bus added Successfully",Toast.LENGTH_SHORT).show();

                        clearControls();
                    }



                }
                catch (NumberFormatException nfe){
                    Toast.makeText(getApplicationContext(),"Please Enter a Number In here",Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    private void clearControls(){
        txtID.setText("");
        txtRoute.setText("");
        txtLicense.setText("");
        txtSeats.setText("");





    }

}