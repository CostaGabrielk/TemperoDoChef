package com.example.gabri.temperodochef;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabri.temperodochef.model.Client;
import com.example.gabri.temperodochef.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewNomeCliente;
    private Spinner spinnerOpcao;
    private Spinner spinnerTamanho;
    private Spinner spinnerPagto;
    private EditText editTextTroco;
    private Button buttonAdicionarPedido;

    private DatabaseReference databaseOrders;


    ListView listViewOrders;

    List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        textViewNomeCliente = (TextView) findViewById(R.id.txtNomeCliente);
        spinnerOpcao = (Spinner) findViewById(R.id.sprOpcao);
        spinnerTamanho = (Spinner) findViewById(R.id.sprTamanho);
        spinnerPagto = (Spinner) findViewById(R.id.sprPagto);
        editTextTroco = (EditText) findViewById(R.id.txtTroco);
        buttonAdicionarPedido = (Button) findViewById(R.id.btnAdicionarPedido);
        buttonAdicionarPedido.setOnClickListener(this);

        listViewOrders = (ListView) findViewById(R.id.listViewOrders);
        orderList = new ArrayList<>();

        Intent intent = getIntent();
        String idClient = intent.getStringExtra(ClientActivity.CLIENTE_ID);
        String nameClient = intent.getStringExtra(ClientActivity.NOME_CLIENTE);

        textViewNomeCliente.setText("Novo pedido do cliente: "+ nameClient);
        databaseOrders = FirebaseDatabase.getInstance().getReference("pedidos").child(idClient);


        spinnerPagto.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getSelectedItem().toString();

                if (selectedItem.equals("Dinheiro"))
                {
                    editTextTroco.setVisibility(View.VISIBLE);
                }else{
                    editTextTroco.setVisibility(View.GONE);
                }
            }

            public void onNothingSelected(AdapterView<?> parent)
            {
                String texto = "Não Possui troco";
                editTextTroco.setText(texto);
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();

        databaseOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                orderList.clear();

                for (DataSnapshot orderSnapshot: dataSnapshot.getChildren()){
                    Order order = orderSnapshot.getValue(Order.class);
                    orderList.add(order);
                }

                OrderList adapter = new OrderList(AddOrderActivity.this, orderList);
                listViewOrders.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void addOrder() {


        String opcao = spinnerOpcao.getSelectedItem().toString();
        String tamanho = spinnerTamanho.getSelectedItem().toString();
        String pgto = spinnerPagto.getSelectedItem().toString();
        String troco = editTextTroco.getText().toString().trim();



        if ((!TextUtils.isEmpty(opcao)) && (!TextUtils.isEmpty(tamanho)) && (!TextUtils.isEmpty(pgto))) {

            //take the id of the client and insert this order on client register
            String id = databaseOrders.push().getKey();

            Order order = new Order(id,opcao, tamanho, pgto, troco);
            databaseOrders.child(id).setValue(order);


            Toast.makeText(this, "Pedido efetuado com sucesso!", Toast.LENGTH_SHORT).show();
            editTextTroco.setVisibility(View.GONE);

        } else {

            Toast.makeText(this, "Selecione os detalhes do seu pedido", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdicionarPedido){
            addOrder();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

}
