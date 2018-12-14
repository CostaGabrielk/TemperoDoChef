package com.example.gabri.temperodochef;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrieveActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RetrieveAdapter retrieveAdapter;
    private List<OrderR> retrieveList;

    private DatabaseReference dbRetrieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        retrieveList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewRetrieve);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        retrieveAdapter = new RetrieveAdapter(this, retrieveList);
        recyclerView.setAdapter(retrieveAdapter);

        dbRetrieve = FirebaseDatabase.getInstance().getReference("pedidos");
        Query query = FirebaseDatabase.getInstance().getReference("pedidos").orderByChild("clientOrderId");
        query.addListenerForSingleValueEvent(valueEventListener);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            retrieveList.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    OrderR orderR = dataSnapshot1.getValue(OrderR.class);

                    retrieveList.add(orderR);
                }

                retrieveAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
