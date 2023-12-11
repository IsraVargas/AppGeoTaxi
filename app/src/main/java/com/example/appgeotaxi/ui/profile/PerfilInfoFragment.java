package com.example.appgeotaxi.ui.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appgeotaxi.MainActivity;
import com.example.appgeotaxi.R;

import java.util.HashMap;
import java.util.Map;

public class PerfilInfoFragment extends Fragment {

    ImageButton bt_regresar;
    ImageButton bt_guardar;
    EditText editTextNombre, editTextApellidos, editTextPhone, editTextCorreo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_perfil_info, container, false);

        bt_regresar = root.findViewById(R.id.bt_regresar);
        bt_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para iniciar la actividad PerfilActivity
                Intent intent = new Intent(requireContext(), MainActivity.class);

                // Inicia la actividad
                startActivity(intent);
            }
        });

        bt_guardar = root.findViewById(R.id.bt_guardar);
        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar();
                // Crea un Intent para iniciar la actividad PerfilActivity
                Intent intent = new Intent(requireContext(), MainActivity.class);

                // Inicia la actividad
                startActivity(intent);
            }
        });

        editTextNombre = root.findViewById(R.id.editTextNombre);
        editTextApellidos = root.findViewById(R.id.editTextApellidos);
        editTextPhone = root.findViewById(R.id.editTextPhone);
        editTextCorreo = root.findViewById(R.id.editTextCorreo);

        editTextNombre.setText("MARIANA");
        editTextApellidos.setText("ALAMILLA");
        editTextPhone.setText("5551234567");
        editTextCorreo.setText("Mariana@example.com");

        return root;
    }

    public void actualizar(){
        final String nombre = editTextNombre.getText().toString().trim();
        final String apellidos = editTextApellidos.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();
        final String correo = editTextCorreo.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Actualizando");
        progressDialog.dismiss();

        StringRequest request = new StringRequest(Request.Method.POST, "https://appgeotaxi.000webhostapp.com/actualizarUsuario.php?User_PK=f7e75a83-562c-4c24-936d-0a49b8b2d4a9", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();


            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("User_firstname", nombre);
                params.put("User_lastname", apellidos);
                params.put("User_phone", phone);
                params.put("User_email", correo);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

        }

}

