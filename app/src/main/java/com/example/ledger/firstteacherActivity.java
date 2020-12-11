package com.example.ledger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
public class firstteacherActivity extends AppCompatActivity {
    public String nom,Image,Id,Descrip,Respon;
    public TextView NomP,Descrip1;
    public CircleImageView visageprof;
    public ImageButton visuliser,marquer,plus;
    public static final String Id_responsabl="Id_responsabl";
    public static final String image="image";
    public static final String Respons="Respons";
    public static final String mo="mo";    public static final String Ctt="Ctt";
    public static  String mo1,Ctt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_firstteacher);

                visuliser=findViewById(R.id.VISULAISER);
                marquer=findViewById(R.id.MARQUER);
                plus=findViewById(R.id.PLUS);
                Intent intent = getIntent();
                nom = intent.getStringExtra(Menu2.Nom);
                NomP = findViewById(R.id.nomProf);
                NomP.setText(nom);
     //tatsali wdirha b php
                if(NomP.getText().equals("SLAOUI")) {mo1="PHP";Ctt1="Cours";}
                if(NomP.getText().equals("BALIHI")){ mo1="IHM";Ctt1="TP";}
                Image = intent.getStringExtra(Menu2.Image);
                visageprof = findViewById(R.id.visageprofeto);
                loadImageFromUrl(Image);
                Descrip=intent.getStringExtra(Menu2.Descrip);
                Descrip1 = findViewById(R.id.Descrip1);
                Descrip1.setText(Descrip);
                Respon=intent.getStringExtra(Menu2.Responsa);
                Id =intent.getStringExtra(Menu2.Id_responsable);
                visuliser.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(firstteacherActivity.this, Absence.class);
                        intent.putExtra("Id_responsabl", Id);
                        intent.putExtra("image", Image);
                        intent.putExtra("mo", mo1);
                        intent.putExtra("Ctt", Ctt1);
                        startActivity(intent);

                    }});
                marquer.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(firstteacherActivity.this, AfficherListe.class);
                        intent.putExtra("Id_responsabl", Id);
                        intent.putExtra("Respons", Respon);
                        intent.putExtra("image", Image);
                        intent.putExtra("mo", mo1);
                        intent.putExtra("Ctt", Ctt1);
                        startActivity(intent);
                    }});
                plus.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(firstteacherActivity.this, listeNoire.class);
                        intent.putExtra("image", Image);
                        intent.putExtra("mo", mo1);
                        intent.putExtra("Ctt", Ctt1);
                        startActivity(intent);

                    }});


            }


            public void loadImageFromUrl(String url){
                Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(visageprof,new com.squareup.picasso.Callback(){
                    @Override
                    public void onSuccess() {
                    }
                    @Override
                    public void onError() {
                    }
                });}}

