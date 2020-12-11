package com.example.ledger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import de.hdodenhof.circleimageview.CircleImageView;
public class listeNoire extends AppCompatActivity {
    public CircleImageView visageEtd;
    private static String URL2 = "https://ledgerplus.000webhostapp.com/Legder/rendreA0.php";
            public Switch  sw1;
            public String Code,ph,Datt,mo,Ctt;
            private static String URL5 = "https://ledgerplus.000webhostapp.com/Legder/NbreBlack.php";
            private static String URL4 = "https://ledgerplus.000webhostapp.com/Legder/Nbreyellow.php";
            private static String URL3 = "https://ledgerplus.000webhostapp.com/Legder/Black_YellowList.php";
            public LinearLayout parentLinearLayout;
            public TextView nbreblack,nbreyellow,module1,module2;
            List<String> b_Nom = new ArrayList<>();
            List<String> b_Prenom = new ArrayList<>();
            List<String> b_Groupe = new ArrayList<>();
            List<String> b_Section = new ArrayList<>();
            List<String> b_Code = new ArrayList<>();
            List<String> b_Nom2 = new ArrayList<>();
            List<String> b_nbreDeSeances= new ArrayList<>();
            List<String> b_mail= new ArrayList<>();
            String tempo;


             @Override
                protected void onCreate(Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);
                 setContentView(R.layout.activity_liste_noire);

                  parentLinearLayout = findViewById(R.id.ter99);
                  nbreblack = findViewById(R.id.black);
                  nbreyellow = findViewById(R.id.yellow);
                  sw1 = findViewById(R.id.switch2);
                  module1=findViewById(R.id.module1);
                  module2=findViewById(R.id.module2);
                 visageEtd=findViewById(R.id.xxxc);

                 NbreBlack();
                 Nbreyellow();

                 sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                     public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                         if (isChecked) {
                             parentLinearLayout.removeAllViews();
                             tempo="Yellow";
                             Black_YellowList(tempo,"YellowList");
                         }
                         else { parentLinearLayout.removeAllViews();tempo="Black";Black_YellowList(tempo,"BlackList");}
                     }
                 });
                 Intent intent = getIntent();
                 mo = intent.getStringExtra(firstteacherActivity.mo);
                 module1.setText(mo);
                 module2.setText(mo);
                 Ctt = intent.getStringExtra(firstteacherActivity.Ctt);
                 ph = intent.getStringExtra(firstteacherActivity.image);
                 loadImageFromUrl(ph);
             }
             //5 NbreBlack
            public void NbreBlack() {
                final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL5,
                        new Response.Listener<String>() {
                            public void onResponse(String response1) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response1);
                                    JSONArray jsonArray = jsonObject.getJSONArray("absence");
                                    JSONObject object2 = jsonArray.getJSONObject(0);
                                    String J = "NbreBlack" ;
                                    nbreblack.setText(object2.getString(J));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(listeNoire.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                                }
                            }
                        }
                        ,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(listeNoire.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("module", mo);
                        params.put("Cours_tp_td", Ctt);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(listeNoire.this);
                requestQueue.add(stringRequest1);


            }
            //4 NbreYellow
            public void Nbreyellow() {
                final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL4,
                        new Response.Listener<String>() {
                            public void onResponse(String response1) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response1);
                                    JSONArray jsonArray = jsonObject.getJSONArray("absence");
                                    JSONObject object2 = jsonArray.getJSONObject(0);
                                    String J = "NbreYellow" ;
                                    nbreyellow.setText(object2.getString(J));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(listeNoire.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                                }
                            }
                        }
                        ,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(listeNoire.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("module", mo);
                        params.put("Cours_tp_td", Ctt);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(listeNoire.this);
                requestQueue.add(stringRequest1);


            }
             //3
            public void  Black_YellowList(final String tem,final String tre){
            final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL3,
            new Response.Listener<String>() {
            public void onResponse(String response1) {
            try {
            b_Nom.clear();
            b_Prenom.clear();
            b_Section.clear();
            b_Groupe.clear();
            b_Nom2.clear();
            b_Code.clear();
            b_nbreDeSeances.clear();
            b_mail.clear();
            JSONObject jsonObject = new JSONObject(response1);
            JSONArray jsonArray = jsonObject.getJSONArray("absence");

            String sizeTab = jsonObject.getString("elt");
           String succese = jsonObject.getString("succes");
           if(succese.equals("1")) {

               JSONObject object2 = jsonArray.getJSONObject(0);
               int j = 0;
               for (int i = 0; i < Integer.parseInt(sizeTab); i = i + 7) {
                   String J = "b_Code" + j;
                   b_Code.add(object2.getString(J));
                   j++;
               }
               j = 0;
               for (int i = 1; i < Integer.parseInt(sizeTab); i = i + 7) {
                   String J = "b_Nom" + j;
                   b_Nom.add(object2.getString(J));
                   j++;
               }
               j = 0;
               for (int i = 2; i < Integer.parseInt(sizeTab); i = i + 7) {
                   String J = "b_Prenom" + j;
                   b_Prenom.add(object2.getString(J));
                   j++;
               }
               j = 0;
               for (int i = 3; i < Integer.parseInt(sizeTab); i = i + 7) {
                   String J = "b_Groupe" + j;
                   b_Groupe.add(object2.getString(J));
                   j++;
               }
               j = 0;
               for (int i = 4; i < Integer.parseInt(sizeTab); i = i + 7) {
                   String J = "b_Section" + j;
                   b_Section.add(object2.getString(J));
                   j++;
               }
               j = 0;
               for (int i = 5; i < Integer.parseInt(sizeTab); i = i + 7) {
                   String J = "b_mail" + j;
                   b_mail.add(object2.getString(J));
                   j++;
               }
               j = 0;
               for (int i = 6; i < Integer.parseInt(sizeTab); i = i + 7) {
                   String J = "b_nbreDeSeances" + j;
                   b_nbreDeSeances.add(object2.getString(J));
                   j++;
               }


               int ty = 0;
               while (ty < b_Nom.size()) {
                   onAddField(b_Nom.get(ty), b_Prenom.get(ty), b_Groupe.get(ty), b_Section.get(ty), b_Code.get(ty), tem, b_nbreDeSeances.get(ty), b_mail.get(ty), tre);
                   ty++;
               }

           }else{

               Toast.makeText(listeNoire.this, "aucun elt dans la liste"+tre, Toast.LENGTH_LONG).show();
           }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(listeNoire.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                                }
                            }
                        }
                        ,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(listeNoire.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("Color", tem);
                        params.put("module", mo);
                        params.put("Cours_tp_td", Ctt);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(listeNoire.this);
                requestQueue.add(stringRequest1);
            }
    public void onAddField(final String nom, final String prenom, final String gro,final String dec, final String codaa,final String te,final String pr, final String mail,final String b_lis) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                @SuppressLint("InflateParams") final View rowView = inflater.inflate(R.layout.blacklist, null);
                TextView nomr = rowView.findViewById(R.id.b_nom);
                TextView prenomr = rowView.findViewById(R.id.b_prenom);
                TextView code = rowView.findViewById(R.id.b_code);
                TextView secto = rowView.findViewById(R.id.b_section);
                TextView groupe = rowView.findViewById(R.id.b_groupe);
                TextView nom2 = rowView.findViewById(R.id.b_nom2);
                TextView prese = rowView.findViewById(R.id.nbre);
                TextView mai= rowView.findViewById(R.id.mail);
                TextView b_lost = rowView.findViewById(R.id.b_list);
                b_lost.setText(b_lis);
                mai.setText(mail);
                prese.setText(pr);
                RelativeLayout re=rowView.findViewById(R.id.backGr);
                nomr.setText(nom);
                prenomr.setText(prenom);
                code.setText(codaa);
                secto.setText(dec);
                groupe.setText(gro);
                nom2.setText(nom);
                parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());
            }
    public void loadImageFromUrl(String url){
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(visageEtd,new com.squareup.picasso.Callback(){
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError() {
            }
        });}
    public void onDeletefrom(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") final View rowView = (View) v.getParent();
        TextView Codez = rowView.findViewById(R.id.b_code);
        TextView lisa = rowView.findViewById(R.id.b_list);
        String t1=Codez.getText().toString();
        String t2=lisa.getText().toString();
       rendreA0(t1,t2);

    }
    public void onSendWArning(View v) {
        @SuppressLint("InflateParams") final View rowView = (View) v.getParent();
        TextView mail = rowView.findViewById(R.id.mail);
        Intent i=new Intent(Intent.ACTION_SEND);
        String re=mail.getText().toString();
        String[] tabl={re};
        i.putExtra(Intent.EXTRA_EMAIL,tabl);
        i.setType("message/rfc822");
        startActivity(Intent.createChooser(i,"veuillez choisir un service de messagerie svp"));
    }
    public void rendreA0(final String Code,final String list){
        final StringRequest stringRequest2 = new StringRequest(Request.Method.POST, URL2,
                new Response.Listener<String>() {
                    public void onResponse(String response1) {
                        try {
                            JSONObject jsonObject = new JSONObject(response1);
                            String reussite = jsonObject.getString("succes");
                            if (reussite.equals("1")) {
                                Toast.makeText(listeNoire.this, "suppression effectuée de "+list, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(listeNoire.this, "suppression echouée de "+list, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(listeNoire.this, "error12:" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(listeNoire.this, "error:" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("CODe", Code);
                params.put("list", list);
                params.put("module", mo);
                params.put("Cours_tp_td", Ctt);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(listeNoire.this);
        requestQueue.add(stringRequest2);
    }
}

