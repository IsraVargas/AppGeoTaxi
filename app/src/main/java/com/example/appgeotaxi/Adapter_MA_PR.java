package com.example.appgeotaxi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appgeotaxi.ui.dashboard.MandadosFragmentPR;
import com.example.appgeotaxi.ui.dashboard.ViajesFragmentVP;

import java.util.List;

public class Adapter_MA_PR extends ArrayAdapter<MandadosFragmentPR> {

    Context context;
    List<MandadosFragmentPR> MandadosFragmentList;

    public Adapter_MA_PR(@NonNull Context context, List<MandadosFragmentPR>MandadosFragmentList) {

        super(context, R.layout.list_travelrequest_cr_pr,MandadosFragmentList);
        this.context=context;
        this.MandadosFragmentList=MandadosFragmentList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travelrequest_ma_pr,null, true);

        //TextView infUser = view.findViewById(R.id.inf_user);
        //TextView infStatus = view.findViewById(R.id.inf_status);
        TextView infOrigin = view.findViewById(R.id.inf_origin);
        TextView infDestination = view.findViewById(R.id.inf_destination);
        TextView infPrice = view.findViewById(R.id.inf_price);
        TextView infFecha = view.findViewById(R.id.inf_fecha);


        //infUser.setText(ViajesFragmentList.get(position).getUser_FK());
        infOrigin.setText(MandadosFragmentList.get(position).getTR_origin());
        infDestination.setText(MandadosFragmentList.get(position).getTR_destination());
        infPrice.setText(MandadosFragmentList.get(position).getTR_price());
        infFecha.setText(MandadosFragmentList.get(position).getTR_datetime());

        return view;

    }

    public Adapter_MA_PR(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public Adapter_MA_PR(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public Adapter_MA_PR(@NonNull Context context, int resource, @NonNull MandadosFragmentPR[] objects) {
        super(context, resource, objects);
    }

    public Adapter_MA_PR(@NonNull Context context, int resource, int textViewResourceId, @NonNull MandadosFragmentPR[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public Adapter_MA_PR(@NonNull Context context, int resource, @NonNull List<MandadosFragmentPR> objects) {
        super(context, resource, objects);
    }

    public Adapter_MA_PR(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<MandadosFragmentPR> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
