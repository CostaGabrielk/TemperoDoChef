package com.example.gabri.temperodochef;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText editTextPesquisar;
    RecyclerView recyclerView;
    DatabaseReference databaseClients;


    ArrayList<String> nomeClienteList;
    ArrayList<String> foneClienteList;
    ArrayList<String> enderecoClienteList;

    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextPesquisar = (EditText) findViewById(R.id.edtPesquisa);
        recyclerView = findViewById(R.id.rcvClients);

        databaseClients = FirebaseDatabase.getInstance().getReference();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        nomeClienteList = new ArrayList<>();
        foneClienteList = new ArrayList<>();
        enderecoClienteList = new ArrayList<>();

        editTextPesquisar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());

                }else{
                    nomeClienteList.clear();
                    foneClienteList.clear();
                    enderecoClienteList.clear();
                    recyclerView.removeAllViews();

                }
            }
        });
    }

    private void setAdapter(final String textoProcurado) {



        databaseClients.child("clientes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                nomeClienteList.clear();
                foneClienteList.clear();
                enderecoClienteList.clear();
                recyclerView.removeAllViews();

                int count = 0;

                for (DataSnapshot clientSnapshot : dataSnapshot.getChildren()) {


                    String clienteID = clientSnapshot.getKey();
                    String clienteNome = clientSnapshot.child("clienteNome").getValue(String.class);
                    String clienteFone = clientSnapshot.child("clienteFone").getValue(String.class);
                    String clienteEndereco = clientSnapshot.child("clienteEndereco").getValue(String.class);

                    if (clienteNome.toLowerCase().contains(textoProcurado.toLowerCase())) {

                        nomeClienteList.add(clienteNome);
                        foneClienteList.add(clienteFone);
                        enderecoClienteList.add(clienteEndereco);
                        count++;

                    } else if (clienteFone.toLowerCase().contains(textoProcurado.toLowerCase())) {

                        nomeClienteList.add(clienteNome);
                        foneClienteList.add(clienteFone);
                        enderecoClienteList.add(clienteEndereco);
                        count++;
                    }

                    if(count==15){
                        break;
                    }

                    searchAdapter = new SearchAdapter(SearchActivity.this, nomeClienteList, foneClienteList, enderecoClienteList);
                    recyclerView.setAdapter(searchAdapter);
                }

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
