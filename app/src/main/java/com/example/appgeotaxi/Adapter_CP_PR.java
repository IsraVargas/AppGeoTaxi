package com.example.appgeotaxi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.appgeotaxi.ui.dashboard.ConductorFragmentPR;

import java.util.List;

public class Adapter_CP_PR extends ArrayAdapter<ConductorFragmentPR> {

    Context context;
    List<ConductorFragmentPR> ConductorFragmentList;

    public Adapter_CP_PR(@NonNull Context context, List<ConductorFragmentPR>ConductorFragmentList) {

        super(context, R.layout.list_travelrequest_cr_pr,ConductorFragmentList);
        this.context=context;
        this.ConductorFragmentList =ConductorFragmentList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travelrequest_cp_pr,null, true);

        //TextView infUser = view.findViewById(R.id.inf_user);
        //TextView infStatus = view.findViewById(R.id.inf_status);
        TextView infOrigin = view.findViewById(R.id.inf_origin);
        TextView infDestination = view.findViewById(R.id.inf_destination);
        TextView infPrice = view.findViewById(R.id.inf_price);
        TextView infFecha = view.findViewById(R.id.inf_fecha);
        TextView infdescription = view.findViewById(R.id.inf_description);


        //infUser.setText(ViajesFragmentList.get(position).getUser_FK());
        infOrigin.setText(ConductorFragmentList.get(position).getTR_origin());
        infDestination.setText(ConductorFragmentList.get(position).getTR_destination());
        infPrice.setText(ConductorFragmentList.get(position).getTR_price());
        infFecha.setText(ConductorFragmentList.get(position).getTR_datetime());
        infdescription.setText(ConductorFragmentList.get(position).getTR_description());

        return view;

    }

    public Adapter_CP_PR(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public Adapter_CP_PR(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public Adapter_CP_PR(@NonNull Context context, int resource, @NonNull ConductorFragmentPR[] objects) {
        super(context, resource, objects);
    }

    public Adapter_CP_PR(@NonNull Context context, int resource, int textViewResourceId, @NonNull ConductorFragmentPR[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public Adapter_CP_PR(@NonNull Context context, int resource, @NonNull List<ConductorFragmentPR> objects) {
        super(context, resource, objects);
    }

    public Adapter_CP_PR(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<ConductorFragmentPR> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
