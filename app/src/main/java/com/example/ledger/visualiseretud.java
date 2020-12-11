package com.example.ledger;

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

import de.hdodenhof.circleimageview.CircleImageView;
public class visualiseretud extends AppCompatActivity {
public EditText date;
public Button bouton;
public String Code,ph,Datt;
private static String URL5 = "https://ledgerplus.000webhostapp.com/Legder/Absnece.php";
private static String URL4 = "https://ledgerplus.000webhostapp.com/Legder/NbreFoisTotal.php";
private static String URL3 = "https://ledgerplus.000webhostapp.com/Legder/NbreFoisParModule.php";
public LinearLayout parentLinearLayout,parentLinearLayout2;
    public TextView Nbre;
    List<String> Pr_ou_Ab = new ArrayList<>();
    List<String> nbreDeSeances = new ArrayList<>();
    List<String> Module = new ArrayList<>();
    List<String> module = new ArrayList<>();
    List<String> Cour_tp_td = new ArrayList<>();
    public CircleImageView visageEtd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiseretud);

        parentLinearLayout=findViewById(R.id.ter6);
        parentLinearLayout2=findViewById(R.id.ter4);
        Intent intent = getIntent();
        Code = intent.getStringExtra(Etudiant.Code);
        ph = intent.getStringExtra(Etudiant.Image);
        Datt = intent.getStringExtra(Etudiant.datt);
        NbreTotal(Code);
        NbreparModule(Code);
//if(!Datt.isEmpty()) date.setText(Datt);
        date=findViewById(R.id.dat);
        bouton=findViewById(R.id.button20);
        Nbre=findViewById(R.id.textView47);
        visageEtd=findViewById(R.id.visageEtud100);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date.getText().toString().isEmpty())    date.setText("champ vide");
                else {
                    Absence(Code, date.getText().toString());
                }
            }
        });
        loadImageFromUrl(ph);
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==true){
                    date.setText("");}
            }
        });
    }

   public void  Absence(final String Code,final String Date){
       final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL5,
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
                               String triplet = jsonObject.getString("triplet");
                               int j = 0;
                               for (int i = 0; i < Integer.parseInt(sizeTab); i = i + 3) {
                                   String J = "P_ou_A" + j;
                                   Pr_ou_Ab.add(object2.getString(J));
                                   j++;
                               }
                               j = 0;
                               for (int i = 2; i < Integer.parseInt(sizeTab); i = i + 3) {
                                   String J = "Module" + j;
                                   Module.add(object2.getString(J));
                                   j++;
                               }
                               j = 0;
                               for (int i = 1; i < Integer.parseInt(sizeTab); i = i + 3) {
                                   String J = "Cour_tp_td" + j;
                                   Cour_tp_td.add(object2.getString(J));
                                   j++;
                               }
int ty=0;
while(ty<Integer.parseInt(triplet)) {
    onAddField(Pr_ou_Ab.get(ty),Cour_tp_td.get(ty),Module.get(ty));
ty++;
}
                       } catch (JSONException e) {
                           e.printStackTrace();
                           Toast.makeText(visualiseretud.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                       }
                   }
               }
               ,
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(visualiseretud.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                   }
               }) {
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params = new HashMap<>();
               params.put("Code", Code);
               params.put("Date", Date);
               return params;
           }
       };
       RequestQueue requestQueue = Volley.newRequestQueue(visualiseretud.this);
       requestQueue.add(stringRequest1);
   }
   public void onAddField(final String ab_ou_pr, final String Cour_tp_td, final String module) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") final View rowView = inflater.inflate(R.layout.absencepardate, null);
        TextView nomr = rowView.findViewById(R.id.textView49);
        TextView prenomr = rowView.findViewById(R.id.textView51);
        TextView Codr = rowView.findViewById(R.id.textView53);
        nomr.setText(ab_ou_pr);
        prenomr.setText(Cour_tp_td);
        Codr.setText(module);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());
    }
   public void NbreTotal(final String Code){
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
                           String J = "Nbrefois" ;
                           Nbre.setText(object2.getString(J));
                       } catch (JSONException e) {
                           e.printStackTrace();
                           Toast.makeText(visualiseretud.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                       }
                   }
               }
               ,
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(visualiseretud.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                   }
               }) {
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params = new HashMap<>();
               params.put("Code", Code);
               return params;
           }
       };
       RequestQueue requestQueue = Volley.newRequestQueue(visualiseretud.this);
       requestQueue.add(stringRequest1);


   }
   public void NbreparModule(final String Code){
        final StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL3,
                new Response.Listener<String>() {
                    public void onResponse(String response1) {
                        try {
                            nbreDeSeances.clear();
                            module.clear();
                            JSONObject jsonObject = new JSONObject(response1);
                              JSONArray jsonArray = jsonObject.getJSONArray("absence");
                                JSONObject object2 = jsonArray.getJSONObject(0);
                                String sizeTab = jsonObject.getString("size");
                                String Couple = jsonObject.getString("couple");
                                int j = 0;
                                for (int i = 0; i < Integer.parseInt(sizeTab); i = i + 2) {
                                    String J = "NbreDeSeances" + j;
                                    nbreDeSeances.add(object2.getString(J));
                                    j++;
                                }
                                j = 0;
                                for (int i = 1; i < Integer.parseInt(sizeTab); i = i + 2) {
                                    String J = "Module" + j;
                                    module.add(object2.getString(J));
                                    j++;
                                }
                            int ty=0;
                            while(ty<Integer.parseInt(Couple)) {
                                onAddField2(nbreDeSeances.get(ty),module.get(ty));
ty++;
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(visualiseretud.this, "error1" + e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(visualiseretud.this, "error2" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Code", Code);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(visualiseretud.this);
        requestQueue.add(stringRequest1);


    }
   public void onAddField2(final String nbre, final String mod) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") final View rowView = inflater.inflate(R.layout.absenceparmodule, null);
        TextView nomr = rowView.findViewById(R.id.nbre);
        TextView prenomr = rowView.findViewById(R.id.mod);
         nomr.setText(nbre);
         prenomr.setText(mod);
        parentLinearLayout2.addView(rowView, parentLinearLayout2.getChildCount());
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
