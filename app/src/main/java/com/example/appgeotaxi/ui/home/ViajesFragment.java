package com.example.appgeotaxi.ui.home;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.appgeotaxi.Adapter_CR;
import com.example.appgeotaxi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ViajesFragment extends Fragment {

    //Para solicitar viajes
    private EditText tr_origin;
    private EditText tr_destination;
    private Button btnSolicitarCR;

    //Para listar solicitudes
    ListView listViewViajes;
    Adapter_CR adapterCR;
    public static ArrayList<ViajesFragment> viajesFragmentList = new ArrayList<>();
    String url="https://appgeotaxi.000webhostapp.com/mostrar.php?TypeTravel=CR";
    ViajesFragment viajesFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_viajes, container, false);




        //Para solicitar viajes
        tr_origin = root.findViewById(R.id.tr_origin);
        tr_destination = root.findViewById(R.id.tr_destination);
        btnSolicitarCR = root.findViewById(R.id.bt_request);

        btnSolicitarCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTravelRequest("CR");
            }
        });

        // Para listar viajes
        listViewViajes = root.findViewById(R.id.listViewViajes);
        adapterCR = new Adapter_CR(getActivity(), viajesFragmentList);
        listViewViajes.setAdapter(adapterCR);

        if (viajesFragmentList.isEmpty()) {
            ListarDatos();
        }

        return root;
    }

    //MÉTODO PARA SOLICITAR UN VIAJE
    public void insertTravelRequest(String TypeTravel) {
        String typetravel = TypeTravel;
        String origin = tr_origin.getText().toString().trim();
        String destination = tr_destination.getText().toString().trim();

        if (origin.isEmpty() || destination.isEmpty()) {
            Toast.makeText(getActivity(), "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "https://appgeotaxi.000webhostapp.com/insertar.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("Datos insertados")) {
                            Toast.makeText(getActivity(), "Datos insertados", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                            // Resetea los campos después de la inserción exitosa
                            tr_origin.setText("");
                            tr_destination.setText("");

                            // Actualiza la lista después de la inserción
                            ListarDatos();
                        } else {
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TravelRequest_origin", origin);
                params.put("TravelRequest_destination", destination);
                params.put("TypeTravel_FK", typetravel);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }




    //PARA LISTAR LOS VIAJES
    String User_FK, TR_origin, TR_destination, TR_price;

    public ViajesFragment(){
    }

    public ViajesFragment(String User_FK, String TR_origin, String TR_destination, String TR_price){
        this.User_FK = User_FK;
        this.TR_origin = TR_origin;
        this.TR_destination = TR_destination;
        this.TR_price = TR_price;
    }

    public String getUser_FK() {
        return User_FK;
    }

    public void setUser_FK(String user_FK) {
        User_FK = user_FK;
    }

    public String getTR_origin() {
        return TR_origin;
    }

    public void setTR_origin(String TR_origin) {
        this.TR_origin = TR_origin;
    }

    public String getTR_destination() {
        return TR_destination;
    }

    public void setTR_destination(String TR_destination) {
        this.TR_destination = TR_destination;
    }

    public String getTR_price() {
        return TR_price;
    }

    public void setTR_price(String TR_price) {
        this.TR_price = TR_price;
    }

    //MÉTODO PARA LISTAR LOS DATOS
    private void ListarDatos(){
        viajesFragmentList.clear(); // Limpiar la lista antes de agregar nuevos datos

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String exito = jsonObject.getString("exito");
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    if (exito.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            String user = object.getString("User_FK");
                            String origin = object.getString("TravelRequest_origin");
                            String destination = object.getString("TravelRequest_destination");
                            String price = object.getString("TravelRequest_price");

                            viajesFragment = new ViajesFragment(user, origin, destination, price);
                            viajesFragmentList.add(viajesFragment);
                        }
                        adapterCR.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

}

