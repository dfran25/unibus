package com.diegolozano.unabus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearNotificacionparada extends AppCompatActivity {

    Button btn_volveradmin;
    Button btn_addparada;
    EditText edtNameParada;

    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_notificacionparada);

        this.setTitle("Crear parada");
        mfirestore = FirebaseFirestore.getInstance();

        btn_volveradmin = findViewById(R.id.btn_volveradmin);


        edtNameParada = findViewById(R.id.edt_parada_crear);
        btn_volveradmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Adminnoti.class);
                startActivity(intent);

            }
        });
        btn_addparada = findViewById(R.id.btn_addparada);

        btn_addparada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = edtNameParada.getText().toString().trim();
                if (n.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingrese datos", Toast.LENGTH_SHORT).show();
                } else {
                    Parada newParada = new Parada(n);
                    mfirestore.collection("paradas").add(newParada).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CrearNotificacionparada.this, "CREADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();

                            } else {
                                Log.d("diegoerror", task.getException().toString());
                            }
                        }
                    });


                }
            }
        });

    }

}




