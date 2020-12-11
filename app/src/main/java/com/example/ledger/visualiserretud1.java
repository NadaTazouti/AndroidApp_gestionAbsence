package com.example.ledger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageButton;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

public class visualiserretud1 extends AppCompatActivity {
    String[] monthName = {"Janvier", "Février",
            "Mars", "Avril", "Mai", "Juin", "Juillet",
            "Août", "Septembre", "Octobre", "Novembre",
            "Decembre"};
    List<String> SeCt,ab_ou_Pr,DAte ;
public TextView M1,M2,M3,ert3;
public int i=0;
public Spinner S1,S2;
public String etv3,choix,ph,Code;
public Button chercher;
public ImageButton calen;
    RadioGroup g;
    public RadioButton b99,b44,b10;
    public LinearLayout parentLinearLayout;
    private static String URL1 = "https://ledgerplus.000webhostapp.com/Legder/GetModules.php";
    private static String URL2 = "https://ledgerplus.000webhostapp.com/Legder/GetAbsenceParModule.php";
    private static String URL3 = "https://ledgerplus.000webhostapp.com/Legder/GetAbsenceParModule_mois.php";
    private static String URL4 = "https://ledgerplus.000webhostapp.com/Legder/GetAbsenceParModule_mois_mois.php";
    public CircleImageView visageEtd;
    public ImageButton bp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_visualiserretud1);
        GetModules("1");
        SeCt= new ArrayList<String>();
        ab_ou_Pr= new ArrayList<String>();
        DAte= new ArrayList<String>();

        chercher=findViewById(R.id.button21) ;visageEtd=findViewById(R.id.visageEtud4) ;
        Intent intent = getIntent();
        Code = intent.getStringExtra(Etudiant.Code1);

        ph = intent.getStringExtra(Etudiant.Image1);
        final Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)];
        loadImageFromUrl(ph);
         M1=findViewById(R.id.M1) ;
        bp=findViewById(R.id.imageButton3) ;
        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(visualiserretud1.this, calendrier.class);
                startActivity(intent);
            }
        });
        parentLinearLayout=findViewById(R.id.ter77) ;
        S1=findViewById(R.id.spinner3) ;
        S2=findViewById(R.id.spinner2) ;
        ert3=findViewById(R.id.temp5) ;
        M1.setText(month);
       while(i<monthName.length){
            if(monthName[i].equals(month)) break;
            i++;
        }
        SeCt.add("Aucun");
        SeCt.add("Cours");
        SeCt.add("TD");
        SeCt.add("TP");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(visualiserretud1.this, android.R.layout.simple_spinner_item, SeCt);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        S2.setAdapter(dataAdapter);



chercher.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) { g= (findViewById(R.id.gt));
        parentLinearLayout.removeAllViews();
        int selectedId = g.getCheckedRadioButtonId();
        RadioButton radioSexButton =findViewById(selectedId);
        if(S1.getSelectedItem().equals("Aucun"))   Toast.makeText(visualiserretud1.this, "veuillez choisir un module svp!", Toast.LENGTH_LONG).show();
        else{

            if(radioSexButton.getText().equals("Depuis le début du semestre"))
            {
            if(S2.getSelectedItem().equals("Aucun") ) Absencekolchi(S1.getSelectedItem().toString(),Code);
            else Absence(S1.getSelectedItem().toString(),S2.getSelectedItem().toString(),Code);
            }
            if(radioSexButton.getText().equals("uniquement pour le mois de"))
            {
                if(S2.getSelectedItem().equals("Aucun") ) Absencekolchi2(S1.getSelectedItem().toString(),cal.get(Calendar.MONTH)+1);
                else Absence2(S1.getSelectedItem().toString(),S2.getSelectedItem().toString(), String.valueOf(cal.get(Calendar.MONTH)+1));
            }
        }
    }
});

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
                                            SeCtion.add("Aucun");
                                            String r;
                                            for (int i = 0; i < list.size(); i++) {
                                                String gt=list.get(i);
                                                SeCtion.add(gt);
                                            }

                                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(visualiserretud1.this, android.R.layout.simple_spinner_item, SeCtion);
                                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            S1.setAdapter(dataAdapter);

                                        }

                                    }
        );
    }
//absence/modules
            public void GetModules(final String Code){
    final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL1,
            new Response.Listener<String>() {
                public void onResponse(String response1) {
                    try {
                        JSONObject jsonObject = new JSONObject(response1);
                        JSONArray jsonArray = jsonObject.getJSONArray("absence");
                        JSONObject object2 = jsonArray.getJSONObject(0);
                        String J = "Modules" ;
                        ert3.setText(object2.getString(J));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(visualiserretud1.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                    }
                }
            }
            ,
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(visualiserretud1.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                }
            }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("Code", Code);
            return params;
        }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(visualiserretud1.this);
    requestQueue.add(stringRequest1);





}
//url2+ab_ou_Pr,DAte=>listes+PHP=>P_ou_A,date,para dentree Module+Cours_TD_TD dans
            public void onAddField(final String ab_ou_pr, final String date) {
        String resu;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") final View rowView = inflater.inflate(R.layout.absenceparmodule2, null);
        TextView datee = rowView.findViewById(R.id.datee);
        TextView prese = rowView.findViewById(R.id.a_p);
        datee.setText(date);
        if(ab_ou_pr.equals("P")) resu="présent(e)";
      else  resu="absent(e)";
       prese.setText(resu);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());
    }
            public void onAddField2(final String Cours_TD_TP) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") final View rowView = inflater.inflate(R.layout.header, null);
        TextView Cours_TD_Tp = rowView.findViewById(R.id.textView56);
        Cours_TD_Tp.setText(Cours_TD_TP);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());
    }//separator
            public void  Absencekolchi(final String Modulty,final String couido) {

Absence5(Modulty,"Cours",couido);
Absence5(Modulty,"TD",couido);
Absence5(Modulty,"TP",couido);
}
 public void  Absence5(final String Modulty,final String Cours_TD_TP,final String couido){
        final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL2,
                new Response.Listener<String>() {
                    public void onResponse(String response1) {
                        try {
                            ab_ou_Pr.clear();
                            DAte.clear();
                            JSONObject jsonObject = new JSONObject(response1);
                            JSONArray jsonArray = jsonObject.getJSONArray("absence");
                            JSONObject object2 = jsonArray.getJSONObject(0);
                            String sizeTab = jsonObject.getString("elt");
                            String triplet = jsonObject.getString("couple");
                            int j = 0;
                            for (int i = 0; i < Integer.parseInt(sizeTab); i = i + 2) {
                                String J = "date" + j;
                                DAte.add(object2.getString(J));
                                j++;
                            }
                            j = 0;
                            for (int i = 1; i < Integer.parseInt(sizeTab); i = i + 2) {
                                String J = "P_ou_A" + j;
                                ab_ou_Pr.add(object2.getString(J));
                                j++;
                            }



                            int ty=0;
                            if(Cours_TD_TP.equals("Cours"))onAddField2("Cours");
                            if(Cours_TD_TP.equals("TD"))onAddField2("TD");
                            if(Cours_TD_TP.equals("TP"))onAddField2("TP");
                            while(ty<Integer.parseInt(triplet))
                            {
                                onAddField(ab_ou_Pr.get(ty),DAte.get(ty));
                                ty++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(visualiserretud1.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(visualiserretud1.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Code", couido);
                params.put("Module", Modulty);
                params.put("Cours_TD_TP", Cours_TD_TP);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(visualiserretud1.this);
        requestQueue.add(stringRequest1);
    }
public void  Absence(final String Modulty,final String Cours_TD_TP,final String couido){
        final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL2,
                new Response.Listener<String>() {
                    public void onResponse(String response1) {
                        try {
                            ab_ou_Pr.clear();
                            DAte.clear();
                            JSONObject jsonObject = new JSONObject(response1);
                            JSONArray jsonArray = jsonObject.getJSONArray("absence");
                            JSONObject object2 = jsonArray.getJSONObject(0);
                            String sizeTab = jsonObject.getString("elt");
                            String triplet = jsonObject.getString("couple");
                            int j = 0;
                            for (int i = 0; i < Integer.parseInt(sizeTab); i = i + 2) {
                                String J = "date" + j;
                                DAte.add(object2.getString(J));
                                j++;
                            }
                            j = 0;
                            for (int i = 1; i < Integer.parseInt(sizeTab); i = i + 2) {
                                String J = "P_ou_A" + j;
                                ab_ou_Pr.add(object2.getString(J));
                                j++;
                            }



                            int ty=0;
                            while(ty<Integer.parseInt(triplet))
                            {
                                onAddField(ab_ou_Pr.get(ty),DAte.get(ty));
                                ty++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(visualiserretud1.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(visualiserretud1.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Code", couido);
                params.put("Module", Modulty);
                params.put("Cours_TD_TP", Cours_TD_TP);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(visualiserretud1.this);
        requestQueue.add(stringRequest1);
    }
public void  Absencekolchi2(final String Modulty,int mm) {
        String trans= String.valueOf(mm);
        onAddField2("Cours");
        Absence2(Modulty,"Cours",trans);
        onAddField2("TD");
        Absence2(Modulty,"TD",trans);
        onAddField2("TP");
        Absence2(Modulty,"TP",trans);
    }
public void  Absencekolchi3(final String Modulty,int mm) {
        String trans= String.valueOf(mm);
        onAddField2("Cours");
        Absence3(Modulty,"Cours",trans);
        onAddField2("TD");
        Absence3(Modulty,"TD",trans);
        onAddField2("TP");
        Absence3(Modulty,"TP",trans);
    }
public void  Absence2(final String Modulty,final String Cours_TD_TP,final String mm){
        final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL3,
                new Response.Listener<String>() {
                    public void onResponse(String response1) {
                        try {
                            ab_ou_Pr.clear();
                            DAte.clear();
                            JSONObject jsonObject = new JSONObject(response1);
                            JSONArray jsonArray = jsonObject.getJSONArray("absence");
                            JSONObject object2 = jsonArray.getJSONObject(0);
                            String sizeTab = jsonObject.getString("elt");
                            String triplet = jsonObject.getString("couple");
                            int j = 0;
                            for (int i = 0; i < Integer.parseInt(sizeTab); i = i + 2) {
                                String J = "P_ou_A" + j;
                                ab_ou_Pr.add(object2.getString(J));
                                j++;
                            }
                            j = 0;
                            for (int i = 0; i < Integer.parseInt(sizeTab); i = i + 2) {
                                String J = "date" + j;
                                DAte.add(object2.getString(J));
                                j++;
                            }

                            int ty=0;
                            while(ty<Integer.parseInt(triplet))
                            {
                                onAddField(ab_ou_Pr.get(ty),DAte.get(ty));
                                ty++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(visualiserretud1.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(visualiserretud1.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Module", Modulty);
                params.put("Cours_TD_TD", Cours_TD_TP);
                params.put("mois", mm);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(visualiserretud1.this);
        requestQueue.add(stringRequest1);
    }
public void  Absence3(final String Modulty,final String Cours_TD_TP,final String mm){
        final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL4,
                new Response.Listener<String>() {
                    public void onResponse(String response1) {
                        try {
                            ab_ou_Pr.clear();
                            DAte.clear();
                            JSONObject jsonObject = new JSONObject(response1);
                            JSONArray jsonArray = jsonObject.getJSONArray("absence");
                            JSONObject object2 = jsonArray.getJSONObject(0);
                            String sizeTab = jsonObject.getString("elt");
                            String triplet = jsonObject.getString("couple");
                            int j = 0;
                            for (int i = 0; i < Integer.parseInt(sizeTab); i = i + 2) {
                                String J = "P_ou_A" + j;
                                ab_ou_Pr.add(object2.getString(J));
                                j++;
                            }
                            j = 0;
                            for (int i = 0; i < Integer.parseInt(sizeTab); i = i + 2) {
                                String J = "date" + j;
                                DAte.add(object2.getString(J));
                                j++;
                            }

                            int ty=0;
                            while(ty<Integer.parseInt(triplet))
                            {
                                onAddField(ab_ou_Pr.get(ty),DAte.get(ty));
                                ty++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(visualiserretud1.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(visualiserretud1.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Module", Modulty);
                params.put("Cours_TD_TD", Cours_TD_TP);
                params.put("mois", mm);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(visualiserretud1.this);
        requestQueue.add(stringRequest1);
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
}