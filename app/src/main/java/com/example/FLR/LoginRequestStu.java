package com.example.FLR;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequestStu extends StringRequest {

    final static private String URL = "http://114.71.61.251/~cip1973/student/login_stu.php";
    private Map<String, String> map;


    public LoginRequestStu(String stuID, String stuPassword, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("student_id",stuID);
        map.put("student_password", stuPassword);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
