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

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appgeotaxi.Adapter_MA_PR;
import com.example.appgeotaxi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MandadosFragmentPR extends Fragment {


    //PARA SOLICITAR VIAJES PROG
    private EditText descriptionEditText, originEditText, destinationEditText, dayEditText, monthEditText, yearEditText;
    private Spinner hourSpinner, minuteSpinner;
    private Button requestButton;


    //Para listar solicitudes
    ListView listViewViajes;
    Adapter_MA_PR adapterMaPr;
    public static ArrayList<MandadosFragmentPR> mandadosFragmentList = new ArrayList<>();
    String url="https://appgeotaxi.000webhostapp.com/mostrar.php?TypeTravel=MP";
    MandadosFragmentPR mandadosFragmentPR;

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

    //PARA LISTAR LOS VIAJES
    String User_FK, TR_origin, TR_destination, TR_price, TR_datetime;

    public MandadosFragmentPR(){
    }

    public MandadosFragmentPR(String User_FK, String TR_origin, String TR_destination, String TR_price, String TR_datetime){
        this.User_FK = User_FK;
        this.TR_origin = TR_origin;
        this.TR_destination = TR_destination;
        this.TR_price = TR_price;
        this.TR_datetime = TR_datetime;
    }

    public String getTR_origin() { return TR_origin; }

    public String getTR_destination() { return TR_destination; }

    public String getTR_price() { return TR_price; }

    public String getTR_datetime() { return TR_datetime; }


    //MÉTODO PARA LISTAR LOS DATOS
    private void ListarDatos() {
        Log.e("Entrada1", "Entro al método ListarDatos");
        mandadosFragmentList.clear(); // Limpiar la lista antes de agregar nuevos datos

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Respuesta del servidor", response);  // Registra la respuesta completa
                Log.e("Entrada2", "Entro al onResponse");
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
                            String datetimeString = object.getString("TravelRequest_datetime");

                            // Formatea el datetime para mostrar solo hasta los minutos
                            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
                            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy   HH:mm");
                            Date date = inputFormat.parse(datetimeString);
                            String formattedDatetime = outputFormat.format(date);

                            mandadosFragmentPR = new MandadosFragmentPR(user, origin, destination, price, formattedDatetime);
                            mandadosFragmentList.add(mandadosFragmentPR);
                            Log.e("Entrada3", "Entro y pasó por el for");
                        }
                        adapterMaPr.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
                    }
                } catch (JSONException | ParseException e) {
                    Log.e("Entrada4", "Entro al catch");
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

