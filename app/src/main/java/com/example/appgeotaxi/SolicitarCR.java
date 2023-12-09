package com.example.appgeotaxi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

//NO SE ESTÁ USANDO ESTÁ CLASE
public class SolicitarCR extends AppCompatActivity {

    EditText tr_origin, tr_destination;
    Button bt_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_viajes);

        tr_origin = findViewById(R.id.tr_origin);
        tr_destination = findViewById(R.id.tr_destination);
        bt_request = findViewById(R.id.bt_request);

        Log.d("SolicitarCR", "Aun no");
        bt_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTravelRequest();
            }
        });
    }

    private void insertTravelRequest() {
        Log.d("SolicitarCR", "Entra");
        Toast.makeText(this, "hola", Toast.LENGTH_SHORT).show();

        String origin = tr_origin.getText().toString().trim();
        String destination = tr_destination.getText().toString().trim();

        Log.d("SolicitarCR", "Origin: " + origin + ", Destination: " + destination);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");

        if (origin.isEmpty() || destination.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        } else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://geotaxi-website.000webhostapp.com/insertar.php",
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            if (response.equalsIgnoreCase("Datos insertados")) {
                                Toast.makeText(SolicitarCR.this, "Datos insertados", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), SolicitarCR.class));
                                finish();
                            } else {
                                Toast.makeText(SolicitarCR.this, "Error", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SolicitarCR.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
            ) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>params=new HashMap<>();
                    params.put("TravelRequest_origin",origin);
                    params.put("TravelRequest_destination",destination);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(SolicitarCR.this);
            requestQueue.add(request);
        }
        }
    }


