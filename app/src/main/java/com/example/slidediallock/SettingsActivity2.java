package com.example.slidediallock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import org.json.JSONObject;

import static android.R.color.holo_green_dark;

public class SettingsActivity2 extends Activity {

    private Button next;
    private Button before;

    private CheckBox preset1;
    private CheckBox preset2;
    private CheckBox preset3;
    private CheckBox preset4;

    private CheckBox btnTheme1;
    private CheckBox btnTheme2;
    private CheckBox btnTheme3;
    private CheckBox btnTheme4;
    private CheckBox btnTheme5;

    private CheckBox bgTheme1;
    private CheckBox bgTheme2;
    private CheckBox bgTheme3;
    private CheckBox bgTheme4;
    private CheckBox bgTheme5;
    private CheckBox bgTheme_custom;

    private CheckBox effTheme1;
    private CheckBox effTheme2;
    private CheckBox effTheme3;
    private CheckBox effTheme4;

    private ImageView bgImage;
    private ImageView btnImage1;
    private ImageView btnImage2;
    private ImageView btnImage3;
    private ImageView btnImage4;
    private ImageView btnImage5;

    private LottieAnimationView eff_cursor1;
    private LottieAnimationView eff_cursor2;
    private LottieAnimationView eff_cursor3;
    private LottieAnimationView eff_cursor4;

    private JSONObject eff1;
    private JSONObject eff2;
    private JSONObject eff3;
    private JSONObject eff4;

    // 테마 저장 변수 (인덱스)
    private Integer btnTheme;
    private Integer bgTheme;
    private Integer effTheme;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Activity", "Setting Run!");
        setContentView(R.layout.activity_settings2);

        SharedPreferences sf = getSharedPreferences("sFile",MODE_PRIVATE);

        btnTheme = sf.getInt("tmpBtnTheme", 1);
        bgTheme = sf.getInt("tmpBgTheme", 1);
        effTheme = sf.getInt("tmpEffTheme", 1);

        preset1 = (CheckBox)findViewById(R.id.preset1);
        preset2 = (CheckBox)findViewById(R.id.preset2);
        preset3 = (CheckBox)findViewById(R.id.preset3);
        preset4 = (CheckBox)findViewById(R.id.preset4);

        btnTheme1 = (CheckBox)findViewById(R.id.btn_theme1);
        btnTheme2 = (CheckBox)findViewById(R.id.btn_theme2);
        btnTheme3 = (CheckBox)findViewById(R.id.btn_theme3);
        btnTheme4 = (CheckBox)findViewById(R.id.btn_theme4);
        btnTheme5 = (CheckBox)findViewById(R.id.btn_theme5);

        bgTheme1 = (CheckBox)findViewById(R.id.bg_theme1);
        bgTheme2 = (CheckBox)findViewById(R.id.bg_theme2);
        bgTheme3 = (CheckBox)findViewById(R.id.bg_theme3);
        bgTheme4 = (CheckBox)findViewById(R.id.bg_theme4);
        bgTheme5 = (CheckBox)findViewById(R.id.bg_theme5);
        bgTheme_custom = (CheckBox)findViewById(R.id.bg_theme6);

        bgImage = (ImageView)findViewById(R.id.background);
        btnImage1 = (ImageView)findViewById(R.id.btn1);
        btnImage2 = (ImageView)findViewById(R.id.btn2);
        btnImage3 = (ImageView)findViewById(R.id.btn3);
        btnImage4 = (ImageView)findViewById(R.id.btn4);
        btnImage5 = (ImageView)findViewById(R.id.btn5);

        effTheme1 = (CheckBox)findViewById(R.id.eff_theme1);
        effTheme2 = (CheckBox)findViewById(R.id.eff_theme2);
        effTheme3 = (CheckBox)findViewById(R.id.eff_theme3);
        effTheme4 = (CheckBox)findViewById(R.id.eff_theme4);

        //eff1 = (JSONObject)"fireworks_ice_x2.json";

        eff_cursor1 = (LottieAnimationView)findViewById(R.id.imgEff1);
        eff_cursor2 = (LottieAnimationView)findViewById(R.id.imgEff2);
        eff_cursor3 = (LottieAnimationView)findViewById(R.id.imgEff3);
        eff_cursor4 = (LottieAnimationView)findViewById(R.id.imgEff4);

        switch(btnTheme){
            case 1:
                btnImage1.setBackgroundResource(R.drawable.moon_100);
                btnImage2.setBackgroundResource(R.drawable.moon_75);
                btnImage3.setBackgroundResource(R.drawable.moon_50);
                btnImage4.setBackgroundResource(R.drawable.moon_25);
                btnImage5.setBackgroundResource(R.drawable.moon_0);
                btnTheme1.setChecked(true);
                break;
            case 2:
                btnImage1.setBackgroundResource(R.drawable.star_neon);
                btnImage2.setBackgroundResource(R.drawable.star_neon);
                btnImage3.setBackgroundResource(R.drawable.star_neon);
                btnImage4.setBackgroundResource(R.drawable.star_neon);
                btnImage5.setBackgroundResource(R.drawable.star_neon);
                btnTheme2.setChecked(true);
                break;
            case 3:
                btnImage1.setBackgroundResource(R.drawable.snowflake);
                btnImage2.setBackgroundResource(R.drawable.snowflake);
                btnImage3.setBackgroundResource(R.drawable.snowflake);
                btnImage4.setBackgroundResource(R.drawable.snowflake);
                btnImage5.setBackgroundResource(R.drawable.snowflake);
                btnTheme3.setChecked(true);
                break;
            case 4:
                btnImage1.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage2.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage3.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage4.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage5.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnTheme4.setChecked(true);
                break;
            case 5:
                btnImage1.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage2.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage3.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage4.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage5.setBackgroundResource(R.drawable.clover_cute_comp);
                btnTheme5.setChecked(true);
                break;
        }

        switch(bgTheme){
            case 1:
                bgTheme1.setChecked(true);
                bgTheme2.setChecked(false);
                bgTheme3.setChecked(false);
                bgTheme4.setChecked(false);
                bgTheme5.setChecked(false);
                bgTheme_custom.setChecked(false);
                bgImage.setBackgroundResource(R.drawable.orora);
                break;
            case 2:
                bgTheme1.setChecked(false);
                bgTheme2.setChecked(true);
                bgTheme3.setChecked(false);
                bgTheme4.setChecked(false);
                bgTheme5.setChecked(false);
                bgTheme_custom.setChecked(false);

                bgImage.setBackgroundResource(R.drawable.neon);
                break;
            case 3:
                bgTheme1.setChecked(false);
                bgTheme2.setChecked(false);
                bgTheme3.setChecked(true);
                bgTheme4.setChecked(false);
                bgTheme5.setChecked(false);
                bgTheme_custom.setChecked(false);

                bgImage.setBackgroundResource(R.drawable.frozen);
                break;
            case 4:
                bgTheme1.setChecked(false);
                bgTheme2.setChecked(false);
                bgTheme3.setChecked(false);
                bgTheme4.setChecked(true);
                bgTheme5.setChecked(false);
                bgTheme_custom.setChecked(false);

                bgImage.setBackgroundResource(R.drawable.frozen4);
                break;
            case 5:
                bgTheme1.setChecked(false);
                bgTheme2.setChecked(false);
                bgTheme3.setChecked(false);
                bgTheme4.setChecked(false);
                bgTheme5.setChecked(true);
                bgTheme_custom.setChecked(false);

                //bgImage.setBackgroundResource(R.drawable.bg_green);
                bgImage.setBackgroundColor(getResources().getColor(holo_green_dark));
                break;
            case 6:
                break;
            case 7:
                bgTheme1.setChecked(false);
                bgTheme2.setChecked(false);
                bgTheme3.setChecked(false);
                bgTheme4.setChecked(false);
                bgTheme5.setChecked(false);
                bgTheme_custom.setChecked(false);

                bgImage.setBackgroundColor(getResources().getColor(android.R.color.black));
                break;
        }

        switch(effTheme){
            case 1:
                effTheme1.setChecked(true);
                effTheme2.setChecked(false);
                effTheme3.setChecked(false);
                effTheme4.setChecked(false);

                eff_cursor1.setVisibility(View.VISIBLE);
                eff_cursor2.setVisibility(View.INVISIBLE);
                eff_cursor3.setVisibility(View.INVISIBLE);
                eff_cursor4.setVisibility(View.INVISIBLE);
                /*
                eff_cursor.setAnimation("pw_check.json");
                eff_cursor.loop(true);
                eff_cursor.playAnimation();
                */
                break;
            case 2:
                effTheme1.setChecked(false);
                effTheme2.setChecked(true);
                effTheme3.setChecked(false);
                effTheme4.setChecked(false);

                eff_cursor1.setVisibility(View.INVISIBLE);
                eff_cursor2.setVisibility(View.VISIBLE);
                eff_cursor3.setVisibility(View.INVISIBLE);
                eff_cursor4.setVisibility(View.INVISIBLE);
                break;
            case 3:
                effTheme1.setChecked(false);
                effTheme2.setChecked(false);
                effTheme3.setChecked(true);
                effTheme4.setChecked(false);

                eff_cursor1.setVisibility(View.INVISIBLE);
                eff_cursor2.setVisibility(View.INVISIBLE);
                eff_cursor3.setVisibility(View.VISIBLE);
                eff_cursor4.setVisibility(View.INVISIBLE);
                break;
            case 4:
                effTheme1.setChecked(false);
                effTheme2.setChecked(false);
                effTheme3.setChecked(false);
                effTheme4.setChecked(true);

                eff_cursor1.setVisibility(View.INVISIBLE);
                eff_cursor2.setVisibility(View.INVISIBLE);
                eff_cursor3.setVisibility(View.INVISIBLE);
                eff_cursor4.setVisibility(View.VISIBLE);

                break;
        }

        preset1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(true);
                preset2.setChecked(false);
                preset3.setChecked(false);
                preset4.setChecked(false);

                btnTheme = 1;
                bgTheme = 1;
                effTheme = 1;

                btnTheme1.setChecked(true);
                btnTheme2.setChecked(false);
                btnTheme3.setChecked(false);
                btnTheme4.setChecked(false);
                btnTheme5.setChecked(false);

                bgTheme1.setChecked(true);
                bgTheme2.setChecked(false);
                bgTheme3.setChecked(false);
                bgTheme4.setChecked(false);
                bgTheme5.setChecked(false);
                bgTheme_custom.setChecked(false);

                effTheme1.setChecked(true);
                effTheme2.setChecked(false);
                effTheme3.setChecked(false);
                effTheme4.setChecked(false);

                bgImage.setBackgroundResource(R.drawable.orora);
                btnImage1.setBackgroundResource(R.drawable.moon_100);
                btnImage2.setBackgroundResource(R.drawable.moon_75);
                btnImage3.setBackgroundResource(R.drawable.moon_50);
                btnImage4.setBackgroundResource(R.drawable.moon_25);
                btnImage5.setBackgroundResource(R.drawable.moon_0);

                eff_cursor1.setVisibility(View.VISIBLE);
                eff_cursor2.setVisibility(View.INVISIBLE);
                eff_cursor3.setVisibility(View.INVISIBLE);
            }
        });

        preset2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(false);
                preset2.setChecked(true);
                preset3.setChecked(false);
                preset4.setChecked(false);

                btnTheme = 2;
                bgTheme = 2;
                effTheme = 2;

                btnTheme1.setChecked(false);
                btnTheme2.setChecked(false);
                btnTheme3.setChecked(false);
                btnTheme4.setChecked(true);
                btnTheme5.setChecked(false);

                bgTheme1.setChecked(false);
                bgTheme2.setChecked(true);
                bgTheme3.setChecked(false);
                bgTheme4.setChecked(false);
                bgTheme5.setChecked(false);
                bgTheme_custom.setChecked(false);

                effTheme1.setChecked(false);
                effTheme2.setChecked(true);
                effTheme3.setChecked(false);
                effTheme4.setChecked(false);

                bgImage.setBackgroundResource(R.drawable.neon);

                btnImage1.setBackgroundResource(R.drawable.star_neon);
                btnImage2.setBackgroundResource(R.drawable.star_neon);
                btnImage3.setBackgroundResource(R.drawable.star_neon);
                btnImage4.setBackgroundResource(R.drawable.star_neon);
                btnImage5.setBackgroundResource(R.drawable.star_neon);

                eff_cursor1.setVisibility(View.INVISIBLE);
                eff_cursor2.setVisibility(View.VISIBLE);
                eff_cursor3.setVisibility(View.INVISIBLE);
            }
        });

        preset3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(false);
                preset2.setChecked(false);
                preset3.setChecked(true);
                preset4.setChecked(false);

                btnTheme = 3;
                bgTheme = 3;
                effTheme = 3;

                btnTheme1.setChecked(false);
                btnTheme2.setChecked(false);
                btnTheme3.setChecked(true);
                btnTheme4.setChecked(false);
                btnTheme5.setChecked(false);

                bgTheme1.setChecked(false);
                bgTheme2.setChecked(false);
                bgTheme3.setChecked(true);
                bgTheme4.setChecked(false);
                bgTheme5.setChecked(false);
                bgTheme_custom.setChecked(false);

                effTheme1.setChecked(false);
                effTheme2.setChecked(false);
                effTheme3.setChecked(true);
                effTheme4.setChecked(false);

                bgImage.setBackgroundResource(R.drawable.frozen);

                btnImage1.setBackgroundResource(R.drawable.snowflake);
                btnImage2.setBackgroundResource(R.drawable.snowflake);
                btnImage3.setBackgroundResource(R.drawable.snowflake);
                btnImage4.setBackgroundResource(R.drawable.snowflake);
                btnImage5.setBackgroundResource(R.drawable.snowflake);

                eff_cursor1.setVisibility(View.INVISIBLE);
                eff_cursor2.setVisibility(View.INVISIBLE);
                eff_cursor3.setVisibility(View.VISIBLE);
            }
        });

        preset4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(false);
                preset2.setChecked(false);
                preset3.setChecked(false);
                preset4.setChecked(true);

                btnTheme = 5;
                bgTheme = 5;
                effTheme = 3;

                btnTheme1.setChecked(false);
                btnTheme2.setChecked(false);
                btnTheme3.setChecked(false);
                btnTheme4.setChecked(false);
                btnTheme5.setChecked(true);

                bgTheme1.setChecked(false);
                bgTheme2.setChecked(false);
                bgTheme3.setChecked(false);
                bgTheme4.setChecked(false);
                bgTheme5.setChecked(true);
                bgTheme_custom.setChecked(false);

                effTheme1.setChecked(false);
                effTheme2.setChecked(false);
                effTheme3.setChecked(true);
                effTheme4.setChecked(false);

                //bgImage.setBackgroundResource(R.drawable.bg_green);
                bgImage.setBackgroundColor(getResources().getColor(holo_green_dark));

                btnImage1.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage2.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage3.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage4.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage5.setBackgroundResource(R.drawable.clover_cute_comp);

                eff_cursor1.setVisibility(View.INVISIBLE);
                eff_cursor2.setVisibility(View.INVISIBLE);
                eff_cursor3.setVisibility(View.VISIBLE);
            }
        });

        btnTheme1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(false);
                preset2.setChecked(false);
                preset3.setChecked(false);
                preset4.setChecked(false);

                btnTheme = 1;

                btnTheme1.setChecked(true);
                btnTheme2.setChecked(false);
                btnTheme3.setChecked(false);
                btnTheme4.setChecked(false);
                btnTheme5.setChecked(false);

                btnImage1.setBackgroundResource(R.drawable.moon_100);
                btnImage2.setBackgroundResource(R.drawable.moon_75);
                btnImage3.setBackgroundResource(R.drawable.moon_50);
                btnImage4.setBackgroundResource(R.drawable.moon_25);
                btnImage5.setBackgroundResource(R.drawable.moon_0);
            }
        });

        btnTheme2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(false);
                preset2.setChecked(false);
                preset3.setChecked(false);
                preset4.setChecked(false);

                btnTheme = 2;

                btnTheme1.setChecked(false);
                btnTheme2.setChecked(true);
                btnTheme3.setChecked(false);
                btnTheme4.setChecked(false);
                btnTheme5.setChecked(false);

                btnImage1.setBackgroundResource(R.drawable.star_neon);
                btnImage2.setBackgroundResource(R.drawable.star_neon);
                btnImage3.setBackgroundResource(R.drawable.star_neon);
                btnImage4.setBackgroundResource(R.drawable.star_neon);
                btnImage5.setBackgroundResource(R.drawable.star_neon);

            }
        });

        btnTheme3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(false);
                preset2.setChecked(false);
                preset3.setChecked(false);
                preset4.setChecked(false);

                btnTheme = 3;

                btnTheme1.setChecked(false);
                btnTheme2.setChecked(false);
                btnTheme3.setChecked(true);
                btnTheme4.setChecked(false);
                btnTheme5.setChecked(false);

                btnImage1.setBackgroundResource(R.drawable.snowflake);
                btnImage2.setBackgroundResource(R.drawable.snowflake);
                btnImage3.setBackgroundResource(R.drawable.snowflake);
                btnImage4.setBackgroundResource(R.drawable.snowflake);
                btnImage5.setBackgroundResource(R.drawable.snowflake);
            }
        });

        btnTheme4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(false);
                preset2.setChecked(false);
                preset3.setChecked(false);
                preset4.setChecked(false);

                btnTheme = 4;

                btnTheme1.setChecked(false);
                btnTheme2.setChecked(false);
                btnTheme3.setChecked(false);
                btnTheme4.setChecked(true);
                btnTheme5.setChecked(false);

                btnImage1.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage2.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage3.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage4.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage5.setBackgroundResource(R.drawable.dot_inside_a_circle);
            }
        });

        btnTheme5.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(false);
                preset2.setChecked(false);
                preset3.setChecked(false);
                preset4.setChecked(false);

                btnTheme = 5;

                btnTheme1.setChecked(false);
                btnTheme2.setChecked(false);
                btnTheme3.setChecked(false);
                btnTheme4.setChecked(false);
                btnTheme5.setChecked(true);

                btnImage1.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage2.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage3.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage4.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage5.setBackgroundResource(R.drawable.clover_cute_comp);

            }
        });

        bgTheme1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(false);
                preset2.setChecked(false);
                preset3.setChecked(false);
                preset4.setChecked(false);

                bgTheme = 1;

                bgTheme1.setChecked(true);
                bgTheme2.setChecked(false);
                bgTheme3.setChecked(false);
                bgTheme4.setChecked(false);
                bgTheme5.setChecked(false);
                bgTheme_custom.setChecked(false);

                bgImage.setBackgroundResource(R.drawable.orora);
            }
        });

        bgTheme2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(false);
                preset2.setChecked(false);
                preset3.setChecked(false);
                preset4.setChecked(false);

                bgTheme = 2;

                bgTheme1.setChecked(false);
                bgTheme2.setChecked(true);
                bgTheme3.setChecked(false);
                bgTheme4.setChecked(false);
                bgTheme5.setChecked(false);
                bgTheme_custom.setChecked(false);

                bgImage.setBackgroundResource(R.drawable.neon);
            }
        });

        bgTheme3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(false);
                preset2.setChecked(false);
                preset3.setChecked(false);
                preset4.setChecked(false);

                bgTheme = 3;

                bgTheme1.setChecked(false);
                bgTheme2.setChecked(false);
                bgTheme3.setChecked(true);
                bgTheme4.setChecked(false);
                bgTheme5.setChecked(false);
                bgTheme_custom.setChecked(false);

                bgImage.setBackgroundResource(R.drawable.frozen);
            }
        });

        bgTheme4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(false);
                preset2.setChecked(false);
                preset3.setChecked(false);
                preset4.setChecked(false);

                bgTheme = 4;

                bgTheme1.setChecked(false);
                bgTheme2.setChecked(false);
                bgTheme3.setChecked(false);
                bgTheme4.setChecked(true);
                bgTheme5.setChecked(false);
                bgTheme_custom.setChecked(false);

                bgImage.setBackgroundResource(R.drawable.frozen4);
            }
        });

        bgTheme5.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                preset1.setChecked(false);
                preset2.setChecked(false);
                preset3.setChecked(false);
                preset4.setChecked(false);

                bgTheme = 5;

                bgTheme1.setChecked(false);
                bgTheme2.setChecked(false);
                bgTheme3.setChecked(false);
                bgTheme4.setChecked(false);
                bgTheme5.setChecked(true);
                bgTheme_custom.setChecked(false);

                bgImage.setBackgroundColor(getResources().getColor(android.R.color.black));
            }
        });

        effTheme1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){

                effTheme = 1;

                effTheme1.setChecked(true);
                effTheme2.setChecked(false);
                effTheme3.setChecked(false);
                effTheme4.setChecked(false);

                /*
                eff_cursor.setScaleX(1);
                eff_cursor.setScaleY(1);

                eff_cursor.setAnimation("pw_check.json");
                eff_cursor.loop(true);
                eff_cursor.playAnimation();
                */
                eff_cursor1.setVisibility(View.VISIBLE);
                eff_cursor2.setVisibility(View.INVISIBLE);
                eff_cursor3.setVisibility(View.INVISIBLE);
                eff_cursor4.setVisibility(View.INVISIBLE);
            }
        });

        effTheme2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){

                effTheme = 2;

                effTheme1.setChecked(false);
                effTheme2.setChecked(true);
                effTheme3.setChecked(false);
                effTheme4.setChecked(false);

                eff_cursor1.setVisibility(View.INVISIBLE);
                eff_cursor2.setVisibility(View.VISIBLE);
                eff_cursor3.setVisibility(View.INVISIBLE);
                eff_cursor4.setVisibility(View.INVISIBLE);
            }
        });

        effTheme3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){

                effTheme = 3;

                effTheme1.setChecked(false);
                effTheme2.setChecked(false);
                effTheme3.setChecked(true);
                effTheme4.setChecked(false);

                eff_cursor1.setVisibility(View.INVISIBLE);
                eff_cursor2.setVisibility(View.INVISIBLE);
                eff_cursor3.setVisibility(View.VISIBLE);
                eff_cursor4.setVisibility(View.INVISIBLE);
            }
        });

        effTheme4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){

                effTheme = 4;

                effTheme1.setChecked(false);
                effTheme2.setChecked(false);
                effTheme3.setChecked(false);
                effTheme4.setChecked(true);

                eff_cursor1.setVisibility(View.INVISIBLE);
                eff_cursor2.setVisibility(View.INVISIBLE);
                eff_cursor3.setVisibility(View.INVISIBLE);
                eff_cursor4.setVisibility(View.VISIBLE);
            }
        });

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener(){
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
                        SettingsActivity.class); // 이동할 컴포넌트
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                finish();
            }
        });
    }

    void ConfirmSettings(){
        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("tmpBtnTheme", btnTheme);
        editor.putInt("tmpBgTheme", bgTheme);
        editor.putInt("tmpEffTheme", effTheme);
        editor.commit();

        Intent intent = new Intent(
                getApplicationContext(),//현재제어권자
                SettingsActivity3.class); // 이동할 컴포넌트
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        finish();
    }

    @Override
    protected void onStop(){

        super.onStop();
    }
}
