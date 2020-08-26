package com.example.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Add extends AppCompatActivity {

    ImageButton cat,dog,vetr;
    EditText namepet,edadpet,descripet,namepersona,direccion,email,ubicacion;
    Button inscribirse;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Datos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        cat = findViewById(R.id.gato);
        dog = findViewById(R.id.perro);
        vetr = findViewById(R.id.veterinario);

        namepet = findViewById(R.id.namepet);
        edadpet= findViewById(R.id.agepet);
        descripet= findViewById(R.id.descpet);
        namepersona= findViewById(R.id.nombre);
        direccion= findViewById(R.id.direccion);
        email= findViewById(R.id.email);
        ubicacion= findViewById(R.id.ciudad);
        inscribirse= findViewById(R.id.btninscribirse);

        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Add.this, Cat.class);
                startActivity(i);
            }
        });
        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Add.this, Dog.class);
                startActivity(i);
            }
        });

        vetr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Add.this, Veterinarian.class);
                startActivity(i);

            }
        });


        inscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre_mascota =namepet.getText().toString();
                int edad_mascota = Integer.parseInt(edadpet.getText().toString());
                String desc_mascota =descripet.getText().toString();
                String nombre_persona =namepersona.getText().toString();
                String direccion_persona =direccion.getText().toString();
                String email_persona =email.getText().toString();
                String ubicacion_persona =ubicacion.getText().toString();

                Map<String, Object> datosinscripcion  = new HashMap<>();

                datosinscripcion.put("npet",nombre_mascota);
                datosinscripcion.put("apet",edad_mascota);
                datosinscripcion.put("descpet",desc_mascota);
                datosinscripcion.put("npers",nombre_persona);
                datosinscripcion.put("dirpers",direccion_persona);
                datosinscripcion.put("epers",email_persona);
                datosinscripcion.put("ubiper",ubicacion_persona);

                myRef.child("Inscripcion").push().setValue(datosinscripcion);

            }
        });


    }
}