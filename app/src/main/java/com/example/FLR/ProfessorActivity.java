package com.example.FLR;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.FLR.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class
ProfessorActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "114.71.61.251/~cip1973/student";
    private TextView mPname,mPposi,mPtel1,mPtel2,mPtel3,mPemail1,mPemail2,mProom,mDname,mPstatus;
    private Button mPro_timetable;
    private TextView mTextViewResult;
    private static String TAG = "laststudent_db";
    public String result_pro = "";
    private String mJsonString;//json문자열을 저장할 변수
    private String proName="",proName1="",proName2="",proName3="",proName4="",proName5="",proName6="",proName7="",proNum="";
    public static Context context_pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        context_pro = this;

        mPname = (TextView)findViewById(R.id.Pname_text);
        mPposi = (TextView)findViewById(R.id.Pposi_text);
        mPtel1 = (TextView)findViewById(R.id.Ptel1_text);
        mPtel2 = (TextView)findViewById(R.id.Ptel2_text);
        mPtel3 = (TextView)findViewById(R.id.Ptel3_text);
        mPemail1 = (TextView)findViewById(R.id.Pemail1_text);
        mPemail2 = (TextView)findViewById(R.id.Pemail2_text);
        mProom= (TextView)findViewById(R.id.Proom_text);
        mDname= (TextView)findViewById(R.id.Dname_text);
        mPro_timetable = (Button)findViewById(R.id.Pro_timetable);
        mPstatus = (TextView)findViewById(R.id.Pstatus_text);

        Intent intent = getIntent();

        proName1 = intent.getStringExtra("py");
        proName2 = intent.getStringExtra("pc");
        proName3 = intent.getStringExtra("pl");
        proName4 = intent.getStringExtra("pj");
        proName5 = intent.getStringExtra("ps");
        proName6 = intent.getStringExtra("pk");
        proName7 = intent.getStringExtra("pz");

        if(proName1!=null) {
            proName = proName1;

        }else if(proName2!=null){
            proName = proName2;

        }else if(proName3!=null){
            proName = proName3;

        }else if(proName4!=null){
            proName = proName4;

        }else if(proName5!=null){
            proName = proName5;

        }else if(proName6!=null){
            proName = proName6;

        }else if(proName7!=null){
            proName = proName7;
        }

        GetData_professor task = new GetData_professor(); //교수 정보를 가져오기 위해서
        task.execute("http://" + IP_ADDRESS + "/get_pro.php", proName);//AsyncTask 기능 실행

        if(proName.equals(proName1))
        {   proNum = "P01";

        }else if(proName.equals(proName2)){
            proNum = "p02";

        }else if(proName.equals(proName3)){
            proNum = "p03";

        }else if(proName.equals(proName4)){
            proNum = "p04";

        }else if(proName.equals(proName5)){
            proNum = "p05";

        }else if(proName.equals(proName6)){
            proNum = "p06";

        }else if(proName.equals(proName7)){
            proNum = "p07";

        }
        GetDataPro result_pro_course = new GetDataPro(); //교수 시간표를 보기 위해서
        AsyncTask<String, Void, String> at = result_pro_course.execute("http://" + IP_ADDRESS + "/search_pro_course.php", proNum);//AsyncTask 기능 실행

        try {
            result_pro = at.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("result_pro",result_pro);

        mPro_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), TimetableProInfor.class);
                intent.putExtra("proName",proName);
                startActivity(intent);

            }
        });

   }

    private class GetDataPro extends AsyncTask<String, Void, String>{ //교수 시간표를 보기 위한 AsyncTask.

        ProgressDialog progressDialog;//사용자에게 실시간 진행 상태를 알릴수 있다.
        String errorString = null;

        //<실시간 진행 상태 >
        @Override
        //--작업시작, progressDialog 객체를 생성하고 시작한다.--
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ProfessorActivity.this,
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

    private class GetData_professor extends AsyncTask<String, Void, String> { //교수 정보를 가져오기 위한 AsyncTask

        ProgressDialog progressDialog;//사용자에게 실시간 진행 상태를 알릴수 있다.
        String errorString = null;

        //<실시간 진행 상태 >
        @Override
        //--작업시작, progressDialog 객체를 생성하고 시작한다.--
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ProfessorActivity.this,
                    "Please Wait", null, true, true);
        }

        //<디버깅 결과 창에 json값으로 세팅 >
        @Override
        //--작업종료, progressDialog 종료 기능을 구현한다.--
        protected void onPostExecute(String result) { //doInBackground에서 리턴한 값(총 json값)을 새로운 result 문자열에 삽입
            super.onPostExecute(result);
            Log.d("pro_result",result);
            if (result != null) { //받아온 json값이 널이 아니면
                mJsonString = result; //json 값을 mJsonString 변수에 넣고
                showResult(); //결과 값을 출력하는 함수 호출
            }
            progressDialog.dismiss();

        }

        //http(php)와 연결하여 값을 보내고 받는 부분
        @Override
        //--작업진행중, progressDialog의 진행 정도를 표현해 준다.--
        protected String doInBackground(String... params) { //execute의 매개변수를 받아와서 사용

            String serverURL = params[0];   //"http://"+IP_ADDRESS+"/search.php"와 "http://"+IP_ADDRESS+"/getjson.php" 값이 매개변수로 날아옴
            String postParameters = "Pname=" + params[1];//Keyword2(입력받은 학번값)와 "" 값이 매개변수로 날아옴
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
                String pro_information =sb.toString().trim();
                Log.d("pro_information",pro_information);
                return pro_information;

            } catch (Exception e) {//에러가 발생하면

                Log.d(TAG, "InsertData: Error ", e);//에러 값을 e에 넣는다.
                errorString = e.toString();//e(에러 값)을 문자열로 변환하고 errorString 변수에 넣는다.
                return null;
            }
        }
    }


    private void showResult() {
        String TAG_JSON = "Professor";          //Student에 저장되어 있는 값들(전체 값)을 TAG_JSON 변수에 저장
        String TAG_PNUMBER = "Pnumber";     //Snumber로 저장되어 있는 값들(전체 값)을 TAG_SNUMBER 변수에 저장
        String TAG_PNAME = "Pname";     //Snumber로 저장되어 있는 값들(전체 값)을 TAG_SNUMBER 변수에 저장
        String TAG_PPOSI = "Pposi";     //Rnumber로 저장되어 있는 값들(전체 값)을 TAG_RNUMBER 변수에 저장
        String TAG_PTEL1 = "Ptel1";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_PTEL2 = "Ptel2";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_PTEL3 = "Ptel3";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_PEMAIL1 = "Pemail1";          //Stime로 저장되어 있는 값들(전체 값)을 TAG_STIME 변수에 저장
        String TAG_PEMAIL2 = "Pemail2";          //Stime로 저장되어 있는 값들(전체 값)을 TAG_STIME 변수에 저장
        String TAG_PROOM = "Proom";          //Etime로 저장되어 있는 값들(전체 값)을 TAG_ETIME 변수에 저장
        String TAG_DNAME = "Dname";
        String TAG_PSTATUS = "Pstatus";


        //JSONObject JSON형태의 데이터 관리
        try {
            JSONObject jsonObject = new JSONObject(mJsonString); //json값을 저장하고 있는 변수(mJsonString)로 jsonObject 객체를 초기화
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);//Student에 저장되어 있는 값들(전체 값)을 jsonArray에 넣음

            for (int i = 0; i < jsonArray.length(); i++) {//student에 저장되어 있는 값만큼 반복

                JSONObject item = jsonArray.getJSONObject(i);//jsonArray에서 JSONObject값을 배열 값들 수만큼 반복하면서 가져와 item에 저장

                String Pnumber = item.getString(TAG_PNUMBER);//item에서 TAG_PNUMBER(Pnumber)값만 가져와 Pnumber 변수에 넣는다.
                String Pname = item.getString(TAG_PNAME);//item에서 TAG_PNAME(Pname)값만 가져와 Pname 변수에 넣는다.
                String Pposi = item.getString(TAG_PPOSI);//item에서 TAG_PPOSI(Pposi)값만 가져와 Pposi 변수에 넣는다.
                String Ptel1 = item.getString(TAG_PTEL1);//item에서 TAG_PTEL1(Ptel1)값만 가져와 Ptel1 변수에 넣는다.
                String Ptel2 = item.getString(TAG_PTEL2);//item에서 TAG_PTEL2(Ptel2)값만 가져와 Ptel2 변수에 넣는다.
                String Ptel3 = item.getString(TAG_PTEL3);//item에서 TAG_PTEL3(Ptel3)값만 가져와 Ptel3 변수에 넣는다.
                String Pemail1 = item.getString(TAG_PEMAIL1);//item에서 TAG_PEMAIL1(Pemail1)값만 가져와 Pemail1 변수에 넣는다.
                String Pemail2 = item.getString(TAG_PEMAIL2);//item에서 TAG_PEMAIL2(Pemail2)값만 가져와 Pemail2 변수에 넣는다.
                String Proom = item.getString(TAG_PROOM);//item에서 TAG_PROOM(Proom)값만 가져와 Proom 변수에 넣는다.
                String Dname = item.getString(TAG_DNAME);//item에서 TAG_DNAME(Dname)값만 가져와 Dname 변수에 넣는다.
                String Pstatus = item.getString(TAG_PSTATUS);//item에서 TAG_PSTATUS(Pstatus)값만 가져와 Pstatus 변수에 넣는다.

                PersonalData personalData = new PersonalData(); //PersonalData 클래스 대해 personalData 객체를 만든다.

                personalData.setProfessor_Number(Pnumber);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.
                personalData.setProfessor_Pname(Pname);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.
                personalData.setProfessor_Pposi(Pposi);//personalData 객체의 setStudent_Rnumber메소드를 호출하여 매개변수로 Rnumber값을 전달한다.
                personalData.setProfessor_Ptel1(Ptel1);//personalData 객체의 setStudent_Sub메소드를 호출하여 매개변수로 Sub값을 전달한다.
                personalData.setProfessor_Ptel2(Ptel2);//personalData 객체의 setStudent_Sub메소드를 호출하여 매개변수로 Sub값을 전달한다.
                personalData.setProfessor_Ptel3(Ptel3);//personalData 객체의 setStudent_Sub메소드를 호출하여 매개변수로 Sub값을 전달한다.
                personalData.setProfessor_Pemail1(Pemail1);//personalData 객체의 setStudent_Stime메소드를 호출하여 매개변수로 Stime값을 전달한다.
                personalData.setProfessor_Pemail2(Pemail2);//personalData 객체의 setStudent_Stime메소드를 호출하여 매개변수로 Stime값을 전달한다.
                personalData.setProfessor_Proom(Proom);//personalData 객체의 setStudent_Etime메소드를 호출하여 매개변수로 Etime값을 전달한다.
                personalData.setDepartment_Dname(Dname);//personalData 객체의 setStudent_Etime메소드를 호출하여 매개변수로 Etime값을 전달한다.
                personalData.setProfessor_Pstatus(Pstatus);//personalData 객체의 setStudent_Etime메소드를 호출하여 매개변수로 Etime값을 전달한다.

                mPname.setText(personalData.getProfessor_Pname());
                mPposi.setText(personalData.getProfessor_Pposi());
                mPtel1.setText(personalData.getProfessor_Ptel1());
                mPtel2.setText(personalData.getProfessor_Ptel2());
                mPtel3.setText(personalData.getProfessor_Ptel3());
                mPemail1.setText(personalData.getProfessor_Pemail1());
                mPemail2.setText(personalData.getProfessor_Pemail2());
                mProom.setText(personalData.getProfessor_Proom());
                mDname.setText(personalData.getDepartment_Dname());
                mPstatus.setText(personalData.getProfessor_Pstatus());


            }

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }
}