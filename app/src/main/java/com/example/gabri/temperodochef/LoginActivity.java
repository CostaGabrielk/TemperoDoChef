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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonEntrar;
    private TextView textViewCadastrar;

    private ProgressDialog Loading;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.txtEmail);
        editTextSenha = (EditText) findViewById(R.id.txtSenha);
        buttonEntrar = (Button) findViewById(R.id.btnEntrar);
        textViewCadastrar = (TextView) findViewById(R.id.txtCadastrar);

        Loading = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonEntrar.setOnClickListener(this);
        textViewCadastrar.setOnClickListener(this);

        //verify if the current user is already logged if it is, send then to MainActivity
        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    private void signinUser() {

        String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();

        //Check if the edit text is not empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Por favor, digite o seu e-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(senha)) {
            Toast.makeText(this, "Por favor, digite a sua senha", Toast.LENGTH_SHORT).show();
        }

        /*if("admin".equalsIgnoreCase(email) && "admin".equalsIgnoreCase(senha)){

            finish();
            startActivity(new Intent(getApplicationContext(), AdminActivity.class));

        }else{*/
        Loading.setMessage("Entrando...");
        Loading.show();

        //if everything is ok we will sigin in at the app
        firebaseAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Loading.dismiss();
                        if(task.isSuccessful()){
                            //end this activity, and call the main acitivity
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "Email ou senha invalidos, Tente Novamente", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {

        if(v == buttonEntrar){
            signinUser();
        }

        if(v == textViewCadastrar){
            finish();
            startActivity(new Intent(this, CreateAccountActivity.class));
        }
    }

}
