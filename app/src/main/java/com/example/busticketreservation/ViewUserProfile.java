package com.example.busticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ViewUserProfile extends AppCompatActivity {
    EditText txName,txMail;
    private String name,mail;

    FirebaseAuth frbAuth;
    FirebaseFirestore fStore;
    private String useId ;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_profile);


        txMail = findViewById(R.id.txM);
        txName = findViewById(R.id.numOfSeats);


        frbAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        useId = frbAuth.getCurrentUser().getUid();

//        DocumentReference documentReference = fStore.collection("Users").document(useId);


        //Retrieve login user details
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(useId);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txMail.setText(snapshot.child("Mail").getValue().toString());
                txName.setText(snapshot.child("Name").getValue().toString());
                Toast.makeText(ViewUserProfile.this, "User Profile", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewUserProfile.this, "Have Error", Toast.LENGTH_SHORT).show();
            }
        });

//        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if(task.isSuccessful()){
//                    DocumentSnapshot documentSnapshot = task.getResult();
//                    if(documentSnapshot.exists()){
//                        txMail.setText(documentSnapshot.getString("Mail"));
//                        txName.setText(documentSnapshot.getString("Name"));
//                        Toast.makeText(ViewUserProfile.this, "User Profile", Toast.LENGTH_SHORT).show();
//
//                    }else {
//
//                        Toast.makeText(ViewUserProfile.this, "Have Error", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            }
//        });
    }


    //Update login user details
    public void upDate(View view) {
        String name, mail;

        name = txName.getText().toString();
        mail = txMail.getText().toString();

        Map<String,Object> user = new HashMap<>();
        user.put("Mail",mail);
        user.put("Name",name);

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Name Is Required", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mail)) {
            Toast.makeText(this, "Mail is Required", Toast.LENGTH_SHORT).show();
        } else {
//            DocumentReference documentReference = fStore.collection("Users").document(useId);
            databaseReference.updateChildren(user);
            Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
        }
    }



    //Deactivate user account
    public void Delete(View view){

        //Create alert box to confirm deactivate request
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are You Sure");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            //If user entered yes button run this part
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(useId);
                databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ViewUserProfile.this, "Deactivating...", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().getCurrentUser().delete();
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ViewUserProfile.this, "Try Again", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //If user entered No Button run this part
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ViewUserProfile.this, "Cancelling...", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}