package com.example.FLR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.FLR.R;

public class HallwayPicture extends AppCompatActivity {
    private View v;
    private ImageView window95,corridor95,two_stairs85,small_stairs85,corridor85_1,corridor85_2,center_stairs85,corridor85_3,corridor85_4;
    private ImageView hallwayPicture;
    private TextView hallwayName;
    private String corrider1="",corrider2="",corrider3="",corrider4="",corrider5="",corrider6="",corrider7="",corrider8="",corrider9="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hallway_picture);

        hallwayName = (TextView) findViewById(R.id.hallwayName);
        hallwayPicture = (ImageView) findViewById(R.id.hallwayPicture);

        Intent intent = getIntent();
        corrider1 = intent.getStringExtra("window95");
        corrider2 = intent.getStringExtra("corridor95");
        corrider3 = intent.getStringExtra("two_stairs85");
        corrider4 = intent.getStringExtra("small_stairs85");
        corrider5 = intent.getStringExtra("corridor85_1");
        corrider6 = intent.getStringExtra("corridor85_2");
        corrider7 = intent.getStringExtra("center_stairs85");
        corrider8 = intent.getStringExtra("corridor85_3");
        corrider9 = intent.getStringExtra("corridor85_4");


        if(corrider1!=null) {
            hallwayName.setText("9동 5층 (중앙)");
            hallwayPicture.setImageResource(R.drawable.window95);

        }else if(corrider2!=null){
            hallwayName.setText("9동 5층 (9501~9506)");
            hallwayPicture.setImageResource(R.drawable.corridor95);

        }else if(corrider3!=null){
            hallwayName.setText("8동 5층 (2계단 강의실)");
            hallwayPicture.setImageResource(R.drawable.two_stairs85);

        }else if(corrider4!=null){
            hallwayName.setText("8동 5층 (작은 계단)");
            hallwayPicture.setImageResource(R.drawable.small_stairs85);

        }else if(corrider5!=null){
            hallwayName.setText("8동 5층 (8511~8523)");
            hallwayPicture.setImageResource(R.drawable.corridor85_1);

        }else if(corrider6!=null){
            hallwayName.setText("8동 5층 (중앙계단 좌측)");
            hallwayPicture.setImageResource(R.drawable.corridor85_2);

        }else if(corrider7!=null){
            hallwayName.setText("8동 5층 (작은 계단)");
            hallwayPicture.setImageResource(R.drawable.center_stairs85);

        }else if(corrider8!=null){
            hallwayName.setText("8동 5층 (중앙계단)");
            hallwayPicture.setImageResource(R.drawable.corridor85_3);

        }else if(corrider9!=null){
            hallwayName.setText("8동 5층 (8524~8528 \n\t\t\t\t\t\t\t\t\t\t\t\t\t8501~8506)");
            hallwayPicture.setImageResource(R.drawable.corridor85_4);

        }



    }
}