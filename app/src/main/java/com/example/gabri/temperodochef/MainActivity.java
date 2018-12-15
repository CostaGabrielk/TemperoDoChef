package com.example.gabri.temperodochef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCadastrarClientes;
    private  Button buttonConsulta;
    private Button buttonCardapio;
    private  Button buttonSair;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCadastrarClientes = (Button) findViewById(R.id.btnCadastrarCliente);
        buttonConsulta = (Button) findViewById(R.id.btnConsultarClientes);
        buttonCardapio = (Button) findViewById(R.id.btnCardapio);
        buttonSair = (Button) findViewById(R.id.btnSair);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonCadastrarClientes.setOnClickListener(this);
        buttonConsulta.setOnClickListener(this);
        buttonCardapio.setOnClickListener(this);
        buttonSair.setOnClickListener(this);

        //if there are no user logged, the login activity will be called
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

    }

    @Override
    public void onClick(View v) {

        if(v == buttonCadastrarClientes){
            finish();
            startActivity(new Intent(this, ClientActivity.class));
        }

        if(v == buttonConsulta){
            finish();
            startActivity(new Intent(this, SearchActivity.class));
        }

        if(v == buttonCardapio){
            finish();
            startActivity(new Intent(this, CardapioActivity.class));
        }

        if(v == buttonSair){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}
