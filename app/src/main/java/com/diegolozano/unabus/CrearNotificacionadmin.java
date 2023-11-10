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
import java.util.Objects;

public class CrearNotificacionadmin extends AppCompatActivity {

    Button btn_volveradmin;
    Button btn_addnoti;
    EditText descripcion,notificacion;

    private FirebaseFirestore mfirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_notificacionadmin);

        this.setTitle( "Crear notificacion");
        mfirestore = FirebaseFirestore.getInstance();

        btn_volveradmin = findViewById(R.id.btn_volveradmin);
        btn_volveradmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Adminnoti.class);
                startActivity(intent);

            }
        });

        descripcion = findViewById(R.id.notificacion);
        btn_addnoti =findViewById(R.id.btn_addnoti);

        btn_addnoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = notificacion.getText().toString();
                if(n.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingrese datos", Toast.LENGTH_SHORT).show();
                }else{
                    postPet(n);
                }
            }
        });

    }

    private void postPet(String n) {

        Map<String, Object> map = new HashMap<>();
        map.put("notificacion",notificacion);
        mfirestore.collection("notificacion").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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