package com.example.appgeotaxi.ui.profile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appgeotaxi.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PerfilFragment extends Fragment {

    TextView usurname, infoCuenta, contrasenaCuenta, terminos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        // Configura tu interfaz de usuario y lógica aquí
        usurname = root.findViewById(R.id.p_username);
        infoCuenta = root.findViewById(R.id.bt_info);
        contrasenaCuenta = root.findViewById(R.id.bt_contra);
        terminos = root.findViewById(R.id.bt_terminos);

        // Obtiene el User_PK de la URL
        String userPK = "f7e75a83-562c-4c24-936d-0a49b8b2d4a9";

        // Realiza la solicitud HTTP para obtener el nombre del usuario
        new ObtenerNombreUsuario().execute(userPK);

        // Configura el OnClickListener para "Información de la cuenta"
        infoCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reemplaza 'fragment_perfil_info' con el nombre de tu fragmento de información de cuenta
                Fragment fragmentInfo = new PerfilInfoFragment();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(com.google.android.material.R.id.container, fragmentInfo);
                transaction.addToBackStack(null);  // Agrega a la pila de retroceso
                transaction.commit();
            }
        });

        contrasenaCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reemplaza 'fragment_perfil_info' con el nombre de tu fragmento de información de cuenta
                Fragment fragmentContra = new PerfilContrasenaFragment();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(com.google.android.material.R.id.container, fragmentContra);
                transaction.addToBackStack(null);  // Agrega a la pila de retroceso
                transaction.commit();
            }
        });

        terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reemplaza 'fragment_perfil_info' con el nombre de tu fragmento de información de cuenta
                Fragment fragmentTerminos = new PerfilTerminosFragment();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(com.google.android.material.R.id.container, fragmentTerminos);
                transaction.addToBackStack(null);  // Agrega a la pila de retroceso
                transaction.commit();
            }
        });

        return root;
    }

    private class ObtenerNombreUsuario extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String userPK = params[0];
            String url = "https://appgeotaxi.000webhostapp.com/mostrarUsuario.php?user_pk=" + userPK;

            try {
                // Realiza la solicitud HTTP
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                reader.close();
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String nombreUsuario) {
            // Muestra el nombre del usuario en tu interfaz de usuario
            if (nombreUsuario != null) {
                usurname.setText(nombreUsuario);
            } else {
                usurname.setText("Error al obtener el nombre del usuario");
            }
        }
    }
}
