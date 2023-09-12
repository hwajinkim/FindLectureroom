package com.example.FLR;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.FLR.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "114.71.61.251/~cip1973/student";
    private static String TAG = "laststudent_db";
    private ArrayList<PersonalData> mArrayList; //학생들의 정보를 저장할 ArrayList
    private UsersAdapter mAdapter;
    private EditText et_Id,et_Password;
    public static Context context_main;
    public static String StuID,StuPass,Stu_ID,Stu_Pass,Stu_Name;
    public static String ProID,ProPass,Pro_ID,Pro_Pass,Pro_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(this, Loading.class);
        startActivity(intent);

        context_main = this;
        et_Id = (EditText) findViewById(R.id.et_Id);//'ID' Edit창을 mEditTextSearchKeyword 변수에 선언
        et_Password = (EditText) findViewById(R.id.et_Password);//'PassWord' Edit창을 mEditTextSearchKeyword 변수에 선언


        mArrayList = new ArrayList<>(); //PersonalData 클래스에 대한 배열 리스트를 생성

        mAdapter = new UsersAdapter(this, mArrayList);//UsersAdater는 mRecyclerView를 정해진 레이아웃 형태로 화면에 출력해주기 위해 생성한 클래스이다.
        //UsersAdater에 대한 mAdater객체를 생성
        //학생정보가 저장되어 있는 mArrayList를 mAdapter객체를 초기화하기 위한 매개변수로 보냄.
        //UesrAdapter 클래스로 이동


        Button button_search = (Button) findViewById(R.id.button_main_search); //로그인 버튼을 button_search 변수로 선언
        button_search.setOnClickListener(new View.OnClickListener() {//안내시작 버튼을 눌렀을 때 하는 기능
            public void onClick(View v) {

                mArrayList.clear();//학생 정보가 들어있는 배열리스트를 초기화
                mAdapter.notifyDataSetChanged();//mAdapter DB 검색 결과 값 창 내용을 새로고침

                if(et_Id.getText().toString().matches("(.*)P(.*)"))
                {
                    ProID = et_Id.getText().toString();
                    ProPass = et_Password.getText().toString();
                    Log.d("ProID",ProID);
                    Log.d("ProPass",ProPass);

                }
                else {
                    StuID = et_Id.getText().toString();
                    StuPass = et_Password.getText().toString();
                    Log.d("StuID",StuID);
                    Log.d("StuPass",StuPass);
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                            System.out.println("hongchul" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 로그인에 성공한 경우
                                if(et_Id.getText().toString().matches("(.*)P(.*)")) {
                                    Pro_ID = jsonObject.getString("Professor_number");
                                    Pro_Pass = jsonObject.getString("P_pw");
                                    Pro_Name = jsonObject.getString("Professor_name");
                                    Log.d("Pro_ID_M",Pro_ID);
                                    Log.d("Pro_Pass_M",Pro_Pass);
                                    Log.d("Pro_Name_M",Pro_Name);

                                }else{
                                    Stu_ID = jsonObject.getString("Student_number");
                                    Stu_Pass = jsonObject.getString("Student_password");
                                    Stu_Name = jsonObject.getString("Name");
                                    Log.d("Stu_ID_M",Stu_ID);
                                    Log.d("Stu_Pass_M",Stu_Pass);
                                    Log.d("Stu_Name_M",Stu_Name);

                                }
                                Toast.makeText(getApplicationContext(),"로그인에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                et_Id.setText("");
                                et_Password.setText("");
                                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                                startActivity(intent);

                            } else { // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                };
                if(et_Id.getText().toString().matches("(.*)P(.*)")) {
                    LoginRequestPro loginRequestPro = new LoginRequestPro(ProID, ProPass, responseListener);
                    RequestQueue queuePro = Volley.newRequestQueue(MainActivity.this);
                    queuePro.add(loginRequestPro);

                }else {
                    LoginRequestStu loginRequestStu = new LoginRequestStu(StuID, StuPass, responseListener);
                    RequestQueue queueStu = Volley.newRequestQueue(MainActivity.this);
                    queueStu.add(loginRequestStu);
                }

            }
        });

    }
}