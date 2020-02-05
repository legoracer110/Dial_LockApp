package com.diallock.slidediallock;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceManager;


public class SettingsActivity extends Activity  {

    private boolean powerOn;
    private String pw;
    private Integer pwSize;
    private Integer theme;
    private Integer effTheme;

    private Button btnConfirm;

    // Google AdMob 전면광고
    private InterstitialAd mInterstitialAd;
    private boolean removeAd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Log.d("Activity", "Setting Run!");
        //setContentView(R.layout.temp);
        //addPreferencesFromResource(R.xml.setting_preference);

        //setContentView(R.layout.setting_fragment_test);

        setContentView(R.layout.activity_settings_re);

        btnConfirm = (Button)findViewById(R.id.btnSave);
        btnConfirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                confirmSettings();
            }
        });

        AppStorage storage = new AppStorage(this);
        removeAd = storage.purchasedRemoveAds();
        if(!removeAd) {
            MobileAds.initialize(this, getString(R.string.admob_app_id));
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id_for_test));
            mInterstitialAd.loadAd(new AdRequest.Builder().build());

        }


//        mInterstitialAd.setAdListener(new AdListener() {
//            public void onAdLoaded(){
//                if (mInterstitialAd.isLoaded()) {
//                    mInterstitialAd.show();
//                } else {
//                    Log.d("asd", "The interstitial wasn't loaded yet.");
//                }
//            }
//        });

        /*
        btn_no_ads = (Button)findViewById(R.id.btn_no_ads);
        btn_no_ads.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //v.startAnimation(Animator.getClickAnimation(this));
                checkNoAds();
            }
        });
        */

        getFragmentManager().beginTransaction()
                .replace(R.id.content, new SettingsFragment())
                .commit();


    }

    public static class SettingsFragment extends PreferenceFragment {
        public SettingsFragment() {
        }

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.setting_preference);

            /*
            Preference pf = (Preference)findPreference("pf_no_ads");
            pf.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
                @Override
                public boolean onPreferenceClick(Preference preference){
                    Log.d("PREFERENCE_TEST", "PF CLICKED");
                    return false;
                }
            });
            */
        }
    }

    public void changePwSize(){
        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Log.d("sf", "changed!:"+pwSize);

        editor.putInt("tmpPwSize", pwSize);
        editor.commit();
    }

    public void confirmSettings(){

        //Log.d("test", "confirmed!");

        SharedPreferences prefs =  PreferenceManager.getDefaultSharedPreferences( this );
        SharedPreferences sf = getSharedPreferences("sFile",MODE_PRIVATE);

        powerOn = prefs.getBoolean("isLock", true);
        String str_pwSize = prefs.getString("tmpPwSize", "0");
        pwSize = Integer.parseInt(str_pwSize);

        pw = sf.getString("tmpPw", "0000");
        theme = sf.getInt("tmpTheme", 10);
        effTheme = sf.getInt("tmpEffTheme", 3);

        Toast myToast;

        if(pw.equals("0000")){
            myToast = Toast.makeText(this.getApplicationContext(), "패스워드를 설정해주세요", Toast.LENGTH_SHORT);
        }else {
            SharedPreferences.Editor editor = sf.edit();

            editor.putBoolean("isLock", powerOn);
            editor.putString("pwList", pw);
            editor.putInt("pwSize", pwSize);
            editor.putInt("tmpPwSize", pwSize);
            editor.putInt("theme", theme);
            editor.putInt("effTheme", effTheme);

            editor.commit();

            myToast = Toast.makeText(this.getApplicationContext(), "패스워드 설정이 변경되었습니다", Toast.LENGTH_SHORT);
        }
        myToast.show();

        if(!removeAd && mInterstitialAd.isLoaded())
            mInterstitialAd.show();
        //finish();
    }

    @Override
    protected void onStop(){

        super.onStop();
    }

    @Override
    public void onDestroy(){
        if(!removeAd && mInterstitialAd.isLoaded())
            mInterstitialAd.show();

        super.onDestroy();
    }
}
