package com.example.pet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pet.Animals.Adapter_animals;
import com.example.pet.Animals.Adatos_animals;
import com.example.pet.inscripcion.Add;
import com.example.pet.veterinarian.Veterinarian;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Animales1 extends AppCompatActivity {

    ImageButton add,login;
    RecyclerView recyclerView;
    Adapter_animals adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales1);
        add=findViewById(R.id.inscrbirse1);
        getSupportActionBar().setTitle("Feedit");
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

                Intent i = new Intent(Animales1.this, Add.class);
                startActivity(i);
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