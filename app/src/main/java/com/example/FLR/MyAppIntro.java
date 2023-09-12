package com.example.FLR;

import android.os.Bundle;

import com.example.FLR.R;
import com.github.paolorotolo.appintro.AppIntro;

public class MyAppIntro extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_app_intro);
    }
}