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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";

    FirebaseAuth frb;
    EditText txtName,txtPhone,txtPassword,txtComPassword;
    String name,phone,pass,comPass;
    FirebaseFirestore fStore;
    String userId;



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



    public void backToLogin(View view){

        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }

    public void Login(View view){

        name = txtName.getText().toString().trim();
        phone = txtPhone.getText().toString().trim();
        pass = txtPassword.getText().toString().trim();
        comPass = txtComPassword.getText().toString().trim();

        if(TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Name Is Required", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)) {
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

            frb.createUserWithEmailAndPassword(phone,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                        userId = frb.getCurrentUser().getUid();

                        DocumentReference documentReference = fStore.collection("Users").document(userId);
                        Map<String,Object> user = new HashMap<>();
                        user.put("Name",name);
                        user.put("Mail",phone);

                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG,"user profile is created for "+ userId);
                            }
                        });

                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
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