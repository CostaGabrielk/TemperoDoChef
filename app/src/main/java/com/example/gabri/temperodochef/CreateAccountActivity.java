package com.example.gabri.temperodochef;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonCadastrar;
    private TextView textViewLogar;

    private ProgressDialog Loading;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editTextEmail = (EditText) findViewById(R.id.txtEmail);
        editTextSenha = (EditText) findViewById(R.id.txtSenha);
        buttonCadastrar = (Button) findViewById(R.id.btnCadastrar);
        textViewLogar = (TextView) findViewById(R.id.txtLogar);

        Loading = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonCadastrar.setOnClickListener(this);
        textViewLogar.setOnClickListener(this);
    }

    private void createAccount() {

        String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();

        //Check if the edit text is not empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Por favor, digite o seu e-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(senha)) {
            Toast.makeText(this, "Por favor, digite a sua senha", Toast.LENGTH_SHORT).show();
            return;
        }

        Loading.setMessage("Realizando cadastro...");
        Loading.show();

        //if eveything is ok we will register the user account
        firebaseAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Loading.dismiss();
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(CreateAccountActivity.this, "Cadastro Invalido, Tente Novamente", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View view) {

        if (view == buttonCadastrar) {
            createAccount();
        }

        if (view == textViewLogar) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

    }

}
