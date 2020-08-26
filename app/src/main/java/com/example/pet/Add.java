package com.example.pet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Add extends AppCompatActivity {

    ImageButton cat,dog,vetr;
    EditText namepet,edadpet,descripet,namepersona,direccion,email,ubicacion;
    Button inscribirse;
    private Spinner pet;
    String pet_select ="";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Datos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        cat = findViewById(R.id.gato);
        dog = findViewById(R.id.perro);
        vetr = findViewById(R.id.veterinario);

        instanciar();
        buttons();
        loadtipo();


        inscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre_mascota =namepet.getText().toString();
                String edad_mascota = edadpet.getText().toString();
                String desc_mascota =descripet.getText().toString();
                String nombre_persona =namepersona.getText().toString();
                String direccion_persona =direccion.getText().toString();
                String email_persona =email.getText().toString();
                String ubicacion_persona =ubicacion.getText().toString();

                if (namepet.getText().toString().trim().equalsIgnoreCase("")){
                    edadpet.setError("Campo requerido");
                    descripet.setError("Campo requerido");
                    namepersona.setError("Campo requerido");
                    direccion.setError("Campo requerido");
                    email.setError("Campo requerido");
                    ubicacion.setError("Campo requerido");
                    Toast.makeText(getApplicationContext(),"No se pudo completar el registro, llene todos los campos", Toast.LENGTH_LONG).show();
                }else{
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    Toast.makeText(getApplicationContext(),"Registro exitoso", Toast.LENGTH_LONG).show();
                    loadbd(nombre_mascota, edad_mascota, desc_mascota, nombre_persona, direccion_persona, email_persona, ubicacion_persona);
                    delete_data();
                }

            }
        });
    }

    private void instanciar() {
        namepet = findViewById(R.id.namepet);
        edadpet= findViewById(R.id.agepet);
        descripet= findViewById(R.id.descpet);
        namepersona= findViewById(R.id.nombre);
        direccion= findViewById(R.id.direccion);
        email= findViewById(R.id.email);
        ubicacion= findViewById(R.id.ciudad);
        pet=findViewById(R.id.spinnergenero);
        inscribirse= findViewById(R.id.btninscribirse);
    }

    private void buttons() {
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
    }

    private void delete_data() {
        namepet.setText("");
        edadpet.setText("");
        descripet.setText("");
        namepersona.setText("");
        direccion.setText("");
        email.setText("");
        ubicacion.setText("");
    }

    private void loadbd(String nombre_mascota, String edad_mascota, String desc_mascota, String nombre_persona, String direccion_persona, String email_persona, String ubicacion_persona) {
        Map<String, Object> datosinscripcion  = new HashMap<>();

        datosinscripcion.put("npet",nombre_mascota);
        datosinscripcion.put("apet",edad_mascota);
        datosinscripcion.put("descpet",desc_mascota);
        datosinscripcion.put("npers",nombre_persona);
        datosinscripcion.put("dirpers",direccion_persona);
        datosinscripcion.put("epers",email_persona);
        datosinscripcion.put("ubiper",ubicacion_persona);
        datosinscripcion.put("petSpinner",pet_select);

        myRef.child("Inscripcion").push().setValue(datosinscripcion);
    }

    private void loadtipo(){

        final List<Tipo> type_pet = new ArrayList<>();
        myRef.child("Tipo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        String id = ds.getKey();
                        String nombre = ds.child("nombre").getValue().toString();
                        type_pet.add(new Tipo(nombre,id));
                    }
                    ArrayAdapter<Tipo> arrayAdapter = new ArrayAdapter<>(Add.this, android.R.layout.simple_dropdown_item_1line, type_pet);
                    pet.setAdapter(arrayAdapter);
                    pet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            pet_select= adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}