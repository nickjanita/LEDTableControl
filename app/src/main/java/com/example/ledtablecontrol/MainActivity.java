package com.example.ledtablecontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {

    Button changePattern, pair, colorPick, pMaker;

    BluetoothAdapter BTAdapter = null;
    BluetoothSocket BTSocket = null;

    String address = null;
    boolean isBtConnected = false;

    int pickedColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changePattern = findViewById(R.id.patternBtn);
        pair          = findViewById(R.id.pairBtn);
        colorPick     = findViewById(R.id.colorPicker);
        pMaker        = findViewById(R.id.Pmaker);

        pickedColor  = ContextCompat.getColor(MainActivity.this,R.color.colorPrimary);

        //BT socket
        BTSocket = Bluetooth.getApplication().getCurrentBluetoothConnection();

        //Buttons
        changePattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Changing Pattern...");
                changePat();

            }
        });
        pair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Opening Pair Menu");
                Intent change = new Intent(MainActivity.this, pairActivity.class);
                startActivity(change);
            }
        });

        colorPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(BTSocket!=null){
                    try{
                        BTSocket.getOutputStream().write("2".getBytes());
                    } catch (IOException e) {
                        showToast("Error sending signal");
                    }
                }
                openColorPicker();
            }
        });

        pMaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(BTSocket!=null){
                    try{
                        BTSocket.getOutputStream().write("3".getBytes());
                    } catch (IOException e) {
                        showToast("Error sending signal");
                    }
                }
                Intent maker = new Intent(MainActivity.this, makerActivity.class);
                startActivity(maker);
            }
        });
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //Pattern Change function
    private void changePat(){
        if(BTSocket!=null){
            try{
                BTSocket.getOutputStream().write("1".getBytes());
            } catch (IOException e) {
                showToast("Error sending signal");
            }
        }
    }

    //Color picker
    public void openColorPicker(){
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, pickedColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                pickedColor = color;
                try{
                    BTSocket.getOutputStream().write(Integer.toString(pickedColor).getBytes());
                } catch (IOException e) {
                    showToast("Error sending signal");
                }


            }
        });
        colorPicker.show();
    }
//    private class ConnectBT extends AsyncTask<Void, Void, Void>  {
//        private boolean ConnectSuccess = true;
//
//        @Override
//        protected void onPreExecute() {
//            showToast("Connecting...");
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... devices)
//        {
//            try {
//                if (BTSocket == null||!isBtConnected) {
//                    BTAdapter = BluetoothAdapter.getDefaultAdapter();
//                    BluetoothDevice device = BTAdapter.getRemoteDevice(address);
//                    BTSocket = device.createInsecureRfcommSocketToServiceRecord(myUUID);
//                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
//                    BTSocket.connect();
//                }
//            }
//            catch (IOException e) {
//                ConnectSuccess = false;
//            }
//            return null;
//        }
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//
//            if (!ConnectSuccess){
//                showToast("Connection Failed.");
//
//            }
//            else {
//                showToast("Connected.");
//                isBtConnected = true;
//            }
//        }
//    }

//    public class ConnectBT extends Bluetooth{
//        @Override
//        public void setupBluetoothConnection() {
//            super.setupBluetoothConnection();
//
//
//        }
//    }
//    public class setSocket extends Bluetooth{
//        @Override
//        public BluetoothSocket getCurrentBluetoothConnection() {
//            return super.getCurrentBluetoothConnection();
//
//        }
//    }

}
