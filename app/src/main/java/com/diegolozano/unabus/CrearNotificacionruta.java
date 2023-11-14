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

public class CrearNotificacionruta extends AppCompatActivity {

    Button btn_volveradmin;
    Button btn_addruta;
    EditText edtNameruta;

    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_notificacionruta);

        this.setTitle( "Crear ruta");
        mfirestore = FirebaseFirestore.getInstance();

        btn_volveradmin = findViewById(R.id.btn_volveradmin);

        edtNameruta = findViewById(R.id.edt_ruta_crear);
        btn_volveradmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Adminnoti.class);
                startActivity(intent);

            }
        });

        btn_addruta =findViewById(R.id.btn_addruta);

        btn_addruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = edtNameruta.getText().toString();
                if(n.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingrese datos", Toast.LENGTH_SHORT).show();
                }else{
                    Ruta newRuta = new Ruta(n);
                    mfirestore.collection("rutas").add(newRuta).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CrearNotificacionruta.this, "CREADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();

                            } else {
                                Log.d("diegoerror", task.getException().toString());
                            }
                        }
                    });

                }
            }
        });

    }

    private void postRuta(String n) {

        Map<String, Object> map = new HashMap<>();
        map.put("ruta",n);
        mfirestore.collection("ruta").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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