package com.diallock.slidediallock;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;

import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import androidx.appcompat.app.AlertDialog;

public class pwSettingActivity extends Activity {

    private Integer pwSize;

    private String inputNum;
    private String inputPw;

    private String tmp_inputPw;

    //private LinearLayout bg_screen;

    private boolean isCheck;

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

    private Intent intent;

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

        setContentView(R.layout.activity_draglock);

        ScreenSize = getScreenSize(this);

        SharedPreferences sf = getSharedPreferences("sFile",MODE_PRIVATE);
        pwSize = sf.getInt("tmpPwSize", 2);
        isCheck = sf.getBoolean("isCheck", false);

        inputPw = "";

        img_input = (ImageView) findViewById(R.id.input_img);
        if(pwSize==2) {
            img_input.setImageResource(R.drawable.input_2pw0);
            tmp_inputPw = sf.getString("tmpPw", "bb");
        }
        else {
            img_input.setImageResource(R.drawable.input_4pw0);
            tmp_inputPw = sf.getString("tmpPw", "0000");
        }

        mImg1 = (ImageView) findViewById(R.id.img1);
        mImg1.setTag(IMAGEVIEW_TAG);
        mImg2 = (ImageView) findViewById(R.id.img2);
        mImg2.setTag(IMAGEVIEW_TAG);
        mImg3 = (ImageView) findViewById(R.id.img3);
        mImg3.setTag(IMAGEVIEW_TAG);
        mImg4 = (ImageView) findViewById(R.id.img4);
        mImg4.setTag(IMAGEVIEW_TAG);
        mImg5 = (ImageView) findViewById(R.id.img5);
        mImg5.setTag(IMAGEVIEW_TAG);

        img_cursor = (ImageView) findViewById((R.id.i_cursor));
        img_cursor.setTag(IMAGEVIEW_TAG);

        img_cursor2 = (LottieAnimationView) findViewById(R.id.anim_cursor);
        img_cursor2.setTag(IMAGEVIEW_TAG);

        img_check = (LottieAnimationView) findViewById(R.id.anim_cursor2);
        img_check.setTag(IMAGEVIEW_TAG);

        animator = ValueAnimator.ofFloat(0f, 1f)
                .setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                img_check.setProgress((Float) animation.getAnimatedValue());
            }
        });

        mImg1.setOnTouchListener(new pwSettingActivity.TouchListener());
        mImg1.setClickable(true);
        mImg2.setOnTouchListener(new pwSettingActivity.TouchListener());
        mImg2.setClickable(true);
        mImg3.setOnTouchListener(new pwSettingActivity.TouchListener());
        mImg3.setClickable(true);
        mImg4.setOnTouchListener(new pwSettingActivity.TouchListener());
        mImg4.setClickable(true);
        mImg5.setOnTouchListener(new pwSettingActivity.TouchListener());
        mImg5.setClickable(true);

        if(isCheck){

            //Log.d("check", "AlertDialog");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("패스워드 재확인").setMessage("패스워드를 재입력해주세요.");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int id)
                {
                }
            });

            AlertDialog alertDialog = builder.create();

            alertDialog.show();
        }
    }

    class TouchListener implements View.OnTouchListener{
        public boolean onTouch(View v, MotionEvent motionEvent) {

            int parentWidth = ((ViewGroup)v.getParent().getParent()).getWidth();    // 부모 View 의 Width
            int parentHeight = ((ViewGroup)v.getParent().getParent()).getHeight();    // 부모 View 의 Height

            switch(motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN :

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

                    if(img_cursor2.getY() < ScreenSize.y/10*4 ){
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

    void enterInput(String str){
        inputPw+=str;
        img_check.setVisibility(View.VISIBLE);
        img_check.setX(img_cursor2.getX());
        img_check.setY(img_cursor2.getY());
        animator.start();
        //Log.d("Input2", "INPUT : "+inputPw);

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

        if(inputPw.length()==pwSize)
            pwConfirm();
    }

    void pwConfirm(){

        //Log.d("check", "isCheck : " + isCheck);

        if(isCheck) {
            //editor.putString("tmpPw", inputPw);
            CheckInputPw();     //  확인이니까 비밀번호 일치여부 확인

        }
        else {
            // 처음 입력이니깐 확인단계로 넘어가
            // 다시 (현Activity) 재시작
            SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isCheck", true);
            editor.putString("tmpPw", inputPw);
            editor.commit();

            //Log.d("check", "restartActivity");

            intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    void CheckInputPw(){
        boolean wrongPw = false;

        //Log.d("input", "INPUT PW2 = " + inputPw);
        //Log.d("input", "REAL PW = " + pw);

        if(!inputPw.equals(tmp_inputPw))
            wrongPw = true;

        SharedPreferences sf = getSharedPreferences("sFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();

        editor.putBoolean("isCheck", false);
        editor.commit();

        if(wrongPw){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("패스워드 오류").setMessage("패스워드가 일치하지 않습니다.\n패스워드를 재입력해주세요.");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int id)
                {
                    intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });

            AlertDialog alertDialog = builder.create();

            alertDialog.show();


        }else{

            intent = new Intent(
                    getApplicationContext(),//현재제어권자
                    SettingsActivity.class); // 이동할 컴포넌트
            finish();
            startActivity(intent);
        }

    }

    @Override
    protected void onStop(){
        super.onStop();
        //finishAffinity();
    }
}
