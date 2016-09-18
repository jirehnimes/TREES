
package com.royce.sample3;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by johnroycepunay on 9/18/2016.
 */

public class Settings extends Activity implements OnClickListener{

    private BluetoothAdapter mBluetoothAdapter;
    private Button changeStatus;
    private TextView status;
    private static String usernames;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.settings_layout);


        final String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.TvUsername);
        tv.setText(username);
        tv.setVisibility(View.INVISIBLE);
        //reference to the button
        changeStatus = (Button) findViewById(R.id.changeStatus);
        changeStatus.setOnClickListener(this);
        usernames = tv.getText().toString();
        //reference to the text view
        status = (TextView) findViewById(R.id.status);

        //reference to the bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //check if adatpter is available, please note if you running
        //this application in emulator currently there is no support for bluetooth
        if(mBluetoothAdapter == null){
            status.setText("BlueTooth adapter not found");
            changeStatus.setText("BlueTooth Disabled");
            changeStatus.setEnabled(false);
        }
        //check the status and set the button text accordingly
        else {
            if (mBluetoothAdapter.isEnabled()) {
                status.setText("BlueTooth is currently switched ON");
                changeStatus.setText("Switch OFF Bluetooth");
            }else{
                status.setText("BlueTooth is currently switched OFF");
                changeStatus.setText("Switch ON Bluetooth");
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.breal_layout, menu);
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.changeStatus:
                //disable the bluetooth adapter
                if (mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.disable();
                    status.setText("BlueTooth is currently switched OFF");
                    changeStatus.setText("Switch ON Bluetooth");
                }
                //enable the bluetooth adapter
                else{
                    mBluetoothAdapter.enable();
                    status.setText("BlueTooth is currently switched ON");
                    changeStatus.setText("Switch OFF Bluetooth");
                }
                break;
            // More buttons go here (if any) ...
        }

    }
    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you will exit the settings?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        String username = String.valueOf(usernames);
                        Intent ourIntent2 = new Intent(Settings.this, Home.class);
                        ourIntent2.putExtra("Username",username);
                        startActivity(ourIntent2);
                        finish();
                    }
                }).create().show();
    }
}