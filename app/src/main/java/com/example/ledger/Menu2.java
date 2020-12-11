package com.example.ledger;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
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
import java.util.HashMap;
import java.util.Map;
public class Menu2 extends AppCompatActivity {
    private EditText mnom;
    private  EditText mmotDePasse;
    private ProgressBar mp1;
    private Button mButton;
    public Switch swi;
    private static String URL1="https://ledgerplus.000webhostapp.com/Legder/login2.php";
    private static String URL2="https://ledgerplus.000webhostapp.com/Legder/login21.php";
    public static final String Id_responsable="Id_responsable";
    public static final String Nom="Nom";
    public static final String Responsa="Responsa";
    public static final String Image="Image";
    public static final String Descrip="Descrip";
    public static final String CodeE= "CodeE";
    public static final String nom ="nom";
    public static final String prenom= "prenom";
    public static final String id_Section= "id_Section";
    public static final String id_Groupe= "id_Groupe";
    public static final String photo ="photo";
    public static final String Pas ="Pas";
    public static final String MotDe ="MotDe";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        swi=findViewById(R.id.switch1);
        mnom=findViewById(R.id.editText_2);
        mmotDePasse=findViewById(R.id.editText_2_8);
        mButton=findViewById(R.id.button_2_4);
        mp1=findViewById(R.id.adminProgressBar_2);
        mp1.setVisibility(View.GONE);
        final MediaPlayer Welcome=MediaPlayer.create(this,R.raw.az);
        Welcome.start();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom=mnom.getText().toString().trim();
                String motDepasse=mmotDePasse.getText().toString().trim();
                if( nom.isEmpty())
                {
                    Toast.makeText(Menu2.this, "veuillez entrer le Login svp", Toast.LENGTH_LONG).show();
                }
                if(motDepasse.isEmpty())
                {
                    Toast.makeText(Menu2.this, "veuillez entrer le mot de passe svp", Toast.LENGTH_LONG).show();
                }
                if(!nom.isEmpty()&&!motDepasse.isEmpty())
                {
                     if(!swi.isChecked())
                     {
                     login1(nom,motDepasse);
                     }
                     else
                     {
                     login2(nom,motDepasse);
                    }
                }

            }
        });}
    //login pour enseignat a condition que le switch est unchecked
    private void login1(final String nom, final String motDepasse){
        mp1.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);
        final StringRequest stringRequest=new StringRequest(Request.Method.POST,URL1,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("succes");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if (succes.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String Id = object.getString("Id");
                                    String nom = object.getString("Nom");
                                    String Password = object.getString("Password");
                                    String descrip = object.getString("Description");
                                    String image = object.getString("image");
                                    String responsa = object.getString("responsableDe");

                                    mButton.setVisibility(View.VISIBLE);
                                    mp1.setVisibility(View.GONE);
                                    mnom.setText("");
                                    mmotDePasse.setText("");
                                    Intent intent = new Intent(Menu2.this, firstteacherActivity.class);

                                    intent.putExtra("Id_responsable",Id);
                                    intent.putExtra("Nom",nom);
                                    intent.putExtra("Descrip",descrip);
                                    intent.putExtra("Image",image);
                                    intent.putExtra("Responsa",responsa);
                                    intent.putExtra("Pas",Password);


                                    startActivity(intent);
                                }
                            }

                            else{
                                Toast.makeText(Menu2.this,"Nom ou Mot de passe incorrecte ", Toast.LENGTH_LONG).show();
                                mp1.setVisibility(View.GONE);
                                mButton.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Menu2.this, "error1"+e.toString(), Toast.LENGTH_LONG).show();
                            mButton.setVisibility(View.VISIBLE);
                            mp1.setVisibility(View.GONE);
                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Menu2.this,"error2"+error.toString(),Toast.LENGTH_LONG).show();
                        mButton.setVisibility(View.VISIBLE);
                        mp1.setVisibility(View.GONE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Nom", nom);
                params.put("Password", motDepasse);
                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    //login pour etudiant a condition que le switch est checked
    private void login2(final String CodeE2, final String Password2){
        mp1.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);
        final StringRequest stringRequest=new StringRequest(Request.Method.POST,URL2,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("succes");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if (succes.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String CodeE1 = object.getString("CodeE");
                                    String nom1 = object.getString("Nom");
                                    String prenom1 = object.getString("Prenom");
                                    String id_Section1 = object.getString("Id_Section");
                                    String id_Groupe1 = object.getString("Id_Groupe");
                                    String photo1 = object.getString("Photo");
                                    String mot1 = object.getString("MotDePasse");
                                    mButton.setVisibility(View.VISIBLE);
                                    mp1.setVisibility(View.GONE);
                                    mnom.setText("");
                                    mmotDePasse.setText("");
                                    Intent intent = new Intent(Menu2.this, Etudiant.class);
                                    intent.putExtra("CodeE",CodeE1);
                                    intent.putExtra("nom",nom1);
                                    intent.putExtra("prenom",prenom1);
                                    intent.putExtra("id_Section",id_Section1);
                                    intent.putExtra("id_Groupe",id_Groupe1);
                                    intent.putExtra("photo",photo1);
                                    intent.putExtra("MotDe",mot1);

                                    startActivity(intent);
                                }
                            }

                            else{
                                Toast.makeText(Menu2.this,"Code ou Mot de passe incorrecte ", Toast.LENGTH_LONG).show();
                                mp1.setVisibility(View.GONE);
                                mButton.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Menu2.this, "error1"+e.toString(), Toast.LENGTH_LONG).show();
                            mButton.setVisibility(View.VISIBLE);
                            mp1.setVisibility(View.GONE);
                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Menu2.this,"error2"+error.toString(),Toast.LENGTH_LONG).show();
                        mButton.setVisibility(View.VISIBLE);
                        mp1.setVisibility(View.GONE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("CodeE", CodeE2);
                params.put("Password", Password2);
                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
