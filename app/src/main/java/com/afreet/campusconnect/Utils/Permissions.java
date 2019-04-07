package com.afreet.campusconnect.Utils;

import android.Manifest;

public class Permissions {

    public static final String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE
    };

    public static final String[] WRITE_STORAGE_PERMISSION = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static final String[] READ_STORAGE_PERMISSION = {Manifest.permission.READ_EXTERNAL_STORAGE};
    public static final String[] INTERNET = {Manifest.permission.INTERNET};
    public static final String[] ACCESS_COARSE_LOCATION = {Manifest.permission.ACCESS_COARSE_LOCATION};
    public static final String[] ACCESS_FINE_LOCATION = {Manifest.permission.ACCESS_FINE_LOCATION};
    public static final String[] ACCESS_WIFI_STATE = {Manifest.permission.ACCESS_WIFI_STATE};
    public static final String[] ACCESS_NETWORK_STATE = {Manifest.permission.ACCESS_NETWORK_STATE};

}
