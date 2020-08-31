package com.example.pet.inscripcion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pet.MainActivity;
import com.example.pet.R;
import com.example.pet.Tipo;
import com.example.pet.veterinarian.Veterinarian;
import com.example.pet.cat.Cat;
import com.example.pet.dog.Dog;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Add extends AppCompatActivity {

    private ImageButton cat,dog,vetr,choosephoto, uploadphoto;
    private EditText namepet,edadpet,descripet,namepersona,direccion,email,ubicacion, choosename,numtel;
    private ImageView iv_image;
    private Button inscribirse;
    private static final int GALLERY_INTENT = 1 ;
    Uri uriImage;
    private Spinner pet;
    private String pet_select ="", ImageUrl;
    private ProgressDialog progressDialog;
    private UploadTask uploadTask;

    //Firebase

    DatabaseReference myRef ;
    FirebaseStorage storage;
    StorageReference storageRef, imageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setTitle("Inscripción");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cat = findViewById(R.id.gato);
        dog = findViewById(R.id.perro);
        vetr = findViewById(R.id.veterinario);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        myRef=FirebaseDatabase.getInstance().getReference();

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
                String numerotel=numtel.getText().toString();

                //validacion
                if (nombre_mascota.isEmpty() &&edad_mascota.isEmpty()&&desc_mascota.isEmpty()&&nombre_persona.isEmpty()
                        &&direccion_persona.isEmpty()&&email_persona.isEmpty()&&ubicacion_persona.isEmpty()&&numerotel.isEmpty()){
                    namepet.setError("Campo requerrido"); edadpet.setError("Campo requerrido"); descripet.setError("Campo requerrido");
                    namepersona.setError("Campo requerrido");  direccion.setError("Campo requerrido"); email.setError("Campo requerrido");
                    ubicacion.setError("Campo requerrido"); numtel.setError("Campo requerrido");
                }
                if (TextUtils.isEmpty(numerotel)){
                    Toast.makeText(getApplicationContext(),"No se pudo completar el registro, llene todos los campos", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(getApplicationContext(),"Registro exitoso", Toast.LENGTH_LONG).show();
                loadbd(nombre_mascota, edad_mascota, desc_mascota, nombre_persona, direccion_persona, email_persona, ubicacion_persona,numerotel);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                delete_data();

            }
        });

        choosephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photopicker =new Intent(Intent.ACTION_PICK);
                photopicker.setType("image/*");
                startActivityForResult(photopicker,GALLERY_INTENT);
               Toast.makeText(getApplicationContext(),"Foto cargada correctamente, pulsa en el boton subir",Toast.LENGTH_LONG).show();

            }
        });

        choosename.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                uploadphoto.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadimage();

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case GALLERY_INTENT:
                if(resultCode == RESULT_OK){
                    uriImage = data.getData();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),
                                uriImage);
                        iv_image.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

        }



    }
    private void uploadimage() {

        if(choosename.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(),"Por favor sube una imagen",Toast.LENGTH_LONG).show();
        }else{



        imageRef =storageRef.child("FolderPets/"+ choosename.getText().toString()+"."+
                GetExtension(uriImage));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Subiendo...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        progressDialog.setCancelable(false);

        uploadTask = imageRef.putFile(uriImage);

        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress= (100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                progressDialog.incrementProgressBy((int)progress);
            }
        });

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Falló",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"Se subió con exito",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        ImageUrl = uri.toString();
                    }
                });
            }
        });
        }
    }
    private String GetExtension(Uri uriImage) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uriImage));
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
        choosephoto=findViewById(R.id.choosephoto);
        uploadphoto=findViewById(R.id.uploadfoto);
        choosename=findViewById(R.id.pic_name);
        numtel=findViewById(R.id.numerotelefono);
        iv_image=findViewById(R.id.iv_result);
        progressDialog = new ProgressDialog(this);

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
    private void loadbd(String nombre_mascota, String edad_mascota, String desc_mascota, String nombre_persona, String direccion_persona, String email_persona, String ubicacion_persona, String numerotel) {

        Map<String, Object> datosinscripcion  = new HashMap<>();
        datosinscripcion.put("npet",nombre_mascota);
        datosinscripcion.put("apet",edad_mascota);
        datosinscripcion.put("descpet",desc_mascota);
        datosinscripcion.put("npers",nombre_persona);
        datosinscripcion.put("dirpers",direccion_persona);
        datosinscripcion.put("epers",email_persona);
        datosinscripcion.put("ubiper",ubicacion_persona);
        datosinscripcion.put("numcel",numerotel);
        datosinscripcion.put("petSpinner",pet_select);
        datosinscripcion.put("urlimage",ImageUrl);

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


    @Override
    public void onBackPressed() {
    }
}