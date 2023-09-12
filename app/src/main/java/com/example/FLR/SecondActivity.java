package com.example.FLR;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.example.FLR.R;
import com.google.android.material.navigation.NavigationView;


public class SecondActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "114.71.61.251/~cip1973/student";
    private static String TAG = "laststudent_db";
    private TextView mSnumber,mRnumber,mSub,mStime,mEtime;
    private TextView mPnumber;
    private TextView n8501,n8513,n8517,n8521,n8522,n8527,n8528,n9501,n9503,n9506;
    public static String s_id, s_password, s_name;
    public static String p_id, p_password, p_name;
    private DrawerLayout mDrawerLayout;
    private TextView tx_name,tx_id;
    private Context context = this;
    private ArrayList<PersonalData> mArrayList; //학생들의 정보를 저장할 ArrayList
    private UsersAdapter mAdapter;//UsersAdater는 mRecyclerView를 정해진 레이아웃 형태로 화면에 출력해주기 위해 생성한 클래스 변수 선언.
    private String mJsonString;//json문자열을 저장할 변수
    public static Context context_second;
    public static String n_8501,n_8513,n_8517,n_8521,n_8522,n_8527,n_8528,n_9501,n_9503,n_9506;
    public String roomnum = "";
    public String result_search_stu = "";
    public String result_search_pro = "";
    public String result_query = "";
    public String result_depart = "";
    public String class_information="";
    public static ImageView window95,corridor95,two_stairs85,small_stairs85,corridor85_1,corridor85_2,center_stairs85,corridor85_3,corridor85_4;


    final static MainActivity mainActivity = new MainActivity();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        context_second = this;

        mSnumber = (TextView)findViewById(R.id.Snumber_text);
        mRnumber = (TextView)findViewById(R.id.Rnumber_text);
        mSub = (TextView)findViewById(R.id.Sub_text);
        mStime = (TextView)findViewById(R.id.Stime_text);
        mEtime= (TextView)findViewById(R.id.Etime_text);

        mPnumber = (TextView)findViewById(R.id.Snumber_text);


        //호실번호
        n8501 = (TextView)findViewById(R.id.n8501);
        n8513 = (TextView)findViewById(R.id.n8513);
        n8517 = (TextView)findViewById(R.id.n8517);
        n8521 = (TextView)findViewById(R.id.n8521);
        n8522 = (TextView)findViewById(R.id.n8522);
        n8527 = (TextView)findViewById(R.id.n8527);
        n8528 = (TextView)findViewById(R.id.n8528);
        n9501 = (TextView)findViewById(R.id.n9501);
        n9503 = (TextView)findViewById(R.id.n9503);
        n9506 = (TextView)findViewById(R.id.n9506);

        n_8501 = n8501.getText().toString();
        n_8513 = n8513.getText().toString();
        n_8517 = n8517.getText().toString();
        n_8521 = n8521.getText().toString();
        n_8522 = n8522.getText().toString();
        n_8527 = n8527.getText().toString();
        n_8528 = n8528.getText().toString();
        n_9501 = n9501.getText().toString();
        n_9503 = n9503.getText().toString();
        n_9506 = n9506.getText().toString();


        n8501.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), TimetableClass.class);
                intent.putExtra("n_8501",n_8501);
                startActivity(intent);
            }
        });

        n8513.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), TimetableClass.class);
                intent.putExtra("n_8513",n_8513);
                startActivity(intent);
            }
        });

       n8517.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), TimetableClass.class);
                intent.putExtra("n_8517",n_8517);
                startActivity(intent);
            }
        });

        n8521.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), TimetableClass.class);
                intent.putExtra("n_8521",n_8521);
                startActivity(intent);
            }
        });

        n8522.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), TimetableClass.class);
                intent.putExtra("n_8522",n_8522);
                startActivity(intent);
            }
        });

        n8527.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), TimetableClass.class);
                intent.putExtra("n_8527",n_8527);
                startActivity(intent);
            }
        });

        n8528.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), TimetableClass.class);
                intent.putExtra("n_8528",n_8528);
                startActivity(intent);
            }
        });

        n9501.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), TimetableClass.class);
                intent.putExtra("n_9501",n_9501);
                startActivity(intent);
            }
        });

        n9503.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), TimetableClass.class);
                intent.putExtra("n_9503",n_9503);
                startActivity(intent);
            }
        });

        n9506.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), TimetableClass.class);
                intent.putExtra("n_9506",n_9506);
                startActivity(intent);
            }
        });

        mArrayList = new ArrayList<>(); //PersonalData 클래스에 대한 배열 리스트를 생성

        mAdapter = new UsersAdapter(this, mArrayList);//UsersAdater는 mRecyclerView를 정해진 레이아웃 형태로 화면에 출력해주기 위해 생성한 클래스이다.
        //UsersAdater에 대한 mAdater객체를 생성
        //학생정보가 저장되어 있는 mArrayList를 mAdapter객체를 초기화하기 위한 매개변수로 보냄.
        //UesrAdapter 클래스로 이동


        if(mainActivity.Pro_ID != null) {
            p_id = mainActivity.Pro_ID;
            p_password = mainActivity.Pro_Pass;
            p_name = mainActivity.Pro_Name;
            Log.d("mainActivity.Pro_ID",mainActivity.Pro_ID);

        }else if(mainActivity.Stu_ID != null){
            s_id = mainActivity.Stu_ID;
            s_password = mainActivity.Stu_Pass;
            s_name = mainActivity.Stu_Name;
            Log.d("mainActivity.Stu_ID",mainActivity.Stu_ID);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.menu_); //뒤로가기 버튼 이미지 지정

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();

        View nav_header_view = navigationView.getHeaderView(0);
        if(mainActivity.Pro_ID != null) { //교수 모드라면
            tx_name = (TextView) nav_header_view.findViewById(R.id.tx_name);
            tx_name.setText(p_name);
            tx_id = (TextView) nav_header_view.findViewById(R.id.tx_id);
            tx_id.setText(p_id);
            Log.d("p_name",p_name);
            Log.d("p_id",p_id);

            MenuItem history = menu.findItem(R.id.history);
            MenuItem book_calender = menu.findItem(R.id.book_calender);
            MenuItem reservation_list = menu.findItem(R.id.reservation_list);
            MenuItem student_menual = menu.findItem(R.id.student_menual);
            history.setVisible(false);
            book_calender.setVisible(false);
            reservation_list.setVisible(false);
            student_menual.setVisible(false);

        }else{  //학생 모드라면
            tx_name = (TextView) nav_header_view.findViewById(R.id.tx_name);
            tx_name.setText(s_name);
            tx_id = (TextView) nav_header_view.findViewById(R.id.tx_id);
            tx_id.setText(s_id);
            Log.d("s_name",s_name);
            Log.d("s_id",s_id);

            MenuItem lab_absence = menu.findItem(R.id.lab_absence);
            MenuItem professor_menual = menu.findItem(R.id.professor_menual);
            professor_menual.setVisible(false);
            lab_absence.setVisible(false);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if (id == R.id.history) {
                    Toast.makeText(context, title + ": 수강신청내역 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
                    mArrayList.clear();//학생 정보가 들어있는 배열리스트를 초기화
                    mAdapter.notifyDataSetChanged();//mAdapter, DB 검색 결과 값 창 내용을 새로고침

                    Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);//ThirdActivity로 이동하는 내용을 intent 객체에 담음
                    startActivity(intent);//이동을 시작해라

                    GetDataStu task = new GetDataStu();//AsyncTask( GetDataStu) 클래스(틀)의 객체(찍어낸 내용) 생성
                    task.execute("http://" + IP_ADDRESS + "/search.php", s_id);//AsyncTask 기능 실행


                } else if (id == R.id.timetable) {
                    Toast.makeText(context, title + ": 수업 시간표 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();

                    if(mainActivity.Pro_ID != null) {
                        Intent intent = new Intent(getApplicationContext(), TimetableProSelf.class);//TimetableProSelf로 이동하는 내용을 intent 객체에 담음
                        startActivity(intent);//이동을 시작해라
                    }else {
                        Intent intent = new Intent(getApplicationContext(), TimetableStu.class);//TimetableStu로 이동하는 내용을 intent 객체에 담음
                        startActivity(intent);//이동을 시작해라
                    }

                } else if(id==R.id .book_calender){
                    Toast.makeText(context, title + ": 강의실 예약 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ClassroomReservation.class);//ClassroomReservation로 이동하는 내용을 intent 객체에 담음
                    startActivity(intent);//이동을 시작해라

                } else if(id==R.id .reservation_list){
                    Toast.makeText(context, title + ": 예약 목록 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ClassroomReservationList.class);//ClassroomReservationList로 이동하는 내용을 intent 객체에 담음
                    startActivity(intent);//이동을 시작해라

                } else if(id==R.id .lab_absence){
                    Toast.makeText(context, title + ": 교수 재실 여부 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LabAbsence.class);//LabAbsence로 이동하는 내용을 intent 객체에 담음
                    startActivity(intent);//이동을 시작해라

                } else if(id==R.id .student_menual){
                    Toast.makeText(context, title + ": 학생 메뉴얼 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), StudentMenual.class);//LabAbsence로 이동하는 내용을 intent 객체에 담음
                    startActivity(intent);//이동을 시작해라

                } else if(id==R.id .professor_menual){
                    Toast.makeText(context, title + ": 교수 메뉴얼 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ProfessorMenual.class);//LabAbsence로 이동하는 내용을 intent 객체에 담음
                    startActivity(intent);//이동을 시작해라

                } else if (id == R.id.logout) {
                    AlertDialog.Builder alt_bld = new AlertDialog.Builder(navigationView.getContext());
                    alt_bld.setMessage("로그아웃 하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(intent);
                                    System.exit(0);
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                }
                return true;
            }
        });

        GetDataStu taskStu = new GetDataStu();//AsyncTask( GetDataStu) 클래스(틀)의 객체(찍어낸 내용) 생성
        AsyncTask<String, Void, String> atStu = taskStu.execute( "http://"+IP_ADDRESS+"/search.php", s_id);//AsyncTask 기능 실행

        try {
            result_search_stu = atStu.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("result_search_stuCourse",result_search_stu);

        GetDataPro taskPro = new GetDataPro();
        AsyncTask<String, Void, String> atPro = taskPro.execute( "http://"+IP_ADDRESS+"/search_pro_course.php", p_id);//AsyncTask 기능 실행

        try {
            result_search_pro = atPro.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("result_search_proCourse",result_search_pro);


        if(mainActivity.Stu_ID != null) {
            GetDataStu taskS = new GetDataStu();//AsyncTask( GetDataStu) 클래스(틀)의 객체(찍어낸 내용) 생성
            AsyncTask<String, Void, String> at = taskS.execute("http://" + IP_ADDRESS + "/query.php", s_id);//AsyncTask 기능 실행

            try {
                result_query = at.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String errorString = null;
            if (result_query.equals("학번이 " + s_id + "인 학생은 현재 수업이 없습니다.")) {
                Toast.makeText(context, result_query, Toast.LENGTH_SHORT).show();
            } else if (result_query == null) { //받아온 json값이 널이면
                Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show();

            } else {  // 받아온 json값이 널이 아니면

                mJsonString = result_query; //json 값을 mJsonString 변수에 넣고
                Log.d("mJsonString", mJsonString);
                showResult(); //결과 값을 출력하는 함수 호출
            }
        }
        // 교수 수업
        if(mainActivity.Pro_ID != null) {
            GetDataPro taskP = new GetDataPro();//AsyncTask( GetDataPro) 클래스(틀)의 객체(찍어낸 내용) 생성
            AsyncTask<String, Void, String> at2 = taskP.execute("http://" + IP_ADDRESS + "/query1.php", p_id);//AsyncTask 기능 실행

            try {
                result_query = at2.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String errorString = null;
            if (result_query.equals("교수번호가 " + p_id + "인 교수님은 현재 수업이 없습니다.")) {
                Toast.makeText(context, result_query, Toast.LENGTH_SHORT).show();
            } else if (result_query == null) { //받아온 json값이 널이면
                Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show();

            } else {  // 받아온 json값이 널이 아니면

                mJsonString = result_query; //json 값을 mJsonString 변수에 넣고
                Log.d("mJsonString", mJsonString);
                showResult(); //결과 값을 출력하는 함수 호출
            }
        }

        window95 = (ImageView) findViewById(R.id.window95);
        window95.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HallwayPicture.class);
                intent.putExtra("window95","window95");
                startActivity(intent);
            }
        });

        corridor95 = (ImageView) findViewById(R.id.corridor95);
        corridor95.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HallwayPicture.class);
                intent.putExtra("corridor95","corridor95");
                startActivity(intent);
            }
        });

        two_stairs85 = (ImageView) findViewById(R.id.two_stairs85);
        two_stairs85.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HallwayPicture.class);
                intent.putExtra("two_stairs85","two_stairs85");
                startActivity(intent);
            }
        });

        small_stairs85 = (ImageView) findViewById(R.id.small_stairs85);
        small_stairs85.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HallwayPicture.class);
                intent.putExtra("small_stairs85","small_stairs85");
                startActivity(intent);
            }
        });

        corridor85_1 = (ImageView) findViewById(R.id.corridor85_1);
        corridor85_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HallwayPicture.class);
                intent.putExtra("corridor85_1","corridor85_1");
                startActivity(intent);
            }
        });

        corridor85_2 = (ImageView) findViewById(R.id.corridor85_2);
        corridor85_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HallwayPicture.class);
                intent.putExtra("corridor85_2","corridor85_2");
                startActivity(intent);
            }
        });

        center_stairs85 = (ImageView) findViewById(R.id.center_stairs85);
        center_stairs85.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HallwayPicture.class);
                intent.putExtra("center_stairs85","center_stairs85");
                startActivity(intent);
            }
        });

        corridor85_3 = (ImageView) findViewById(R.id.corridor85_3);
        corridor85_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HallwayPicture.class);
                intent.putExtra("corridor85_3","corridor85_3");
                startActivity(intent);
            }
        });

        corridor85_4 = (ImageView) findViewById(R.id.corridor85_4);
        corridor85_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HallwayPicture.class);
                intent.putExtra("corridor85_4","corridor85_4");
                startActivity(intent);
            }
        });

        TextView findroomlogo = (TextView)findViewById(R.id.logo);
        findroomlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
            }
        });

        TextView pyou = (TextView) findViewById(R.id.p_you);
        pyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String py = pyou.getText().toString();
                Intent intent = new Intent(getApplication(), ProfessorActivity.class);
                intent.putExtra("py",py);
                startActivity(intent);
            }
        });

        TextView pjang = (TextView) findViewById(R.id.p_jang);
        pjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pj = pjang.getText().toString();
                Intent intent = new Intent(getApplication(), ProfessorActivity.class);
                intent.putExtra("pj",pj);
                startActivity(intent);
            }
        });

        TextView plim = (TextView) findViewById(R.id.p_lim);
        plim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pl = plim.getText().toString();
                Intent intent = new Intent(getApplication(), ProfessorActivity.class);
                intent.putExtra("pl",pl);
                startActivity(intent);
            }
        });

        TextView pkim = (TextView) findViewById(R.id.p_kim);
        pkim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pk = pkim.getText().toString();
                Intent intent = new Intent(getApplication(), ProfessorActivity.class);
                intent.putExtra("pk",pk);
                startActivity(intent);
            }
        });

        TextView pchoi = (TextView) findViewById(R.id.p_choi);
        pchoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pc = pchoi.getText().toString();
                Intent intent = new Intent(getApplication(), ProfessorActivity.class);
                intent.putExtra("pc",pc);
                startActivity(intent);
            }
        });

        TextView pjung = (TextView) findViewById(R.id.p_jung);
        pjung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pz = pjung.getText().toString();
                Intent intent = new Intent(getApplication(), ProfessorActivity.class);
                intent.putExtra("pz",pz);
                startActivity(intent);
            }
        });

        TextView pshin = (TextView) findViewById(R.id.p_shin);
        pshin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ps = pshin.getText().toString();
                Intent intent = new Intent(getApplication(), ProfessorActivity.class);
                intent.putExtra("ps",ps);
                startActivity(intent);

            }
        });

        TextView poffice = (TextView) findViewById(R.id.p_office);
        poffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), DepartActivity.class);
                startActivity(intent);

                String po = poffice.getText().toString();

                Log.d("po",po);
                GetData_department task = new GetData_department();
                AsyncTask<String, Void, String> at = task.execute( "http://"+IP_ADDRESS+"/get_dpm.php", po);//AsyncTask 기능 실행

                try {
                    result_depart = at.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("result_depart",result_depart);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private class GetDataStu extends AsyncTask<String, Void, String>{

        ProgressDialog progressDialog;//사용자에게 실시간 진행 상태를 알릴수 있다.
        String errorString = null;

        //<실시간 진행 상태 >
        @Override
        //--작업시작, progressDialog 객체를 생성하고 시작한다.--
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(SecondActivity.this,
                    "Please Wait", null, true, true);
        }

        //<디버깅 결과 창에 json값으로 세팅 >
        @Override
        //--작업종료, progressDialog 종료 기능을 구현한다.--
        protected void onPostExecute(String result) { //doInBackground에서 리턴한 값(총 json값)을 새로운 result 문자열에 삽입
            super.onPostExecute(result);
            progressDialog.dismiss();

        }

        //http(php)와 연결하여 값을 보내고 받는 부분
        @Override
        //--작업진행중, progressDialog의 진행 정도를 표현해 준다.--
        protected String doInBackground(String... params) { //execute의 매개변수를 받아와서 사용

            String serverURL = params[0];   //"http://"+IP_ADDRESS+"/search.php"와 "http://"+IP_ADDRESS+"/getjson.php" 값이 매개변수로 날아옴
            String postParameters = "Snumber=" + params[1];//Keyword2(입력받은 학번값)와 "" 값이 매개변수로 날아옴
            Log.d("param[1]",postParameters);

            try {
                //<안드로이드와 php연결 >
                URL url = new URL(serverURL); //url객체를 생성하여 serverURL로 초기화함
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//url과 연결 =  HttpURLConnection


                httpURLConnection.setReadTimeout(5000);//url과 연결,  읽을 시 연결 시간 5000초로 지정
                httpURLConnection.setConnectTimeout(5000);//url과 연결, 서버 접속시 연결 시간 5000초로 지정
                httpURLConnection.setRequestMethod("POST");//요청 방식  post로 지정, 파라미터 값이 url주소에 포함되어 가지 않음
                httpURLConnection.setDoInput(true);//InputStream으로 서버로 부터 응답을 받겠다는 옵션
                httpURLConnection.connect();//url과 연결하겠다

                //<입력한 내용을 php에 전달 >
                OutputStream outputStream = httpURLConnection.getOutputStream();//RequestBody(사용자가 입력한 내용,요청한 내용)에 데이터를 담기 위해 OutputStream 객체를 생성
                outputStream.write(postParameters.getBytes("euc-kr"));//사용자가 입력한 내용을 euc-kr로 세팅
                outputStream.flush();//입력한 내용을 서버에 전송한다.
                outputStream.close();//OutputStream 종료
                //앱에서 데이터 전송

                //<php에서 처리한 값(json)을 안드로이드에 전달 >
                int responseStatusCode = httpURLConnection.getResponseCode();//실제 서버에서 응답한 내용을 받아온다(json)
                Log.d(TAG, "response code2 - " + responseStatusCode);

                InputStream inputStream; //stream이란 외부에서 들어오는 데이터를 입력받고 출력하는 터널과 같은 중간자 역할 inputStream : 입력을 받아오는 스트림
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {//연결 상태를 확인한다. 연결이 잘 되었다면
                    inputStream = httpURLConnection.getInputStream(); //스트림에 읽어온 값을 넣음
                }
                else{//연결이 잘 안 되었다면
                    inputStream = httpURLConnection.getErrorStream();//스트림에 에러 값을 넣음
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "euc-kr"); //서버에서 읽어온 값(json )을 "euc-kr"형식으로 변환하여 inputStreamReader 객체를 초기화
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);//euc-kr로 변환된 읽어온 값으로 초기화된 객체로 bufferedReader 객체를 초기화함
                //BufferedReader는 버퍼를 이용하기 때문에 이함수를 이용하면 입출력의 효울이 비교할 수 없을 정도로 좋아진다. 그래서 이 함수를 사용한다.

                StringBuilder sb = new StringBuilder();//StringBuilder는 String과 문자열을 더할 때 새로운 객체를 만들지 않고 기존의 데이터에 더하는 방식을 사용하기 때문에 속도가 빠르다.
                String line;//문자열 line 변수를 생 성

                while((line = bufferedReader.readLine()) != null){//버퍼에서 읽은 값을 line 변수에 넣고 이값이 널값이 아니면
                    sb.append(line);//StringBuilder 객체인 sb에 line(버퍼에서 읽은 값)을 추가한다.
                }

                bufferedReader.close();//읽어오는 역할을 하는 버퍼를 종료한다.

                return sb.toString().trim();

            }
            catch (Exception e) {//에러가 발생하면

                Log.d(TAG, "InsertData: Error ", e);//에러 값을 e에 넣는다.
                errorString = e.toString();//e(에러 값)을 문자열로 변환하고 errorString 변수에 넣는다.
                return null;
            }
        }
    }
    private class GetDataPro extends AsyncTask<String, Void, String>{

        ProgressDialog progressDialog;//사용자에게 실시간 진행 상태를 알릴수 있다.
        String errorString = null;

        //<실시간 진행 상태 >
        @Override
        //--작업시작, progressDialog 객체를 생성하고 시작한다.--
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(SecondActivity.this,
                    "Please Wait", null, true, true);
        }

        //<디버깅 결과 창에 json값으로 세팅 >
        @Override
        //--작업종료, progressDialog 종료 기능을 구현한다.--
        protected void onPostExecute(String result) { //doInBackground에서 리턴한 값(총 json값)을 새로운 result 문자열에 삽입
            super.onPostExecute(result);
            progressDialog.dismiss();

        }

        //http(php)와 연결하여 값을 보내고 받는 부분
        @Override
        //--작업진행중, progressDialog의 진행 정도를 표현해 준다.--
        protected String doInBackground(String... params) { //execute의 매개변수를 받아와서 사용

            String serverURL = params[0];   //"http://"+IP_ADDRESS+"/search.php"와 "http://"+IP_ADDRESS+"/getjson.php" 값이 매개변수로 날아옴
            String postParameters = "Pnumber=" + params[1];//Keyword2(입력받은 학번값)와 "" 값이 매개변수로 날아옴
            Log.d("param[1]",postParameters);

            try {
                //<안드로이드와 php연결 >
                URL url = new URL(serverURL); //url객체를 생성하여 serverURL로 초기화함
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//url과 연결 =  HttpURLConnection


                httpURLConnection.setReadTimeout(5000);//url과 연결,  읽을 시 연결 시간 5000초로 지정
                httpURLConnection.setConnectTimeout(5000);//url과 연결, 서버 접속시 연결 시간 5000초로 지정
                httpURLConnection.setRequestMethod("POST");//요청 방식  post로 지정, 파라미터 값이 url주소에 포함되어 가지 않음
                httpURLConnection.setDoInput(true);//InputStream으로 서버로 부터 응답을 받겠다는 옵션
                httpURLConnection.connect();//url과 연결하겠다

                //<입력한 내용을 php에 전달 >
                OutputStream outputStream = httpURLConnection.getOutputStream();//RequestBody(사용자가 입력한 내용,요청한 내용)에 데이터를 담기 위해 OutputStream 객체를 생성
                outputStream.write(postParameters.getBytes("euc-kr"));//사용자가 입력한 내용을 euc-kr로 세팅
                outputStream.flush();//입력한 내용을 서버에 전송한다.
                outputStream.close();//OutputStream 종료
                //앱에서 데이터 전송

                //<php에서 처리한 값(json)을 안드로이드에 전달 >
                int responseStatusCode = httpURLConnection.getResponseCode();//실제 서버에서 응답한 내용을 받아온다(json)
                Log.d(TAG, "response code2 - " + responseStatusCode);

                InputStream inputStream; //stream이란 외부에서 들어오는 데이터를 입력받고 출력하는 터널과 같은 중간자 역할 inputStream : 입력을 받아오는 스트림
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {//연결 상태를 확인한다. 연결이 잘 되었다면
                    inputStream = httpURLConnection.getInputStream(); //스트림에 읽어온 값을 넣음
                }
                else{//연결이 잘 안 되었다면
                    inputStream = httpURLConnection.getErrorStream();//스트림에 에러 값을 넣음
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "euc-kr"); //서버에서 읽어온 값(json )을 "euc-kr"형식으로 변환하여 inputStreamReader 객체를 초기화
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);//euc-kr로 변환된 읽어온 값으로 초기화된 객체로 bufferedReader 객체를 초기화함
                //BufferedReader는 버퍼를 이용하기 때문에 이함수를 이용하면 입출력의 효울이 비교할 수 없을 정도로 좋아진다. 그래서 이 함수를 사용한다.

                StringBuilder sb = new StringBuilder();//StringBuilder는 String과 문자열을 더할 때 새로운 객체를 만들지 않고 기존의 데이터에 더하는 방식을 사용하기 때문에 속도가 빠르다.
                String line;//문자열 line 변수를 생 성

                while((line = bufferedReader.readLine()) != null){//버퍼에서 읽은 값을 line 변수에 넣고 이값이 널값이 아니면
                    sb.append(line);//StringBuilder 객체인 sb에 line(버퍼에서 읽은 값)을 추가한다.
                }

                bufferedReader.close();//읽어오는 역할을 하는 버퍼를 종료한다.

                return sb.toString().trim();

            }
            catch (Exception e) {//에러가 발생하면

                Log.d(TAG, "InsertData: Error ", e);//에러 값을 e에 넣는다.
                errorString = e.toString();//e(에러 값)을 문자열로 변환하고 errorString 변수에 넣는다.
                return null;
            }
        }
    }

    private class GetData_department extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;//사용자에게 실시간 진행 상태를 알릴수 있다.
        String errorString = null;

        //<실시간 진행 상태 >
        @Override
        //--작업시작, progressDialog 객체를 생성하고 시작한다.--
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(SecondActivity.this,
                    "Please Wait", null, true, true);
        }

        //<디버깅 결과 창에 json값으로 세팅 >
        @Override
        //--작업종료, progressDialog 종료 기능을 구현한다.--
        protected void onPostExecute(String result_you) { //doInBackground에서 리턴한 값(총 json값)을 새로운 result 문자열에 삽입
            super.onPostExecute(result_you);

            progressDialog.dismiss();

        }

        //http(php)와 연결하여 값을 보내고 받는 부분
        @Override
        //--작업진행중, progressDialog의 진행 정도를 표현해 준다.--
        protected String doInBackground(String... params) { //execute의 매개변수를 받아와서 사용

            String serverURL = params[0];   //"http://"+IP_ADDRESS+"/search.php"와 "http://"+IP_ADDRESS+"/getjson.php" 값이 매개변수로 날아옴
            String postParameters = "Dname=" + params[1];//Keyword2(입력받은 학번값)와 "" 값이 매개변수로 날아옴
            Log.d("param[1]", postParameters);

            try {
                //<안드로이드와 php연결 >
                URL url = new URL(serverURL); //url객체를 생성하여 serverURL로 초기화함
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//url과 연결 =  HttpURLConnection

                httpURLConnection.setReadTimeout(5000);//url과 연결,  읽을 시 연결 시간 5000초로 지정
                httpURLConnection.setConnectTimeout(5000);//url과 연결, 서버 접속시 연결 시간 5000초로 지정
                httpURLConnection.setRequestMethod("POST");//요청 방식  post로 지정, 파라미터 값이 url주소에 포함되어 가지 않음
                httpURLConnection.setDoInput(true);//InputStream으로 서버로 부터 응답을 받겠다는 옵션
                httpURLConnection.connect();//url과 연결하겠다

                //<입력한 내용을 php에 전달 >
                OutputStream outputStream = httpURLConnection.getOutputStream();//RequestBody(사용자가 입력한 내용,요청한 내용)에 데이터를 담기 위해 OutputStream 객체를 생성
                outputStream.write(postParameters.getBytes("euc-kr"));//사용자가 입력한 내용을 euc-kr로 세팅
                outputStream.flush();//입력한 내용을 서버에 전송한다.
                outputStream.close();//OutputStream 종료
                //앱에서 데이터 전송

                //<php에서 처리한 값(json)을 안드로이드에 전달 >
                int responseStatusCode = httpURLConnection.getResponseCode();//실제 서버에서 응답한 내용을 받아온다(json)
                Log.d(TAG, "response code2 - " + responseStatusCode);

                InputStream inputStream; //stream이란 외부에서 들어오는 데이터를 입력받고 출력하는 터널과 같은 중간자 역할 inputStream : 입력을 받아오는 스트림
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {//연결 상태를 확인한다. 연결이 잘 되었다면
                    inputStream = httpURLConnection.getInputStream(); //스트림에 읽어온 값을 넣음
                } else {//연결이 잘 안 되었다면
                    inputStream = httpURLConnection.getErrorStream();//스트림에 에러 값을 넣음
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "euc-kr"); //서버에서 읽어온 값(json )을 "euc-kr"형식으로 변환하여 inputStreamReader 객체를 초기화
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);//euc-kr로 변환된 읽어온 값으로 초기화된 객체로 bufferedReader 객체를 초기화함
                //BufferedReader는 버퍼를 이용하기 때문에 이함수를 이용하면 입출력의 효울이 비교할 수 없을 정도로 좋아진다. 그래서 이 함수를 사용한다.

                StringBuilder sb = new StringBuilder();//StringBuilder는 String과 문자열을 더할 때 새로운 객체를 만들지 않고 기존의 데이터에 더하는 방식을 사용하기 때문에 속도가 빠르다.
                String line;//문자열 line 변수를 생 성

                while ((line = bufferedReader.readLine()) != null) {//버퍼에서 읽은 값을 line 변수에 넣고 이값이 널값이 아니면
                    sb.append(line);//StringBuilder 객체인 sb에 line(버퍼에서 읽은 값)을 추가한다.
                }

                bufferedReader.close();//읽어오는 역할을 하는 버퍼를 종료한다.
                String dpm_information =sb.toString().trim();
                Log.d("you",dpm_information);
                return dpm_information;

            } catch (Exception e) {//에러가 발생하면

                Log.d(TAG, "InsertData: Error ", e);//에러 값을 e에 넣는다.
                errorString = e.toString();//e(에러 값)을 문자열로 변환하고 errorString 변수에 넣는다.
                return null;
            }
        }
    }

    private void showResult() {
        String TAG_JSON = "Student";          //Student에 저장되어 있는 값들(전체 값)을 TAG_JSON 변수에 저장
        String TAG_JSON_P = "Professor";
        String TAG_SNUMBER = "Snumber";     //Snumber로 저장되어 있는 값들(전체 값)을 TAG_SNUMBER 변수에 저장
        String TAG_RNUMBER = "Rnumber";     //Rnumber로 저장되어 있는 값들(전체 값)을 TAG_RNUMBER 변수에 저장
        String TAG_SUB = "Sub";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_STIME = "Stime";          //Stime로 저장되어 있는 값들(전체 값)을 TAG_STIME 변수에 저장
        String TAG_ETIME = "Etime";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장

        String TAG_PNUMBER = "Pnumber";     //Snumber로 저장되어 있는 값들(전체 값)을 TAG_SNUMBER 변수에 저장


        if (mainActivity.Stu_ID != null) {
            //JSONObject JSON형태의 데이터 관리
            try {
                JSONObject jsonObject = new JSONObject(mJsonString); //json값을 저장하고 있는 변수(mJsonString)로 jsonObject 객체를 초기화
                JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);//Student에 저장되어 있는 값들(전체 값)을 jsonArray에 넣음

                for (int i = 0; i < jsonArray.length(); i++) {//student에 저장되어 있는 값만큼 반복

                    JSONObject item = jsonArray.getJSONObject(i);//jsonArray에서 JSONObject값을 배열 값들 수만큼 반복하면서 가져와 item에 저장

                    String Snumber = item.getString(TAG_SNUMBER);//item에서 TAG_SNUMBER(Snumber)값만 가져와 Snumber 변수에 넣는다.
                    String Rnumber = item.getString(TAG_RNUMBER);//item에서 TAG_RNUMBER(Rnumber)값만 가져와 Rnumber 변수에 넣는다.
                    String Sub = item.getString(TAG_SUB);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                    String Stime = item.getString(TAG_STIME);//item에서 TAG_STIME(Stime)값만 가져와 Stime 변수에 넣는다.
                    String Etime = item.getString(TAG_ETIME);//item에서 TAG_ETIME(Etime)값만 가져와 Etime 변수에 넣는다.


                    PersonalData personalData = new PersonalData(); //PersonalData 클래스 대해 personalData 객체를 만든다.

                    personalData.setStudent_Snumber(Snumber);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.
                    personalData.setStudent_Rnumber(Rnumber);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                    personalData.setStudent_Sub(Sub);//personalData 객체의 setStudent_Sub메소드를 호출하여 매개변수로 Sub값을 전달한다.
                    personalData.setStudent_Stime(Stime);//personalData 객체의 setStudent_Stime메소드를 호출하여 매개변수로 Stime값을 전달한다.
                    personalData.setStudent_Etime(Etime);//personalData 객체의 setStudent_Etime메소드를 호출하여 매개변수로 Etime값을 전달한다.

                    mSnumber.setText(personalData.getStudent_Snumber());
                    mRnumber.setText(personalData.getStudent_Rnumber());
                    mSub.setText(personalData.getStudent_Sub());
                    mStime.setText(personalData.getStudent_Stime());
                    mEtime.setText(personalData.getStudent_Etime());

                    roomnum = personalData.getStudent_Rnumber();
                    String num8501 = n8501.getText().toString();
                    String num8513 = n8513.getText().toString();
                    String num8517 = n8517.getText().toString();
                    String num8521 = n8521.getText().toString();
                    String num8522 = n8522.getText().toString();
                    String num8527 = n8527.getText().toString();
                    String num8528 = n8528.getText().toString();
                    String num9501 = n9501.getText().toString();
                    String num9503 = n9503.getText().toString();
                    String num9506 = n9506.getText().toString();

                    Log.d("mRnum", roomnum);
                    if (num8501.equals(roomnum)) {
                        n8501.setTextColor(Color.parseColor("#FF0000"));
                        n8501.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num8513.equals(roomnum)) {
                        n8513.setTextColor(Color.parseColor("#FF0000"));
                        n8513.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num8517.equals(roomnum)) {
                        n8517.setTextColor(Color.parseColor("#FF0000"));
                        n8517.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num8521.equals(roomnum)) {
                        n8521.setTextColor(Color.parseColor("#FF0000"));
                        n8521.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                    } else if (num8522.equals(roomnum)) {
                        n8522.setTextColor(Color.parseColor("#FF0000"));
                        n8522.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num8527.equals(roomnum)) {
                        n8527.setTextColor(Color.parseColor("#FF0000"));
                        n8527.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num8528.equals(roomnum)) {
                        n8528.setTextColor(Color.parseColor("#FF0000"));
                        n8528.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num9501.equals(roomnum)) {
                        n9501.setTextColor(Color.parseColor("#FF0000"));
                        n9501.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num9503.equals(roomnum)) {
                        n9503.setTextColor(Color.parseColor("#FF0000"));
                        n9503.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num9506.equals(roomnum)) {
                        n9506.setTextColor(Color.parseColor("#FF0000"));
                        n9506.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    }
                    //매개변수로 전달받은 값으로 personalData 객체의 필드 값을 초기화 함.
                    mArrayList.add(personalData);//학생의 정보를 저장할 mArrayList변수에 personal 객체를 추가함.
                    mAdapter.notifyDataSetChanged();//mAdapter(mRecyclerView(DB 결과 창)를 정해진 레이아웃 형태로 화면에 출력해주기 위해 생성한 클래스의 객체)를 새로고침한다.
                }

            } catch (JSONException e) {

                Log.d(TAG, "showResult : ", e);
            }
        }else if(mainActivity.Pro_ID != null) {
            try {
                JSONObject jsonObject = new JSONObject(mJsonString); //json값을 저장하고 있는 변수(mJsonString)로 jsonObject 객체를 초기화
                JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON_P);//Student에 저장되어 있는 값들(전체 값)을 jsonArray에 넣음

                for (int i = 0; i < jsonArray.length(); i++) {//student에 저장되어 있는 값만큼 반복

                    JSONObject item = jsonArray.getJSONObject(i);//jsonArray에서 JSONObject값을 배열 값들 수만큼 반복하면서 가져와 item에 저장

                    String Pnumber = item.getString(TAG_PNUMBER);//item에서 TAG_SNUMBER(Snumber)값만 가져와 Snumber 변수에 넣는다.
                    String Rnumber = item.getString(TAG_RNUMBER);//item에서 TAG_RNUMBER(Rnumber)값만 가져와 Rnumber 변수에 넣는다.
                    String Sub = item.getString(TAG_SUB);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                    String Stime = item.getString(TAG_STIME);//item에서 TAG_STIME(Stime)값만 가져와 Stime 변수에 넣는다.
                    String Etime = item.getString(TAG_ETIME);//item에서 TAG_ETIME(Etime)값만 가져와 Etime 변수에 넣는다.


                    PersonalData personalData = new PersonalData(); //PersonalData 클래스 대해 personalData 객체를 만든다.

                    personalData.setProfessor_Number(Pnumber);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.
                    personalData.setStudent_Rnumber(Rnumber);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                    personalData.setStudent_Sub(Sub);//personalData 객체의 setStudent_Sub메소드를 호출하여 매개변수로 Sub값을 전달한다.
                    personalData.setStudent_Stime(Stime);//personalData 객체의 setStudent_Stime메소드를 호출하여 매개변수로 Stime값을 전달한다.
                    personalData.setStudent_Etime(Etime);//personalData 객체의 setStudent_Etime메소드를 호출하여 매개변수로 Etime값을 전달한다.

                    mPnumber.setText(personalData.getProfessor_Number());
                    mRnumber.setText(personalData.getStudent_Rnumber());
                    mSub.setText(personalData.getStudent_Sub());
                    mStime.setText(personalData.getStudent_Stime());
                    mEtime.setText(personalData.getStudent_Etime());

                    roomnum = personalData.getStudent_Rnumber();
                    String num8501 = n8501.getText().toString();
                    String num8513 = n8513.getText().toString();
                    String num8517 = n8517.getText().toString();
                    String num8521 = n8521.getText().toString();
                    String num8522 = n8522.getText().toString();
                    String num8527 = n8527.getText().toString();
                    String num8528 = n8528.getText().toString();
                    String num9501 = n9501.getText().toString();
                    String num9503 = n9503.getText().toString();
                    String num9506 = n9506.getText().toString();

                    Log.d("mRnum", roomnum);
                    if (num8501.equals(roomnum)) {
                        n8501.setTextColor(Color.parseColor("#FF0000"));
                        n8501.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num8513.equals(roomnum)) {
                        n8513.setTextColor(Color.parseColor("#FF0000"));
                        n8513.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num8517.equals(roomnum)) {
                        n8517.setTextColor(Color.parseColor("#FF0000"));
                        n8517.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num8521.equals(roomnum)) {
                        n8521.setTextColor(Color.parseColor("#FF0000"));
                        n8521.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num8522.equals(roomnum)) {
                        n8522.setTextColor(Color.parseColor("#FF0000"));
                        n8522.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num8527.equals(roomnum)) {
                        n8527.setTextColor(Color.parseColor("#FF0000"));
                        n8527.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num8528.equals(roomnum)) {
                        n8528.setTextColor(Color.parseColor("#FF0000"));
                        n8528.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num9501.equals(roomnum)) {
                        n9501.setTextColor(Color.parseColor("#FF0000"));
                        n9501.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num9503.equals(roomnum)) {
                        n9503.setTextColor(Color.parseColor("#FF0000"));
                        n9503.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    } else if (num9506.equals(roomnum)) {
                        n9506.setTextColor(Color.parseColor("#FF0000"));
                        n9506.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                    }
                    //매개변수로 전달받은 값으로 personalData 객체의 필드 값을 초기화 함.
                    mArrayList.add(personalData);//학생의 정보를 저장할 mArrayList변수에 personal 객체를 추가함.
                    mAdapter.notifyDataSetChanged();//mAdapter(mRecyclerView(DB 결과 창)를 정해진 레이아웃 형태로 화면에 출력해주기 위해 생성한 클래스의 객체)를 새로고침한다.
                }

            } catch (JSONException e) {

                Log.d(TAG, "showResult : ", e);
            }
        }
    }
}