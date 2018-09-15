package com.example.user.brunadolavaleat.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.user.brunadolavaleat.Activity.Entities.User;
import com.example.user.brunadolavaleat.DAO.ConfigFirebase;
import com.example.user.brunadolavaleat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtCadEmail;
    private EditText edtCadNome;
    private EditText edtCadSobrenome;
    private EditText edtCadSenha;
    private EditText edtCadConfirmarSenha;
    private EditText edtCadAniversario;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private Button btnGravar;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtCadEmail = (EditText)findViewById(R.id.edtCadEmail);
        edtCadNome = (EditText)findViewById(R.id.edtCadNome);
        edtCadSobrenome = (EditText)findViewById(R.id.edtCadSobrenome);
        edtCadSenha = (EditText)findViewById(R.id.edtCadSenha);
        edtCadConfirmarSenha = (EditText)findViewById(R.id.edtCadSenha);
        edtCadAniversario = (EditText)findViewById(R.id.edtCadAniversario);
        radioFemale = (RadioButton)findViewById(R.id.radioFemale);
        radioFemale = (RadioButton)findViewById(R.id.radioMale);
        btnGravar = (Button)findViewById(R.id.btnGravar);

    }

    private void confirmPassword(){
        if (edtCadSenha.getText().toString().equals(edtCadConfirmarSenha.getText().toString())) {
            user = new User();
            user.setEmail(edtCadNome.getText().toString());
            user.setEmail(edtCadEmail.getText().toString());
            user.setEmail(edtCadSenha.getText().toString());
            user.setEmail(edtCadAniversario.getText().toString());
            user.setEmail(edtCadSobrenome.getText().toString());

            if(radioFemale.isChecked()) {
                user.setSexo("Feminino");
            } else {
                user.setSexo("Masculino");
            }
        } else {
            Toast.makeText(CadastroActivity.this, "As senhas não correspondem", Toast.LENGTH_LONG).show();
        }
    }

}
