package com.example.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText edituser, editcontra;
    Button buttoningre ,buttoncancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edituser = findViewById(R.id.user);
        editcontra = findViewById(R.id.contra);
        buttoningre = findViewById(R.id.ingresar);
        buttoncancel = findViewById(R.id.cancelar);

        buttoningre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(edituser.getText().toString()) && TextUtils.isEmpty(editcontra.getText().toString())){
                    Toast.makeText(getApplicationContext(),"llene todos los campos", Toast.LENGTH_LONG).show();
                    edituser.setError("Campo requerido");
                    editcontra.setError("Campo requerido");
                    return;
                }

                if (edituser.getText().toString().equals("chawuala22") && editcontra.getText().toString().equals("12345")) {
                    Intent intent = new Intent(getApplicationContext(), Animales1.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Datos invalidos o incompletos", Toast.LENGTH_LONG).show();
                }
            }


        });

    }
}