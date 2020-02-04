package com.diallock.slidediallock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class SettingsActivity2_re extends Activity {

    private Button next;
    //private Button before;

    private CheckBox theme1;
    private CheckBox theme2;
    private CheckBox theme3;
    private CheckBox theme4;
    private CheckBox theme5;
    private CheckBox theme6;
    private CheckBox theme7;
    private CheckBox theme8;
    private CheckBox theme9;
    private CheckBox theme10;
    private CheckBox theme11;
    private CheckBox theme12;
    private CheckBox theme13;
    private CheckBox theme14;
    private CheckBox theme15;
    private CheckBox theme16;

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

    // 테마 저장 변수 (인덱스)
    private Integer iTheme;
    private Integer tmpEffTheme;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Log.d("Activity", "Setting Run!");
        setContentView(R.layout.activity_settings2_re);

        SharedPreferences sf = getSharedPreferences("sFile",MODE_PRIVATE);

        iTheme = sf.getInt("tmpTheme", 10);
        tmpEffTheme = sf.getInt("tmpEffTheme",3);

        theme1 = (CheckBox)findViewById(R.id.calm1);
        theme2 = (CheckBox)findViewById(R.id.calm2);
        theme3 = (CheckBox)findViewById(R.id.calm3);
        theme4 = (CheckBox)findViewById(R.id.calm4);
        theme5 = (CheckBox)findViewById(R.id.mood1);
        theme6 = (CheckBox)findViewById(R.id.mood2);
        theme7 = (CheckBox)findViewById(R.id.mood3);
        theme8 = (CheckBox)findViewById(R.id.mood4);
        theme9 = (CheckBox)findViewById(R.id.cute1);
        theme10 = (CheckBox)findViewById(R.id.cute2);
        theme11 = (CheckBox)findViewById(R.id.cute3);
        theme12 = (CheckBox)findViewById(R.id.cute4);
        theme13 = (CheckBox)findViewById(R.id.cute5);
        theme14 = (CheckBox)findViewById(R.id.cute6);
        theme15 = (CheckBox)findViewById(R.id.cute7);
        theme16 = (CheckBox)findViewById(R.id.cute8);

        effTheme1 = (CheckBox)findViewById(R.id.eff_theme1);
        effTheme2 = (CheckBox)findViewById(R.id.eff_theme2);
        effTheme3 = (CheckBox)findViewById(R.id.eff_theme3);
        effTheme4 = (CheckBox)findViewById(R.id.eff_theme4);

        bgImage = (ImageView)findViewById(R.id.background);
        btnImage1 = (ImageView)findViewById(R.id.btn1);
        btnImage2 = (ImageView)findViewById(R.id.btn2);
        btnImage3 = (ImageView)findViewById(R.id.btn3);
        btnImage4 = (ImageView)findViewById(R.id.btn4);
        btnImage5 = (ImageView)findViewById(R.id.btn5);

        //eff1 = (JSONObject)"fireworks_ice_x2.json";

        eff_cursor1 = (LottieAnimationView)findViewById(R.id.imgEff1);
        eff_cursor2 = (LottieAnimationView)findViewById(R.id.imgEff2);
        eff_cursor3 = (LottieAnimationView)findViewById(R.id.imgEff3);
        eff_cursor4 = (LottieAnimationView)findViewById(R.id.imgEff4);

        switch(iTheme){
            case 1: // Fantasy
                bgImage.setBackgroundResource(R.drawable.fantasy);
                btnImage1.setBackgroundResource(R.drawable.light_skyblue);
                btnImage2.setBackgroundResource(R.drawable.light_skyblue);
                btnImage3.setBackgroundResource(R.drawable.light_skyblue);
                btnImage4.setBackgroundResource(R.drawable.light_skyblue);
                btnImage5.setBackgroundResource(R.drawable.light_skyblue);
                theme1.setChecked(true);
                break;
            case 2: // MilkyWay
                bgImage.setBackgroundResource(R.drawable.milkyway);
                btnImage1.setBackgroundResource(R.drawable.star3);
                btnImage2.setBackgroundResource(R.drawable.star3);
                btnImage3.setBackgroundResource(R.drawable.star3);
                btnImage4.setBackgroundResource(R.drawable.star3);
                btnImage5.setBackgroundResource(R.drawable.star3_selected);

                /*
                bgImage.setBackgroundResource(R.drawable.milkyway);
                btnImage1.setBackgroundResource(R.drawable.star2);
                btnImage2.setBackgroundResource(R.drawable.star2);
                btnImage3.setBackgroundResource(R.drawable.star2);
                btnImage4.setBackgroundResource(R.drawable.star2);
                btnImage5.setBackgroundResource(R.drawable.star2);
                 */
                theme2.setChecked(true);
                break;
            case 3: // X-mas
                bgImage.setBackgroundResource(R.drawable.christmas6);
                btnImage1.setBackgroundResource(R.drawable.bauble);
                btnImage2.setBackgroundResource(R.drawable.bauble);
                btnImage3.setBackgroundResource(R.drawable.bauble);
                btnImage4.setBackgroundResource(R.drawable.bauble);
                btnImage5.setBackgroundResource(R.drawable.bauble_selected);
                theme3.setChecked(true);
                break;
            case 4: // 투명
                bgImage.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                btnImage1.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage2.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage3.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage4.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage5.setBackgroundResource(R.drawable.dot_inside_a_circle);
                theme4.setChecked(true);
                break;
            case 5: // Frozen
                bgImage.setBackgroundResource(R.drawable.winter1);
                btnImage1.setBackgroundResource(R.drawable.snowflake3);
                btnImage2.setBackgroundResource(R.drawable.snowflake3);
                btnImage3.setBackgroundResource(R.drawable.snowflake3);
                btnImage4.setBackgroundResource(R.drawable.snowflake3);
                btnImage5.setBackgroundResource(R.drawable.snowflake3_selected);
                theme5.setChecked(true);
                break;
            case 6: // Neon
                bgImage.setBackgroundResource(R.drawable.neon);
                btnImage1.setBackgroundResource(R.drawable.star_neon);
                btnImage2.setBackgroundResource(R.drawable.star_neon);
                btnImage3.setBackgroundResource(R.drawable.star_neon);
                btnImage4.setBackgroundResource(R.drawable.star_neon);
                btnImage5.setBackgroundResource(R.drawable.star_neon);
                theme6.setChecked(true);
                break;
            case 7: // Snow
                bgImage.setBackgroundResource(R.drawable.winter2);

                btnImage1.setBackgroundResource(R.drawable.snowflake2);
                btnImage2.setBackgroundResource(R.drawable.snowflake2);
                btnImage3.setBackgroundResource(R.drawable.snowflake2);
                btnImage4.setBackgroundResource(R.drawable.snowflake2);
                btnImage5.setBackgroundResource(R.drawable.snowflake2_selected);

                theme7.setChecked(true);
                break;
            case 8: // Couple

                theme8.setChecked(true);
                break;
            case 9: // Eyes
                //bgImage.setBackgroundColor(getResources().getColor(holo_green_dark));
                bgImage.setBackgroundResource(R.drawable.eyes_bg);
                btnImage1.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage2.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage3.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage4.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage5.setBackgroundResource(R.drawable.clover_cute_selected);
                theme9.setChecked(true);

                break;
            case 10: // Lover
                bgImage.setBackgroundResource(R.drawable.love7);
                btnImage1.setBackgroundResource(R.drawable.moon_100);
                btnImage2.setBackgroundResource(R.drawable.moon_75);
                btnImage3.setBackgroundResource(R.drawable.moon_50);
                btnImage4.setBackgroundResource(R.drawable.moon_25);
                btnImage5.setBackgroundResource(R.drawable.moon_0);
                theme10.setChecked(true);
                break;
            case 11: // Shy
                bgImage.setBackgroundResource(R.drawable.cute_couple);
                btnImage1.setBackgroundResource(R.drawable.shy_heart_normal);
                btnImage2.setBackgroundResource(R.drawable.shy_heart_normal);
                btnImage3.setBackgroundResource(R.drawable.shy_heart_normal);
                btnImage4.setBackgroundResource(R.drawable.shy_heart_normal);
                btnImage5.setBackgroundResource(R.drawable.shy_heart);
                theme11.setChecked(true);
                break;
            case 12: // Puppy
                bgImage.setBackgroundResource(R.drawable.puppy);
                btnImage1.setBackgroundResource(R.drawable.blue_bone);
                btnImage2.setBackgroundResource(R.drawable.blue_bone);
                btnImage3.setBackgroundResource(R.drawable.blue_bone);
                btnImage4.setBackgroundResource(R.drawable.blue_bone);
                btnImage5.setBackgroundResource(R.drawable.gold_broken_bone);
                theme12.setChecked(true);
                break;
            case 13: // Crayon 1
                bgImage.setBackgroundResource(R.drawable.crayon1);
                btnImage1.setBackgroundResource(R.drawable.chocoby);
                btnImage2.setBackgroundResource(R.drawable.chocoby);
                btnImage3.setBackgroundResource(R.drawable.chocoby);
                btnImage4.setBackgroundResource(R.drawable.chocoby);
                btnImage5.setBackgroundResource(R.drawable.chocoby_open);
                theme13.setChecked(true);
                break;
            case 14: // Crayon 2
                bgImage.setBackgroundResource(R.drawable.crayon_2);
                btnImage1.setBackgroundResource(R.drawable.crayon_jjangoo);
                btnImage2.setBackgroundResource(R.drawable.crayon_chulsoo);
                btnImage3.setBackgroundResource(R.drawable.crayon_huni);
                btnImage4.setBackgroundResource(R.drawable.crayon_yuri);
                btnImage5.setBackgroundResource(R.drawable.crayon_manggoo);
                theme14.setChecked(true);
                break;
            case 15: // PengSoo
                bgImage.setBackgroundResource(R.drawable.pengsoo);
                btnImage1.setBackgroundResource(R.drawable.pengsoo_icon1);
                btnImage2.setBackgroundResource(R.drawable.pengsoo_icon2);
                btnImage3.setBackgroundResource(R.drawable.pengsoo_icon3);
                btnImage4.setBackgroundResource(R.drawable.pengsoo_icon4);
                btnImage5.setBackgroundResource(R.drawable.pengsoo_icon5);
                break;
        }

        setEff(tmpEffTheme);

        theme1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(true);
                theme2.setChecked(false);
                theme3.setChecked(false);
                theme4.setChecked(false);
                theme5.setChecked(false);
                theme6.setChecked(false);
                theme7.setChecked(false);
                theme8.setChecked(false);
                theme9.setChecked(false);
                theme10.setChecked(false);
                theme11.setChecked(false);
                theme12.setChecked(false);
                theme13.setChecked(false);
                theme14.setChecked(false);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 1;
                setEff(1);

                bgImage.setBackgroundResource(R.drawable.fantasy);
                btnImage1.setBackgroundResource(R.drawable.light_skyblue);
                btnImage2.setBackgroundResource(R.drawable.light_skyblue);
                btnImage3.setBackgroundResource(R.drawable.light_skyblue);
                btnImage4.setBackgroundResource(R.drawable.light_skyblue);
                btnImage5.setBackgroundResource(R.drawable.light_skyblue_selected);
            }
        });

        theme2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(true);
                theme3.setChecked(false);
                theme4.setChecked(false);
                theme5.setChecked(false);
                theme6.setChecked(false);
                theme7.setChecked(false);
                theme8.setChecked(false);
                theme9.setChecked(false);
                theme10.setChecked(false);
                theme11.setChecked(false);
                theme12.setChecked(false);
                theme13.setChecked(false);
                theme14.setChecked(false);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 2;
                setEff(1);

                bgImage.setBackgroundResource(R.drawable.milkyway);
                btnImage1.setBackgroundResource(R.drawable.star3);
                btnImage2.setBackgroundResource(R.drawable.star3);
                btnImage3.setBackgroundResource(R.drawable.star3);
                btnImage4.setBackgroundResource(R.drawable.star3);
                btnImage5.setBackgroundResource(R.drawable.star3_selected);
            }
        });

        theme3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(false);
                theme3.setChecked(true);
                theme4.setChecked(false);
                theme5.setChecked(false);
                theme6.setChecked(false);
                theme7.setChecked(false);
                theme8.setChecked(false);
                theme9.setChecked(false);
                theme10.setChecked(false);
                theme11.setChecked(false);
                theme12.setChecked(false);
                theme13.setChecked(false);
                theme14.setChecked(false);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 3;
                setEff(1);

                bgImage.setBackgroundResource(R.drawable.christmas6);
                btnImage1.setBackgroundResource(R.drawable.bauble);
                btnImage2.setBackgroundResource(R.drawable.bauble);
                btnImage3.setBackgroundResource(R.drawable.bauble);
                btnImage4.setBackgroundResource(R.drawable.bauble);
                btnImage5.setBackgroundResource(R.drawable.bauble_selected);
            }
        });

        theme4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(false);
                theme3.setChecked(false);
                theme4.setChecked(true);
                theme5.setChecked(false);
                theme6.setChecked(false);
                theme7.setChecked(false);
                theme8.setChecked(false);
                theme9.setChecked(false);
                theme10.setChecked(false);
                theme11.setChecked(false);
                theme12.setChecked(false);
                theme13.setChecked(false);
                theme14.setChecked(false);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 4;
                setEff(1);

                bgImage.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                btnImage1.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage2.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage3.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage4.setBackgroundResource(R.drawable.dot_inside_a_circle);
                btnImage5.setBackgroundResource(R.drawable.dot_inside_a_circle);
            }
        });

        theme5.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(false);
                theme3.setChecked(false);
                theme4.setChecked(false);
                theme5.setChecked(true);
                theme6.setChecked(false);
                theme7.setChecked(false);
                theme8.setChecked(false);
                theme9.setChecked(false);
                theme10.setChecked(false);
                theme11.setChecked(false);
                theme12.setChecked(false);
                theme13.setChecked(false);
                theme14.setChecked(false);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 5;
                setEff(2);

                bgImage.setBackgroundResource(R.drawable.winter1);
                btnImage1.setBackgroundResource(R.drawable.snowflake3);
                btnImage2.setBackgroundResource(R.drawable.snowflake3);
                btnImage3.setBackgroundResource(R.drawable.snowflake3);
                btnImage4.setBackgroundResource(R.drawable.snowflake3);
                btnImage5.setBackgroundResource(R.drawable.snowflake3_selected);
            }
        });

        theme6.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(false);
                theme3.setChecked(false);
                theme4.setChecked(false);
                theme5.setChecked(false);
                theme6.setChecked(true);
                theme7.setChecked(false);
                theme8.setChecked(false);
                theme9.setChecked(false);
                theme10.setChecked(false);
                theme11.setChecked(false);
                theme12.setChecked(false);
                theme13.setChecked(false);
                theme14.setChecked(false);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 6;
                setEff(1);

                bgImage.setBackgroundResource(R.drawable.neon);
                btnImage1.setBackgroundResource(R.drawable.star_neon);
                btnImage2.setBackgroundResource(R.drawable.star_neon);
                btnImage3.setBackgroundResource(R.drawable.star_neon);
                btnImage4.setBackgroundResource(R.drawable.star_neon);
                btnImage5.setBackgroundResource(R.drawable.star_neon);
            }
        });

        theme7.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(false);
                theme3.setChecked(false);
                theme4.setChecked(false);
                theme5.setChecked(false);
                theme6.setChecked(false);
                theme7.setChecked(true);
                theme8.setChecked(false);
                theme9.setChecked(false);
                theme10.setChecked(false);
                theme11.setChecked(false);
                theme12.setChecked(false);
                theme13.setChecked(false);
                theme14.setChecked(false);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 7;
                setEff(2);

                bgImage.setBackgroundResource(R.drawable.winter2);

                btnImage1.setBackgroundResource(R.drawable.snowflake2);
                btnImage2.setBackgroundResource(R.drawable.snowflake2);
                btnImage3.setBackgroundResource(R.drawable.snowflake2);
                btnImage4.setBackgroundResource(R.drawable.snowflake2);
                btnImage5.setBackgroundResource(R.drawable.snowflake2_selected);

            }
        });

        theme8.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(false);
                theme3.setChecked(false);
                theme4.setChecked(false);
                theme5.setChecked(false);
                theme6.setChecked(false);
                theme7.setChecked(false);
                theme8.setChecked(true);
                theme9.setChecked(false);
                theme10.setChecked(false);
                theme11.setChecked(false);
                theme12.setChecked(false);
                theme13.setChecked(false);
                theme14.setChecked(false);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 8;

                setEff(3);
            }
        });

        theme9.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(false);
                theme3.setChecked(false);
                theme4.setChecked(false);
                theme5.setChecked(false);
                theme6.setChecked(false);
                theme7.setChecked(false);
                theme8.setChecked(false);
                theme9.setChecked(true);
                theme10.setChecked(false);
                theme11.setChecked(false);
                theme12.setChecked(false);
                theme13.setChecked(false);
                theme14.setChecked(false);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 9;

                //bgImage.setBackgroundColor(getResources().getColor(holo_green_dark));
                bgImage.setBackgroundResource(R.drawable.eyes_bg);
                btnImage1.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage2.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage3.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage4.setBackgroundResource(R.drawable.clover_cute_comp);
                btnImage5.setBackgroundResource(R.drawable.clover_cute_selected);

                setEff(3);
            }
        });

        theme10.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(false);
                theme3.setChecked(false);
                theme4.setChecked(false);
                theme5.setChecked(false);
                theme6.setChecked(false);
                theme7.setChecked(false);
                theme8.setChecked(false);
                theme9.setChecked(false);
                theme10.setChecked(true);
                theme11.setChecked(false);
                theme12.setChecked(false);
                theme13.setChecked(false);
                theme14.setChecked(false);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 10;

                bgImage.setBackgroundResource(R.drawable.love7);
                btnImage1.setBackgroundResource(R.drawable.moon_100);
                btnImage2.setBackgroundResource(R.drawable.moon_75);
                btnImage3.setBackgroundResource(R.drawable.moon_50);
                btnImage4.setBackgroundResource(R.drawable.moon_25);
                btnImage5.setBackgroundResource(R.drawable.moon_0);

                setEff(3);
            }
        });

        theme11.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(false);
                theme3.setChecked(false);
                theme4.setChecked(false);
                theme5.setChecked(false);
                theme6.setChecked(false);
                theme7.setChecked(false);
                theme8.setChecked(false);
                theme9.setChecked(false);
                theme10.setChecked(false);
                theme11.setChecked(true);
                theme12.setChecked(false);
                theme13.setChecked(false);
                theme14.setChecked(false);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 11;

                bgImage.setBackgroundResource(R.drawable.cute_couple);
                btnImage1.setBackgroundResource(R.drawable.shy_heart_normal);
                btnImage2.setBackgroundResource(R.drawable.shy_heart_normal);
                btnImage3.setBackgroundResource(R.drawable.shy_heart_normal);
                btnImage4.setBackgroundResource(R.drawable.shy_heart_normal);
                btnImage5.setBackgroundResource(R.drawable.shy_heart);

                setEff(3);
            }
        });

        theme12.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(false);
                theme3.setChecked(false);
                theme4.setChecked(false);
                theme5.setChecked(false);
                theme6.setChecked(false);
                theme7.setChecked(false);
                theme8.setChecked(false);
                theme9.setChecked(false);
                theme10.setChecked(false);
                theme11.setChecked(false);
                theme12.setChecked(true);
                theme13.setChecked(false);
                theme14.setChecked(false);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 12;

                bgImage.setBackgroundResource(R.drawable.puppy);
                btnImage1.setBackgroundResource(R.drawable.blue_bone);
                btnImage2.setBackgroundResource(R.drawable.blue_bone);
                btnImage3.setBackgroundResource(R.drawable.blue_bone);
                btnImage4.setBackgroundResource(R.drawable.blue_bone);
                btnImage5.setBackgroundResource(R.drawable.gold_broken_bone);

                setEff(3);
            }
        });

        theme13.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(false);
                theme3.setChecked(false);
                theme4.setChecked(false);
                theme5.setChecked(false);
                theme6.setChecked(false);
                theme7.setChecked(false);
                theme8.setChecked(false);
                theme9.setChecked(false);
                theme10.setChecked(false);
                theme11.setChecked(false);
                theme12.setChecked(false);
                theme13.setChecked(true);
                theme14.setChecked(false);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 13;

                bgImage.setBackgroundResource(R.drawable.crayon1);
                btnImage1.setBackgroundResource(R.drawable.chocoby);
                btnImage2.setBackgroundResource(R.drawable.chocoby);
                btnImage3.setBackgroundResource(R.drawable.chocoby);
                btnImage4.setBackgroundResource(R.drawable.chocoby);
                btnImage5.setBackgroundResource(R.drawable.chocoby_open);

                setEff(3);
            }
        });

        theme14.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(false);
                theme3.setChecked(false);
                theme4.setChecked(false);
                theme5.setChecked(false);
                theme6.setChecked(false);
                theme7.setChecked(false);
                theme8.setChecked(false);
                theme9.setChecked(false);
                theme10.setChecked(false);
                theme11.setChecked(false);
                theme12.setChecked(false);
                theme13.setChecked(false);
                theme14.setChecked(true);
                theme15.setChecked(false);
                theme16.setChecked(false);

                iTheme = 14;

                bgImage.setBackgroundResource(R.drawable.crayon_2);
                btnImage1.setBackgroundResource(R.drawable.crayon_jjangoo);
                btnImage2.setBackgroundResource(R.drawable.crayon_chulsoo);
                btnImage3.setBackgroundResource(R.drawable.crayon_huni);
                btnImage4.setBackgroundResource(R.drawable.crayon_yuri);
                btnImage5.setBackgroundResource(R.drawable.crayon_manggoo);

                setEff(1);
            }
        });

        theme15.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                theme1.setChecked(false);
                theme2.setChecked(false);
                theme3.setChecked(false);
                theme4.setChecked(false);
                theme5.setChecked(false);
                theme6.setChecked(false);
                theme7.setChecked(false);
                theme8.setChecked(false);
                theme9.setChecked(false);
                theme10.setChecked(false);
                theme11.setChecked(false);
                theme12.setChecked(false);
                theme13.setChecked(false);
                theme14.setChecked(false);
                theme15.setChecked(true);
                theme16.setChecked(false);

                iTheme = 15;

                bgImage.setBackgroundResource(R.drawable.pengsoo);
                btnImage1.setBackgroundResource(R.drawable.pengsoo_icon1);
                btnImage2.setBackgroundResource(R.drawable.pengsoo_icon2);
                btnImage3.setBackgroundResource(R.drawable.pengsoo_icon3);
                btnImage4.setBackgroundResource(R.drawable.pengsoo_icon4);
                btnImage5.setBackgroundResource(R.drawable.pengsoo_icon5);

                setEff(3);
            }
        });

        effTheme1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                setEff(1);
            }
        });
        effTheme2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                setEff(2);
            }
        });
        effTheme3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                setEff(3);
            }
        });
        effTheme4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                setEff(4);
            }
        });

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ConfirmSettings();
            }
        });

        /*
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
        */
    }

    void setEff(int index){

        effTheme1.setChecked(false);
        effTheme2.setChecked(false);
        effTheme3.setChecked(false);
        effTheme4.setChecked(false);

        eff_cursor1.setVisibility(View.INVISIBLE);
        eff_cursor2.setVisibility(View.INVISIBLE);
        eff_cursor3.setVisibility(View.INVISIBLE);
        eff_cursor4.setVisibility(View.INVISIBLE);

        switch(index){
            case 1:
                effTheme1.setChecked(true);
                eff_cursor1.setVisibility(View.VISIBLE);
                break;
            case 2:
                effTheme2.setChecked(true);
                eff_cursor2.setVisibility(View.VISIBLE);
                break;
            case 3:
                effTheme3.setChecked(true);
                eff_cursor3.setVisibility(View.VISIBLE);
                break;
            case 4:
                effTheme4.setChecked(true);
                eff_cursor4.setVisibility(View.VISIBLE);
                break;
        }

        tmpEffTheme = index;
    }

    void ConfirmSettings(){
        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("tmpTheme", iTheme);
        editor.putInt("tmpEffTheme", tmpEffTheme);
        editor.commit();

        Intent intent = new Intent(
                getApplicationContext(),//현재제어권자
                SettingsActivity.class); // 이동할 컴포넌트
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        //overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        finish();
    }

    @Override
    protected void onStop(){

        super.onStop();
    }
}
