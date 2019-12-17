package com.example.slidediallock;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

import static android.R.color.holo_green_dark;

public class DialLockActivity extends Activity {

    private String pw;

    private Integer pwSize;

    private String inputNum;
    private String inputPw;

    //private LinearLayout bg_screen;
    private ImageView bg_screen;

    // 테마 저장 변수 (인덱스)
    private Integer btnTheme;
    private Integer bgTheme;
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

    WindowManager wm;
    View mView;
    Vibrator vibrator;

    ////////////// 해상도 구하기 ////////////////
    int standardSize_X, standardSize_Y;
    float density;

    Point ScreenSize;

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

        mView = inflate.inflate(R.layout.activity_draglock, null);
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
        pw = sf.getString("pwList", "bfha");
        //pw = "bfha";
        pwSize = sf.getInt("pwSize", 4);
        //pwSize = pw.length();

        btnTheme = sf.getInt("btnTheme", 4);
        bgTheme = sf.getInt("bgTheme", 5);
        effTheme = sf.getInt("effTheme", 1);

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


        switch(btnTheme){
            case 1:
                mImg1.setBackgroundResource(R.drawable.moon_100);
                mImg2.setBackgroundResource(R.drawable.moon_75);
                mImg3.setBackgroundResource(R.drawable.moon_50);
                mImg4.setBackgroundResource(R.drawable.moon_25);
                mImg5.setBackgroundResource(R.drawable.moon_0);
                break;
            case 2:
                mImg1.setBackgroundResource(R.drawable.star_neon);
                mImg2.setBackgroundResource(R.drawable.star_neon);
                mImg3.setBackgroundResource(R.drawable.star_neon);
                mImg4.setBackgroundResource(R.drawable.star_neon);
                mImg5.setBackgroundResource(R.drawable.star_neon);
                break;
            case 3:
                mImg1.setBackgroundResource(R.drawable.snowflake);
                mImg2.setBackgroundResource(R.drawable.snowflake);
                mImg3.setBackgroundResource(R.drawable.snowflake);
                mImg4.setBackgroundResource(R.drawable.snowflake);
                mImg5.setBackgroundResource(R.drawable.snowflake);
                break;
            case 4:
                mImg1.setBackgroundResource(R.drawable.dot_inside_a_circle);
                mImg2.setBackgroundResource(R.drawable.dot_inside_a_circle);
                mImg3.setBackgroundResource(R.drawable.dot_inside_a_circle);
                mImg4.setBackgroundResource(R.drawable.dot_inside_a_circle);
                mImg5.setBackgroundResource(R.drawable.dot_inside_a_circle);
                break;
            case 5:
                mImg1.setBackgroundResource(R.drawable.clover_cute_comp);
                mImg2.setBackgroundResource(R.drawable.clover_cute_comp);
                mImg3.setBackgroundResource(R.drawable.clover_cute_comp);
                mImg4.setBackgroundResource(R.drawable.clover_cute_comp);
                mImg5.setBackgroundResource(R.drawable.clover_cute_comp);
                break;
        }

        switch(bgTheme){
            case 1:
                bg_screen.setBackgroundResource(R.drawable.orora);
                break;
            case 2:
                bg_screen.setBackgroundResource(R.drawable.neon);
                break;
            case 3:
                bg_screen.setBackgroundResource(R.drawable.frozen);
                break;
            case 4:
                bg_screen.setBackgroundResource(R.drawable.frozen4);
                break;
            case 5:
                bg_screen.setBackgroundColor(getResources().getColor(holo_green_dark));
                //bg_screen.setBackgroundResource(R.drawable.bg_green2);
                img_input.setImageResource(R.drawable.eyes_0);
                break;
            case 6:
                break;
            case 7:
                bg_screen.setBackgroundResource(0);
                break;
            case 8:
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
                img_cursor2.setAnimation("circle_anim_yellow.json");
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

        //bg_screen.setBackgroundColor(getResources().getColor(android.R.color.black));

        /*
        customBgImg = sf.getBoolean("customBgImg", false);
        customBgImgPath = sf.getString("pwImgPath","");

        if(customBgImg){

            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap originalBm = BitmapFactory.decodeFile(customBgImgPath, options);

            Drawable drawable = new BitmapDrawable(originalBm);
            //drawable.setAlpha(30);
            bg_screen.setBackground(drawable);

        }else {
            //Drawable originbg = getResources().getDrawable(R.drawable.sample_bg);
            //originbg.setAlpha(30);
            //bg_screen.setBackgroundResource(R.drawable.sample_bg);
            bg_screen.setBackgroundColor(getResources().getColor(android.R.color.black));
            //bg_screen.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
        */

        wm.addView(mView, params);

        Log.d("Activity", "beforeSettingActivity!");
    }

    class TouchListener implements View.OnTouchListener{
        public boolean onTouch(View v, MotionEvent motionEvent) {

            int parentWidth = ((ViewGroup)v.getParent().getParent()).getWidth();    // 부모 View 의 Width
            int parentHeight = ((ViewGroup)v.getParent().getParent()).getHeight();    // 부모 View 의 Height

            switch(motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN :

                    ChangeSelectedBtnImg(v, true);

                    img_cursor2.setX(motionEvent.getRawX()-img_cursor2.getWidth()/2);
                    img_cursor2.setY(motionEvent.getRawY()-(img_cursor2.getHeight()*2)/3);
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
                    img_cursor2.setY(v.getY() + (motionEvent.getY()) - (img_cursor2.getHeight()/2));

                    float p = 0.0009f;
                    float tmpY = img_cursor2.getY()-(parentWidth/10*11);
                    //float tmp = img_cursor2.getY()-parentHeight/2;
                    img_cursor2.setX(p*tmpY*tmpY+(parentWidth/5*3));

                    break;
                case MotionEvent.ACTION_UP:

                    ChangeSelectedBtnImg(v, false);

                    if(img_cursor2.getY() < ScreenSize.y/10*4){
                        switch(inputNum){
                            case "" : inputNum = "h"; break;
                            case "a" : inputNum =""; break;
                            case "b" : inputNum ="e"; break;
                            case "c" : inputNum ="f"; break;
                            case "d" : inputNum ="g"; break;
                        }

                        if(inputNum!="")
                            enterInput(inputNum);

                    }else if(img_cursor2.getY() > ScreenSize.y/5*4){
                        if(inputNum!="")
                            enterInput(inputNum);
                    }

                    v.setVisibility(View.VISIBLE);
                    img_cursor2.setVisibility(View.INVISIBLE);


                    break;
                case MotionEvent.ACTION_CANCEL:

                    break;
            }
            return true;
        }
    }

    void ChangeSelectedBtnImg(View v, Boolean selected) {
        if (selected) {
            switch (btnTheme) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    v.setBackgroundResource(R.drawable.clover_cute_selected);
                    break;
            }
        } else {
            switch (btnTheme) {
                case 1:
                    mImg1.setBackgroundResource(R.drawable.moon_100);
                    mImg2.setBackgroundResource(R.drawable.moon_75);
                    mImg3.setBackgroundResource(R.drawable.moon_50);
                    mImg4.setBackgroundResource(R.drawable.moon_25);
                    mImg5.setBackgroundResource(R.drawable.moon_0);
                    break;
                case 2:
                    v.setBackgroundResource(R.drawable.star_neon);
                    break;
                case 3:
                    v.setBackgroundResource(R.drawable.snowflake);
                    break;
                case 4:
                    v.setBackgroundResource(R.drawable.dot_inside_a_circle);
                    break;
                case 5:
                    v.setBackgroundResource(R.drawable.clover_cute_comp);
                    break;
            }
        }
    }

    void CheckPW() {

        Log.d("input", "Check Pw");

        boolean wrongPw = false;

        Log.d("input", "INPUT PW2 = " + inputPw);
        Log.d("input", "REAL PW = " + pw);

        if(!inputPw.equals(pw))
            wrongPw = true;

        Log.d("input", "WRONG PW ? = " + wrongPw);
        Log.d("input", "SIZE ? = " + inputPw.length() + "/"+pw.length());

        if(wrongPw){
            if(bgTheme==5)
                img_input.setImageResource(R.drawable.eyes_wrong2);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    incorrect();
                }
            }, 200);    //0.2초 뒤에
            wrongPw = false;
            return;
        }else{
            Log.d("input", "unlock");
            Unlock();
        }

    }

    void Unlock(){
        finishAffinity();
    }

    void incorrect(){

        inputPw = "";
        if(pwSize==2) {
            img_input.setImageResource(R.drawable.input_2pw0);
        }else if(pwSize==4){
            img_input.setImageResource(R.drawable.input_4pw0);
        }
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
        Log.d("Input2", "INPUT : "+inputPw);

        if(pwSize==2) {
            if (inputPw.length() == 1) {
                img_input.setImageResource(R.drawable.input_2pw1);
            } else if (inputPw.length() == 2) {
                img_input.setImageResource(R.drawable.input_2pw2);
            } else {
                img_input.setImageResource(R.drawable.input_2pw0);
            }
        }else {
            switch (inputPw.length()) {
                case 0:
                    img_input.setImageResource(R.drawable.input_4pw0);
                    break;
                case 1:
                    img_input.setImageResource(R.drawable.input_4pw1);
                    break;
                case 2:
                    img_input.setImageResource(R.drawable.input_4pw2);
                    break;
                case 3:
                    img_input.setImageResource(R.drawable.input_4pw3);
                    break;
                case 4:
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
