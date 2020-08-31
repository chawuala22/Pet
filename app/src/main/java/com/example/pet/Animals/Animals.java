package com.example.pet.Animals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pet.inscripcion.Add;
import com.example.pet.R;
import com.example.pet.veterinarian.Veterinarian;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class Animals extends AppCompatActivity {

    ImageButton add,vetr;
    RecyclerView recyclerView;
    Adapter_animals adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales);
        getSupportActionBar().setTitle("Perros");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        add = findViewById(R.id.inscrbirse);
        vetr = findViewById(R.id.veterinario);

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


                Intent i = new Intent(Animals.this, Add.class);
                startActivity(i);
            }
        });

           vetr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(Animals.this, Veterinarian.class);
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

