package com.example.appgeotaxi.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.appgeotaxi.ui.home.MandadosFragment;
import com.example.appgeotaxi.ui.home.ViajesFragment;

public class DashboardPagerAdapter extends FragmentPagerAdapter{

    public DashboardPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ViajesFragmentVP(); // Reemplaza ViajesFragment con tu fragmento real
            case 1:
                return new MandadosFragmentPR(); // Reemplaza MandadosFragment con tu fragmento real
            case 2:
                return new ConductorFragmentPR(); // Reemplaza MandadosFragment con tu fragmento real
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3; // Número de pestañas
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Retorna el título correspondiente a la posición
        switch (position) {
            case 0:
                return "Viajes";
            case 1:
                return "Mandados";
            case 2:
                return "Conductor";
            default:
                return null;
        }
    }
}
