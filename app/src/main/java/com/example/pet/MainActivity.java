package com.example.pet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

import com.example.pet.cat.Cat;
import com.example.pet.dog.Dog;
import com.example.pet.inscripcion.Add;
import com.example.pet.veterinarian.Veterinarian;

public class MainActivity extends AppCompatActivity {

    ImageButton add,dog,cat,vetr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.inscrbirse);
        dog = findViewById(R.id.perro);
        cat = findViewById(R.id.gato);
        vetr = findViewById(R.id.veterinario);
        buttons();

    }

    private void buttons() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, Add.class);
                startActivity(i);

            }
        });

        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(MainActivity.this, Cat.class);
                startActivity(i);

            }
        });

        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(MainActivity.this, Dog.class);
                startActivity(i);

            }
        });

        vetr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(MainActivity.this, Veterinarian.class);
                startActivity(i);

            }
        });
    }

    //Dialog pregunta salir
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode== event.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de Adottare?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }

                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}