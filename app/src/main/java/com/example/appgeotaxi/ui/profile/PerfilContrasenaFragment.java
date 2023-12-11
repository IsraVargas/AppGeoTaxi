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

public class PerfilContrasenaFragment extends Fragment {

    ImageButton bt_regresar, bt_guardar;
    EditText editTextPass, editTextConfirmPass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_perfil_contra, container, false);

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

        editTextPass = root.findViewById(R.id.editTextPass);
        editTextConfirmPass = root.findViewById(R.id.editTextConfirmPass);

        editTextPass.setText("MarA1");
        editTextConfirmPass.setText("MarA1");

        return root;
    }

    public void actualizar(){
        final String contra = editTextPass.getText().toString().trim();
        final String confirmarcontra = editTextConfirmPass.getText().toString().trim();

        if (!contra.equals(confirmarcontra)) {
            Toast.makeText(getActivity(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Actualizando");
        progressDialog.dismiss();

        StringRequest request = new StringRequest(Request.Method.POST, "https://appgeotaxi.000webhostapp.com/actualizarContraUsuario.php?User_PK=f7e75a83-562c-4c24-936d-0a49b8b2d4a9", new Response.Listener<String>(){
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
                params.put("User_password", contra);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

    }
}
