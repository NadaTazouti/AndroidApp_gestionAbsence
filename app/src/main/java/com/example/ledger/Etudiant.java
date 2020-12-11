package com.example.ledger;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.ledger.Menu2.Image;
public class Etudiant extends AppCompatActivity {
public CircleImageView visageEtd;
public static final String ety="ety",SHARED_PREFS3 = "sharedPrefs";
public final String URL1="https://ledgerplus.000webhostapp.com/Legder/updatePassEtu.php";
public ImageButton plus;
public EditText ancienpas,Nouveaupas;
public TextView Nom,prenom;
public TextView date36;
public Button validerpas,validerchoix;
public ImageButton data;
public String id_S,id_Gr,n,p,ph,Mo,cO,Etd;
public Switch MySwitch;
public ImageView background;
public static String Code = "Code";
public static String Image = "Image";
public static String Code1 = "Code1";
public static String Image1 = "Image1";
public static String datt = "datt";
public RadioGroup g;
public EditText date1;
public static final String profile = "profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant);
        visageEtd = findViewById(R.id.visageEtud);
        Nom = findViewById(R.id.NomEtud);
        background = findViewById(R.id.x99);
        validerchoix = findViewById(R.id.button16);


        validerchoix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g = (findViewById(R.id.bt));
                int selectedId = g.getCheckedRadioButtonId();
                RadioButton radioSexButton = findViewById(selectedId);

                if (radioSexButton.getText().equals("par date")) {
                    Intent intent = new Intent(Etudiant.this, visualiseretud.class);
                    intent.putExtra("Code", cO);
                    intent.putExtra("Image", ph);
                    startActivity(intent);
                }
                if (radioSexButton.getText().equals("par module")) {
                    Intent intent = new Intent(Etudiant.this, visualiserretud1.class);
                    intent.putExtra("Code1", cO);
                    intent.putExtra("Image1", ph);
                    startActivity(intent);
                }
                else{

                }
            }
        });
        MySwitch = findViewById(R.id.switch3);
        date36 = findViewById(R.id.date36);
        prenom = findViewById(R.id.PrenomEtud);
        validerpas = findViewById(R.id.ChangerPass);
        ancienpas = findViewById(R.id.ancienMOT);
        Nouveaupas = findViewById(R.id.NewPassword);
        plus = findViewById(R.id.SavaoirPlusEtud);
        Intent intent = getIntent();
        n = intent.getStringExtra(Menu2.nom);
        p = intent.getStringExtra(Menu2.prenom);
        id_S = intent.getStringExtra(Menu2.id_Section);
        id_Gr = intent.getStringExtra(Menu2.id_Groupe);
        ph = intent.getStringExtra(Menu2.photo);
        Mo = intent.getStringExtra(Menu2.MotDe);
        cO = intent.getStringExtra(Menu2.CodeE);
        loadImageFromUrl(ph);
        Nom.setText(n);
        prenom.setText(p);
        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(Etudiant.this).create();
                alertDialog.setCancelable(true);
                alertDialog.setTitle("Plus d'informations");
                alertDialog.setMessage("+Nom: " + n + "\n+Prénom: " + p + " \n+Code de l'étudiant: " + cO + "\n+Section: " + id_S + "\n+Groupe: " + id_Gr + "\n+Mot de passe: " + Mo);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
        validerpas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!(ancienpas.getText().toString().isEmpty()) && (!(Nouveaupas.getText().toString().isEmpty()))) {
                    String top = ancienpas.getText().toString();
                    if (top.equals(Mo)) Updatepass(cO, Nouveaupas.getText().toString());
                    else
                        Toast.makeText(Etudiant.this, "mot de passe incorrecte", Toast.LENGTH_LONG).show();
                }
                if (ancienpas.getText().toString().isEmpty()) ancienpas.setText("champ vide");
                if (Nouveaupas.getText().toString().isEmpty()) Nouveaupas.setText("champ vide");
            }
        });
        ancienpas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    ancienpas.setText("");
                }
                if (hasFocus == true) {
                    ancienpas.setText("");
                }
            }
        });
        Nouveaupas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    Nouveaupas.setText("");
                }
            }
        });
        loadData2();
        updateData2();
        MySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) background.setImageResource(R.drawable.yty);
                else background.setImageResource(R.drawable.font);
            }
        });

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
    public void  Updatepass(final String CodeE,final String NP){
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL1,
                new Response.Listener<String>()
                {
                    public void onResponse(String response)
                    {
                        Toast.makeText(Etudiant.this, "Changement éffectué", Toast.LENGTH_SHORT).show();

                        saveData2();
                    }
                }
                ,
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(Etudiant.this, "Changement échoué", Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("CodeE", CodeE);
                params.put("MotDePasse",NP);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Etudiant.this);
        requestQueue.add(stringRequest);

    }
    public void saveData2() {
        SharedPreferences sharedPreferences1 = getSharedPreferences(SHARED_PREFS3, MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferences1.edit();
        editor3.putString(ety,getCurrentDay() +" à "+getCurrentTimeUsingDate());
        editor3.apply();
    }
    public void loadData2() {
        SharedPreferences sharedPreferences1 = getSharedPreferences(SHARED_PREFS3, MODE_PRIVATE);
        Etd = sharedPreferences1.getString(ety, "Aucun changement trouvé");

    }
    public void updateData2() {
        date36.setText(Etd);

    }
    public static String getCurrentTimeUsingDate() {

        Date date = new Date();

        String strDateFormat = "hh:mm:ss a";

        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

        String formattedDate= dateFormat.format(date);

       return(formattedDate);

    }
    public static String getCurrentDay(){
        Calendar calendar=Calendar.getInstance();
        String currentDate=DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        return currentDate;



    }
}
