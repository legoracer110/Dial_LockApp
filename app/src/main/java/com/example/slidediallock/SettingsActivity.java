package com.example.slidediallock;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingsActivity extends Activity {

    private boolean powerOn;
    private boolean boot_first;

    private CheckBox useLock;
    private CheckBox dontUse;

    private CheckBox spw1;
    private CheckBox spw2;

    private Button btn_input;
    private Button btn_check;

    private Button confirm;

    private Integer pwSize;
    private Integer pwList;

    private ArrayList<Integer> inputPw;
    private ArrayList<Integer> checkPw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Activity", "Setting Run!");
        setContentView(R.layout.activity_settings);

        SharedPreferences sf = getSharedPreferences("sFile",MODE_PRIVATE);
        powerOn = sf.getBoolean("isLock", true);

        boot_first = false;
        boot_first = sf.getBoolean("firstBoot2", false);

        useLock = (CheckBox)findViewById(R.id.check_use);
        useLock.setChecked(powerOn);
        useLock.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : process the click event.
                Log.d("power","power1 : " + powerOn);
                if(useLock.isChecked()) {
                    useLock.setChecked(true);
                    dontUse.setChecked(false);
                    powerOn = true;
                }else{
                    dontUse.setChecked(true);
                    powerOn = false;
                }
            }
        }) ;
        dontUse = (CheckBox)findViewById(R.id.check_dont);
        dontUse.setChecked(!powerOn);
        dontUse.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : process the click event.
                Log.d("power","power2 : " + powerOn);
                if(dontUse.isChecked()) {
                    useLock.setChecked(false);
                    dontUse.setChecked(true);
                    powerOn = false;
                }else{
                    useLock.setChecked(true);
                    powerOn = true;
                }
            }
        }) ;

        spw1 = (CheckBox)findViewById(R.id.pwSize2);
        spw2 = (CheckBox)findViewById(R.id.pwSize4);

        spw1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : process the click event.
                Log.d("Activity", "CheckBox1 Clicked!");
                pwSize = 2;
                spw1.setChecked(true);
                spw2.setChecked(false);
            }
        }) ;
        spw2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : process the click event.
                pwSize = 4;
                spw2.setChecked(true);
                spw1.setChecked(false);
            }
        }) ;

        //inputPw = sf.getInteger("firstBoot2", false);

        confirm = (Button)findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //ConfirmSettings();
            }
        });
    }



    @Override
    protected void onStop(){

        super.onStop();
    }
}
