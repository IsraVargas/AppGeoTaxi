package com.example.appgeotaxi.ui.dashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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
import com.example.appgeotaxi.Adapter_CR;
import com.example.appgeotaxi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ViajesFragmentPR extends Fragment {
/*
    //Para solicitar viajes
    private EditText tr_origin_pr;
    private EditText tr_destination_pr;
    private EditText tr_dia_pr;
    private EditText tr_mes_pr;
    private EditText tr_año_pr;
    private Spinner tr_hora_pr;
    private Spinner tr_minutos_pr;
    private Button btnSolicitarCR;



    //Para listar solicitudes
    ListView listViewViajes;
    Adapter_CR adapterCR;
    public static ArrayList<ViajesFragmentPR> viajesFragmentList = new ArrayList<>();
    String url="https://appgeotaxi.000webhostapp.com/mostrar.php?TypeTravel=VA";
    ViajesFragmentPR viajesFragmentPR;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



    }*/
        /*
        View root = inflater.inflate(R.layout.fragment_viajes_pr, container, false);

        // Para solicitar viajes
        tr_origin_pr = root.findViewById(R.id.tr_origin_pr);
        tr_destination_pr = root.findViewById(R.id.tr_destination_pr);
        tr_dia_pr = root.findViewById(R.id.tr_dia_pr);
        tr_mes_pr = root.findViewById(R.id.tr_mes_pr);
        tr_año_pr = root.findViewById(R.id.tr_año_pr);
        tr_hora_pr = root.findViewById(R.id.tr_hora_pr);
        tr_minutos_pr = root.findViewById(R.id.tr_minutos_pr);
        btnSolicitarCR = root.findViewById(R.id.bt_request);

        btnSolicitarCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el valor seleccionado del Spinner de horas
                String horaSeleccionada = tr_hora_pr.getSelectedItem().toString();

                // Obtener el valor seleccionado del Spinner de minutos
                String minutosSeleccionados = tr_minutos_pr.getSelectedItem().toString();

                insertTravelRequest("VP", horaSeleccionada, minutosSeleccionados);
            }
        });

        return root;
    }

    /*
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_viajes_pr, container, false);




        //Para solicitar viajes
        tr_origin_pr = root.findViewById(R.id.tr_origin_pr);
        tr_destination_pr = root.findViewById(R.id.tr_destination_pr);
        tr_dia_pr = root.findViewById(R.id.tr_dia_pr);
        tr_mes_pr = root.findViewById(R.id.tr_mes_pr);
        tr_año_pr = root.findViewById(R.id.tr_año_pr);
        //tr_hora_pr = root.findViewById(R.id.tr_hora_pr);
        //tr_minutos_pr = root.findViewById(R.id.tr_minutos_pr);
        tr_hora_pr = root.findViewById(R.id.tr_hora_pr);
        tr_minutos_pr = root.findViewById(R.id.tr_minutos_pr);
        btnSolicitarCR = root.findViewById(R.id.bt_request);

        // Obtener el valor seleccionado del Spinner de horas
        String horaSeleccionada = tr_hora_pr.getSelectedItem().toString();

        // Obtener el valor seleccionado del Spinner de minutos
        String minutosSeleccionados = tr_minutos_pr.getSelectedItem().toString();

        btnSolicitarCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTravelRequest("VP");
            }
        });

        /*
        // Para listar viajes
        listViewViajes = root.findViewById(R.id.listViewViajes);
        //adapterCR = new Adapter_CR(getActivity(), viajesFragmentList);
        listViewViajes.setAdapter(adapterCR);

        if (viajesFragmentList.isEmpty()) {
            ListarDatos();
        }*/

        //return root;
    //}
        /*
    public void insertTravelRequest(String TypeTravel, String hora, String minutos) {
        String typetravel = TypeTravel;
        String origin = tr_origin_pr.getText().toString().trim();
        String destination = tr_destination_pr.getText().toString().trim();
        String dia = tr_dia_pr.getText().toString().trim();
        String mes = tr_mes_pr.getText().toString().trim();
        String año = tr_año_pr.getText().toString().trim();

        if (origin.isEmpty() || destination.isEmpty()) {
            Toast.makeText(getActivity(), "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        String url = "https://appgeotaxi.000webhostapp.com/insertar.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Manejar la respuesta del servidor
                        // Puedes imprimir la respuesta directamente o mostrarla de alguna otra manera
                        System.out.println("Response from server: " + response);

                        // Agregar mensajes de registro después de la inserción
                        System.out.println("Insertado en la base de datos con éxito");
                        System.out.println("Parámetros utilizados:");
                        System.out.println("TravelRequest_origin = " + origin);
                        System.out.println("TravelRequest_destination = " + destination);
                        System.out.println("TravelRequest_datetime = " + año + "-" + mes + "-" + dia + " " + hora + ":" + minutos + ":00");
                        System.out.println("TypeTravel_FK = " + typetravel);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar errores de la solicitud
                        // Puedes imprimir el mensaje de error o manejarlo de alguna otra manera
                        System.err.println("Error en la solicitud: " + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                // Agregar todos los parámetros necesarios para la solicitud
                params.put("TravelRequest_origin", origin);
                params.put("TravelRequest_destination", destination);
                String dateTime = año + "-" + mes + "-" + dia + " " + hora + ":" + minutos + ":00";
                params.put("TravelRequest_datetime", dateTime);
                params.put("TypeTravel_FK", typetravel);

                return params;
            }
        };

        // Agregar la solicitud a la cola
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }



/*
    //MÉTODO PARA SOLICITAR UN VIAJE
    public void insertTravelRequest(String TypeTravel) {
        String typetravel = TypeTravel;
        String origin = tr_origin_pr.getText().toString().trim();
        String destination = tr_destination_pr.getText().toString().trim();
        String dia = tr_dia_pr.getText().toString().trim();
        String mes = tr_mes_pr.getText().toString().trim();
        String año = tr_año_pr.getText().toString().trim();
        //String hora = tr_hora_pr.getText().toString().trim();
        //String minutos = tr_minutos_pr.getText().toString().trim();
        String hora = tr_hora_pr.getSelectedItem().toString();
        String minutos = tr_minutos_pr.getSelectedItem().toString();

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
                            tr_origin_pr.setText("");
                            tr_destination_pr.setText("");
                            tr_dia_pr.setText("");
                            tr_mes_pr.setText("");
                            tr_año_pr.setText("");
                            //tr_hora_pr.setText("");
                            //tr_minutos_pr.setText("");

                            // Actualiza la lista después de la inserción
                            //ListarDatos();
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
                String dateTime = año + "-" + mes + "-" + dia + " " + hora + ":" + minutos + ":00";
                params.put("TravelRequest_datetime", dateTime);
                params.put("TypeTravel_FK", typetravel);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }
*/


    /*
    //PARA LISTAR LOS VIAJES
    String User_FK, TR_origin, TR_destination, TR_price;

    public ViajesFragmentPR(){
    }

    public ViajesFragmentPR(String User_FK, String TR_origin, String TR_destination, String TR_price){
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

                            viajesFragment = new ViajesFragmentPR(user, origin, destination, price);
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
    */
}

