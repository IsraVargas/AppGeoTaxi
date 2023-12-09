package com.example.appgeotaxi.ui.home;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.appgeotaxi.Adapter_MA;
import com.example.appgeotaxi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MandadosFragment extends Fragment {

    //Para solicitar mandados
    private EditText tr_origin;
    private EditText tr_destination;
    private EditText tr_description;
    private Button btnSolicitarMA;

    //Para listar solicitudes
    ListView listViewMandados;
    Adapter_MA adapterMA;
    public static ArrayList<MandadosFragment> mandadosFragmentList = new ArrayList<>();
    String url="https://appgeotaxi.000webhostapp.com/mostrar.php?TypeTravel=MA";
    MandadosFragment mandadosFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mandados, container, false);
        // Configura tu interfaz de usuario y lógica aquí



        //Para solicitar mandados
        tr_origin = root.findViewById(R.id.tr_origin);
        tr_destination = root.findViewById(R.id.tr_destination);
        tr_description = root.findViewById(R.id.tr_description);
        btnSolicitarMA = root.findViewById(R.id.bt_request);

        btnSolicitarMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { insertTravelRequest("MA"); }
        });


        // Para listar viajes
        listViewMandados = root.findViewById(R.id.listViewMandados);
        adapterMA = new Adapter_MA(getActivity(), mandadosFragmentList);
        listViewMandados.setAdapter(adapterMA);

        if (mandadosFragmentList.isEmpty()) {
            ListarDatos();
        }



        return root;
    }



    //MÉTODO PARA SOLICITAR UN VIAJE
    public void insertTravelRequest(String TypeTravel) {
        String typetravel = TypeTravel;
        String description = tr_description.getText().toString().trim();
        String origin = tr_origin.getText().toString().trim();
        String destination = tr_destination.getText().toString().trim();

        if (description.isEmpty() || origin.isEmpty() || destination.isEmpty()) {
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
                            tr_description.setText("");
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
                params.put("TravelRequest_description",description);
                params.put("TypeTravel_FK", typetravel);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }





    //PARA LISTAR LOS MANDADOS
    String User_FK, TR_origin, TR_destination, TR_description, TR_price;

    public MandadosFragment(){
    }

    public MandadosFragment(String User_FK, String TR_origin, String TR_destination, String TR_description, String TR_price){
        this.User_FK = User_FK;
        this.TR_origin = TR_origin;
        this.TR_destination = TR_destination;
        this.TR_description = TR_description;
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

    public String getTR_description() { return TR_description; }

    public void setTR_description(String TR_description) { this.TR_description = TR_description; }

    public String getTR_price() {
        return TR_price;
    }

    public void setTR_price(String TR_price) {
        this.TR_price = TR_price;
    }

    //MÉTODO PARA LISTAR LOS DATOS
    private void ListarDatos(){
        mandadosFragmentList.clear(); // Limpiar la lista antes de agregar nuevos datos

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
                            String description = object.getString("TravelRequest_description");
                            String price = object.getString("TravelRequest_price");

                            mandadosFragment = new MandadosFragment(user, origin, destination, description, price);
                            mandadosFragmentList.add(mandadosFragment);
                        }
                        adapterMA.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
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

