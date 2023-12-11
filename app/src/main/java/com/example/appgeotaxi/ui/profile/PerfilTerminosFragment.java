package com.example.appgeotaxi.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appgeotaxi.MainActivity;
import com.example.appgeotaxi.R;

public class PerfilTerminosFragment extends Fragment {
    ImageButton bt_regresar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_terminos_condiciones, container, false);

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

        return root;
    }
}
