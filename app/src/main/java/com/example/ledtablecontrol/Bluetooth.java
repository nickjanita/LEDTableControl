package com.example.ledtablecontrol;


import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class Bluetooth extends Application {

    private static Bluetooth sInstance;
     BluetoothAdapter BTAdapter = null;


    public static Bluetooth getApplication() {
        return sInstance;
    }

    BluetoothSocket btSocket = null;

    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }

    public void setupBluetoothConnection(String address, UUID myUUID) {
        try {
            if (btSocket == null) {
                BTAdapter = BluetoothAdapter.getDefaultAdapter();
                BluetoothDevice device = BTAdapter.getRemoteDevice(address);
                btSocket = device.createInsecureRfcommSocketToServiceRecord(myUUID);
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                btSocket.connect();
            }
        }
        catch (IOException e) {
            showToast("Connection lost!");
        }
    }

    public BluetoothSocket getCurrentBluetoothConnection() {

        return btSocket;
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
