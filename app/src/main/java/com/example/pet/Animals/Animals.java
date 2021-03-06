package com.example.pet.Animals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pet.Login;
import com.example.pet.inscripcion.Add;
import com.example.pet.R;
import com.example.pet.veterinarian.Veterinarian;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class Animals extends AppCompatActivity {

    ImageButton add,login;
    RecyclerView recyclerView;
    Adapter_animals adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales);
        getSupportActionBar().setTitle("Feedit");


        add = findViewById(R.id.inscrbirse);
        login = findViewById(R.id.login);

        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Adatos_animals> options =
                new FirebaseRecyclerOptions.Builder<Adatos_animals>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Inscripcion"), Adatos_animals.class)
                        .build();

        adapter = new Adapter_animals(options);
        recyclerView.setAdapter(adapter);

        buttons();





    }
    private void buttons(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Inicia sesión para postear", Toast.LENGTH_LONG).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
    }
}

