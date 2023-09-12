package com.example.FLR;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import java.util.ArrayList;

public class ClassroomReservationList extends AppCompatActivity {

    private static String IP_ADDRESS = "114.71.61.251/~cip1973/student";
    private static String TAG = "laststudent_db";
    final static MainActivity mainActivity = new MainActivity();
    public static String s_id;
    private String mJsonString;
    private String student_name;
    private TextView mStudent_name;
    private ImageView back;
    private RecyclerView mRecyclerView;
    private ArrayList<PersonalData> mArrayList; //학생들의 정보를 저장할 ArrayList
    private UsersAdapterBook mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_reservation_list);

        s_id = mainActivity.Stu_ID;

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SecondActivity.class);
                startActivity(intent);
            }
        });

        student_name = ((MainActivity) MainActivity.context_main).Stu_Name;
        mStudent_name = (TextView) findViewById(R.id.student_name);
        mStudent_name.setText(student_name);

        mRecyclerView = (RecyclerView) findViewById(R.id.listView_main_list);//DB 검색 결과 값 창을 mRecyclerView 변수로 선언
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//여러 타입의 리스트들을 지원해주는 함수를 호출
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mArrayList = new ArrayList<>(); //PersonalData 클래스에 대한 배열 리스트를 생성

        mAdapter = new UsersAdapterBook(this,mArrayList);//UsersAdater는 mRecyclerView를 정해진 레이아웃 형태로 화면에 출력해주기 위해 생성한 클래스이다.
        //UsersAdater에 대한 mAdater객체를 생성
        //학생정보가 저장되어 있는 mArrayList를 mAdapter객체를 초기화하기 위한 매개변수로 보냄.
        //UesrAdapter 클래스로 이동
        mRecyclerView.setAdapter(mAdapter);//mRecyclerView를 UsersAdater에서 지정한 형식으로 세팅한다.(mAdapter를 매개변수로 보내줌)

        GetData data = new GetData();
        data.execute("http://" + IP_ADDRESS + "/get_book_list.php", s_id);

    }

    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;//사용자에게 실시간 진행 상태를 알릴수 있다.
        String errorString = null;

        //<실시간 진행 상태 >
        @Override
        //--작업시작, progressDialog 객체를 생성하고 시작한다.--
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ClassroomReservationList.this,
                    "Please Wait", null, true, true);
        }

        @Override
        //--작업종료, progressDialog 종료 기능을 구현한다.--
        protected void onPostExecute(String result) { //doInBackground에서 리턴한 값(총 json값)을 새로운 result 문자열에 삽입
            super.onPostExecute(result);

            if (result != null) { //받아온 json값이 널이면

                mJsonString = result; //json 값을 mJsonString 변수에 넣고
                showResult(); //결과 값을 출력하는 함수 호출            } else {  // 받아온 json값이 널이 아니면
            }
            progressDialog.dismiss();
        }

        //http(php)와 연결하여 값을 보내고 받는 부분
        @Override
        //--작업진행중, progressDialog의 진행 정도를 표현해 준다.--
        protected String doInBackground(String... params) { //execute의 매개변수를 받아와서 사용

            String serverURL = params[0];   //"http://"+IP_ADDRESS+"/query.php"와 "http://"+IP_ADDRESS+"/getjson.php" 값이 매개변수로 날아옴
            String postParameters = "Snum=" + params[1];//Keyword(입력받은 학번값)와 "" 값이 매개변수로 날아옴
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
                Log.d("sb", sb.toString().trim());

                return sb.toString().trim();

            } catch (Exception e) {//에러가 발생하면

                Log.d(TAG, "InsertData: Error ", e);//에러 값을 e에 넣는다.
                errorString = e.toString();//e(에러 값)을 문자열로 변환하고 errorString 변수에 넣는다.
                return null;
            }
        }
    }

    private void showResult() {
        String TAG_JSON = "Book_list";          //Student에 저장되어 있는 값들(전체 값)을 TAG_JSON 변수에 저장
        String TAG_SNUMBER = "Snumber";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_PNUMBER = "Pnumber";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_RNUMBER = "Rnumber";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_RSTIME = "Rstime";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_RETIME = "Retime";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_RDATE = "Rdate";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_PUSE = "Puse";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_BSTATUS = "Bstatus";              //Sub로 저장되어 있는 값들(전체 값)을 TAG_SUB 변수에 저장
        String TAG_CREASON = "Creason";

        //JSONObject JSON형태의 데이터 관리
        try {

            JSONObject jsonObject = new JSONObject(mJsonString); //json값을 저장하고 있는 변수(mJsonString)로 jsonObject 객체를 초기화
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);//Student에 저장되어 있는 값들(전체 값)을 jsonArray에 넣음

            for (int i = 0; i < jsonArray.length(); i++) {//student에 저장되어 있는 값만큼 반복

                JSONObject item = jsonArray.getJSONObject(i);//jsonArray에서 JSONObject값을 배열 값들 수만큼 반복하면서 가져와 item에 저장

                String Snumber = item.getString(TAG_SNUMBER);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Pnumber = item.getString(TAG_PNUMBER);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Rnumber = item.getString(TAG_RNUMBER);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Rstime = item.getString(TAG_RSTIME);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Retime = item.getString(TAG_RETIME);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Rdate = item.getString(TAG_RDATE);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Puse = item.getString(TAG_PUSE);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Bstatus = item.getString(TAG_BSTATUS);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.
                String Creason = item.getString(TAG_CREASON);//item에서 TAG_SUB(Sub)값만 가져와 Sub 변수에 넣는다.

                Log.d("Snumber",Snumber);
                Log.d("Pnumber",Pnumber);
                Log.d("Rnumber",Rnumber);
                Log.d("Rstime",Rstime);
                Log.d("Retime",Retime);
                Log.d("Rdate",Rdate);
                Log.d("Puse",Puse);
                Log.d("Bstatus",Bstatus);
                Log.d("Creason",Creason);


                PersonalData personalData = new PersonalData(); //PersonalData 클래스 대해 personalData 객체를 만든다.

                personalData.setStudent_Name(student_name);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.
                personalData.setReservationPeopleNum(Pnumber);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.
                personalData.setReservationRoomNum(Rnumber);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.
                personalData.setReservationRentalStartTime(Rstime);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.
                personalData.setReservationRentalEndTime(Retime);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.
                personalData.setReservationRentalDate(Rdate);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.
                personalData.setReservationPerposeOfUse(Puse);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.
                personalData.setReservationBookStatus(Bstatus);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.
                personalData.setReservationCancelReason(Creason);//personalData 객체의 setStudent_Snumber메소드를 호출하여 매개변수로 Snumber값을 전달한다.

                mArrayList.add(personalData);//학생의 정보를 저장할 mArrayList변수에 personal 객체를 추가함.
                mAdapter.notifyDataSetChanged();//mAdapter(mRecyclerView(DB 결과 창)를 정해진 레이아웃 형태로 화면에 출력해주기 위해 생성한 클래스의 객체)를 새로고침한다.

            }

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
}