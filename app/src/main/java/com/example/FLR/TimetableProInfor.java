package com.example.FLR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.FLR.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TimetableProInfor extends AppCompatActivity {
    private static String TAG = "laststudent_db";
    private String mJsonString;
    private ImageView back;
    private AutoResizeTextView monday[]= new AutoResizeTextView[14];
    private AutoResizeTextView tuesday[]= new AutoResizeTextView[14];
    private AutoResizeTextView wednesday[]= new AutoResizeTextView[14];
    private AutoResizeTextView thursday[]= new AutoResizeTextView[14];
    private AutoResizeTextView friday[]= new AutoResizeTextView[14];
    private Schedule schedule = new Schedule();
    private Context context;
    private TextView mName;
    private String proName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        context = this;


        monday[0] = (AutoResizeTextView)findViewById(R.id.monday0);
        monday[1] = (AutoResizeTextView)findViewById(R.id.monday1);
        monday[2] = (AutoResizeTextView)findViewById(R.id.monday2);
        monday[3] = (AutoResizeTextView)findViewById(R.id.monday3);
        monday[4] = (AutoResizeTextView)findViewById(R.id.monday4);
        monday[5] = (AutoResizeTextView)findViewById(R.id.monday5);
        monday[6] = (AutoResizeTextView)findViewById(R.id.monday6);
        monday[7] = (AutoResizeTextView)findViewById(R.id.monday7);
        monday[8] = (AutoResizeTextView)findViewById(R.id.monday8);
        monday[9] = (AutoResizeTextView)findViewById(R.id.monday9);
        monday[10] = (AutoResizeTextView)findViewById(R.id.monday10);
        monday[11] = (AutoResizeTextView)findViewById(R.id.monday11);
        monday[12] = (AutoResizeTextView)findViewById(R.id.monday12);
        monday[13] = (AutoResizeTextView)findViewById(R.id.monday13);
        tuesday[0] = (AutoResizeTextView)findViewById(R.id.tuesday0);
        tuesday[1] = (AutoResizeTextView)findViewById(R.id.tuesday1);
        tuesday[2] = (AutoResizeTextView)findViewById(R.id.tuesday2);
        tuesday[3] = (AutoResizeTextView)findViewById(R.id.tuesday3);
        tuesday[4] = (AutoResizeTextView)findViewById(R.id.tuesday4);
        tuesday[5] = (AutoResizeTextView)findViewById(R.id.tuesday5);
        tuesday[6] = (AutoResizeTextView)findViewById(R.id.tuesday6);
        tuesday[7] = (AutoResizeTextView)findViewById(R.id.tuesday7);
        tuesday[8] = (AutoResizeTextView)findViewById(R.id.tuesday8);
        tuesday[9] = (AutoResizeTextView)findViewById(R.id.tuesday9);
        tuesday[10] = (AutoResizeTextView)findViewById(R.id.tuesday10);
        tuesday[11] = (AutoResizeTextView)findViewById(R.id.tuesday11);
        tuesday[12] = (AutoResizeTextView)findViewById(R.id.tuesday12);
        tuesday[13] = (AutoResizeTextView)findViewById(R.id.tuesday13);
        wednesday[0] = (AutoResizeTextView)findViewById(R.id.wednesday0);
        wednesday[1] = (AutoResizeTextView)findViewById(R.id.wednesday1);
        wednesday[2] = (AutoResizeTextView)findViewById(R.id.wednesday2);
        wednesday[3] = (AutoResizeTextView)findViewById(R.id.wednesday3);
        wednesday[4] = (AutoResizeTextView)findViewById(R.id.wednesday4);
        wednesday[5] = (AutoResizeTextView)findViewById(R.id.wednesday5);
        wednesday[6] = (AutoResizeTextView)findViewById(R.id.wednesday6);
        wednesday[7] = (AutoResizeTextView)findViewById(R.id.wednesday7);
        wednesday[8] = (AutoResizeTextView)findViewById(R.id.wednesday8);
        wednesday[9] = (AutoResizeTextView)findViewById(R.id.wednesday9);
        wednesday[10] = (AutoResizeTextView)findViewById(R.id.wednesday10);
        wednesday[11] = (AutoResizeTextView)findViewById(R.id.wednesday11);
        wednesday[12] = (AutoResizeTextView)findViewById(R.id.wednesday12);
        wednesday[13] = (AutoResizeTextView)findViewById(R.id.wednesday13);
        thursday[0] = (AutoResizeTextView)findViewById(R.id.thursday0);
        thursday[1] = (AutoResizeTextView)findViewById(R.id.thursday1);
        thursday[2] = (AutoResizeTextView)findViewById(R.id.thursday2);
        thursday[3] = (AutoResizeTextView)findViewById(R.id.thursday3);
        thursday[4] = (AutoResizeTextView)findViewById(R.id.thursday4);
        thursday[5] = (AutoResizeTextView)findViewById(R.id.thursday5);
        thursday[6] = (AutoResizeTextView)findViewById(R.id.thursday6);
        thursday[7] = (AutoResizeTextView)findViewById(R.id.thursday7);
        thursday[8] = (AutoResizeTextView)findViewById(R.id.thursday8);
        thursday[9] = (AutoResizeTextView)findViewById(R.id.thursday9);
        thursday[10] = (AutoResizeTextView)findViewById(R.id.thursday10);
        thursday[11] = (AutoResizeTextView)findViewById(R.id.thursday11);
        thursday[12] = (AutoResizeTextView)findViewById(R.id.thursday12);
        thursday[13] = (AutoResizeTextView)findViewById(R.id.thursday13);
        friday[0] = (AutoResizeTextView)findViewById(R.id.friday0);
        friday[1] = (AutoResizeTextView)findViewById(R.id.friday1);
        friday[2] = (AutoResizeTextView)findViewById(R.id.friday2);
        friday[3] = (AutoResizeTextView)findViewById(R.id.friday3);
        friday[4] = (AutoResizeTextView)findViewById(R.id.friday4);
        friday[5] = (AutoResizeTextView)findViewById(R.id.friday5);
        friday[6] = (AutoResizeTextView)findViewById(R.id.friday6);
        friday[7] = (AutoResizeTextView)findViewById(R.id.friday7);
        friday[8] = (AutoResizeTextView)findViewById(R.id.friday8);
        friday[9] = (AutoResizeTextView)findViewById(R.id.friday9);
        friday[10] = (AutoResizeTextView)findViewById(R.id.friday10);
        friday[11] = (AutoResizeTextView)findViewById(R.id.friday11);
        friday[12] = (AutoResizeTextView)findViewById(R.id.friday12);
        friday[13] = (AutoResizeTextView)findViewById(R.id.friday13);

        Intent intent = getIntent();
        proName = intent.getStringExtra("proName");

        String result_search_pro = ((ProfessorActivity) ProfessorActivity.context_pro).result_pro;
        Log.d("result_search_Timetable", result_search_pro);

        if (result_search_pro != null) { //받아온 json값이 널이면

            mJsonString = result_search_pro; //json 값을 mJsonString 변수에 넣고
            showResult(); //결과 값을 출력하는 함수 호출
        }


        mName = (TextView)findViewById(R.id.name);
        mName.setText(proName);

    }

    private void showResult(){//json을 가공하여 DB 검색 결과 값 창을 띄우는 부분

        String TAG_JSON="Course";          //Student에 저장되어 있는 값들(전체 값)을 TAG_JSON 변수에 저장
        String TAG_SNAME ="Sname";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_STIME ="Subtime";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_RNUM ="Rnum";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장
        String TAG_PNUM ="Pnum";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장


        //JSONObject JSON형태의 데이터 관리
        try {
            JSONObject jsonObject = new JSONObject(mJsonString); //json값을 저장하고 있는 변수(mJsonString)로 jsonObject 객체를 초기화
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);//Student에 저장되어 있는 값들(전체 값)을 jsonArray에 넣음

            for(int i=0;i<jsonArray.length();i++){//student에 저장되어 있는 값만큼 반복

                JSONObject item = jsonArray.getJSONObject(i);//jsonArray에서 JSONObject값을 배열 값들 수만큼 반복하면서 가져와 item에 저장

                String sname = item.getString(TAG_SNAME);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String subtime = item.getString(TAG_STIME);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String rnum = item.getString(TAG_RNUM);//item에서 TAG_ETIME(Etime)값만 가져와 Etime 변수에 넣는다.
                String pnum = item.getString(TAG_PNUM);//item에서 TAG_ETIME(Etime)값만 가져와 Etime 변수에 넣는다.
                String pname = "";

                if(pnum.equals("P01")){
                    pname  = "유우종";//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(pnum.equals("P02")){
                    pname  = "최현호";//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(pnum.equals("P03")){
                    pname  = "임관철";//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(pnum.equals("P04")){
                    pname  = "장수진";//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(pnum.equals("P05")){
                    pname  = "신진섭";//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(pnum.equals("P06")){
                    pname  = "김기봉";//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(pnum.equals("P07")){
                    pname  = "정진영";//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(pnum.equals("P08")){
                    pname  = "우인성";//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(pnum.equals("P09")){
                    pname  = "홍진숙";//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(pname.equals("P10")){
                    pname  = "이주현";//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(pnum.equals("P11")){
                    pname  = "정영석";//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(pnum.equals("P12")){
                    pname  = "서정민";//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(pnum.equals("P13")){
                    pname  = "조희진";//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }

                schedule.addSchedule(subtime, sname, pname);

            }

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
        schedule.setting(monday, tuesday, wednesday, thursday, friday, context);
    }

}