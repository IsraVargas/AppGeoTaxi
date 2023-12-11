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
import com.example.appgeotaxi.ui.notifications.HistorialFragment;

import java.util.List;

public class Adapter_historial extends ArrayAdapter<HistorialFragment>  {
    Context context;
    List<HistorialFragment> HistorialFragmentList;
    public Adapter_historial(@NonNull Context context, List<HistorialFragment>HistorialFragmentList) {

        super(context, R.layout.list_travelrequest_historial,HistorialFragmentList);
        this.context=context;
        this.HistorialFragmentList=HistorialFragmentList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travelrequest_historial,null, true);

        //TextView infUser = view.findViewById(R.id.inf_user);
        //TextView infStatus = view.findViewById(R.id.inf_status);
        TextView infOrigin = view.findViewById(R.id.inf_origin);
        TextView infDestination = view.findViewById(R.id.inf_destination);
        TextView infPrice = view.findViewById(R.id.inf_price);
        TextView infFecha = view.findViewById(R.id.inf_fecha);
        TextView infdescription = view.findViewById(R.id.inf_description);


        //infUser.setText(ViajesFragmentList.get(position).getUser_FK());
        infOrigin.setText(HistorialFragmentList.get(position).getTR_origin());
        infDestination.setText(HistorialFragmentList.get(position).getTR_destination());
        infPrice.setText(HistorialFragmentList.get(position).getTR_price());
        infFecha.setText(HistorialFragmentList.get(position).getTR_datetime());
        infdescription.setText(HistorialFragmentList.get(position).getTR_description());

        return view;

    }


}
