package com.example.duan_qlsach.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan_qlsach.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, DangNhapActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.transistion_manhinhchao, R.anim.transision_manhinhchao2);
                finish();
            }
        }, 3000);

    }


}
