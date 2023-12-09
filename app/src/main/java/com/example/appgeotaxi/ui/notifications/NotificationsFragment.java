package com.example.appgeotaxi.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appgeotaxi.R;
import com.example.appgeotaxi.databinding.FragmentHomeBinding;
import com.example.appgeotaxi.ui.dashboard.DashboardPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class NotificationsFragment extends Fragment {


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Configurar ViewPager y TabLayout
        ViewPager viewPager = root.findViewById(R.id.viewPager);
        TabLayout tabLayout = root.findViewById(R.id.tabs);

        // Adaptador para el ViewPager
        NotificationsPagerAdapter adapter = new NotificationsPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        // Conectar TabLayout con ViewPager
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}