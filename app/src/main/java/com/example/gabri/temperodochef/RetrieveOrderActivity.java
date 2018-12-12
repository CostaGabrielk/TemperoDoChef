package com.example.gabri.temperodochef;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.gabri.temperodochef.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrieveOrderActivity extends AppCompatActivity {

    ListView listViewOrders;

    List<Order> orderList;

    private DatabaseReference databaseOrders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_order);

        databaseOrders = FirebaseDatabase.getInstance().getReference("pedidos").child("OrderID");

        listViewOrders = (ListView) findViewById(R.id.lstViewOrders);

        orderList = new ArrayList<>();

       /* Query query = FirebaseDatabase.getInstance().getReference("pedidos")
                .orderByChild("OrderId");
        query.addValueEventListener(ValueEventListener);*/
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot orderSnapshot: dataSnapshot.getChildren()){
                    Order order = orderSnapshot.getValue(Order.class);
                    orderList.add(order);
                }

                OrderList adapter = new OrderList(RetrieveOrderActivity.this, orderList);
                listViewOrders.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}
