package com.diallock.slidediallock;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DescActivitiy extends Activity {

    private Button btn_ok;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_desc);

        btn_ok = (Button)findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
    }
}
