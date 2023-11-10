package com.diegolozano.unabus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firestore.v1.WriteResult;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {


    EditText editTextEmail,editTextPassword,editTextnombre,editTextapellido,editTextanother_password;
    Button buttonReg;

    ProgressBar progressBar;

    Button button;

    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent =new Intent(getApplicationContext(), mapa.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonReg = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
        button= findViewById(R.id.logiNow);
        editTextnombre= findViewById(R.id.nombre);
        editTextapellido=findViewById(R.id.apellido);
        editTextanother_password=findViewById(R.id.another_password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password,nombre,apellido,anoher_password;
                nombre= String.valueOf(editTextnombre.getText());
                apellido= String.valueOf(editTextapellido.getText());
                anoher_password= String.valueOf(editTextanother_password.getText());
                email= String.valueOf(editTextEmail.getText());
                password= String.valueOf(editTextPassword.getText());
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this,"ingresa un correo",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "ingresa una contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(nombre)){
                    Toast.makeText(Register.this,"ingresa un Nombre",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(apellido)) {
                    Toast.makeText(Register.this, "ingresa una apellito", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(anoher_password)){
                    Toast.makeText(Register.this,"Confirma la contraseña",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password!=anoher_password){
                    Toast.makeText(Register.this,"Contraseña diferente",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                                    firestore.collection("usuarios").add(new Usuario(nombre,apellido,email));

                                    Toast.makeText(Register.this, "cuenta creada",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register.this, "Autentificacion fallida.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }

                        });

                // Add a new document with a generated ID
                DocumentReference docRef = db.collection("users").document(email);
                // Add document data with an additional field ("middle")
                Map<String, Object> data = new HashMap<>();





            }
        });
    }
}