package com.example.appgeotaxi.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appgeotaxi.Adapter_MA;
import com.example.appgeotaxi.Adapter_historial;
import com.example.appgeotaxi.R;
import com.example.appgeotaxi.ui.dashboard.MandadosFragmentPR;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistorialFragment extends Fragment {

    ListView listViewHistorial;
    Adapter_historial adapterHistorial;
    public static ArrayList<HistorialFragment> historialFragmentList = new ArrayList<>();
    String url="https://appgeotaxi.000webhostapp.com/mostrarTodos.php";
    HistorialFragment historialFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_historial, container, false);

        // Para listar viajes
        listViewHistorial = root.findViewById(R.id.listViewHistorial);
        adapterHistorial = new Adapter_historial(getActivity(), historialFragmentList);
        listViewHistorial.setAdapter(adapterHistorial);

        if (historialFragmentList.isEmpty()) {
            ListarDatos();
        }
        return root;
    }

    //PARA LISTAR LOS VIAJES
    String User_FK, TR_origin, TR_destination, TR_price, TR_datetime, TR_description;

    public HistorialFragment(){
    }

    public HistorialFragment(String User_FK, String TR_origin, String TR_destination, String TR_price, String TR_datetime, String TR_description){
        this.User_FK = User_FK;
        this.TR_origin = TR_origin;
        this.TR_destination = TR_destination;
        this.TR_price = TR_price;
        this.TR_datetime = TR_datetime;
        this.TR_description = TR_description;
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

    public String getTR_datetime() {
        return TR_datetime;
    }

    public void setTR_datetime(String TR_datetime) {
        this.TR_datetime = TR_datetime;
    }

    public String getTR_description() {
        return TR_description;
    }

    public void setTR_description(String TR_description) {
        this.TR_description = TR_description;
    }

    //MÉTODO PARA LISTAR LOS DATOS
    private void ListarDatos() {
        Log.e("Entrada1", "Entro al método ListarDatos");
        historialFragmentList.clear(); // Limpiar la lista antes de agregar nuevos datos

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
                            String description = object.getString("TravelRequest_description");

                            // Formatea el datetime para mostrar solo hasta los minutos
                            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
                            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy   HH:mm");
                            Date date = inputFormat.parse(datetimeString);
                            String formattedDatetime = outputFormat.format(date);

                            historialFragment = new HistorialFragment(user, origin, destination, price, formattedDatetime, description);
                            historialFragmentList.add(historialFragment);
                            Log.e("Entrada3", "Entro y pasó por el for");
                        }
                        adapterHistorial.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
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
