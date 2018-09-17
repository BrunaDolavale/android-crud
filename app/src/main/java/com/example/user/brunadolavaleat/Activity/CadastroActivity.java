package com.example.user.brunadolavaleat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.user.brunadolavaleat.Activity.Entities.User;
import com.example.user.brunadolavaleat.DAO.ConfigFirebase;
import com.example.user.brunadolavaleat.Helper.Base64Custom;
import com.example.user.brunadolavaleat.Helper.Preferences;
import com.example.user.brunadolavaleat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;


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
    private FirebaseAuth authentication;


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

        btnGravar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Toast.makeText(CadastroActivity.this, "CHAMOU FUNÇÃO", Toast.LENGTH_LONG).show();
                if (edtCadSenha.getText().toString().equals(edtCadConfirmarSenha.getText().toString())) {
                    user = new User();
                    user.setNome(edtCadNome.getText().toString());
                    user.setEmail(edtCadEmail.getText().toString());
                    user.setSenha(edtCadSenha.getText().toString());
                    user.setAniversario(edtCadAniversario.getText().toString());
                    user.setSobrenome(edtCadSobrenome.getText().toString());

                    if (radioFemale.isChecked()) {
                        user.setSexo("Feminino");
                    } else {
                        user.setSexo("Masculino");
                    }
                } else {
                    Toast.makeText(CadastroActivity.this, "As senhas não correspondem", Toast.LENGTH_LONG).show();
                }
                cadastrarUsuario();
            }
        });
    }

    private void cadastrarUsuario() {
                        authentication = ConfigFirebase.getFireBaseAuthentication();
                        authentication.createUserWithEmailAndPassword(
                                user.getEmail(),
                                user.getSenha()
                        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(CadastroActivity.this, "Usuário cadastrado", Toast.LENGTH_LONG).show();

                                    String userIdentification = Base64Custom.codificateBase64(user.getEmail());

                                    FirebaseUser firebaseUser = task.getResult().getUser();
                                    user.setId(userIdentification);
                                    user.save();

                                    Preferences preferences = new Preferences(CadastroActivity.this);
                                    preferences.saveUserPreferencies(userIdentification, user.getNome());
                    openUserLogin();
                } else {
                    String errorException = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        errorException = "Digite uma senha forte, de no mínimo 8 caracteres";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        errorException = "Email inválido";
                    } catch (FirebaseAuthUserCollisionException e){
                        errorException = "E-mail já cadastrado";
                    } catch (Exception e) {
                        errorException = "Erro ao efetuar cadastro";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, "Erro " + errorException, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void openUserLogin() {
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

//    public void openUserRegister(View v){
//        Intent intent = new Intent(CadastroActivity.this, ListActivity.class);
//        startActivity(intent);
//    }



}