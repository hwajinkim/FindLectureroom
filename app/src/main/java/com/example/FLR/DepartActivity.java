package com.example.FLR;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.FLR.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DepartActivity extends AppCompatActivity {

    private TextView mDname,mDtel1,mDtel2,mDtel3,mDroom,mDstart,mDend;
    private TextView mTextViewResult;
    private static String TAG = "laststudent_db";
    private String mJsonString;//json문자열을 저장할 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depart);

        mDname = (TextView)findViewById(R.id.Dname_text);
        mDtel1 = (TextView)findViewById(R.id.Dtel1_text);
        mDtel2 = (TextView)findViewById(R.id.Dtel2_text);
        mDtel3 = (TextView)findViewById(R.id.Dtel3_text);
        mDroom = (TextView)findViewById(R.id.Droomnum_text);
        mDstart = (TextView)findViewById(R.id.Dstart_text);
        mDend= (TextView)findViewById(R.id.Dend_text);

        mTextViewResult = (TextView) findViewById(R.id.textView_main_result);// 디버깅 결과 창을 나타내는 mTextViewResult 변수에 레이아웃 아이템 inflate
        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());//디버깅 결과를 스크롤 해주는 함수를 호출

        String result_depart = ((SecondActivity) SecondActivity.context_second).result_depart;
        String errorString = null;
        Log.d("result_choi", result_depart);
        mTextViewResult.setText(result_depart);
        if (result_depart == null) { //받아온 json값이 널이면

            mTextViewResult.setText(errorString);   //디버깅 결과 창에 에러문자열로 세팅
        } else {  // 받아온 json값이 널이 아니면

            mJsonString = result_depart; //json 값을 mJsonString 변수에 넣고
            Log.d("mJsonString", mJsonString);

            showResult(); //결과 값을 출력하는 함수 호출
        }
    }

    private void showResult() {
        String TAG_JSON = "Department";          //Student에 저장되어 있는 값들(전체 값)을 TAG_JSON 변수에 저장
        String TAG_DNAME = "Dname";     //Snumber로 저장되어 있는 값들(전체 값)을 TAG_SNUMBER 변수에 저장
        String TAG_DTEL1 = "Dtel1";     //Rnumber로 저장되어 있는 값들(전체 값)을 TAG_RNUMBER 변수에 저장
        String TAG_DTEL2 = "Dtel2";     //Rnumber로 저장되어 있는 값들(전체 값)을 TAG_RNUMBER 변수에 저장
        String TAG_DTEL3 = "Dtel3";     //Rnumber로 저장되어 있는 값들(전체 값)을 TAG_RNUMBER 변수에 저장
        String TAG_DROOMNUM = "Droomnum";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_DSTART = "Dstart";          //Stime로 저장되어 있는 값들(전체 값)을 TAG_STIME 변수에 저장
        String TAG_DEND = "Dend";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장



        //JSONObject JSON형태의 데이터 관리
        try {
            JSONObject jsonObject = new JSONObject(mJsonString); //json값을 저장하고 있는 변수(mJsonString)로 jsonObject 객체를 초기화
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);//Student에 저장되어 있는 값들(전체 값)을 jsonArray에 넣음

            for (int i = 0; i < jsonArray.length(); i++) {//student에 저장되어 있는 값만큼 반복

                JSONObject item = jsonArray.getJSONObject(i);//jsonArray에서 JSONObject값을 배열 값들 수만큼 반복하면서 가져와 item에 저장

                String Dname = item.getString(TAG_DNAME);//item에서 TAG_SNUMBER(Snumber)값만 가져와 Snumber 변수에 넣는다.
                String Dtel1 = item.getString(TAG_DTEL1);//item에서 TAG_RNUMBER(Rnumber)값만 가져와 Rnumber 변수에 넣는다.
                String Dtel2 = item.getString(TAG_DTEL2);//item에서 TAG_RNUMBER(Rnumber)값만 가져와 Rnumber 변수에 넣는다.
                String Dtel3 = item.getString(TAG_DTEL3);//item에서 TAG_RNUMBER(Rnumber)값만 가져와 Rnumber 변수에 넣는다.
                String Droomnum = item.getString(TAG_DROOMNUM);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Dstart = item.getString(TAG_DSTART);//item에서 TAG_STIME(Stime)값만 가져와 Stime 변수에 넣는다.
                String Dend = item.getString(TAG_DEND);//item에서 TAG_ETIME(Etime)값만 가져와 Etime 변수에 넣는다.

                PersonalData personalData = new PersonalData(); //PersonalData 클래스 대해 personalData 객체를 만든다.

                personalData.setDepartment_Dname(Dname);//personalData 객체의 setStudent_Etime메소드를 호출하여 매개변수로 Etime값을 전달한다.
                personalData.setDepartment_Dtel1(Dtel1);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setDepartment_Dtel2(Dtel2);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setDepartment_Dtel3(Dtel3);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setDepartment_Droomnum(Droomnum);//personalData 객체의 setStudent_Sub메소드를 호출하여 매개변수로 Sub값을 전달한다.
                personalData.setDepartment_Dstart(Dstart);//personalData 객체의 setStudent_Stime메소드를 호출하여 매개변수로 Stime값을 전달한다.
                personalData.setDepartment_Dend(Dend);//personalData 객체의 setStudent_Etime메소드를 호출하여 매개변수로 Etime값을 전달한다.


                mDname.setText(personalData.getDepartment_Dname());
                mDtel1.setText(personalData.getDepartment_Dtel1());
                mDtel2.setText(personalData.getDepartment_Dtel2());
                mDtel3.setText(personalData.getDepartment_Dtel3());
                mDroom.setText(personalData.getDepartment_Droomnum());
                mDstart.setText(personalData.getDepartment_Dstart());
                mDend.setText(personalData.getDepartment_Dend());

            }

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }
}