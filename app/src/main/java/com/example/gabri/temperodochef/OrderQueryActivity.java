package com.example.gabri.temperodochef;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.gabri.temperodochef.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class OrderQueryActivity extends AppCompatActivity {

    DatabaseReference databaseOrders;
    RecyclerView recyclerView;
    ArrayList<Order> orderArrayList;
    OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_query);


        recyclerView = (RecyclerView) findViewById(R.id.rcvOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        orderArrayList = new ArrayList<Order>();

        databaseOrders = FirebaseDatabase.getInstance().getReference().child("Pedido");
        databaseOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {

                    Order order = dataSnapshot1.getValue(Order.class);
                    orderArrayList.add(order);
                }
                adapter = new OrderAdapter(OrderQueryActivity.this, orderArrayList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(OrderQueryActivity.this, "Não há nenhum registro de pedido", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
