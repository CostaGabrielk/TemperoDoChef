package com.example.gabri.temperodochef;

import android.content.DialogInterface;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.gabri.temperodochef.model.Client;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClientActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String CLIENTE_ID = "clienteID";
    public static final String NOME_CLIENTE = "nomeCliente";

    private EditText edtNome;
    private EditText edtFone;
    private EditText edtEndereco;
    private Button buttonSalvar;
    private Button buttonClientes;

    DatabaseReference databaseClients;

    ListView listViewClients;
    List<Client> clientList;

    LinearLayout clientRegister;
    RelativeLayout clientsInfo;
    RelativeLayout buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        edtNome = (EditText) findViewById(R.id.txtNome);
        edtFone = (EditText) findViewById(R.id.txtFone);
        edtEndereco = (EditText) findViewById(R.id.txtEndereco);
        buttonSalvar = (Button) findViewById(R.id.btnSalvar);
        buttonClientes = (Button) findViewById(R.id.btnClientes);

        listViewClients = (ListView) findViewById(R.id.listViewClients);
        clientList = new ArrayList<>();
        clientRegister = (LinearLayout) findViewById(R.id.clientRegister);
        clientsInfo = (RelativeLayout) findViewById(R.id.clientsInfo);
        buttons = (RelativeLayout) findViewById(R.id.buttons);

        buttonSalvar.setOnClickListener(this);
        buttonClientes.setOnClickListener(this);

        databaseClients = FirebaseDatabase.getInstance().getReference("clientes");


        listViewClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Client cliente = clientList.get(i);

                Intent intent = new Intent(getApplicationContext(), AddOrderActivity.class);
                intent.putExtra(CLIENTE_ID, cliente.getClienteId());
                intent.putExtra(NOME_CLIENTE, cliente.getClienteNome());

                startActivity(intent);
            }
        });

        listViewClients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {

                Client cliente = clientList.get(i);
                showUpdateDialog(cliente.getClienteId(), cliente.getClienteNome());


                return true;
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseClients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //clear the list if have any artist in your firs execute, i guess
                clientList.clear();

                for (DataSnapshot clientSnapshot : dataSnapshot.getChildren()) {

                    Client client = clientSnapshot.getValue(Client.class);
                    clientList.add(client);
                }

                ClientList adapter = new ClientList(ClientActivity.this, clientList);
                listViewClients.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void showUpdateDialog(final String clientID, String clientName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.updateclient_dialog, null);

        dialogBuilder.setView(dialogView);

        final EditText editTextNome = (EditText) dialogView.findViewById(R.id.txtNome);
        final EditText editTextFone = (EditText) dialogView.findViewById(R.id.txtFone);
        final EditText editTextEndereco = (EditText) dialogView.findViewById(R.id.txtEndereco);
        final Button buttonAlterar = (Button) dialogView.findViewById(R.id.btnAlterar);
        final Button buttonApagar = (Button) dialogView.findViewById(R.id.btnApagar);

        dialogBuilder.setTitle("Alterar Cliente: " + clientName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = editTextNome.getText().toString().trim();
                String fone = editTextFone.getText().toString().trim();
                String endereco = editTextEndereco.getText().toString().trim();

                //check if all the edit text are filled with info
                if ((!TextUtils.isEmpty(nome)) && (!TextUtils.isEmpty(fone)) && (!TextUtils.isEmpty(endereco))) {

                    updateClient(clientID, nome, fone, endereco);
                    alertDialog.dismiss();


                }else {
                    editTextNome.setError("Digite o nome");
                    editTextFone.setError("Digite o Telefone");
                    editTextEndereco.setError("Digite o Endereço");
                }

            }
        });

        buttonApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Isso é um dialog com a opção de cancelar o apagamento
                AlertDialog.Builder builder = new AlertDialog.Builder(ClientActivity.this);
                builder.setMessage("Você tem certeza que quer apagar esse cliente?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseReference databaseReferenceClients = FirebaseDatabase.getInstance().getReference("clientes").child(clientID);
                                DatabaseReference databaseReferenceOrders = FirebaseDatabase.getInstance().getReference("pedidos").child(clientID);

                                databaseReferenceClients.removeValue();
                                databaseReferenceOrders.removeValue();
                                dialog.dismiss();
                                alertDialog.dismiss();

                            }
                        }).setNegativeButton("Cancel", null);

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }


    private boolean updateClient(String id, String nome, String fone, String endereco){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("clientes").child(id);

        Client cliente = new Client(id, nome, fone, endereco);
        databaseReference.setValue(cliente);

        Toast.makeText(this, "Cliente atualizado com Sucesso", Toast.LENGTH_LONG).show();

        return true;

    }

    private void addClient() {

        String nome = edtNome.getText().toString().trim();
        String fone = edtFone.getText().toString().trim();
        String endereco = edtEndereco.getText().toString().trim();

        //check if all the edit text are filled with info
        if ((!TextUtils.isEmpty(nome)) && (!TextUtils.isEmpty(fone)) && (!TextUtils.isEmpty(endereco))) {

            //create an unique line inside clientes table
            String id = databaseClients.push().getKey();

            //create a new client passing the parameters to the constructor
            Client client = new Client(id, nome, fone, endereco);


            databaseClients.child(id).setValue(client);
            Toast.makeText(this, "Adicionando cliente...", Toast.LENGTH_SHORT).show();
            clientRegister.setVisibility(View.GONE);
            buttons.setVisibility(View.GONE);
            clientsInfo.setVisibility(View.VISIBLE);

        } else {

            Toast.makeText(this, "Preencha todos os Campos", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onClick(View v) {

        if (v == buttonSalvar) {
            addClient();
        }
        if (v == buttonClientes) {
            clientRegister.setVisibility(View.GONE);
            buttons.setVisibility(View.GONE);
            clientsInfo.setVisibility(View.VISIBLE);
        }

    }

}
