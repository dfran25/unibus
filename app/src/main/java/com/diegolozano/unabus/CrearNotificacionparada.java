package com.diegolozano.unabus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearNotificacionparada extends AppCompatActivity {

    Button btn_volveradmin;
    Button btn_addparada;
    EditText descripcion,parada;

    private FirebaseFirestore mfirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_notificacionparada);

        this.setTitle( "Crear parada");
        mfirestore = FirebaseFirestore.getInstance();

        btn_volveradmin = findViewById(R.id.btn_volveradmin);


        btn_volveradmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Adminnoti.class);
                startActivity(intent);

            }
        });

        descripcion = findViewById(R.id.parada);
        btn_addparada =findViewById(R.id.btn_addparada);

        btn_addparada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = descripcion.getText().toString().trim();
                if(n.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingrese datos", Toast.LENGTH_SHORT).show();
                }else{
                    postParada(n);
                }
            }
        });

    }
    private void postParada(String n) {

        Map<String, Object> map = new HashMap<>();
        map.put("parada",n);
        mfirestore.collection("parada").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Creado existosamente",Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

