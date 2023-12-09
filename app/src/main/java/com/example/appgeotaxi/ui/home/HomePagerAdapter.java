package com.example.appgeotaxi.ui.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomePagerAdapter extends FragmentPagerAdapter {

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ViajesFragment(); // Reemplaza ViajesFragment con tu fragmento real
            case 1:
                return new MandadosFragment(); // Reemplaza MandadosFragment con tu fragmento real
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2; // Número de pestañas
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Retorna el título correspondiente a la posición
        switch (position) {
            case 0:
                return "Viajes";
            case 1:
                return "Mandados";
            default:
                return null;
        }
    }
}
