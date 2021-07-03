package com.example.busticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";

    FirebaseAuth frb;
    EditText txtName,txtPhone,txtPassword,txtComPassword;
    String name,mail,pass,comPass;
    FirebaseFirestore fStore;
    String userId;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        frb = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        txtName = findViewById(R.id.editTextName);
        txtPhone = findViewById(R.id.editTextPhone);
        txtPassword = findViewById(R.id.editTextPassword);
        txtComPassword = findViewById(R.id.editComPass);

    }

    //If user click already have an account user redirect to login page
    public void backToLogin(View view){
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }

    public void Register(View view){

        name = txtName.getText().toString().trim();
        mail = txtPhone.getText().toString().trim();
        pass = txtPassword.getText().toString().trim();
        comPass = txtComPassword.getText().toString().trim();

        //Check user enter data is correct or not
        if(TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Name Is Required", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(mail)) {
            Toast.makeText(this, "Phone Is Required", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Password Is Required", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(comPass)) {
            Toast.makeText(this, "Confirm Password Is Required", Toast.LENGTH_SHORT).show();
        }
        else if(pass.length()<6) {
            Toast.makeText(this, "Password Must Be >= 6", Toast.LENGTH_SHORT).show();
        }
        else if(pass.equals(comPass)) {

            //Set mail password authentication
            frb.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                        userId = frb.getCurrentUser().getUid();

                        //Register user
                        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                        Map<String,Object> user = new HashMap<>();
                        user.put("Name",name);
                        user.put("Mail",mail);
                        user.put("Roll","Customer");

                        databaseReference.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG,"user profile is created for "+ userId);
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            }
                        });
                    }else{
                        Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            Toast.makeText(this, "Confirm Password Is Incorrect", Toast.LENGTH_SHORT).show();

        }

    }
}