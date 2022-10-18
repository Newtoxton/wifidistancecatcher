package com.newtoxton.wifidistancecatcher.adapter;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wifidistancecatcher.R;
import com.newtoxton.wifidistancecatcher.model.WifiName;

import java.util.ArrayList;


public class MyAdapter extends ArrayAdapter<WifiName> {

private ArrayList<WifiName> dataSet;
        Context mContext;

// View lookup cache
private static class ViewHolder {
    TextView name_of_wifi;

}

    public MyAdapter(ArrayList<WifiName> data, Context context) {
        super(context, R.layout.custom_view, data);
        this.dataSet = data;
        this.mContext=context;

    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        WifiName dataModel = getItem(position);


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_view, parent, false);
            viewHolder.name_of_wifi =  convertView.findViewById(R.id.name_wifi);




            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        viewHolder.name_of_wifi.setText(dataModel.getWif_name());





        return convertView;
    }}


