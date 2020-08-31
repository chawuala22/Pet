package com.example.pet.Animals;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pet.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_animals extends FirebaseRecyclerAdapter<Adatos_animals, Adapter_animals.myviewholder> {


    Activity activity;
    public Adapter_animals(FirebaseRecyclerOptions<Adatos_animals> options ) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(final myviewholder myviewholder, final int i, final Adatos_animals adatos_animals) {

            myviewholder.name.setText(adatos_animals.getNpet());
            myviewholder.edad.setText(adatos_animals.getApet());
            Glide.with(myviewholder.img.getContext()).load(adatos_animals.getUrlimage()).into(myviewholder.img);


            myviewholder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final DialogPlus dialog = DialogPlus.newDialog(myviewholder.img.getContext())
                            .setContentHolder(new ViewHolder(R.layout.activity_view_dog))
                            .setExpanded(true,1500)
                            .create();

                     final View myview = dialog.getHolderView();

                     final TextView namepet1 = myview.findViewById(R.id.nombrepet_activity);
                    TextView edadpet1 = myview.findViewById(R.id.edadpet_activity);
                    TextView descripet1 = myview.findViewById(R.id.descpetpet_activity);
                    TextView nombreper1 = myview.findViewById(R.id.nombreper_activity);
                    TextView direcper1 = myview.findViewById(R.id.direccionper_activity);
                    TextView emailper1 = myview.findViewById(R.id.emailper_activity);
                    TextView ubiper1 = myview.findViewById(R.id.ubicacionper_activity);

                    final TextView numper1 = myview.findViewById(R.id.numeroper_activity);

                    ImageView image1 = myview.findViewById(R.id.imagen_activity);

                    Button btn1=myview.findViewById(R.id.whatsapp);
                    Button bt2=myview.findViewById(R.id.gmail);
                    namepet1.setText(adatos_animals.getNpet());
                    edadpet1.setText(adatos_animals.getApet());
                    descripet1.setText(adatos_animals.getDescpet());
                    nombreper1.setText(adatos_animals.getNpers());
                    direcper1.setText(adatos_animals.getDirpers());
                    emailper1.setText(adatos_animals.getEpers());
                    ubiper1.setText(adatos_animals.getUbiper());
                    numper1.setText(adatos_animals.getNumcel());
                    Picasso.get().load(adatos_animals.getUrlimage()).into(image1);




                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Toast.makeText(myview.getContext(),"El numero es "+ adatos_animals.getNumcel(),Toast.LENGTH_LONG).show();

                        }
                    });

                    bt2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                         Toast.makeText(myview.getContext(),"Aquí se te mandaría al correo",Toast.LENGTH_LONG).show();
                        }
                    });
                    dialog.show();
                }
            });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_recycler,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        CircleImageView img;
        TextView name,edad;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img= itemView.findViewById(R.id.image);
            name= itemView.findViewById(R.id.nombrepet);
            edad= itemView.findViewById(R.id.edadpet);
            imageView=itemView.findViewById(R.id.update);

        }
    }
}