package com.example.appgeotaxi.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appgeotaxi.R;
import com.example.appgeotaxi.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

public class ProfileFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Configurar ViewPager y TabLayout
        ViewPager viewPager = root.findViewById(R.id.viewPager);
        TabLayout tabLayout = root.findViewById(R.id.tabs);

        // Adaptador para el ViewPager
        ProfilePagerAdapter adapter = new ProfilePagerAdapter(getChildFragmentManager());
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
