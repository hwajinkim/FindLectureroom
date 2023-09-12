package com.example.FLR;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.FLR.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    private static String IP_ADDRESS = "114.71.61.251/~cip1973/student";
    private static String TAG = "laststudent_db";

    private View view;
    private ArrayList<PersonalData> mArrayList; //학생들의 정보를 저장할 ArrayList
    private UsersAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText mEditTextSearchKeyword;
    private String mJsonString;
    private String mJsonString2;
    private TextView mStudent_name;
    private ImageView back;
    private Context context = this;
    private String subname;
    final static MainActivity mainActivity = new MainActivity();
    public static String s_id;
    public static Context context_third;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        context_third = this;

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SecondActivity.class);
                startActivity(intent);
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.listView_main_list);//DB 검색 결과 값 창을 mRecyclerView 변수로 선언
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//여러 타입의 리스트들을 지원해주는 함수를 호출
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

//        String Keyword2 = ((MainActivity)MainActivity.context_main).Keyword;
//
//        mSnumber.setText(Keyword2);

        mArrayList = new ArrayList<>(); //PersonalData 클래스에 대한 배열 리스트를 생성

        mAdapter = new UsersAdapter(this, mArrayList);//UsersAdater는 mRecyclerView를 정해진 레이아웃 형태로 화면에 출력해주기 위해 생성한 클래스이다.
        //UsersAdater에 대한 mAdater객체를 생성
        //학생정보가 저장되어 있는 mArrayList를 mAdapter객체를 초기화하기 위한 매개변수로 보냄.
        //UesrAdapter 클래스로 이동
        mRecyclerView.setAdapter(mAdapter);//mRecyclerView를 UsersAdater에서 지정한 형식으로 세팅한다.(mAdapter를 매개변수로 보내줌)

        s_id = mainActivity.Stu_ID;

        String student_name = ((MainActivity) MainActivity.context_main).Stu_Name;
        mStudent_name = (TextView) findViewById(R.id.student_name);
        mStudent_name.setText(student_name);

        String result_search = ((SecondActivity) SecondActivity.context_second).result_search_stu;
        String result_query = ((SecondActivity) SecondActivity.context_second).result_query;

        Log.d("result_search_thirdActivity", result_search);


         if (result_search != null) { //받아온 json값이 널 아니면
             mJsonString = result_search; //json 값을 mJsonString 변수에 넣고
             showResult_search(); //결과 값을 출력하는 함수 호출
        }

        Log.d("result_query_thirdActivity", result_query);
         if(result_query.equals("학번이 "+s_id+ "인 학생은 현재 수업이 없습니다.")){
            Toast.makeText(context, result_query, Toast.LENGTH_SHORT).show();
        }
        else if (result_query != null) { //받아온 json값이 널이 아니면
            mJsonString2 = result_query; //json 값을 mJsonString 변수에 넣고
            showResult_query(); //결과 값을 출력하는 함수 호출

        }
    }


    private void showResult_search(){//json을 가공하여 DB 검색 결과 값 창을 띄우는 부분

        String TAG_JSON="Course";          //Student에 저장되어 있는 값들(전체 값)을 TAG_JSON 변수에 저장
        String TAG_SNUMBER = "Snumber";     //Snumber로 저장되어 있는 값들(전체 값)을 TAG_SNUMBER 변수에 저장
        String TAG_SCODE = "Scode";     //Rnumber로 저장되어 있는 값들(전체 값)을 TAG_RNUMBER 변수에 저장
        String TAG_SNAME ="Sname";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_SSORT ="Ssort";          //Stime로 저장되어 있는 값들(전체 값)을 TAG_STIME 변수에 저장
        String TAG_SPER ="Sper";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장
        String TAG_SCRE ="Scre";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장
        String TAG_SDAY ="Sday";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장
        String TAG_STIME ="Stime";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장
        String TAG_ETIME ="Etime";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장
        String TAG_SYEAR ="Syear";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장
        String TAG_SCLASS ="Sclass";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장
        String TAG_RNUM ="Rnum";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장
        String TAG_PNUM ="Pnum";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장


        //JSONObject JSON형태의 데이터 관리
        try {
            JSONObject jsonObject = new JSONObject(mJsonString); //json값을 저장하고 있는 변수(mJsonString)로 jsonObject 객체를 초기화
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);//Student에 저장되어 있는 값들(전체 값)을 jsonArray에 넣음

            for(int i=0;i<jsonArray.length();i++){//student에 저장되어 있는 값만큼 반복

                JSONObject item = jsonArray.getJSONObject(i);//jsonArray에서 JSONObject값을 배열 값들 수만큼 반복하면서 가져와 item에 저장

                String Snumber = item.getString(TAG_SNUMBER);//item에서 TAG_SNUMBER(Snumber)값만 가져와 Snumber 변수에 넣는다.
                String Scode = item.getString(TAG_SCODE);//item에서 TAG_RNUMBER(Rnumber)값만 가져와 Rnumber 변수에 넣는다.
                String Sname = item.getString(TAG_SNAME);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Ssort = item.getString(TAG_SSORT);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Sper = item.getString(TAG_SPER);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Scre = item.getString(TAG_SCRE);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Sday = item.getString(TAG_SDAY);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Stime = item.getString(TAG_STIME);//item에서 TAG_STIME(Stime)값만 가져와 Stime 변수에 넣는다.
                String Etime = item.getString(TAG_ETIME);//item에서 TAG_ETIME(Etime)값만 가져와 Etime 변수에 넣는다.
                String Syear = item.getString(TAG_SYEAR);//item에서 TAG_ETIME(Etime)값만 가져와 Etime 변수에 넣는다.
                String Sclass = item.getString(TAG_SCLASS);//item에서 TAG_ETIME(Etime)값만 가져와 Etime 변수에 넣는다.
                String Rnum = item.getString(TAG_RNUM);//item에서 TAG_ETIME(Etime)값만 가져와 Etime 변수에 넣는다.
                String Pnum = item.getString(TAG_PNUM);//item에서 TAG_ETIME(Etime)값만 가져와 Etime 변수에 넣는다.

                PersonalData personalData = new PersonalData(); //PersonalData 클래스 대해 personalData 객체를 만든다.

                personalData.setStudent_Snumber(Snumber);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.
                personalData.setStudent_Scode(Scode);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setStudent_Sname(Sname);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setStudent_Ssort(Ssort);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setStudent_Sper(Sper);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setStudent_Scre(Scre);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setStudent_Sday(Sday);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setStudent_Stime(Stime);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setStudent_Etime(Etime);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setStudent_Syear(Syear);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setStudent_Sclass(Sclass);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setStudent_Rnum(Rnum);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                if(Pnum.equals("P01")){
                    personalData.setStudent_Pnum("유우종");//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(Pnum.equals("P02")){
                    personalData.setStudent_Pnum("최현호");//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(Pnum.equals("P03")){
                    personalData.setStudent_Pnum("임관철");//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(Pnum.equals("P04")){
                    personalData.setStudent_Pnum("장수진");//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(Pnum.equals("P05")){
                    personalData.setStudent_Pnum("신진섭");//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(Pnum.equals("P06")){
                    personalData.setStudent_Pnum("김기봉");//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(Pnum.equals("P07")){
                    personalData.setStudent_Pnum("정진영");//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(Pnum.equals("P08")){
                    personalData.setStudent_Pnum("우인성");//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(Pnum.equals("P09")){
                    personalData.setStudent_Pnum("홍진숙");//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(Pnum.equals("P10")){
                    personalData.setStudent_Pnum("이주현");//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(Pnum.equals("P11")){
                    personalData.setStudent_Pnum("정영석");//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(Pnum.equals("P12")){
                    personalData.setStudent_Pnum("서정민");//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }
                else if(Pnum.equals("P13")){
                    personalData.setStudent_Pnum("조희진");//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                }

                //매개변수로 전달받은 값으로 personalData 객체의 필드 값을 초기화 함.
                mArrayList.add(personalData);//학생의 정보를 저장할 mArrayList변수에 personal 객체를 추가함.
                mAdapter.notifyDataSetChanged();//mAdapter(mRecyclerView(DB 결과 창)를 정해진 레이아웃 형태로 화면에 출력해주기 위해 생성한 클래스의 객체)를 새로고침한다.
            }

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
    private void showResult_query() {
        String TAG_JSON = "Student";          //Student에 저장되어 있는 값들(전체 값)을 TAG_JSON 변수에 저장
        String TAG_SUB = "Sub";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장


        //JSONObject JSON형태의 데이터 관리
        try {

            JSONObject jsonObject = new JSONObject(mJsonString2); //json값을 저장하고 있는 변수(mJsonString)로 jsonObject 객체를 초기화
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);//Student에 저장되어 있는 값들(전체 값)을 jsonArray에 넣음
            if(mJsonString2 == null)
            {
                subname = "";
            }
            for (int i = 0; i < jsonArray.length(); i++) {//student에 저장되어 있는 값만큼 반복

                JSONObject item = jsonArray.getJSONObject(i);//jsonArray에서 JSONObject값을 배열 값들 수만큼 반복하면서 가져와 item에 저장

                String Sub = item.getString(TAG_SUB);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.

                PersonalData personalData = new PersonalData(); //PersonalData 클래스 대해 personalData 객체를 만든다.

                personalData.setStudent_Sub(Sub);//personalData 객체의 setStudent_Sub메소드를 호출하여 매개변수로 Sub값을 전달한다.

                subname = personalData.getStudent_Sub();


            }

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

}