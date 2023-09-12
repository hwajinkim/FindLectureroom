package com.example.FLR;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class ClassroomReservation extends AppCompatActivity {
    private static String IP_ADDRESS = "114.71.61.251/~cip1973/student";
    private static String TAG = "laststudent_db";
    private CalendarView calendarView;
    private TextView tv_date,tv_name;
    private AlertDialog dialog;
    private EditText subscriberName,rentalDate,perposeOfUse,possible_rental;
    private Spinner numberOfPersonsSpinner, roomNumSpinner, rentalTimeSpinner;
    final static MainActivity mainActivity = new MainActivity();
    public static String s_id;
    private String stuName,sNumber,pNumber,rNumber,rTime,rStime,rEtime,rDate,pUse;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    private ImageView back;
    private String mJsonString;
    private View v;
    private String[] mobRtime;
    private Boolean boolResult;
    private String strResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_reservation);

        this.InitializeView();
        this.InitializeListener();

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SecondActivity.class);
                startActivity(intent);
            }
        });

        tv_date=(TextView)findViewById(R.id.tv_date);
        tv_name=(TextView)findViewById(R.id.tv_name);

        calendarView=(CalendarView)findViewById(R.id.calendarView);
        calendarView.setMinDate((new Date().getTime()));

        s_id = mainActivity.Stu_ID;


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                tv_date.setText(String.format("%d년 %d월 %d일",year,month+1,dayOfMonth));

                LayoutInflater inflater = getLayoutInflater();
                v = inflater.inflate(R.layout.schedule_screen, null);

                stuName = ((MainActivity) MainActivity.context_main).Stu_Name;
                subscriberName = (EditText) v.findViewById(R.id.subscriber_name);
                subscriberName.setText(stuName);

                roomNumSpinner = (Spinner)v.findViewById(R.id.room_number);
                ArrayAdapter roomNumAdapter = ArrayAdapter.createFromResource(ClassroomReservation.this,R.array.room_num, android.R.layout.simple_spinner_item);
                roomNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                roomNumSpinner.setAdapter(roomNumAdapter);

                rentalTimeSpinner = (Spinner)v.findViewById(R.id.rental_time);
                ArrayAdapter rentalTimeAdapter = ArrayAdapter.createFromResource(ClassroomReservation.this,R.array.rental_time, android.R.layout.simple_spinner_item);
                rentalTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                rentalTimeSpinner.setAdapter(rentalTimeAdapter);

                rentalDate = (EditText) v.findViewById(R.id.rental_date);
                rentalDate.setText(String.format("%d-%d-%d",year,month+1,dayOfMonth));

                perposeOfUse = (EditText) v.findViewById(R.id.perpose_of_use);

                possible_rental = (EditText) v.findViewById(R.id.possible_rental);

                AlertDialog.Builder alert = new AlertDialog.Builder(ClassroomReservation.this);
                alert.setView(v);

                dialog = alert.create();
                dialog.show();
            }
        });
    }
    public void InitializeView()
    {
        LayoutInflater inflater = getLayoutInflater();
        v = inflater.inflate(R.layout.schedule_screen, null);
        rentalDate = (EditText) v.findViewById(R.id.rental_date);
    }
    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                int month = monthOfYear+1;
                rentalDate.setText(year + "년" + month + "월" + dayOfMonth + "일");
            }
        };
    }
    public void OnClickHandler(View view)
    {
        Calendar cal = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, cal.get(Calendar.YEAR)
                , cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        dialog.show();
    }


    public void onSearchButtonClicked(View view) {
        //EditText에서 받은 내용을 서버에 보냄

        rNumber = roomNumSpinner.getSelectedItem().toString();
        rTime = rentalTimeSpinner.getSelectedItem().toString();
        mobRtime = rTime.split("~");
        rStime=mobRtime[0];
        rEtime=mobRtime[1];
        rDate = rentalDate.getText().toString();

        if(rNumber.equals(""))
        {
            Toast.makeText(ClassroomReservation.this, "강의실 번호를 입력하세요." ,Toast.LENGTH_SHORT).show();
        }
        else if(rStime.equals(""))
        {
            Toast.makeText(ClassroomReservation.this, "대여 시작 시간을 입력하세요." ,Toast.LENGTH_SHORT).show();
        }
        else if(rEtime.equals(""))
        {
            Toast.makeText(ClassroomReservation.this, "대여 종료 시간을 입력하세요." ,Toast.LENGTH_SHORT).show();
        }
        else if(rDate.equals(""))
        {
            Toast.makeText(ClassroomReservation.this, "대여 날짜를 입력하세요." ,Toast.LENGTH_SHORT).show();
        }else
        {
            SearchData sData = new SearchData();
            sData.execute("http://"+IP_ADDRESS+"/reser_search.php",rNumber,rStime,rEtime,rDate);

        }
    }
    private class SearchData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;//사용자에게 실시간 진행 상태를 알릴수 있다.
        String errorString = null;

        //<실시간 진행 상태 >
        @Override
        //--작업시작, progressDialog 객체를 생성하고 시작한다.--
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ClassroomReservation.this,
                    "Please Wait", null, true, true);
        }

        @Override
        //--작업종료, progressDialog 종료 기능을 구현한다.--
        protected void onPostExecute(String result) { //doInBackground에서 리턴한 값(총 json값)을 새로운 result 문자열에 삽입
            super.onPostExecute(result);

            Toast.makeText(ClassroomReservation.this, "강의실 번호 : "+rNumber
                    +"\n대여 시작 시간 : "+rStime+"\n대여 종료 시간 : "+rEtime+"\n대여 날짜 : "+rDate+"\n로 검색 되었습니다. ", Toast.LENGTH_LONG).show();
            char tmp;
            for(int i=0; i<result.length(); i++){
                tmp = result.charAt(i);
                 boolResult = Character.isDigit(tmp);
            }
            if (boolResult.equals(true)) { //받아온 json 문자열 값이 숫자로만 구성되어 있으면
                possible_rental.setText(result);
                int result_num = Integer.parseInt(result);
                numberOfPersonsSpinner = (Spinner) v.findViewById(R.id.number_of_persons);
                ArrayList<String> list = new ArrayList<String>();
                for (int j = 1; j <= result_num; j++) {
                    list.add(Integer.toString(j));
                }
                ArrayAdapter<String> numOfPersonsAdapter = new ArrayAdapter<String>(
                        ClassroomReservation.this, //context(액티비티 인스턴스)
                        android.R.layout.simple_list_item_1, // 한 줄에 하나의 텍스트 아이템만 보여주는 레이아웃 파일
                        // 한 줄에 보여지는 아이템 갯수나 구성을 변경하려면 여기에 새로만든 레이아웃을 지정하면 됩니다.
                        list  // 데이터가 저장되어 있는 ArrayList 객체
                );
                numberOfPersonsSpinner.setAdapter(numOfPersonsAdapter);

            }else if(boolResult == false){ //받아온 json 문자열 값이 숫자로만 구성되어 있지 않으면
                mJsonString = result; //json 값을 mJsonString 변수에 넣고
                Log.d("mJsonString", mJsonString);
                showResult(); //결과 값을 출력하는 함수 호출
            }
            strResult = boolResult.toString();
            Log.d("strResult",strResult);


//            if(result != null){
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);//SecondActivity로 이동하는 내용을 intent 객체에 담음
//                startActivity(intent);//이동을 시작해라
//            }
            progressDialog.dismiss();
        }

        //http(php)와 연결하여 값을 보내고 받는 부분
        @Override
        //--작업진행중, progressDialog의 진행 정도를 표현해 준다.--
        protected String doInBackground(String... params) { //execute의 매개변수를 받아와서 사용

            String serverURL = params[0];   //"http://"+IP_ADDRESS+"/query.php"와 "http://"+IP_ADDRESS+"/getjson.php" 값이 매개변수로 날아옴
            String postParameters ="r_num=" + params[1] + "&r_stime=" + params[2] + "&r_etime=" + params[3] + "&r_date=" + params[4];//Keyword(입력받은 학번값)와 "" 값이 매개변수로 날아옴
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
    private void showResult() {
        String TAG_JSON = "Book";          //Book에 저장되어 있는 값들(전체 값)을 TAG_JSON 변수에 저장
        String TAG_PEOPLE_NUMBER = "People_number";     //People_number로 저장되어 있는 값들(전체 값)을 TAG_SNUMBER 변수에 저장
        String TAG_ROOM_CAPACITY = "Room_capacity";     //Room_capacity로 저장되어 있는 값들(전체 값)을 TAG_RNUMBER 변수에 저장

        try {
            JSONObject jsonObject = new JSONObject(mJsonString); //json값을 저장하고 있는 변수(mJsonString)로 jsonObject 객체를 초기화
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);//Book에 저장되어 있는 값들(전체 값)을 jsonArray에 넣음
            int posibleNum=0, rpn=0,tpn=0;
            for (int i = 0; i < jsonArray.length(); i++) {//Book에 저장되어 있는 값만큼 반복

                JSONObject item = jsonArray.getJSONObject(i);//jsonArray에서 JSONObject값을 배열 값들 수만큼 반복하면서 가져와 item에 저장

                String People_number = item.getString(TAG_PEOPLE_NUMBER);//item에서 TAG_PEOPLE_NUMBER(People_number)값만 가져와 People_number 변수에 넣는다.
                String Room_capacity = item.getString(TAG_ROOM_CAPACITY);//item에서 TAG_ROOM_CAPACITY(Room_capacity)값만 가져와 Room_capacity 변수에 넣는다.

                PersonalData personalData = new PersonalData(); //PersonalData 클래스 대해 personalData 객체를 만든다.

                personalData.setReservationPeopleNum(People_number);//personalData 객체의 setReservationPeopleNum메소드를 호출하여 매개변수로 People_number값을 전달한다.
                personalData.setLectureRoomCapacity(Room_capacity);//personalData 객체의 setLectureRoomCapacity메소드를 호출하여 매개변수로 Room_capacity값을 전달한다.

                String [] rentPeopleNum = new String[jsonArray.length()];
                rentPeopleNum[i] = personalData.getReservationPeopleNum();

                rpn += Integer.parseInt(rentPeopleNum[i]);
                Log.d("rpNum",rentPeopleNum[i]);

                tpn = Integer.parseInt(personalData.getLectureRoomCapacity());

                //possible_rental.setText();
            }
            Log.d("rpn",Integer.toString(rpn));
            Log.d("tpn",Integer.toString(tpn));
            posibleNum = tpn-rpn;
            Log.d("posibleNum",Integer.toString(posibleNum));
            possible_rental.setText(Integer.toString(posibleNum));

            numberOfPersonsSpinner = (Spinner) v.findViewById(R.id.number_of_persons);
            ArrayList<String> list = new ArrayList<String>();
            for (int i = 1; i <= posibleNum; i++) {
                list.add(Integer.toString(i));
            }
            ArrayAdapter<String> numOfPersonsAdapter = new ArrayAdapter<String>(
                    ClassroomReservation.this, //context(액티비티 인스턴스)
                    android.R.layout.simple_list_item_1, // 한 줄에 하나의 텍스트 아이템만 보여주는 레이아웃 파일
                    // 한 줄에 보여지는 아이템 갯수나 구성을 변경하려면 여기에 새로만든 레이아웃을 지정하면 됩니다.
                    list  // 데이터가 저장되어 있는 ArrayList 객체
            );
            numberOfPersonsSpinner.setAdapter(numOfPersonsAdapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }


    public void onRequestButtonClicked(View view) {
        //EditText에서 받은 내용을 서버에 보냄
        sNumber = s_id;
        pNumber = numberOfPersonsSpinner.getSelectedItem().toString();
        rNumber = roomNumSpinner.getSelectedItem().toString();
        rTime = rentalTimeSpinner.getSelectedItem().toString();
        mobRtime = rTime.split("~");
        rStime=mobRtime[0];
        rEtime=mobRtime[1];
        rDate = rentalDate.getText().toString();
        pUse = perposeOfUse.getText().toString();

       if(sNumber.equals(""))
       {
           Toast.makeText(ClassroomReservation.this, "예약자명을 입력하세요." ,Toast.LENGTH_SHORT).show();
       }
       else if(pNumber.equals(""))
       {
           Toast.makeText(ClassroomReservation.this, "인원 수를 입력하세요." ,Toast.LENGTH_SHORT).show();
       }
       else if(rNumber.equals(""))
       {
           Toast.makeText(ClassroomReservation.this, "강의실 번호를 입력하세요." ,Toast.LENGTH_SHORT).show();
       }
       else if(rStime.equals(""))
       {
           Toast.makeText(ClassroomReservation.this, "대여 시작 시간을 입력하세요." ,Toast.LENGTH_SHORT).show();
       }
       else if(rEtime.equals(""))
       {
           Toast.makeText(ClassroomReservation.this, "대여 종료 시간을 입력하세요." ,Toast.LENGTH_SHORT).show();
       }
       else if(rDate.equals(""))
       {
           Toast.makeText(ClassroomReservation.this, "대여 날짜를 입력하세요." ,Toast.LENGTH_SHORT).show();
       }
       else if(pUse.equals(""))
       {
           Toast.makeText(ClassroomReservation.this, "사용 목적을 입력하세요." ,Toast.LENGTH_SHORT).show();
       }
       else
       {
           AddData aData = new AddData();
           aData.execute("http://"+IP_ADDRESS+"/reservation.php",sNumber,pNumber,rNumber,rStime,rEtime,rDate,pUse);

           dialog.dismiss();
       }
    }
    private class AddData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;//사용자에게 실시간 진행 상태를 알릴수 있다.
        String errorString = null;

        //<실시간 진행 상태 >
        @Override
        //--작업시작, progressDialog 객체를 생성하고 시작한다.--
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ClassroomReservation.this,
                    "Please Wait", null, true, true);
        }

        @Override
        //--작업종료, progressDialog 종료 기능을 구현한다.--
        protected void onPostExecute(String result) { //doInBackground에서 리턴한 값(총 json값)을 새로운 result 문자열에 삽입
            super.onPostExecute(result);

            Toast.makeText(ClassroomReservation.this, "예약자명 : "+stuName+"\n인원 수 : "+pNumber+"\n강의실 번호 : "+rNumber
                            +"\n대여 시작 시간 : "+rStime+"\n대여 종료 시간 : "+rEtime+"\n대여 날짜 : "+rDate+"\n사용 목적 : "+pUse+"\n"+result, Toast.LENGTH_LONG).show();

//            if(result != null){
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);//SecondActivity로 이동하는 내용을 intent 객체에 담음
//                startActivity(intent);//이동을 시작해라
//            }
            progressDialog.dismiss();
        }

        //http(php)와 연결하여 값을 보내고 받는 부분
        @Override
        //--작업진행중, progressDialog의 진행 정도를 표현해 준다.--
        protected String doInBackground(String... params) { //execute의 매개변수를 받아와서 사용

            String serverURL = params[0];   //"http://"+IP_ADDRESS+"/query.php"와 "http://"+IP_ADDRESS+"/getjson.php" 값이 매개변수로 날아옴
            String postParameters = "s_num=" + params[1] + "&p_num=" + params[2] +"&r_num=" + params[3] + "&r_stime=" + params[4] + "&r_etime=" + params[5] + "&r_date=" + params[6] + "&p_use=" + params[7];//Keyword(입력받은 학번값)와 "" 값이 매개변수로 날아옴
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