package com.example.ledger;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import de.hdodenhof.circleimageview.CircleImageView;
public class AfficherListe extends AppCompatActivity {
public static final String image = "image", SHARED_PREFS1 = "sharedPrefs", SHARED_PREFS2 = "sharedPrefs2", SECTION = "section", ety = "ety", Id_responsabl = "Id_responsabl";
private static String URL8 = "https://ledgerplus.000webhostapp.com/Legder/getEtudiant.php",
        URL2 = "https://ledgerplus.000webhostapp.com/Legder/RecuperationDateCourPhp.php",//done
        URL3 = "https://ledgerplus.000webhostapp.com/Legder/RecupererListe.php",
        URL5 = "https://ledgerplus.000webhostapp.com/Legder/recupererSECTION.php",
        URL4 = "https://ledgerplus.000webhostapp.com/Legder/InsererEtud.php";
private String etv4,cp, v, y, z, x,f, Noa, Prr, urL, Cod, idsection, idgroupe, P_ou_A, etv1, etv2, etv3, modifiedDate, sectio, Etd, tb, reop;
    public TextView wselTaille, codi, groupi, temp, cpt;
    public int poi;
    public EditText etudCode;
    List<String> verif = new ArrayList<>();
    List<String> Jour = new ArrayList<>();
    List<String> H_Depart = new ArrayList<>();
    List<String> AM_ou_PM = new ArrayList<>();
    List<String> Section = new ArrayList<>();
    List<String> Section2 = new ArrayList<>();
    List<String> Groupe = new ArrayList<>();
    List<String> Code = new ArrayList<>();
    List<String> Nom = new ArrayList<>();
    List<String> Prenom = new ArrayList<>();
    List<String> IdSect = new ArrayList<>();
    List<String> IdGrupe = new ArrayList<>();
    List<String> phototab = new ArrayList<>();
    CircleImageView img ;
    public Spinner tar2;
    private LinearLayout parentLinearLayout;
    public ImageView visageEtudiant, toutestbon, erreurinsertion;
    public CircleImageView visageprof;
    private int mCurrentIndex;
    public int te;
    public TextView temp1, textJOUR, textSECTION, Cour_td_tp, textAM_pm, textH_depart, OOoops, idsectioN, idgroupE, Reponse;
    public Button mbutton2suiv;
    public Button mbutton2prec;
    public String SEc;
    public String tar;
    String var,mo,Ctt;
    public ImageButton go;
    public int te1, mCurrentIndex1;
    String var1, tar1;
    public String nom, id, ta, Image;
    public TextView ert0, ert1, ert2, ert3,ert4, Code_etu, Text_Section, textoGropo, Text_Code;
    public TextView Nom_etu;
    public TextView Prenom_etu;
    Button etudsuiv;
    RadioGroup g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_liste);
        parentLinearLayout = findViewById(R.id.ter);
        codi = findViewById(R.id.textView10);
        groupi = findViewById(R.id.se);
        Reponse = findViewById(R.id.Reponse);
        textoGropo = findViewById(R.id.se);
        Text_Code = findViewById(R.id.textView10);
        Code_etu = findViewById(R.id.Code_Etu);
        cpt = findViewById(R.id.cpt);
        g = (findViewById(R.id.g));
        etudCode = findViewById(R.id.etudCode);
        OOoops = (findViewById(R.id.textView12));
        wselTaille = (findViewById(R.id.wselTAille));
        toutestbon = (findViewById(R.id.toutestbon));
        erreurinsertion = (findViewById(R.id.erreurinsertion));
        etudsuiv = findViewById(R.id.button3);
        Nom_etu = findViewById(R.id.nom_etu);
        Prenom_etu = findViewById(R.id.prenom_etu2);
        visageprof = findViewById(R.id.imageView9);
        visageEtudiant = findViewById(R.id.photoetud);
        Text_Section = findViewById(R.id.code_Etu3);
        go = findViewById(R.id.go);
        tar2 = (findViewById(R.id.spinner));
        idsectioN = findViewById(R.id.Section_etud);
        idgroupE = findViewById(R.id.Groupe_etud);
        ert0 = findViewById(R.id.textView23);
        ert1 = findViewById(R.id.textView26);
        ert2 = findViewById(R.id.textView14);
        ert3 = findViewById(R.id.textView24);
        ert4 = findViewById(R.id.ert4);
        textJOUR = findViewById(R.id.textJour);
        textSECTION = findViewById(R.id.textSection);
        textAM_pm = findViewById(R.id.textAM_pm);
        textH_depart = findViewById(R.id.textH_depart);
        temp = findViewById(R.id.Temp);
        temp1 = findViewById(R.id.Temp1);
        tar2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String id1 = parent.getItemAtPosition(position).toString();
                wselTaille.setText("0");
                if (!id1.equals("Aucune")) {
                    temp1.setText("0");
                    wselTaille.setText("0");
                    etudsuiv.setEnabled(true);
                    Text_Code.setVisibility(View.VISIBLE);
                    textoGropo.setVisibility(View.VISIBLE);
                    Code_etu.setVisibility(View.VISIBLE);
                    Nom_etu.setVisibility(View.VISIBLE);
                    Prenom_etu.setVisibility(View.VISIBLE);
                    visageEtudiant.setVisibility(View.VISIBLE);
                    idgroupE.setVisibility(View.VISIBLE);
                    OOoops.setVisibility(View.GONE);
                    switch (id1) {
                        case "A":
                           // getListe("1");
                            break;
                        case "B":
                           // getListe("2");
                            break;

                        case "A01":
                            //getListe("1");
                            break;

                        case "A02":
                            //getListe("2");
                            break;
                        case "B01":
                            //getListe("3");
                            break;
                        case "B02":
                            //getListe("4");
                            break;




                    }





                /*    saveData1();*/

                } else {

                    new Handler().postDelayed(new Runnable() {

                        @Override

                        public void run() {

                         /*   saveData1();*/
                        }

                    }, 6000);
                    temp1.setText("0");
                    wselTaille.setText("0");
                    etudsuiv.setEnabled(false);
                    Code_etu.setText("xxxxx");
                    Nom_etu.setText("xxxxx");
                    Prenom_etu.setText("xxxxx");
                    idgroupE.setText("xxxxx");
                    visageEtudiant.setImageResource(R.mipmap.ic_launcher);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfficherListe.this, Etudiant.class);
                Cod = String.valueOf(etudCode.getText());
                intent.putExtra("Coda", Cod);
                startActivity(intent);
            }
        });



        erreurinsertion.setVisibility(View.GONE);
        textoGropo.setVisibility(View.GONE);
        Text_Code.setVisibility(View.GONE);
        Code_etu.setVisibility(View.GONE);
        Nom_etu.setVisibility(View.GONE);
        Prenom_etu.setVisibility(View.GONE);
        idsectioN.setVisibility(View.GONE);
        idgroupE.setVisibility(View.GONE);


        Intent intent = getIntent();
        id = intent.getStringExtra(firstteacherActivity.Id_responsabl);//li f Id_responsable kan7etuh f i
        Image = intent.getStringExtra(firstteacherActivity.image);
        reop = intent.getStringExtra(firstteacherActivity.Respons);
        mo=intent.getStringExtra(firstteacherActivity.mo);

        Cour_td_tp = findViewById(R.id.textView62);



        Cour_td_tp.setText(reop);
        loadImageFromUrl(Image);
        ert0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ert1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                etv1 = String.valueOf(ert1.getText());//jour
                final String Tab1[] = etv1.split(",");
            }
        });
        ert2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                etv2 = String.valueOf(ert2.getText());//jour
                final String Tab0[] = etv2.split(",");
            }
        });
        temp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                SharedPreferences sharedPreferences1 = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferences1.edit();
                String yu=String.valueOf(temp1.getText());Toast.makeText(AfficherListe.this, ""+temp1.getText().toString(), Toast.LENGTH_SHORT).show();
                editor2.putString(ety,yu);
                editor2.putString(ta, wselTaille.getText().toString());

                editor2.apply();

            }
        });
       // GetSect_grup(id);
        ert3.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                        }

                                        @Override
                                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                                        }

                                        @Override
                                        public void afterTextChanged(Editable s) {

                                            etv3 = String.valueOf(ert3.getText());
                                            String[] commaSeparatedArr = etv3.split("\\s*,\\s*");
                                            List<String> result = new ArrayList<String>(Arrays.asList(commaSeparatedArr));
                                            Set<String> set = new HashSet<String>(result);
                                            List<String> list = new ArrayList<String>(set);
                                            List<String> SeCtion = new ArrayList<String>();
                                            SeCtion.add("Aucune");
                                            String r;
                                            if (reop.equals("Cours")) {
                                                for (int i = 0; i < list.size(); i++) {
                                                    if (list.get(i).equals("1")) {
                                                        r = "A";
                                                    } else{
                                                        r = "B";
                                                    }
                                                    SeCtion.add(r);
                                                }
                                            }else {
                                                for (int i = 0; i < list.size(); i++) {
                                                if (list.get(i).equals("1")) {
                                                    r = "A01";
                                                }
                                                if (list.get(i).equals("2")) {
                                                    r = "A02";
                                                }
                                                if (list.get(i).equals("3")) {
                                                    r = "B01";
                                                }
                                                if (list.get(i).equals("4")) {
                                                    r = "B02";
                                                }
                                                else
                                                    r = "p";

                                                SeCtion.add(r);
                                                }

                                            }




                                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AfficherListe.this, android.R.layout.simple_spinner_item, SeCtion);
                                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            tar2.setAdapter(dataAdapter);

                                        }


                                    });




        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("succes");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    if (succes.equals("1")) {
                        JSONObject object = jsonArray.getJSONObject(0);
                        String taille = jsonObject.getString("taille");
                        int j = 0;
                        for (int i = 0; i < Integer.parseInt(taille); i = i + 5) {
                            String J = "Jour" + j;
                            Jour.add(object.getString(J));
                            j++;
                        }
                        j = 0;
                        for (int i = 1; i < Integer.parseInt(taille); i = i + 5) {
                            String J = "H_Depart" + j;
                            H_Depart.add(object.getString(J));
                            j++;
                        }

                        j = 0;
                        for (int i = 2; i < Integer.parseInt(taille); i = i + 5) {
                            String J = "AM_ou_PM" + j;
                            AM_ou_PM.add(object.getString(J));
                            j++;
                        }

                        j = 0;
                        for (int i = 3; i < Integer.parseInt(taille); i = i + 5) {
                            String J = "Id_Section" + j;
                            Section.add(object.getString(J));
                            j++;
                        }
                        j = 0;
                        for (int i = 4; i < Integer.parseInt(taille); i = i + 5) {
                            String J = "Id_Groupe" + j;
                            Groupe.add(object.getString(J));
                            j++;
                        }


                        v = Jour.get(0) + ",";
                        for (int i = 1; i < Jour.size(); i++) {
                            if (i == Jour.size() - 1) v = v + Jour.get(i);
                            else v = v + Jour.get(i) + ",";
                        }
                        ert0.setText(v);
                        x = H_Depart.get(0) + ",";
                        for (int i = 1; i < H_Depart.size(); i++) {
                            if (i == H_Depart.size() - 1) x = x + H_Depart.get(i);
                            else x = x + H_Depart.get(i) + ",";
                        }
                        ert1.setText(x);
                        y = AM_ou_PM.get(0) + ",";
                        for (int i = 1; i < AM_ou_PM.size(); i++) {
                            if (i == AM_ou_PM.size() - 1) y = y + AM_ou_PM.get(i);
                            else y = y + AM_ou_PM.get(i) + ",";
                        }

                        ert2.setText(y);
                        z = Section.get(0) + ",";
                        for (int i = 1; i < Section.size(); i++) {
                            if (i == Section.size() - 1) z = z + Section.get(i);
                            else z = z + Section.get(i) + ",";
                        }

                        f = Groupe.get(0) + ",";
                        for (int i = 1; i < Section.size(); i++) {
                            if (i == Groupe.size() - 1) z = z + Groupe.get(i);
                            else z = z + Groupe.get(i) + ",";
                        }
                        if(reop.equals("Cours")) ert3.setText(z);
                       else ert3.setText(f);


                        mCurrentIndex = 0;
                        textAM_pm.setText(AM_ou_PM.get(mCurrentIndex));
                        textJOUR.setText(Jour.get(mCurrentIndex));
                        textH_depart.setText(H_Depart.get(mCurrentIndex));
                        if (reop.equals("Cours")) {
                            if (Section.get(mCurrentIndex).equals("1")) {
                                SEc = "A";
                            } else {
                                SEc = "B";
                            }
                            textSECTION.setText(SEc);
                        } else {
                            if (Groupe.get(mCurrentIndex).equals("1")) {
                                SEc = "A01";
                            }
                            if (Groupe.get(mCurrentIndex).equals("2")) {
                                SEc = "A02";
                            }
                            if (Groupe.get(mCurrentIndex).equals("3")) {
                                SEc = "B01";
                            }
                            if (Groupe.get(mCurrentIndex).equals("4")) {
                                SEc = "B02";
                            }
                            textSECTION.setText(SEc);
                        }
                        mbutton2suiv = findViewById(R.id.button7);
                        mbutton2suiv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                var = (String) temp.getText();
                                te = Integer.parseInt(var);
                                mCurrentIndex = (te + 1);

                                if (mCurrentIndex >= Jour.size() - 1)
                                    mCurrentIndex = Jour.size() - 1;
                                if (reop.equals("Cours")) {
                                    if (Section.get(mCurrentIndex).equals("1")) {
                                        SEc = "A";
                                    } else {
                                        SEc = "B";
                                    }
                                    textSECTION.setText(SEc);
                                } else {
                                    if (Groupe.get(mCurrentIndex).equals("1")) {
                                        SEc = "A01";
                                    }
                                    if (Groupe.get(mCurrentIndex).equals("2")) {
                                        SEc = "A02";
                                    }
                                    if (Groupe.get(mCurrentIndex).equals("3")) {
                                        SEc = "B01";
                                    }
                                    if (Groupe.get(mCurrentIndex).equals("4")) {
                                        SEc = "B02";
                                    }
                                    textSECTION.setText(SEc);
                                }


                                textH_depart.setText(H_Depart.get(mCurrentIndex));
                                textJOUR.setText(Jour.get(mCurrentIndex));
                                textAM_pm.setText(AM_ou_PM.get(mCurrentIndex));
                                tar = String.valueOf(mCurrentIndex);
                                temp.setText(tar);
                            }
                        });


                        mbutton2prec = findViewById(R.id.button14);
                        mbutton2prec.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                var = (String) temp.getText();
                                te = Integer.parseInt(var);
                                mCurrentIndex = (te - 1);

                                if (mCurrentIndex < 0)
                                    mCurrentIndex = Jour.size() - Jour.size();
                                if (reop.equals("Cours")) {
                                    if (Section.get(mCurrentIndex).equals("1")) {
                                        SEc = "A";
                                    } else {
                                        SEc = "B";
                                    }
                                    textSECTION.setText(SEc);
                                } else {
                                    if (Groupe.get(mCurrentIndex).equals("1")) {
                                        SEc = "A01";
                                    }
                                    if (Groupe.get(mCurrentIndex).equals("2")) {
                                        SEc = "A02";
                                    }
                                    if (Groupe.get(mCurrentIndex).equals("3")) {
                                        SEc = "B01";
                                    }
                                    if (Groupe.get(mCurrentIndex).equals("4")) {
                                        SEc = "B02";
                                    }
                                    textSECTION.setText(SEc);
                                }
                                textH_depart.setText(H_Depart.get(mCurrentIndex));
                                textJOUR.setText(Jour.get(mCurrentIndex));
                                textAM_pm.setText(AM_ou_PM.get(mCurrentIndex));
                                tar = String.valueOf(mCurrentIndex);
                                temp.setText(tar);
                                var = (String) temp.getText();
                                te = Integer.parseInt(var);
                                if (te > 0) {
                                    mCurrentIndex = te; //(te - 1);
                                    if (reop.equals("Cours")) {
                                        if (Section.get(mCurrentIndex).equals("1")) {
                                            SEc = "A";
                                        } else {
                                            SEc = "B";
                                        }
                                        textSECTION.setText(SEc);
                                    } else {
                                        if (Groupe.get(mCurrentIndex).equals("1")) {
                                            SEc = "A01";
                                        }
                                        if (Groupe.get(mCurrentIndex).equals("2")) {
                                            SEc = "A02";
                                        }
                                        if (Groupe.get(mCurrentIndex).equals("3")) {
                                            SEc = "B01";
                                        }
                                        if (Groupe.get(mCurrentIndex).equals("4")) {
                                            SEc = "B02";
                                        }
                                        textSECTION.setText(SEc);
                                    }
                                    textSECTION.setText(SEc);
                                    textH_depart.setText(H_Depart.get(mCurrentIndex));
                                    textJOUR.setText(Jour.get(mCurrentIndex));
                                    textAM_pm.setText(AM_ou_PM.get(mCurrentIndex));
                                    mCurrentIndex--;
                                    tar = String.valueOf(mCurrentIndex);//temp.setText(mCurrentIndex);
                                    temp.setText(tar);
                                } else {


                                }
                            }
                        });


                    } else {
                        Toast.makeText(AfficherListe.this, "Desolé!! Vous n'êtes ni professeur de Cour, ni de TD, ni de TP ", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(AfficherListe.this, "error1qsdqs" + e.toString(), Toast.LENGTH_LONG).show();

                }
            }
        }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AfficherListe.this, "error à cause de" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id_prof", id);
                params.put("Cours_tp_td", reop);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void getListe(final String Id) {

        final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL3,
                new Response.Listener<String>() {
                    public void onResponse(String response1) {
                        try {
                            Code.clear();
                            Nom.clear();
                            Prenom.clear();
                            IdSect.clear();
                            IdGrupe.clear();
                            phototab.clear();


                            JSONObject jsonObject = new JSONObject(response1);
                            String reussite = jsonObject.getString("reussite");
                            JSONArray jsonArray = jsonObject.getJSONArray("etudiant");
                            if (reussite.equals("1")) {
                                JSONObject object2 = jsonArray.getJSONObject(0);
                                String sizeTab = jsonObject.getString("sizeTab");
                                int j = 0;
                                for (int i = 0; i < Integer.parseInt(sizeTab); i = i + 6) {
                                    String J = "CodeE" + j;
                                    Code.add(object2.getString(J));
                                    j++;
                                }
                                j = 0;
                                for (int i = 1; i < Integer.parseInt(sizeTab); i = i + 6) {
                                    String J = "Nom" + j;
                                    Nom.add(object2.getString(J));
                                    j++;
                                }
                                j = 0;
                                for (int i = 2; i < Integer.parseInt(sizeTab); i = i + 6) {
                                    String J = "Prenom" + j;
                                    Prenom.add(object2.getString(J));
                                    j++;
                                }
                                j = 0;
                                for (int i = 3; i < Integer.parseInt(sizeTab); i = i + 6) {
                                    String J = "Photo" + j;
                                    phototab.add(object2.getString(J));
                                    j++;
                                }
                                j = 0;
                                for (int i = 0; i < Integer.parseInt(sizeTab); i = i + 6) {
                                    String J = "ID_Groupe" + j;
                                    IdGrupe.add(object2.getString(J));
                                    j++;
                                }
                                j = 0;
                                for (int i = 0; i < Integer.parseInt(sizeTab); i = i + 6) {
                                    String J = "ID_Section" + j;
                                    IdSect.add(object2.getString(J));
                                    j++;
                                }


                                mCurrentIndex1 = 0;
                                Code_etu.setText(Code.get(mCurrentIndex1));
                                Nom_etu.setText(Nom.get(mCurrentIndex1));
                                Prenom_etu.setText(Prenom.get(mCurrentIndex1));
                                idsectioN.setText(IdSect.get(mCurrentIndex1));
                                idgroupE.setText(IdGrupe.get(mCurrentIndex1));
                                loadImageFromUrl1(phototab.get(mCurrentIndex1));

                                etudsuiv.setOnClickListener(new View.OnClickListener() {

                                    @SuppressLint("SimpleDateFormat")
                                    @Override
                                    public void onClick(View v) {
                                        cp = String.valueOf(wselTaille.getText());
                                        poi = Integer.parseInt(cp);
                                        if (poi > Nom.size() - 1) {
                                            temp1.setText("" + (Integer.parseInt(temp1.getText().toString()) - 1));
                                            tar2.setSelection(0);
                                        } else {
                                            Cod = String.valueOf(Code_etu.getText());
                                            Noa = String.valueOf(Nom_etu.getText());
                                            Prr = String.valueOf(Prenom_etu.getText());
                                            urL=String.valueOf(phototab.get(mCurrentIndex1));
                                            idsection = String.valueOf(idsectioN.getText());
                                            idgroupe = String.valueOf(idgroupE.getText());
                                            Date dat = new Date();
                                            SimpleDateFormat modifiedDat = new SimpleDateFormat("yyyy-MM-dd");
                                            final String datp=modifiedDat.format(new Date());
                                            int selectedId = g.getCheckedRadioButtonId();
                                            RadioButton radioSexButton = (RadioButton) findViewById(selectedId);
                                            if (radioSexButton.getText().equals("Present(e)"))
                                                P_ou_A = "P";
                                            if (radioSexButton.getText().equals("Absent(e)"))
                                                P_ou_A = "A";


                                                                                   AlertDialog.Builder builder = new AlertDialog.Builder(AfficherListe.this);
                                        builder.setCancelable(true);
                                        builder.setTitle("Confirmation");
                                        builder.setMessage("Voulez vous vraiment marquer l'etudiant " + radioSexButton.getText());
                                        builder.setPositiveButton("oui",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                        InsertEtud("2019-06-22", Cod, P_ou_A, idsection, idgroupe,Noa,Prr);
                                                        verif.add(Cod);


                                                        var1 =  temp1.getText().toString();
                                                        te1 = Integer.parseInt(var1);
                                                        mCurrentIndex1 = (te1 + 1);


                                                        if (mCurrentIndex1 >= Nom.size() - 1)
                                                            mCurrentIndex1 = Nom.size() - 1;
                                                        Code_etu.setText(Code.get(mCurrentIndex1));
                                                        Nom_etu.setText(Nom.get(mCurrentIndex1));
                                                        Prenom_etu.setText(Prenom.get(mCurrentIndex1));
                                                        idsectioN.setText(IdSect.get(mCurrentIndex1));
                                                        idgroupE.setText(IdGrupe.get(mCurrentIndex1));
                                                        loadImageFromUrl1(phototab.get(mCurrentIndex1));

                                                        tar1 = String.valueOf(mCurrentIndex1);
                                                        temp1.setText(tar1);


                                                    }
                                                });
                                        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });

                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                        poi++;
                                        String tye = "" + poi;
                                        wselTaille.setText(tye);

                                    }


//                                        saveData2();


                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AfficherListe.this, "error1qsdqs" + e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AfficherListe.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Section", Id);
                params.put("Ctt", Ctt);
                params.put("mo", mo);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(AfficherListe.this);
        requestQueue.add(stringRequest1);


    }
    public void loadImageFromUrl(String url) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(visageprof, new com.squareup.picasso.Callback() {


            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }
    public void loadImageFromUrl1(String url) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(visageEtudiant, new com.squareup.picasso.Callback() {


            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });


    }
    public void InsertEtud( final String date,final String CodeEtudiant,final String Noa, final String P_ou_A, final String IdSection, final String IdGroupe,final String Prr) {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL4,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        Toast.makeText(AfficherListe.this, "étudiant(e) marqué(e)", Toast.LENGTH_SHORT).show();
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        erreurinsertion.setVisibility(View.VISIBLE);
                        int uy = Integer.parseInt(String.valueOf(cpt.getText())) + 1;
                        String uyui = String.valueOf(uy);
                        cpt.setText(uyui);
                        onAddField(Noa, Prr,CodeEtudiant,IdGroupe,urL);
                        Toast.makeText(AfficherListe.this, "Insertion échouée", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("CodeEtudiant", CodeEtudiant);
                params.put("date", date);
                params.put("P_ou_A", P_ou_A);
                params.put("ID_Section", IdSection);
                params.put("ID_Groupe", IdGroupe);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(AfficherListe.this);
        requestQueue.add(stringRequest);
    }
   /* public void getEtudiant(final String CoDA) {
        final StringRequest stringRequest2 = new StringRequest(Request.Method.POST, URL5,
                new Response.Listener<String>() {
                    public void onResponse(String response1) {
                        try {


                            JSONObject jsonObject = new JSONObject(response1);
                            String reussite = jsonObject.getString("reussite");
                            JSONArray jsonArray = jsonObject.getJSONArray("etudiant");
                            if (reussite.equals("1")) {
                                JSONObject object2 = jsonArray.getJSONObject(0);
                                Co=(object2.getString("CodeE"));
                                No=(object2.getString("Nom"));
                                Pre=(object2.getString("Prenom"));
                                Ph=(object2.getString("Photo"));
                                Se=(object2.getString("ID_Section"));
                                gr=(object2.getString("ID_Groupe"));
                            } else {
                                Toast.makeText(AfficherListe.this, "Code étudiant introuvable", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AfficherListe.this, "error12:" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AfficherListe.this, "error:" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("CODe", CoDA);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(AfficherListe.this);
        requestQueue.add(stringRequest2);


    }*/
    public void onAddField(final String Nomr, final String Prenomr, final String Coder, final String Groper, final String Img) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") final View rowView = inflater.inflate(R.layout.field, null);
        TextView nomr = rowView.findViewById(R.id.qs1);
        TextView prenomr = rowView.findViewById(R.id.qs2);
        TextView Codr = rowView.findViewById(R.id.qs3);
        TextView gropr = rowView.findViewById(R.id.qs4);
        nomr.setText(Nomr);
        prenomr.setText(Prenomr);
        Codr.setText(Coder);
        gropr.setText(Groper);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());
    }
    public void onDelete(View v) {

        int uye = Integer.parseInt(String.valueOf(cpt.getText())) - 1;
        String uye1 = String.valueOf(uye);
        cpt.setText(uye1);
        if (Integer.parseInt(String.valueOf(cpt.getText())) == 0)
            erreurinsertion.setVisibility(View.GONE);


        parentLinearLayout.removeView((View) v.getParent());
    }
    public void GetSect_grup(final String Cod) {
        final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL5,
                new Response.Listener<String>() {
                    public void onResponse(String response1) {
                        try {
                            Section2.clear();
                            JSONObject jsonObject = new JSONObject(response1);

                            String succes = jsonObject.getString("succes");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (succes.equals("1")) {
                                JSONObject object = jsonArray.getJSONObject(0);
                                String taille = jsonObject.getString("taille");

                                int j = 0;
                                for (int i = 0; i < Integer.parseInt(taille); i++) {
                                    String J = "Id" + j;
                                    Section2.add(object.getString(J));
                                    j++;
                                }
                                z = Section2.get(0) + ",";
                                for (int i = 1; i < Section2.size(); i++) {
                                    if (i == Section2.size() - 1)
                                        z = z + Section2.get(i);
                                    else z = z + Section2.get(i) + ",";
                                }
                            }
                            ert3.setText(z);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AfficherListe.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AfficherListe.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id_prof", Cod);
                params.put("mo", mo);
                params.put("Ctt", Ctt);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest1);
    }

}
