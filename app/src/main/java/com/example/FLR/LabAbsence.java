package com.example.FLR;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.FLR.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LabAbsence extends AppCompatActivity {

    private static String IP_ADDRESS = "114.71.61.251/~cip1973/student";
    private static String TAG = "laststudent_db";
    private RadioButton existRadio,meetingRadio,lectureRadio,consultingRadio,goingOutRadio,eatingRadio,businessTripRadio,leaveWorkRadio;
    private String status,existText,meetingText,lectureText,consultingText,goingOutText,eatingText,businessTripText,leaveWorkText,proNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_absence);

        existRadio = (RadioButton) findViewById(R.id.radio_exist);
        meetingRadio = (RadioButton) findViewById(R.id.radio_meeting);
        lectureRadio = (RadioButton) findViewById(R.id.radio_during_a_lecture);
        consultingRadio = (RadioButton) findViewById(R.id.radio_consulting);
        goingOutRadio = (RadioButton) findViewById(R.id.radio_going_out);
        eatingRadio = (RadioButton) findViewById(R.id.radio_eating);
        businessTripRadio = (RadioButton) findViewById(R.id.radio_business_trip);
        leaveWorkRadio = (RadioButton) findViewById(R.id.radio_leave_work);

        existText = existRadio.getText().toString();
        meetingText = meetingRadio.getText().toString();
        lectureText = lectureRadio.getText().toString();
        consultingText = consultingRadio.getText().toString();
        goingOutText = goingOutRadio.getText().toString();
        eatingText = eatingRadio.getText().toString();
        businessTripText = businessTripRadio.getText().toString();
        leaveWorkText = leaveWorkRadio.getText().toString();

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //RadioButton radioButton = (RadioButton) findViewById(checkedId);

                if(checkedId == R.id.radio_exist){
                    //Toast.makeText(SubActivity.this, "1", Toast.LENGTH_SHORT).show();
                    status = existText;
                } else if(checkedId == R.id.radio_meeting){
                    //Toast.makeText(SubActivity.this, "2", Toast.LENGTH_SHORT).show();
                    status = meetingText;
                } else if (checkedId == R.id.radio_during_a_lecture) {
                    //Toast.makeText(SubActivity.this, "3", Toast.LENGTH_SHORT).show();
                    status = lectureText;
                } else if (checkedId == R.id.radio_consulting){
                    //Toast.makeText(SubActivity.this, "4", Toast.LENGTH_SHORT).show();
                    status = consultingText;
                }else if (checkedId == R.id.radio_going_out){
                    //Toast.makeText(SubActivity.this, "4", Toast.LENGTH_SHORT).show();
                    status = goingOutText;
                }else if (checkedId == R.id.radio_eating){
                    //Toast.makeText(SubActivity.this, "4", Toast.LENGTH_SHORT).show();
                    status = eatingText;
                }else if (checkedId == R.id.radio_business_trip){
                    //Toast.makeText(SubActivity.this, "4", Toast.LENGTH_SHORT).show();
                    status = businessTripText;
                }else if (checkedId == R.id.radio_leave_work){
                    //Toast.makeText(SubActivity.this, "4", Toast.LENGTH_SHORT).show();
                    status = leaveWorkText;
                }
            }
        });

        Button button = (Button) findViewById(R.id.choosebutton);
        button.setOnClickListener(new View.OnClickListener(){//클릭 리스너를 불러옴.

            @Override
            public void onClick(View v){//선택 버튼을 누르면 동작하는 기능.
                //status변수에 있는 값을 DB의 교수 테이블의 상태 필드에 업데이트 해주어야 함.
                //asyncTask 정의 및 호출
                proNum = ((MainActivity) MainActivity.context_main).Pro_ID;
                UpdatePro update = new UpdatePro();
                update.execute("http://" + IP_ADDRESS + "/professor_status_update.php", proNum,status);//AsyncTask 기능 실행

            }
        });
    }
    private class UpdatePro extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;//사용자에게 실시간 진행 상태를 알릴수 있다.
        String errorString = null;

        //<실시간 진행 상태 >
        @Override
        //--작업시작, progressDialog 객체를 생성하고 시작한다.--
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(LabAbsence.this,
                    "Please Wait", null, true, true);
        }

        @Override
        //--작업종료, progressDialog 종료 기능을 구현한다.--
        protected void onPostExecute(String result) { //doInBackground에서 리턴한 값(총 json값)을 새로운 result 문자열에 삽입
            super.onPostExecute(result);
            Toast.makeText(LabAbsence.this,result, Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }

        //http(php)와 연결하여 값을 보내고 받는 부분
        @Override
        //--작업진행중, progressDialog의 진행 정도를 표현해 준다.--
        protected String doInBackground(String... params) { //execute의 매개변수를 받아와서 사용

            String serverURL = params[0];   //"http://"+IP_ADDRESS+"/query.php"와 "http://"+IP_ADDRESS+"/getjson.php" 값이 매개변수로 날아옴
            String postParameters ="pro_num=" + params[1] + "&pro_status=" + params[2];//Keyword(입력받은 학번값)와 "" 값이 매개변수로 날아옴
            Log.d("postParameters", postParameters);

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
                Log.d(TAG, "response code - " + responseStatusCode);

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
                Log.d("sb",sb.toString().trim());

                return sb.toString().trim();

            }
            catch (Exception e) {//에러가 발생하면

                Log.d(TAG, "InsertData: Error ", e);//에러 값을 e에 넣는다.
                errorString = e.toString();//e(에러 값)을 문자열로 변환하고 errorString 변수에 넣는다.
                return null;
            }
        }
    }

}