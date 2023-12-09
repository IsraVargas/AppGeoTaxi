package com.example.appgeotaxi.ui.profile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.appgeotaxi.ui.notifications.HistorialFragment;

public class ProfilePagerAdapter extends FragmentPagerAdapter {
    public ProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PerfilFragment(); // Reemplaza ViajesFragment con tu fragmento real
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 1; // Número de pestañas
    }

    public CharSequence getPageTitle(int position) {
        // Retorna el título correspondiente a la posición
        switch (position) {
            case 0:
                return "Perfil";
            default:
                return null;
        }
    }
}
