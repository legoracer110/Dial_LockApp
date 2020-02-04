package com.diallock.slidediallock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class InAppPayActivitiy extends Activity implements BillingProcessor.IBillingHandler {

    // Google 인앱 결제 처리 객체
    private BillingProcessor bp;
    private AppStorage storage;

    private Button ok_btn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_no_ads);
        //Log.d("NO_ADS", "결제 액티비티 호출");

        storage = new AppStorage(this);
        bp = new BillingProcessor(this, getString(R.string.application_license_key), this);
        bp.initialize();

        ok_btn = (Button)findViewById(R.id.btn_ok);
        ok_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checkNoAds();
            }
        });
    }

    // 광고 없이 이용하기
    void checkNoAds(){

        //Log.d("NO_ADS", "결제 확인창 호출");

        //v.startAnimation(Animator.getClickAnimation(this));
        if (storage.purchasedRemoveAds()) {
            // TODO: 이미 구매하셨습니다. 메세지 띄우기!
        } else {
            bp.purchase(this, getString(R.string.sku));
        }
        finish();
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        // 이 메소드는 구매 '성공'시에만 호출된다.
        if (productId.equals(getString(R.string.sku))) {
            // TODO: 구매 해 주셔서 감사합니다! 메세지 보내기
            Toast myToast = Toast.makeText(this.getApplicationContext(), "구매 해 주셔서 감사합니다!", Toast.LENGTH_SHORT);
            storage.setPurchasedRemoveAds(bp.isPurchased(getString(R.string.sku)));

            // * 광고 제거는 1번 구매하면 영구적으로 사용하는 것이므로 consume하지 않지만,
            // 만약 게임 아이템 100개를 주는 것이라면 아래 메소드를 실행시켜 다음번에도 구매할 수 있도록 소비처리를 해줘야한다.
            // bp.consumePurchase(Config.Sku);
        }
        finish();
    }

    @Override
    public void onPurchaseHistoryRestored() {
        storage.setPurchasedRemoveAds(bp.isPurchased(getString(R.string.sku)));
    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Toast myToast = Toast.makeText(this.getApplicationContext(), "구매 오류 발생!", Toast.LENGTH_SHORT);
//        TODO: 이런식으로 구매 오류시 오류가 발생했다고 알려주는 것도 좋다.
//        if (errorCode != Constants.BILLING_RESPONSE_RESULT_USER_CANCELED) {
//            Snackbar.make(tvRemoveAds, R.string.unknown_error, Snackbar.LENGTH_SHORT).show();
//        }
//
    }

    @Override
    public void onBillingInitialized() {
        // storage에 구매여부 저장
        storage.setPurchasedRemoveAds(bp.isPurchased(getString(R.string.sku)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }
}
