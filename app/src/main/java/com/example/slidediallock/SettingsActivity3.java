package com.example.slidediallock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class SettingsActivity3 extends Activity {

    private Button confirm;
    private Button before;

    private String pw;
    private Integer pwSize;

    private Integer btnTheme;
    private Integer bgTheme;
    private Integer effTheme;

    private boolean powerOn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Activity", "Setting3");
        setContentView(R.layout.activity_settings3);

        SharedPreferences sf = getSharedPreferences("sFile",MODE_PRIVATE);

        powerOn = sf.getBoolean("isLock", true);

        pw = sf.getString("tmpPw", "bfha");
        pwSize = sf.getInt("tmpPwSize", 4);

        btnTheme = sf.getInt("tmpBtnTheme", 1);
        bgTheme = sf.getInt("tmpBgTheme", 1);
        effTheme = sf.getInt("tmpEffTheme", 1);

        confirm = (Button)findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ConfirmSettings();
            }
        });

        before = (Button)findViewById(R.id.before);
        before.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ConfirmSettings();
                Intent intent = new Intent(
                        getApplicationContext(),//현재제어권자
                        SettingsActivity2.class); // 이동할 컴포넌트
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                finish();
            }
        });
    }

    void ConfirmSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("pwList", pw);
        editor.putInt("pwSize", pwSize);
        editor.putInt("btnTheme", btnTheme);
        editor.putInt("bgTheme", bgTheme);
        editor.putInt("effTheme", effTheme);
        editor.commit();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                if(powerOn) {
                    Intent intent = new Intent(
                            getApplicationContext(),//현재제어권자
                            LockScreenService.class); // 이동할 컴포넌트
                    startService(intent);
                }
                finishAffinity();
            }
        }, 500);    //0.5초 뒤에
    }

    @Override
    protected void onStop(){

        super.onStop();
    }
}
