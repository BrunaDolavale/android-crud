package com.example.user.brunadolavaleat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.brunadolavaleat.Activity.Entities.User;
import com.example.user.brunadolavaleat.DAO.ConfigFirebase;
import com.example.user.brunadolavaleat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;
    private Button btnFazerLogin;
    private TextView edtAbreCadastro;
    private FirebaseAuth authentication;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        btnFazerLogin = (Button) findViewById(R.id.btnFazerLogin);
        edtAbreCadastro = (TextView) findViewById(R.id.edtAbreCadastro);
    }

    private void validateLogin(){
        authentication = ConfigFirebase.getFireBaseAuthentication();
        authentication.signInWithEmailAndPassword(user.getEmail(), user.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    openMainView();
                    Toast.makeText(LoginActivity.this, "Login Efetuado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getEmailAndPassword(View view) {
        if (!editEmail.getText().toString().equals("") && !editSenha.getText().toString().equals("")) {
            user = new User();
            user.setEmail(editEmail.getText().toString());
            user.setSenha(editSenha.getText().toString());
            validateLogin();

        } else {
            Toast.makeText(LoginActivity.this, "Preencha", Toast.LENGTH_SHORT).show();
        }
    }


    public void openMainView(){
        Intent intentOpenMainView = new Intent(LoginActivity.this, ListActivity.class);
        startActivity(intentOpenMainView);
    }


    public void openRegisterActivity(View v){
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }



}
