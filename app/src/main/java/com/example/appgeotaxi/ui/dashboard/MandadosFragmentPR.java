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
import com.example.appgeotaxi.Adapter_CR_PR;
import com.example.appgeotaxi.Adapter_MA;
import com.example.appgeotaxi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MandadosFragmentPR extends Fragment {


    //PARA SOLICITAR VIAJES PROG
    private EditText descriptionEditText, originEditText, destinationEditText, dayEditText, monthEditText, yearEditText;
    private Spinner hourSpinner, minuteSpinner;
    private Button requestButton;


    //Para listar solicitudes
    ListView listViewViajes;
    Adapter_CR_PR adapterCrPr;
    public static ArrayList<MandadosFragmentPR> viajesFragmentList = new ArrayList<>();
    String url="https://appgeotaxi.000webhostapp.com/mostrar.php?TypeTravel=MP";
    ViajesFragmentVP viajesFragmentVP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mandados_pr, container, false);

        descriptionEditText = view.findViewById(R.id.tr_description_pr);
        originEditText = view.findViewById(R.id.tr_origin_pr);
        destinationEditText = view.findViewById(R.id.tr_destination_pr);
        dayEditText = view.findViewById(R.id.tr_dia_pr);
        monthEditText = view.findViewById(R.id.tr_mes_pr);
        yearEditText = view.findViewById(R.id.tr_año_pr);
        hourSpinner = view.findViewById(R.id.tr_hora_pr);
        minuteSpinner = view.findViewById(R.id.tr_minutos_pr);
        requestButton = view.findViewById(R.id.bt_request);

        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTravelRequest("MP");
            }
        });

        /*
        // Para listar viajes
        listViewViajes = view.findViewById(R.id.listViewViajes);
        adapterCrPr = new Adapter_CR_PR(getActivity(), viajesFragmentList);
        listViewViajes.setAdapter(adapterCrPr);

        if (viajesFragmentList.isEmpty()) {
            //ListarDatos();
        }
        */
        return view;
    }

    //MÉTODO PARA SOLICITAR
    private void insertTravelRequest(String TypeTravel) {
        String typetravel = TypeTravel;
        String description = descriptionEditText.getText().toString();
        String origin = originEditText.getText().toString();
        String destination = destinationEditText.getText().toString();
        String day = dayEditText.getText().toString();
        String month = monthEditText.getText().toString();
        String year = yearEditText.getText().toString();
        String hour = hourSpinner.getSelectedItem().toString();
        String minute = minuteSpinner.getSelectedItem().toString();

        // Realiza validaciones si es necesario

        // Muestra un ProgressDialog durante la inserción
        ProgressDialog progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Realiza una solicitud a tu script PHP usando Volley o tu biblioteca preferida
        String url = "https://appgeotaxi.000webhostapp.com/insertar.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Maneja la respuesta del servidor
                Log.d("Response", response);

                // Oculta el ProgressDialog después de la inserción
                progressDialog.dismiss();

                // Limpia los campos después de la inserción
                clearFields();

                //ListarDatos();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Maneja errores
                Log.e("Error", "Solicitud fallida");

                // Oculta el ProgressDialog en caso de error
                progressDialog.dismiss();

                // Muestra un mensaje de error
                Toast.makeText(requireContext(), "Error al realizar la solicitud", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("TravelRequest_origin", origin);
                params.put("TravelRequest_destination", destination);
                params.put("TypeTravel_FK", typetravel);
                params.put("day", day);
                params.put("month", month);
                params.put("year", year);
                params.put("hour", hour);
                params.put("minute", minute);
                params.put("TravelRequest_description", description);
                // Agrega otros parámetros si es necesario

                return params;
            }
        };

        // Agrega la solicitud a la RequestQueue
        Volley.newRequestQueue(requireContext()).add(request);
    }

    private void clearFields() {
        // Limpia los campos y establece valores predeterminados
        descriptionEditText.setText("");
        originEditText.setText("");
        destinationEditText.setText("");
        dayEditText.setText("");
        monthEditText.setText("");
        yearEditText.setText("");

        // Establece valores predeterminados para los spinners
        hourSpinner.setSelection(0); // Coloca el primer elemento como seleccionado
        minuteSpinner.setSelection(0);
    }
}

