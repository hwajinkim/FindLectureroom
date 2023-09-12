package com.example.FLR;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class TimetableClass extends AppCompatActivity {
    private static String IP_ADDRESS = "114.71.61.251/~cip1973/student";
    private static String TAG = "laststudent_db";
    private String mJsonString;
    private Schedule schedule = new Schedule();
    private Context context;
    private ImageView back;
    private TextView classname;
    private String classNum="",classNum8501="",classNum8513="",classNum8517="",classNum8521="",
            classNum8522="",classNum8527="",classNum8528="",classNum9501="",classNum9503="",classNum9506="";

    private AutoResizeTextView monday[]= new AutoResizeTextView[14];
    private AutoResizeTextView tuesday[]= new AutoResizeTextView[14];
    private AutoResizeTextView wednesday[]= new AutoResizeTextView[14];
    private AutoResizeTextView thursday[]= new AutoResizeTextView[14];
    private AutoResizeTextView friday[]= new AutoResizeTextView[14];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_n8517);

        context = this;
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SecondActivity.class);
                startActivity(intent);
            }
        });

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


        classname = (TextView) findViewById(R.id.classname);

        Intent intent = getIntent();

        classNum8501= intent.getStringExtra("n_8501");
        classNum8513= intent.getStringExtra("n_8513");
        classNum8517= intent.getStringExtra("n_8517");
        classNum8521= intent.getStringExtra("n_8521");
        classNum8522= intent.getStringExtra("n_8522");
        classNum8527= intent.getStringExtra("n_8527");
        classNum8528= intent.getStringExtra("n_8528");
        classNum9501= intent.getStringExtra("n_9501");
        classNum9503= intent.getStringExtra("n_9503");
        classNum9506= intent.getStringExtra("n_9506");

        if(classNum8501!=null) {
            classNum = classNum8501;
            classname.setText(classNum8501);

        }else if(classNum8513!=null){
            classNum = classNum8513;
            classname.setText(classNum8513);

        }else if(classNum8517!=null){
            classNum = classNum8517;
            classname.setText(classNum8517);

        }else if(classNum8521!=null){
            classNum = classNum8521;
            classname.setText(classNum8521);

        }else if(classNum8522!=null){
            classNum = classNum8522;
            classname.setText(classNum8522);

        }else if(classNum8527!=null){
            classNum = classNum8527;
            classname.setText(classNum8527);

        }else if(classNum8528!=null){
            classNum = classNum8528;
            classname.setText(classNum8528);

        }else if(classNum9501!=null){
            classNum = classNum9501;
            classname.setText(classNum9501);

        }else if(classNum9503!=null){
            classNum = classNum9503;
            classname.setText(classNum9503);

        }else if(classNum9506!=null){
            classNum = classNum9506;
            classname.setText(classNum9506);

        }

        GetData_class task = new GetData_class();
        task.execute( "http://"+IP_ADDRESS+"/get_class.php", classNum);
    }
    private class GetData_class extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;//사용자에게 실시간 진행 상태를 알릴수 있다.
        String errorString = null;

        //<실시간 진행 상태 >
        @Override
        //--작업시작, progressDialog 객체를 생성하고 시작한다.--
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(TimetableClass.this,
                    "Please Wait", null, true, true);
        }

        //<디버깅 결과 창에 json값으로 세팅 >
        @Override
        //--작업종료, progressDialog 종료 기능을 구현한다.--
        protected void onPostExecute(String result) { //doInBackground에서 리턴한 값(총 json값)을 새로운 result 문자열에 삽입
            super.onPostExecute(result);

            if (result.equals(classNum+"강의실 시간표 정보를 찾을 수 없습니다.")) {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            } else if (result == null) { //받아온 json값이 널이면
                Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show();

            } else {  // 받아온 json값이 널이 아니면

                mJsonString = result; //json 값을 mJsonString 변수에 넣고
                Log.d("mJsonString", mJsonString);
                showResult(); //결과 값을 출력하는 함수 호출
            }

            progressDialog.dismiss();

        }

        //http(php)와 연결하여 값을 보내고 받는 부분
        @Override
        //--작업진행중, progressDialog의 진행 정도를 표현해 준다.--
        protected String doInBackground(String... params) { //execute의 매개변수를 받아와서 사용

            String serverURL = params[0];   //"http://"+IP_ADDRESS+"/search.php"와 "http://"+IP_ADDRESS+"/getjson.php" 값이 매개변수로 날아옴
            String postParameters = "Rnum=" + params[1];//Keyword2(입력받은 학번값)와 "" 값이 매개변수로 날아옴
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
                String class_information =sb.toString().trim();
                Log.d("pro_information",class_information);
                return class_information;

            } catch (Exception e) {//에러가 발생하면

                Log.d(TAG, "InsertData: Error ", e);//에러 값을 e에 넣는다.
                errorString = e.toString();//e(에러 값)을 문자열로 변환하고 errorString 변수에 넣는다.
                return null;
            }
        }
    }
    private void showResult(){//json을 가공하여 DB 검색 결과 값 창을 띄우는 부분

        String TAG_JSON="Room_timetable";          //Student에 저장되어 있는 값들(전체 값)을 TAG_JSON 변수에 저장
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