package com.example.ledtablecontrol;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;

import yuku.ambilwarna.AmbilWarnaDialog;

public class makerActivity extends AppCompatActivity implements View.OnClickListener {

    //Led button array for the grid
    Button[] ledButtons = new Button[42];
    //Additional index is for the background color
    int[] ledColor = new int[43];
    int pickedColor;
    Button sendBtn, backColorBtn, colorBtn;
    BluetoothSocket BTSocket;


    //Buttons sent to switch case class below
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maker_activity);

        //This is probably the worst way to do this dont judge
        ledButtons[0] = findViewById(R.id.button44);
        ledButtons[1] = findViewById(R.id.button43);
        ledButtons[2] = findViewById(R.id.button42);
        ledButtons[3] = findViewById(R.id.button41);
        ledButtons[4] = findViewById(R.id.button40);
        ledButtons[5] = findViewById(R.id.button39);
        ledButtons[6] = findViewById(R.id.button38);
        ledButtons[7] = findViewById(R.id.button37);
        ledButtons[8] = findViewById(R.id.button36);
        ledButtons[9] = findViewById(R.id.button35);
        ledButtons[10] = findViewById(R.id.button34);
        ledButtons[11] = findViewById(R.id.button33);
        ledButtons[12] = findViewById(R.id.button32);
        ledButtons[13] = findViewById(R.id.button31);
        ledButtons[14] = findViewById(R.id.button30);
        ledButtons[15] = findViewById(R.id.button29);
        ledButtons[16] = findViewById(R.id.button28);
        ledButtons[17] = findViewById(R.id.button27);
        ledButtons[18] = findViewById(R.id.button26);
        ledButtons[19] = findViewById(R.id.button25);
        ledButtons[20] = findViewById(R.id.button24);
        ledButtons[21] = findViewById(R.id.button23);
        ledButtons[22] = findViewById(R.id.button22);
        ledButtons[23] = findViewById(R.id.button21);
        ledButtons[24] = findViewById(R.id.button20);
        ledButtons[25] = findViewById(R.id.button19);
        ledButtons[26] = findViewById(R.id.button18);
        ledButtons[27] = findViewById(R.id.button17);
        ledButtons[28] = findViewById(R.id.button16);
        ledButtons[29] = findViewById(R.id.button15);
        ledButtons[30] = findViewById(R.id.button14);
        ledButtons[31] = findViewById(R.id.button13);
        ledButtons[32] = findViewById(R.id.button12);
        ledButtons[33] = findViewById(R.id.button11);
        ledButtons[34] = findViewById(R.id.button10);
        ledButtons[35] = findViewById(R.id.button9);
        ledButtons[36] = findViewById(R.id.button8);
        ledButtons[37] = findViewById(R.id.button7);
        ledButtons[38] = findViewById(R.id.button6);
        ledButtons[39] = findViewById(R.id.button5);
        ledButtons[40] = findViewById(R.id.button4);
        ledButtons[41] = findViewById(R.id.button3);
        sendBtn = findViewById(R.id.send);
        backColorBtn = findViewById(R.id.backColor);
        colorBtn = findViewById(R.id.color);

        pickedColor  = ContextCompat.getColor(makerActivity.this,R.color.colorPrimary);
        for(int i = 0;i<43;++i){
            ledColor[i] = 0;
        }
        //Getting BTSocket from past activity
        BTSocket = Bluetooth.getApplication().getCurrentBluetoothConnection();

        //Click listeners
        ledButtons[0].setOnClickListener(this);
        ledButtons[1].setOnClickListener(this);
        ledButtons[2].setOnClickListener(this);
        ledButtons[3].setOnClickListener(this);
        ledButtons[4].setOnClickListener(this);
        ledButtons[5].setOnClickListener(this);
        ledButtons[6].setOnClickListener(this);
        ledButtons[7].setOnClickListener(this);
        ledButtons[8].setOnClickListener(this);
        ledButtons[9].setOnClickListener(this);
        ledButtons[10].setOnClickListener(this);
        ledButtons[11].setOnClickListener(this);
        ledButtons[12].setOnClickListener(this);
        ledButtons[13].setOnClickListener(this);
        ledButtons[14].setOnClickListener(this);
        ledButtons[15].setOnClickListener(this);
        ledButtons[16].setOnClickListener(this);
        ledButtons[17].setOnClickListener(this);
        ledButtons[18].setOnClickListener(this);
        ledButtons[19].setOnClickListener(this);
        ledButtons[20].setOnClickListener(this);
        ledButtons[21].setOnClickListener(this);
        ledButtons[22].setOnClickListener(this);
        ledButtons[23].setOnClickListener(this);
        ledButtons[24].setOnClickListener(this);
        ledButtons[25].setOnClickListener(this);
        ledButtons[26].setOnClickListener(this);
        ledButtons[27].setOnClickListener(this);
        ledButtons[28].setOnClickListener(this);
        ledButtons[29].setOnClickListener(this);
        ledButtons[30].setOnClickListener(this);
        ledButtons[31].setOnClickListener(this);
        ledButtons[32].setOnClickListener(this);
        ledButtons[33].setOnClickListener(this);
        ledButtons[34].setOnClickListener(this);
        ledButtons[35].setOnClickListener(this);
        ledButtons[36].setOnClickListener(this);
        ledButtons[37].setOnClickListener(this);
        ledButtons[38].setOnClickListener(this);
        ledButtons[39].setOnClickListener(this);
        ledButtons[40].setOnClickListener(this);
        ledButtons[41].setOnClickListener(this);
        sendBtn.setOnClickListener(this);
        backColorBtn.setOnClickListener(this);
        colorBtn.setOnClickListener(this);





    }
    public void openColorPicker(){
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, pickedColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                pickedColor = color;

            }
        });
        colorPicker.show();
    }
    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        switch (v.getId()) {
            case R.id.send:
                try {
                    for(int i = 0;i<43;i++){
                        //Making sure all integers have a negative
                        if(ledColor[i] >=0) {
                            String zero = "-" + ledColor[i];
                            BTSocket.getOutputStream().write(zero.getBytes());
                        }else{
                            BTSocket.getOutputStream().write(Integer.toString(ledColor[i]).getBytes());
                        }
                    }

                } catch (IOException e) {
                    showToast("Error sending Array");
                }


                break;

            case R.id.color:
                openColorPicker();
                break;
            case R.id.backColor:
                backColorBtn.setBackgroundColor(pickedColor);
                ledColor[42] = pickedColor;
                break;
            case R.id.button44:
                ledButtons[0].setBackgroundColor(pickedColor);
                ledColor[0] = pickedColor;
                break;

            case R.id.button43:
                ledButtons[1].setBackgroundColor(pickedColor);
                ledColor[1] = pickedColor;
                break;

            case R.id.button42:
                ledButtons[2].setBackgroundColor(pickedColor);
                ledColor[2] = pickedColor;
                break;

            case R.id.button41:
                ledButtons[3].setBackgroundColor(pickedColor);
                ledColor[3] = pickedColor;
                break;

            case(R.id.button40):
                ledButtons[4].setBackgroundColor(pickedColor);
                ledColor[4] = pickedColor;
                break;
            case(R.id.button39):
                ledButtons[5].setBackgroundColor(pickedColor);
                ledColor[5] = pickedColor;
                break;
            case(R.id.button38):
                ledButtons[6].setBackgroundColor(pickedColor);
                ledColor[6] = pickedColor;
                break;
            case(R.id.button37):
                ledButtons[7].setBackgroundColor(pickedColor);
                ledColor[7] = pickedColor;
                break;
            case(R.id.button36):
                ledButtons[8].setBackgroundColor(pickedColor);
                ledColor[8] = pickedColor;
                break;
            case(R.id.button35):
                ledButtons[9].setBackgroundColor(pickedColor);
                ledColor[9] = pickedColor;
                break;

            case(R.id.button34):
                ledButtons[10].setBackgroundColor(pickedColor);
                ledColor[10] = pickedColor;
                break;

            case(R.id.button33):
                ledButtons[11].setBackgroundColor(pickedColor);
                ledColor[11] = pickedColor;
                break;

            case(R.id.button32):
                ledButtons[12].setBackgroundColor(pickedColor);
                ledColor[12] = pickedColor;
                break;

            case(R.id.button31):
                ledButtons[13].setBackgroundColor(pickedColor);
                ledColor[13] = pickedColor;
                break;

            case(R.id.button30):
                ledButtons[14].setBackgroundColor(pickedColor);
                ledColor[14] = pickedColor;
                break;

            case(R.id.button29):
                ledButtons[15].setBackgroundColor(pickedColor);
                ledColor[15] = pickedColor;
                break;

            case(R.id.button28):
                ledButtons[16].setBackgroundColor(pickedColor);
                ledColor[16] = pickedColor;
                break;

            case(R.id.button27):
                ledButtons[17].setBackgroundColor(pickedColor);
                ledColor[17] = pickedColor;
                break;

            case(R.id.button26):
                ledButtons[18].setBackgroundColor(pickedColor);
                ledColor[18] = pickedColor;
                break;

            case(R.id.button25):
                ledButtons[19].setBackgroundColor(pickedColor);
                ledColor[19] = pickedColor;
                break;

            case(R.id.button24):
                ledButtons[20].setBackgroundColor(pickedColor);
                ledColor[20] = pickedColor;
                break;

            case(R.id.button23):
                ledButtons[21].setBackgroundColor(pickedColor);
                ledColor[21] = pickedColor;
                break;

            case(R.id.button22):
                ledButtons[22].setBackgroundColor(pickedColor);
                ledColor[22] = pickedColor;
                break;

            case(R.id.button21):
                ledButtons[23].setBackgroundColor(pickedColor);
                ledColor[23] = pickedColor;
                break;

            case(R.id.button20):
                ledButtons[24].setBackgroundColor(pickedColor);
                ledColor[24] = pickedColor;
                break;

            case(R.id.button19):
                ledButtons[25].setBackgroundColor(pickedColor);
                ledColor[25] = pickedColor;
                break;

            case(R.id.button18):
                ledButtons[26].setBackgroundColor(pickedColor);
                ledColor[26] = pickedColor;
                break;

            case(R.id.button17):
                ledButtons[27].setBackgroundColor(pickedColor);
                ledColor[27] = pickedColor;
                break;

            case(R.id.button16):
                ledButtons[28].setBackgroundColor(pickedColor);
                ledColor[28] = pickedColor;
                break;

            case(R.id.button15):
                ledButtons[29].setBackgroundColor(pickedColor);
                ledColor[29] = pickedColor;
                break;

            case(R.id.button14):
                ledButtons[30].setBackgroundColor(pickedColor);
                ledColor[30] = pickedColor;
                break;

            case(R.id.button13):
                ledButtons[31].setBackgroundColor(pickedColor);
                ledColor[31] = pickedColor;
                break;

            case(R.id.button12):
                ledButtons[32].setBackgroundColor(pickedColor);
                ledColor[32] = pickedColor;
                break;

            case(R.id.button11):
                ledButtons[33].setBackgroundColor(pickedColor);
                ledColor[33] = pickedColor;
                break;

            case(R.id.button10):
                ledButtons[34].setBackgroundColor(pickedColor);
                ledColor[34] = pickedColor;
                break;

            case(R.id.button9):
                ledButtons[35].setBackgroundColor(pickedColor);
                ledColor[35] = pickedColor;
                break;

            case(R.id.button8):
                ledButtons[36].setBackgroundColor(pickedColor);
                ledColor[36] = pickedColor;
                break;

            case(R.id.button7):
                ledButtons[37].setBackgroundColor(pickedColor);
                ledColor[37] = pickedColor;
                break;

            case(R.id.button6):
                ledButtons[38].setBackgroundColor(pickedColor);
                ledColor[38] = pickedColor;
                break;

            case(R.id.button5):
                ledButtons[39].setBackgroundColor(pickedColor);
                ledColor[39] = pickedColor;
                break;

            case(R.id.button4):
                ledButtons[40].setBackgroundColor(pickedColor);
                ledColor[40] = pickedColor;
                break;

            case(R.id.button3):
                ledButtons[41].setBackgroundColor(pickedColor);
                ledColor[41] = pickedColor;
                break;

            default:
                break;
        }
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
