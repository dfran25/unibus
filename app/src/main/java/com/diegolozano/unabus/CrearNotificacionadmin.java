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

public class CrearNotificacionadmin extends AppCompatActivity {

    Button btn_volveradmin;
    Button btn_addnoti;
    EditText edtNameNotificacion;

    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_notificacionadmin);

        this.setTitle("Crear notificacion");
        mfirestore = FirebaseFirestore.getInstance();

        btn_volveradmin = findViewById(R.id.btn_volveradmin);
        edtNameNotificacion = findViewById(R.id.edt_notifi_crear);
        btn_volveradmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Adminnoti.class);
                startActivity(intent);

            }
        });

        btn_addnoti = findViewById(R.id.btn_addnoti);

        btn_addnoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = edtNameNotificacion.getText().toString();
                if (n.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingrese datos", Toast.LENGTH_SHORT).show();
                } else {

                    Notificacion newNotificacion = new Notificacion(n);
                    mfirestore.collection("notificaciones").add(newNotificacion).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CrearNotificacionadmin.this, "CREADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();

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