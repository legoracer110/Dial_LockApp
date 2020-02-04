package com.diallock.slidediallock;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;

import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static android.R.color.holo_green_dark;

public class DialLockActivity extends Activity {

    private String pw;

    private Integer pwSize;

    private String inputNum;
    private String inputPw;

    //private LinearLayout bg_screen;
    private ImageView bg_screen;

    // 테마 저장 변수 (인덱스)
    private Integer theme;
    private Integer effTheme;

    private ImageView mImg1;
    private ImageView mImg2;
    private ImageView mImg3;
    private ImageView mImg4;
    private ImageView mImg5;

    private ImageView img_input;

    private ImageView img_cursor;
    private LottieAnimationView img_cursor2;
    private LottieAnimationView img_check;

    private ValueAnimator animator;

    private static final String IMAGEVIEW_TAG = "드래그 이미지";

    private Integer wrongTrigger;
    private TextView txtWrong;
    private TextView txtWrong2;
    private boolean isFreeze;

    WindowManager wm;
    View mView;
    Vibrator vibrator;

    ////////////// 해상도 구하기 ////////////////
    int standardSize_X, standardSize_Y;
    float density;

    Point ScreenSize;

    ///////////////////////////////////////////

    // Google AdMob
    private AdView mAdView;
    private boolean noAd;

    public Point getScreenSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return  size;
    }
    /*
    public void getStandardSize() {
        Point ScreenSize = getScreenSize(this);
        density  = getResources().getDisplayMetrics().density;

        standardSize_X = (int) (ScreenSize.x / density);
        standardSize_Y = (int) (ScreenSize.y / density);
    }
    //////////////////////////////////
    */

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch(keyCode) {
            case KeyEvent.KEYCODE_BACK:
                // 여기에 뒤로가기 버튼을 눌렀을 때 행동 입력
                return false;

            case KeyEvent.KEYCODE_HOME:
                // 여기에 홈 버튼을 눌렀을 때 행동 입력
                return false;
            case KeyEvent.KEYCODE_MENU:
                return false;
        }

        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScreenSize = getScreenSize(this);

        // 진동
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        getWindow().addFlags(
                // 기본 잠금화면보다 우선출력
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        // 기본 잠금화면 해제시키기
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        // setContentView(R.layout.activity_draglock);
        // bg_screen = (LinearLayout) findViewById(R.id.bg_layout);

        Intent intent = new Intent(
                getApplicationContext(),//현재제어권자
                LockScreenService.class); // 이동할 컴포넌트
        startService(intent);

        LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        |WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        |WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        |WindowManager.LayoutParams.FLAG_FULLSCREEN,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER;

        mView = inflate.inflate(R.layout.activity_draglock_ads, null);
        mView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
        //bg_screen = (LinearLayout)mView.findViewById(R.id.bg_layout);
        bg_screen = (ImageView)mView.findViewById(R.id.bg_img);

        inputPw = "";

        SharedPreferences sf = getSharedPreferences("sFile",MODE_PRIVATE);

        boolean isOn = sf.getBoolean("isLock", true);
        pw = sf.getString("pwList", "0000");

        if(!isOn||pw.equals("0000"))
            finishAffinity();

        AppStorage storage = new AppStorage(this);
        if(storage.purchasedRemoveAds())
            noAd=true;

        //pw = "bfha";
        pwSize = sf.getInt("pwSize", 4);
        //pwSize = pw.length();

        theme = sf.getInt("theme", 10);
        effTheme = sf.getInt("effTheme", 3);

        img_input = (ImageView) mView.findViewById(R.id.input_img);
        if(pwSize==2)
            img_input.setImageResource(R.drawable.input_2pw0);
        else
            img_input.setImageResource(R.drawable.input_4pw0);

        mImg1 = (ImageView) mView.findViewById(R.id.img1);
        mImg1.setTag(IMAGEVIEW_TAG);
        mImg2 = (ImageView) mView.findViewById(R.id.img2);
        mImg2.setTag(IMAGEVIEW_TAG);
        mImg3 = (ImageView) mView.findViewById(R.id.img3);
        mImg3.setTag(IMAGEVIEW_TAG);
        mImg4 = (ImageView) mView.findViewById(R.id.img4);
        mImg4.setTag(IMAGEVIEW_TAG);
        mImg5 = (ImageView) mView.findViewById(R.id.img5);
        mImg5.setTag(IMAGEVIEW_TAG);

        img_cursor = (ImageView) mView.findViewById((R.id.i_cursor));
        img_cursor.setTag(IMAGEVIEW_TAG);

        img_cursor2 = (LottieAnimationView) mView.findViewById(R.id.anim_cursor);
        img_cursor2.setTag(IMAGEVIEW_TAG);

        img_check = (LottieAnimationView) mView.findViewById(R.id.anim_cursor2);
        img_check.setTag(IMAGEVIEW_TAG);

        animator = ValueAnimator.ofFloat(0f, 1f)
                .setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                img_check.setProgress((Float) animation.getAnimatedValue());
            }
        });

        mImg1.setOnTouchListener(new DialLockActivity.TouchListener());
        mImg1.setClickable(true);
        mImg2.setOnTouchListener(new DialLockActivity.TouchListener());
        mImg2.setClickable(true);
        mImg3.setOnTouchListener(new DialLockActivity.TouchListener());
        mImg3.setClickable(true);
        mImg4.setOnTouchListener(new DialLockActivity.TouchListener());
        mImg4.setClickable(true);
        mImg5.setOnTouchListener(new DialLockActivity.TouchListener());
        mImg5.setClickable(true);


        switch(theme){
            case 1:
                bg_screen.setBackgroundResource(R.drawable.fantasy);
                mImg1.setBackgroundResource(R.drawable.light_skyblue);
                mImg2.setBackgroundResource(R.drawable.light_skyblue);
                mImg3.setBackgroundResource(R.drawable.light_skyblue);
                mImg4.setBackgroundResource(R.drawable.light_skyblue);
                mImg5.setBackgroundResource(R.drawable.light_skyblue);
                break;
            case 2:
                /*
                bg_screen.setBackgroundResource(R.drawable.milkyway);
                mImg1.setBackgroundResource(R.drawable.star2);
                mImg2.setBackgroundResource(R.drawable.star2);
                mImg3.setBackgroundResource(R.drawable.star2);
                mImg4.setBackgroundResource(R.drawable.star2);
                mImg5.setBackgroundResource(R.drawable.star2);
                */

                bg_screen.setBackgroundResource(R.drawable.milkyway);
                mImg1.setBackgroundResource(R.drawable.star3);
                mImg2.setBackgroundResource(R.drawable.star3);
                mImg3.setBackgroundResource(R.drawable.star3);
                mImg4.setBackgroundResource(R.drawable.star3);
                mImg5.setBackgroundResource(R.drawable.star3);
                break;
            case 3:
                bg_screen.setBackgroundResource(R.drawable.christmas6);
                mImg1.setBackgroundResource(R.drawable.bauble);
                mImg2.setBackgroundResource(R.drawable.bauble);
                mImg3.setBackgroundResource(R.drawable.bauble);
                mImg4.setBackgroundResource(R.drawable.bauble);
                mImg5.setBackgroundResource(R.drawable.bauble);
                break;
            case 4:
                //bg_screen.setBackgroundColor(getResources().getColor(android.R.color.white));
                mImg1.setBackgroundResource(R.drawable.dot_inside_a_circle);
                mImg2.setBackgroundResource(R.drawable.dot_inside_a_circle);
                mImg3.setBackgroundResource(R.drawable.dot_inside_a_circle);
                mImg4.setBackgroundResource(R.drawable.dot_inside_a_circle);
                mImg5.setBackgroundResource(R.drawable.dot_inside_a_circle);
                break;
            case 5:
                bg_screen.setBackgroundResource(R.drawable.winter1);
                mImg1.setBackgroundResource(R.drawable.snowflake3);
                mImg2.setBackgroundResource(R.drawable.snowflake3);
                mImg3.setBackgroundResource(R.drawable.snowflake3);
                mImg4.setBackgroundResource(R.drawable.snowflake3);
                mImg5.setBackgroundResource(R.drawable.snowflake3);
                break;
            case 6:
                bg_screen.setBackgroundResource(R.drawable.neon);
                mImg1.setBackgroundResource(R.drawable.star_neon);
                mImg2.setBackgroundResource(R.drawable.star_neon);
                mImg3.setBackgroundResource(R.drawable.star_neon);
                mImg4.setBackgroundResource(R.drawable.star_neon);
                mImg5.setBackgroundResource(R.drawable.star_neon);
                break;
            case 7:
                bg_screen.setBackgroundResource(R.drawable.winter2);

                mImg1.setBackgroundResource(R.drawable.snowflake2);
                mImg2.setBackgroundResource(R.drawable.snowflake2);
                mImg3.setBackgroundResource(R.drawable.snowflake2);
                mImg4.setBackgroundResource(R.drawable.snowflake2);
                mImg5.setBackgroundResource(R.drawable.snowflake2);

                break;
            case 8:

                break;
            case 9:
                bg_screen.setBackgroundColor(getResources().getColor(holo_green_dark));
                img_input.setImageResource(R.drawable.eyes_0);
                mImg1.setBackgroundResource(R.drawable.clover_cute_comp);
                mImg2.setBackgroundResource(R.drawable.clover_cute_comp);
                mImg3.setBackgroundResource(R.drawable.clover_cute_comp);
                mImg4.setBackgroundResource(R.drawable.clover_cute_comp);
                mImg5.setBackgroundResource(R.drawable.clover_cute_comp);
                break;
            case 10:
                bg_screen.setBackgroundResource(R.drawable.love7);
                mImg1.setBackgroundResource(R.drawable.moon_100);
                mImg2.setBackgroundResource(R.drawable.moon_75);
                mImg3.setBackgroundResource(R.drawable.moon_50);
                mImg4.setBackgroundResource(R.drawable.moon_25);
                mImg5.setBackgroundResource(R.drawable.moon_0);
                break;
            case 11:
                bg_screen.setBackgroundResource(R.drawable.cute_couple);
                mImg1.setBackgroundResource(R.drawable.shy_heart_normal);
                mImg2.setBackgroundResource(R.drawable.shy_heart_normal);
                mImg3.setBackgroundResource(R.drawable.shy_heart_normal);
                mImg4.setBackgroundResource(R.drawable.shy_heart_normal);
                mImg5.setBackgroundResource(R.drawable.shy_heart_normal);
                break;
            case 12:
                img_input.setScaleX(1.5f);
                img_input.setScaleY(1.5f);
                img_input.setY(img_input.getY()+100f);
                if(pwSize==2)
                    img_input.setImageResource(R.drawable.paw_yellow_2pw0);
                else
                    img_input.setImageResource(R.drawable.paw_yellow_4pw0);
                bg_screen.setBackgroundResource(R.drawable.puppy);
                /*
                mImg1.setBackgroundResource(R.drawable.gold_bone2);
                mImg2.setBackgroundResource(R.drawable.gold_bone2);
                mImg3.setBackgroundResource(R.drawable.gold_bone2);
                mImg4.setBackgroundResource(R.drawable.gold_bone2);
                mImg5.setBackgroundResource(R.drawable.gold_bone2);
                */
                mImg1.setBackgroundResource(R.drawable.blue_bone);
                mImg2.setBackgroundResource(R.drawable.blue_bone);
                mImg3.setBackgroundResource(R.drawable.blue_bone);
                mImg4.setBackgroundResource(R.drawable.blue_bone);
                mImg5.setBackgroundResource(R.drawable.blue_bone);
                break;
            case 13:
                bg_screen.setBackgroundResource(R.drawable.crayon1);
                mImg1.setBackgroundResource(R.drawable.chocoby);
                mImg2.setBackgroundResource(R.drawable.chocoby);
                mImg3.setBackgroundResource(R.drawable.chocoby);
                mImg4.setBackgroundResource(R.drawable.chocoby);
                mImg5.setBackgroundResource(R.drawable.chocoby);
                break;
            case 14:
                img_input.setScaleX(1.5f);
                img_input.setScaleY(1.5f);
                img_input.setY(img_input.getY()+100f);
                img_input.setImageResource(R.drawable.crayon_4pw0);
                bg_screen.setBackgroundResource(R.drawable.crayon_2);
                mImg1.setBackgroundResource(R.drawable.crayon_jjangoo);
                mImg2.setBackgroundResource(R.drawable.crayon_chulsoo);
                mImg3.setBackgroundResource(R.drawable.crayon_huni);
                mImg4.setBackgroundResource(R.drawable.crayon_yuri);
                mImg5.setBackgroundResource(R.drawable.crayon_manggoo);
                break;
            case 15:
                bg_screen.setBackgroundResource(R.drawable.pengsoo);
                mImg1.setBackgroundResource(R.drawable.pengsoo_icon1);
                mImg2.setBackgroundResource(R.drawable.pengsoo_icon2);
                mImg3.setBackgroundResource(R.drawable.pengsoo_icon3);
                mImg4.setBackgroundResource(R.drawable.pengsoo_icon4);
                mImg5.setBackgroundResource(R.drawable.pengsoo_icon5);
                break;
        }

        switch(effTheme){
            case 1:
                img_check.setAnimation("pw_check.json");
                img_cursor2.setAnimation("circle_anim_white.json");
                img_cursor2.setScaleX(0.7f);
                img_cursor2.setScaleY(0.7f);
                img_cursor2.playAnimation();
                break;
            case 2:
                img_check.setAnimation("fireworks_ice_x2.json");
                img_cursor2.setAnimation("circle_anim_white.json");
                img_cursor2.setScaleX(0.7f);
                img_cursor2.setScaleY(0.7f);
                img_cursor2.playAnimation();
                break;
            case 3:
                //img_check = (LottieAnimationView) mView.findViewById(R.id.anim_cursor3);
                img_cursor2.setAnimation("bounce_heart.json");
                img_check.setAnimation("heart_up.json");
                img_check.setScaleX(4);
                img_check.setScaleY(4);
                img_cursor2.playAnimation();
                break;
            case 4:
                break;
        }

        txtWrong = (TextView)mView.findViewById(R.id.txtWarning);
        txtWrong2 = (TextView)mView.findViewById(R.id.txtWarning2);
        wrongTrigger = 0;
        isFreeze = false;
        wm.addView(mView, params);

        if(!noAd) {
            MobileAds.initialize(this, getString(R.string.admob_app_id));
            mAdView = mView.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading
                //Log.d("ADs", "onAdLoaded");
            }
            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                //Log.d("ADs", "onAdOpened");
            }
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                //Log.d("ADs", "onAdClicked");
            }
            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        //Log.d("Activity", "beforeSettingActivity!");
    }

    class TouchListener implements View.OnTouchListener{
        public boolean onTouch(View v, MotionEvent motionEvent) {

            int parentWidth = ((ViewGroup)v.getParent().getParent()).getWidth();    // 부모 View 의 Width
            int parentHeight = ((ViewGroup)v.getParent().getParent()).getHeight();    // 부모 View 의 Height

            if(!isFreeze) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        ChangeSelectedBtnImg(v, true);

                        img_cursor2.setX(motionEvent.getRawX() - img_cursor2.getWidth() / 2);
                        img_cursor2.setY(motionEvent.getRawY() - (img_cursor2.getHeight() * 2) / 3);
                        img_cursor2.setVisibility(View.VISIBLE);
                        //animator.start();
                        switch (v.getId()) {
                            case R.id.img1:
                                inputNum = "a";
                                break;
                            case R.id.img2:
                                inputNum = "b";
                                break;
                            case R.id.img3:
                                inputNum = "c";
                                break;
                            case R.id.img4:
                                inputNum = "d";
                                break;
                            case R.id.img5:
                                inputNum = "";
                                break;
                        }

                        break;
                    case MotionEvent.ACTION_MOVE:
                        img_cursor2.setY(v.getY() + (motionEvent.getY()) - (img_cursor2.getHeight() / 2));

                        float p = 0.0009f;
                        float tmpY = img_cursor2.getY() - (parentHeight / 20 * 12);
                        //float tmp = img_cursor2.getY()-parentHeight/2;
                        img_cursor2.setX(p * tmpY * tmpY + (parentWidth / 5 * 3));

                        break;
                    case MotionEvent.ACTION_UP:

                        ChangeSelectedBtnImg(v, false);

                        if (img_cursor2.getY() < ScreenSize.y / 10 * 4) {
                            switch (inputNum) {
                                case "":
                                    inputNum = "h";
                                    break;
                                case "a":
                                    inputNum = "";
                                    break;
                                case "b":
                                    inputNum = "e";
                                    break;
                                case "c":
                                    inputNum = "f";
                                    break;
                                case "d":
                                    inputNum = "g";
                                    break;
                            }

                            if (inputNum != "")
                                enterInput(inputNum);

                        } else if (img_cursor2.getY() > ScreenSize.y / 5 * 4) {
                            if (inputNum != "")
                                enterInput(inputNum);
                        }

                        v.setVisibility(View.VISIBLE);
                        img_cursor2.setVisibility(View.INVISIBLE);


                        break;
                    case MotionEvent.ACTION_CANCEL:

                        break;
                }
            }
            return true;
        }
    }

    void ChangeSelectedBtnImg(View v, Boolean selected){
        if(selected){
            switch(theme){
                case 1:
                    v.setBackgroundResource(R.drawable.moon_selected2);
                    break;
                case 2:
                    //v.setBackgroundResource(R.drawable.star2_selected);
                    v.setBackgroundResource(R.drawable.star3_selected);
                    break;
                case 3:
                    v.setBackgroundResource(R.drawable.bauble_selected);
                    break;
                case 4:
                    break;
                case 5:
                    v.setBackgroundResource(R.drawable.snowflake3_selected);
                    break;
                case 6:
                    break;
                case 7:
                    v.setBackgroundResource(R.drawable.snowflake2_selected);
                    break;
                case 8:
                    break;
                case 9:
                    v.setBackgroundResource(R.drawable.clover_cute_selected);
                    break;
                case 10:
                    v.setBackgroundResource(R.drawable.moon_selected2);
                    break;
                case 11:
                    v.setBackgroundResource(R.drawable.shy_heart);
                    break;
                case 12:
                    v.setBackgroundResource(R.drawable.gold_broken_bone);
                    break;
                case 13:
                    v.setBackgroundResource(R.drawable.chocoby_open);
                    break;
            }
        }else{
            switch(theme){
                case 1:
                    mImg1.setBackgroundResource(R.drawable.moon_100);
                    mImg2.setBackgroundResource(R.drawable.moon_75);
                    mImg3.setBackgroundResource(R.drawable.moon_50);
                    mImg4.setBackgroundResource(R.drawable.moon_25);
                    mImg5.setBackgroundResource(R.drawable.moon_0);
                    break;
                case 2:
                    //v.setBackgroundResource(R.drawable.star2);
                    v.setBackgroundResource(R.drawable.star3);
                    break;
                case 3:
                    v.setBackgroundResource(R.drawable.bauble);
                    break;
                case 4:
                    break;
                case 5:
                    v.setBackgroundResource(R.drawable.snowflake3);
                    break;
                case 6:
                    break;
                case 7:
                    v.setBackgroundResource(R.drawable.snowflake2);
                    break;
                case 8:
                    break;
                case 9:
                    v.setBackgroundResource(R.drawable.clover_cute_comp);
                    break;
                case 10:
                    mImg1.setBackgroundResource(R.drawable.moon_100);
                    mImg2.setBackgroundResource(R.drawable.moon_75);
                    mImg3.setBackgroundResource(R.drawable.moon_50);
                    mImg4.setBackgroundResource(R.drawable.moon_25);
                    mImg5.setBackgroundResource(R.drawable.moon_0);
                    break;
                case 11:
                    v.setBackgroundResource(R.drawable.shy_heart_normal);
                    break;
                case 12:
                    v.setBackgroundResource(R.drawable.blue_bone);
                    break;
                case 13:
                    v.setBackgroundResource(R.drawable.chocoby);
                    break;
            }
        }

    }

    void CheckPW() {

        //Log.d("input", "Check Pw");

        boolean wrongPw = false;

        //Log.d("input", "INPUT PW2 = " + inputPw);
        //Log.d("input", "REAL PW = " + pw);

        if(!inputPw.equals(pw))
            wrongPw = true;

        //Log.d("input", "WRONG PW ? = " + wrongPw);
        //Log.d("input", "SIZE ? = " + inputPw.length() + "/"+pw.length());

        if(wrongPw){
            if(theme==9)
                img_input.setImageResource(R.drawable.eyes_wrong2);
            txtWrong.setVisibility(View.VISIBLE);
            if(wrongTrigger>=5){
                freezeScreen();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        unFreezeScreen();
                        incorrect();
                    }
                }, 10000);    //10초 뒤에
            }else{
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        incorrect();
                    }
                }, 200);    //0.2초 뒤에
            }
            wrongPw = false;
            return;
        }else{
            //Log.d("input", "unlock");
            Unlock();
        }

    }

    void Unlock(){
        finishAffinity();
    }

    void enableBtns(){
        mImg1.setAlpha(1f);
        mImg2.setAlpha(1f);
        mImg3.setAlpha(1f);
        mImg4.setAlpha(1f);
        mImg5.setAlpha(1f);
    }

    void disableBtns(){
        mImg1.setAlpha(0f);
        mImg2.setAlpha(0f);
        mImg3.setAlpha(0f);
        mImg4.setAlpha(0f);
        mImg5.setAlpha(0f);
    }

    void freezeScreen(){
        isFreeze = true;
        txtWrong2.setVisibility(View.VISIBLE);
        disableBtns();
    }

    void unFreezeScreen(){
        isFreeze = false;
        txtWrong2.setVisibility(View.INVISIBLE);
        enableBtns();
    }

    void incorrect(){

        inputPw = "";
        if(pwSize==2) {
            if(theme==9){
                img_input.setImageResource(R.drawable.eyes_0);
            }else if(theme==12) {
                img_input.setImageResource(R.drawable.paw_yellow_2pw0);
            }else if(theme==14){
                img_input.setImageResource(R.drawable.crayon_4pw0);
            }
            else {
                img_input.setImageResource(R.drawable.input_2pw0);
            }
        }else if(pwSize==4){
            if(theme==9){
                img_input.setImageResource(R.drawable.eyes_0);
            }else if(theme==12){
                img_input.setImageResource(R.drawable.paw_yellow_4pw0);
            }else if(theme==14){
                img_input.setImageResource(R.drawable.crayon_4pw0);
            } else {
                img_input.setImageResource(R.drawable.input_4pw0);
            }
        }
        //txtWrong.setVisibility(View.INVISIBLE);
        wrongTrigger++;
        txtWrong.setText(wrongTrigger+" 회 오류!");
    }

    void enterInput(String str){
        inputPw+=str;
        img_check.setVisibility(View.VISIBLE);
        img_check.setX(img_cursor2.getX());
        img_check.setY(img_cursor2.getY());
        if(effTheme == 3){
            img_check.setY(img_check.getY()-100f);
        }
        animator.start();
        //Log.d("Input2", "INPUT : "+inputPw);

        if(pwSize==2) {
            if (inputPw.length() == 1) {
                if(theme==9)
                    img_input.setImageResource(R.drawable.eyes_2);
                else if(theme==12)
                    img_input.setImageResource(R.drawable.paw_yellow_2pw1);
                else if(theme==14)
                    img_input.setImageResource(R.drawable.crayon_2pw1);
                else
                    img_input.setImageResource(R.drawable.input_2pw1);
            } else if (inputPw.length() == 2) {
                if(theme==9)
                    img_input.setImageResource(R.drawable.eyes_4);
                else if(theme==12)
                    img_input.setImageResource(R.drawable.paw_yellow_2pw2);
                else if(theme==14)
                    img_input.setImageResource(R.drawable.crayon_2pw2);
                else
                    img_input.setImageResource(R.drawable.input_2pw2);
            } else {
                if(theme==9)
                    img_input.setImageResource(R.drawable.eyes_0);
                else if(theme==12)
                    img_input.setImageResource(R.drawable.paw_yellow_2pw0);
                else if(theme==14)
                    img_input.setImageResource(R.drawable.crayon_4pw0);
                else
                    img_input.setImageResource(R.drawable.input_2pw0);
            }
        }else {
            switch (inputPw.length()) {
                case 0:
                    if(theme==9)
                        img_input.setImageResource(R.drawable.eyes_0);
                    else if(theme==12)
                        img_input.setImageResource(R.drawable.paw_yellow_4pw0);
                    else if(theme==14)
                        img_input.setImageResource(R.drawable.crayon_4pw0);
                    else
                        img_input.setImageResource(R.drawable.input_4pw0);
                    break;
                case 1:
                    if(theme==9)
                        img_input.setImageResource(R.drawable.eyes_1);
                    else if(theme==12)
                        img_input.setImageResource(R.drawable.paw_yellow_4pw1);
                    else if(theme==14)
                        img_input.setImageResource(R.drawable.crayon_4pw1);
                    else
                        img_input.setImageResource(R.drawable.input_4pw1);
                    break;
                case 2:
                    if(theme==9)
                        img_input.setImageResource(R.drawable.eyes_2);
                    else if(theme==12)
                        img_input.setImageResource(R.drawable.paw_yellow_4pw2);
                    else if(theme==14)
                        img_input.setImageResource(R.drawable.crayon_4pw2);
                    else
                        img_input.setImageResource(R.drawable.input_4pw2);
                    break;
                case 3:
                    if(theme==9)
                        img_input.setImageResource(R.drawable.eyes_3);
                    else if(theme==12)
                        img_input.setImageResource(R.drawable.paw_yellow_4pw3);
                    else if(theme==14)
                        img_input.setImageResource(R.drawable.crayon_4pw3);
                    else
                        img_input.setImageResource(R.drawable.input_4pw3);
                    break;
                case 4:
                    if(theme==9)
                        img_input.setImageResource(R.drawable.eyes_4);
                    else if(theme==12)
                        img_input.setImageResource(R.drawable.paw_yellow_4pw4);
                    else if(theme==14)
                        img_input.setImageResource(R.drawable.crayon_4pw4);
                    else
                        img_input.setImageResource(R.drawable.input_4pw4);
                    break;
            }
        }

        if(inputPw.length()==pw.length())
            CheckPW();
    }

    @Override
    public void onDestroy(){
        wm.removeViewImmediate(mView);
        finishAffinity();

        super.onDestroy();
    }


    @Override
    protected void onStop(){
        super.onStop();
        //finishAffinity();
    }
}
