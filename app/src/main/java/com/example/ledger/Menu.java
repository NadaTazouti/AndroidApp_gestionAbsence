package com.example.ledger;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;



public class Menu extends AppCompatActivity {
   private EditText mnom;
   private  EditText mmotDePasse;
   private ProgressBar mp1;
   private Button mButton;
   private static String URL="https://ledgerplus.000webhostapp.com/Legder/login.php";     public static final String Name="Name";
   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_menu);

       mnom=(EditText)findViewById(R.id.editText);
       mmotDePasse=(EditText)findViewById(R.id.editText8);
       mButton=(Button)findViewById(R.id.button4);
       mp1=(ProgressBar)findViewById(R.id.adminProgressBar);

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

}
 if(motDepasse.isEmpty())
 {

 }
 if(!nom.isEmpty()&&!motDepasse.isEmpty())
 {
     login(nom,motDepasse);
 }

}
       });}
       private void login(final String nom, final String motDepasse){
mp1.setVisibility(View.VISIBLE);
mButton.setVisibility(View.GONE);
final StringRequest stringRequest=new StringRequest(Request.Method.POST,URL,
                                                 new Response.Listener<String>() {
                                                     public void onResponse(String response) {
                                                         try {
                                                             JSONObject jsonObject = new JSONObject(response);
                                                             String succes = jsonObject.getString("succes");
                                                             JSONArray jsonArray = jsonObject.getJSONArray("login");
                                                             if (succes.equals("1")) {
                                                                 /*for (int i = 0; i < jsonArray.length(); i++) {
                                                                     JSONObject object = jsonArray.getJSONObject(i);
                                                                     *//*String nom = object.getString("nom").trim();
                                                                     String motDepasse = object.getString("motDepasse").trim();*/
                                                                     mButton.setVisibility(View.VISIBLE);
                                                                     mp1.setVisibility(View.GONE);
                                                                     Intent intent = new Intent(Menu.this, admin.class);
                                                                     startActivity(intent);
                                                                 }


                                                             else{
                                                                 Toast.makeText(Menu.this,"Nom ou Mot de passe incorrecte ", Toast.LENGTH_LONG).show();

                                                             }
                                                         } catch (JSONException e) {
                                                             e.printStackTrace();
                                                             Toast.makeText(Menu.this, "error1"+e.toString(), Toast.LENGTH_LONG).show();
                                                             mButton.setVisibility(View.VISIBLE);
                                                             mp1.setVisibility(View.GONE);
                                                         }
                                                     }
                                                 }
                                                  ,
                                                             new Response.ErrorListener() {
    @Override
                                                                   public void onErrorResponse(VolleyError error) {
        Toast.makeText(Menu.this,"error2"+error.toString(),Toast.LENGTH_LONG).show();
        mButton.setVisibility(View.VISIBLE);
        mp1.setVisibility(View.GONE);
    }
})
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("nom", nom);
                    params.put("motDepasse", motDepasse);
                    return params;
                }

                };
                RequestQueue requestQueue= Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

}
        }
