package com.example.ledger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;
public class Absence extends AppCompatActivity {
    public EditText date;
    public Button bouton;
    public String Code, ph, z, etv3, Ctt, mo;
    private static String URL5 = "https://ledgerplus.000webhostapp.com/Legder/recupererSECTION.php";
    private static String URL4 = "https://ledgerplus.000webhostapp.com/Legder/GetAbsence.php";
    public LinearLayout parentLinearLayout, parentLinearLayout2;
    public TextView Nbre, ert3;
    List<String> Pr_ou_Ab = new ArrayList<>();
    List<String> Module = new ArrayList<>();
    List<String> Section = new ArrayList<>();
    List<String> Cour_tp_td = new ArrayList<>();
    List<String> Nom = new ArrayList<>();
    List<String> Prenom = new ArrayList<>();
    List<String> ID_Section = new ArrayList<>();
    List<String> ID_Groupe = new ArrayList<>();
    List<String> Photo = new ArrayList<>();
    List<String> P_ou_A = new ArrayList<>();
    List<String> CodeEtud = new ArrayList<>();


    public CircleImageView visageEtd;
    public Spinner sect_Gr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence);
        parentLinearLayout = findViewById(R.id.ter789);
        parentLinearLayout2 = findViewById(R.id.ter4);
        date = findViewById(R.id.dat2);
        bouton = findViewById(R.id.button17);
        visageEtd = findViewById(R.id.visageEtud57);
        sect_Gr = findViewById(R.id.spinner4);
        Intent intent = getIntent();
        Code = intent.getStringExtra(firstteacherActivity.Id_responsabl);
        ph = intent.getStringExtra(firstteacherActivity.image);
        mo = intent.getStringExtra(firstteacherActivity.mo);
        Ctt = intent.getStringExtra(firstteacherActivity.Ctt);
        ert3 = findViewById(R.id.ert3);


        GetSect_grup(Code);

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
                                            String r = "null";

                                            if (Ctt.equals("Cours")) {
                                                for (int i = 0; i < list.size(); i++) {
                                                    if (list.get(i).equals("1")) {
                                                        r = "A";
                                                    } else {
                                                        r = "B";
                                                    }
                                                    SeCtion.add(r);
                                                }
                                            } else {
                                                for (int i = 0; i < list.size(); i++) {
                                                    switch (list.get(i)) {
                                                        case "1":
                                                            r = "A01";
                                                            break;
                                                        case "2":
                                                            r = "A02";
                                                            break;
                                                        case "3":
                                                            r = "B01";
                                                            break;
                                                        case "4":
                                                            r = "B02";
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                    SeCtion.add(r);
                                                }
                                            }
                                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Absence.this, android.R.layout.simple_spinner_item, SeCtion);
                                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            sect_Gr.setAdapter(dataAdapter);
                                        }
                                    }
        );
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    date.setText("");
                }
            }
        });
        loadImageFromUrl(ph);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date.getText().toString().isEmpty()) date.setText("champ vide");
                else {
                    if (sect_Gr.getSelectedItem().equals("Aucune"))
                        Toast.makeText(Absence.this, "aucun module n'a été choisi", Toast.LENGTH_SHORT).show();
                    else
                        Absence(sect_Gr.getSelectedItem().toString(), date.getText().toString());
                }
            }
        });
    }

    public void GetSect_grup(final String Cod) {
        final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL5,
                new Response.Listener<String>() {
                    public void onResponse(String response1) {
                        try {

                            JSONObject jsonObject = new JSONObject(response1);

                            String succes = jsonObject.getString("succes");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (succes.equals("1")) {
                                JSONObject object = jsonArray.getJSONObject(0);
                                String taille = jsonObject.getString("taille");

                                int j = 0;
                                for (int i = 0; i < Integer.parseInt(taille); i++) {
                                    String J = "Id" + j;
                                    Section.add(object.getString(J));
                                    j++;
                                }
                                z = Section.get(0) + ",";
                                for (int i = 1; i < Section.size(); i++) {
                                    if (i == Section.size() - 1)
                                        z = z + Section.get(i);
                                    else z = z + Section.get(i) + ",";
                                }
                            }
                            ert3.setText(z);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Absence.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Absence.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
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

    public void loadImageFromUrl(String url) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(visageEtd, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {
            }
        });
    }

    public void Absence(final String Gr_Se, final String Date) {
        final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL4,
                new Response.Listener<String>() {
                    public void onResponse(String response1) {
                        try {
                            Pr_ou_Ab.clear();
                            Module.clear();
                            Cour_tp_td.clear();
                            JSONObject jsonObject = new JSONObject(response1);
                            JSONArray jsonArray = jsonObject.getJSONArray("absence");
                            JSONObject object2 = jsonArray.getJSONObject(0);
                            String sizeTab = jsonObject.getString("elt");
                            int j = 0;
                            for (int i = 0; i < Integer.parseInt(sizeTab); i = i + 7) {
                                String J = "CodeEtud" + j;
                                CodeEtud.add(object2.getString(J));
                                j++;
                            }
                            j = 0;
                            for (int i = 1; i < Integer.parseInt(sizeTab); i = i + 7) {
                                String J = "Nom" + j;
                                Nom.add(object2.getString(J));
                                j++;
                            }
                            j = 0;
                            for (int i = 2; i < Integer.parseInt(sizeTab); i = i + 7) {
                                String J = "ID_Section" + j;
                                ID_Section.add(object2.getString(J));
                                j++;
                            }
                            j = 0;
                            for (int i = 3; i < Integer.parseInt(sizeTab); i = i + 7) {
                                String J = "ID_Groupe" + j;
                                ID_Groupe.add(object2.getString(J));
                                j++;
                            }
                            j = 0;
                            for (int i = 4; i < Integer.parseInt(sizeTab); i = i + 7) {
                                String J = "Prenom" + j;
                                Prenom.add(object2.getString(J));
                                j++;
                            }
                            j = 0;
                            for (int i = 5; i < Integer.parseInt(sizeTab); i = i + 7) {
                                String J = "Photo" + j;
                                Photo.add(object2.getString(J));
                                j++;
                            }
                            j = 0;
                            for (int i = 6; i < Integer.parseInt(sizeTab); i = i + 7) {
                                String J = "P_ou_A" + j;
                                P_ou_A.add(object2.getString(J));
                                j++;
                            }
                            int ty = 0;
                            while (ty < Nom.size()) {
                                onAddField(Nom.get(ty), Prenom.get(ty), CodeEtud.get(ty), Photo.get(ty), P_ou_A.get(ty));
                                ty++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Absence.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }
                }
                ,//section,date,cours_tp_td,module
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Absence.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Gr_Se", Gr_Se);
                params.put("Date", Date);
                params.put("mo", mo);
                params.put("Ctt", Ctt);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Absence.this);
        requestQueue.add(stringRequest1);
    }

    public void onAddField(final String nomz, final String prenomz, final String Codez, final String phz, final String a_pz) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") final View rowView = inflater.inflate(R.layout.absenceprof, null);
        TextView nomr = rowView.findViewById(R.id.textView73);
        TextView prenomr = rowView.findViewById(R.id.textView96);
        TextView Codr = rowView.findViewById(R.id.textView98);
        CircleImageView phu = rowView.findViewById(R.id.imgff);
        TextView a_p = rowView.findViewById(R.id.textView95);
        nomr.setText(nomz);
        prenomr.setText(prenomz);
        Codr.setText(Codez);
        Picasso.with(this).load(phz).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(phu, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {
            }
        });
        a_p.setText(a_pz);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());
    }
}




