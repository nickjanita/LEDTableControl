package com.example.ledtablecontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class pairActivity extends AppCompatActivity {

    Button findDevices;
    ListView pairedList;
    BluetoothDevice device;
    BluetoothAdapter BTAdapter;
    BluetoothSocket BTSocket;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    public static String EXTRA_ADDRESS = "device_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pair_activity);

        findDevices = findViewById(R.id.findDevices);
        pairedList = findViewById(R.id.pairedDevices);

        BTAdapter = BluetoothAdapter.getDefaultAdapter();

        findDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Finding previously paired devices...");
                showList();
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void showList() {
        Set<BluetoothDevice> pairedDevices = BTAdapter.getBondedDevices();
        ArrayList list = new ArrayList();

        for (BluetoothDevice device : pairedDevices) {
            list.add(device.getName() + "\n" + device.getAddress());
        }
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        pairedList.setAdapter(adapter);
        pairedList.setOnItemClickListener(deviceClick);
    }

    private AdapterView.OnItemClickListener deviceClick = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {

            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            // Make an intent to start next activity.
            Intent i = new Intent(pairActivity.this, MainActivity.class);

            //Setting up initial BT connection and testing if connected
            Bluetooth.getApplication().setupBluetoothConnection(address,myUUID);
            if(Bluetooth.getApplication().getCurrentBluetoothConnection() != null){
                startActivity(i);
            }
            else{
                showToast("Error connecting to BT device");
            }

        }

    };

}
