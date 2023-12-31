package com.example.appgeotaxi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appgeotaxi.ui.home.ViajesFragment;

import java.util.List;

public class Adapter_CR extends ArrayAdapter<ViajesFragment> {

    Context context;
    List<ViajesFragment> ViajesFragmentList;

    public Adapter_CR(@NonNull Context context, List<ViajesFragment>ViajesFragmentList) {

        super(context, R.layout.list_travelrequest_cr,ViajesFragmentList);
        this.context=context;
        this.ViajesFragmentList=ViajesFragmentList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travelrequest_cr,null, true);

        //TextView infUser = view.findViewById(R.id.inf_user);
        TextView infStatus = view.findViewById(R.id.inf_status);
        TextView infOrigin = view.findViewById(R.id.inf_origin);
        TextView infDestination = view.findViewById(R.id.inf_destination);
        TextView infPrice = view.findViewById(R.id.inf_price);

        //infUser.setText(ViajesFragmentList.get(position).getUser_FK());
        infOrigin.setText(ViajesFragmentList.get(position).getTR_origin());
        infDestination.setText(ViajesFragmentList.get(position).getTR_destination());
        infPrice.setText(ViajesFragmentList.get(position).getTR_price());

        return view;

    }

    public Adapter_CR(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public Adapter_CR(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public Adapter_CR(@NonNull Context context, int resource, @NonNull ViajesFragment[] objects) {
        super(context, resource, objects);
    }

    public Adapter_CR(@NonNull Context context, int resource, int textViewResourceId, @NonNull ViajesFragment[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public Adapter_CR(@NonNull Context context, int resource, @NonNull List<ViajesFragment> objects) {
        super(context, resource, objects);
    }

    public Adapter_CR(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<ViajesFragment> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
