package com.newtoxton.wifidistancecatcher.wifireceiver;

import static java.security.AccessController.getContext;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.newtoxton.wifidistancecatcher.adapter.MyAdapter;
import com.newtoxton.wifidistancecatcher.model.WifiName;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;

public class WifiReceiver extends BroadcastReceiver {
    WifiManager wifiManager;
    StringBuilder sb;
    ArrayList<WifiName> dataModels;
    private static MyAdapter adapter;
    ListView wifiDeviceList;

    public WifiReceiver(WifiManager wifiManager, ListView wifiDeviceList) {
        this.wifiManager = wifiManager;
        this.wifiDeviceList = wifiDeviceList;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
            sb = new StringBuilder();

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(new Activity(),
                        new String[]{Manifest.permission.ACCESS_WIFI_STATE,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION},
                        PackageManager.PERMISSION_GRANTED);
                return;
            }
            List<ScanResult> wifiList = wifiManager.getScanResults();
            for (ScanResult scanResult : wifiList){
                sb.append("\n").append(scanResult.SSID).append(" - ").append(scanResult.capabilities);

                dataModels.add(new WifiName(scanResult.SSID + " - " + scanResult.capabilities));
                adapter= new MyAdapter(dataModels,context);
                wifiDeviceList.setAdapter(adapter);
            }




    }
}}