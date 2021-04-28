//package com.example.busticketreservation;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.List;
//
//public class Bus_View_Adapter extends ArrayAdapter {
//
//    public static final String TAG = "TAG";
//    private Context context;
//    private int resource;
//    TextView tx1,tx2,tx3;
//    String from,to,No;
//    FirebaseAuth frbAuth;
//    FirebaseFirestore fStore;
//    String useId ;
//    String frm = "Kottava";
//    String trm = "Matara";
//
//
//    public Bus_View_Adapter(@NonNull Context context, int resource, List<QuerySnapshot> q1) {
//        super(context, resource,q1);
//        this.context = context;
//        this.resource = resource;
//
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View row = inflater.inflate(resource,parent,false);
//
//        frbAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();
//
//
//
//        TextView BusNo = row.findViewById(R.id.bus_No);
//        Button btn = row.findViewById(R.id.View);
//        TextView Loca = row.findViewById(R.id.Location);
//        TextView Time = row.findViewById(R.id.Time);
//
//        fStore.collection("Routes").whereEqualTo("From",frm).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()){
//                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
//                        BusNo.setText(documentSnapshot.getString("Bus_No"));
//                        Loca.setText("Success");
//                    }
//                }
//
//            }
//        });
//
//        return row;
//    }
//}
//
//
////    public void FindBus(View view) {
////
////        LinearLayout linearLayout;
////        linearLayout = (LinearLayout) findViewById(R.id.layout);
////        TableRow tableRow = new TableRow(this);
////        TableLayout tableLayout = new TableLayout(this);
////
////        tableLayout.setLayoutParams(new TableLayout.LayoutParams(
////                TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT
////        ));
////
////        frbAuth = FirebaseAuth.getInstance();
////        fStore = FirebaseFirestore.getInstance();
////        useId = frbAuth.getCurrentUser().getUid();
////        fStore.collection("Routes").whereEqualTo("From", frm).whereEqualTo("To", trm).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
////            @Override
////            public void onComplete(@NonNull Task<QuerySnapshot> task) {
////                if (task.isSuccessful()) {
////                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
////                    for (QueryDocumentSnapshot document : task.getResult()) {
////
////                        tableRow.setLayoutParams((new TableRow.LayoutParams(
////                                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT
////                        )));
////
////                        TextView textView = new TextView(MainActivity.this);
////
////                        textView.setText("Bus No " + document.getString("Bus_No") + "                                                                    "
////                                + "View More" + "\n\n" + document.getString("From") + " - " + document.getString("To"));
////
////                        textView.setLayoutParams(new TableRow.LayoutParams(
////                                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT
////                        ));
////
////                        tableRow.addView(textView);
////                        tableLayout.addView(tableRow);
////                    }
////                    linearLayout.addView(tableLayout);
////                } else {
////                    Toast.makeText(MainActivity.this, "Not", Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
////
////    }