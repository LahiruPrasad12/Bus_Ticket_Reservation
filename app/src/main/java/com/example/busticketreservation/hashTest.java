package com.example.busticketreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class hashTest extends AppCompatActivity {

    EditText txtId, txtName, txtAdd, txtConNo;
    Button btnSave, btnShow, btnUpdate, btnDelete, btnNext;
    DatabaseReference dbRef;
    Student std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_test);

        txtId = findViewById(R.id.etInputID);
        txtName = findViewById(R.id.etInputName);
        txtAdd = findViewById(R.id.etInputAdd);
        txtConNo = findViewById(R.id.etInputConNo);

        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnNext = findViewById(R.id.btnNext);


        std = new Student();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Sample");
                try {
                    if(TextUtils.isEmpty(txtId.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty ID", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Name", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtAdd.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Address", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtConNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Contact No", Toast.LENGTH_SHORT).show();
                    else{
                        std.setId(txtId.getText().toString().trim());
                        std.setName(txtName.getText().toString().trim());
                        std.setAddress(txtAdd.getText().toString().trim());
                        std.setContact(Integer.parseInt(txtConNo.getText().toString().trim()));
                        dbRef.child("testCase1").setValue(std);
                        Toast.makeText(getApplicationContext(), "Student Inserted Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                }
                catch (NumberFormatException nfe){
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private void clearControls(){
        txtId.setText("");
        txtName.setText("");
        txtAdd.setText("");
        txtConNo.setText("");
    }

}