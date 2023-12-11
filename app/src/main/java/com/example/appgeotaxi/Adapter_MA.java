package com.example.appgeotaxi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appgeotaxi.ui.home.MandadosFragment;
import com.example.appgeotaxi.ui.home.ViajesFragment;

import java.util.List;

public class Adapter_MA extends ArrayAdapter<MandadosFragment> {

    Context context;
    List<MandadosFragment> MandadosFragmentList;

    public Adapter_MA(@NonNull Context context, List<MandadosFragment>MandadosFragmentList) {

        super(context, R.layout.list_travelrequest_ma,MandadosFragmentList);
        this.context=context;
        this.MandadosFragmentList=MandadosFragmentList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travelrequest_ma,null, true);

        //TextView infUser = view.findViewById(R.id.inf_user);
        TextView infStatus = view.findViewById(R.id.inf_status);
        TextView infOrigin = view.findViewById(R.id.inf_origin);
        TextView infDestination = view.findViewById(R.id.inf_destination);
        TextView infDescription = view.findViewById(R.id.inf_description);
        TextView infPrice = view.findViewById(R.id.inf_price);

        //infUser.setText(ViajesFragmentList.get(position).getUser_FK());
        infOrigin.setText(MandadosFragmentList.get(position).getTR_origin());
        infDestination.setText(MandadosFragmentList.get(position).getTR_destination());
        infDescription.setText(MandadosFragmentList.get(position).getTR_description());
        infPrice.setText(MandadosFragmentList.get(position).getTR_price());

        return view;

    }

    public Adapter_MA(@NonNull Context context, int resource, Context context1) {
        super(context, resource);
        this.context = context1;
    }

    public Adapter_MA(@NonNull Context context, int resource, int textViewResourceId, Context context1) {
        super(context, resource, textViewResourceId);
        this.context = context1;
    }

    public Adapter_MA(@NonNull Context context, int resource, @NonNull MandadosFragment[] objects, Context context1) {
        super(context, resource, objects);
        this.context = context1;
    }

    public Adapter_MA(@NonNull Context context, int resource, int textViewResourceId, @NonNull MandadosFragment[] objects, Context context1) {
        super(context, resource, textViewResourceId, objects);
        this.context = context1;
    }

    public Adapter_MA(@NonNull Context context, int resource, @NonNull List<MandadosFragment> objects, Context context1) {
        super(context, resource, objects);
        this.context = context1;
    }

    public Adapter_MA(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<MandadosFragment> objects, Context context1) {
        super(context, resource, textViewResourceId, objects);
        this.context = context1;
    }

    /*public Adapter_MA(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public Adapter_MA(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public Adapter_MA(@NonNull Context context, int resource, @NonNull ViajesFragment[] objects) {
        super(context, resource, objects);
    }

    public Adapter_MA(@NonNull Context context, int resource, int textViewResourceId, @NonNull ViajesFragment[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public Adapter_MA(@NonNull Context context, int resource, @NonNull List<ViajesFragment> objects) {
        super(context, resource, objects);
    }

    public Adapter_MA(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<ViajesFragment> objects) {
        super(context, resource, textViewResourceId, objects);
    }*/
}
