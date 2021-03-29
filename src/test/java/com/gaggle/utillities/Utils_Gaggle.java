package com.gaggle.utillities;


import com.gaggle.pojo.Fields;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils_Gaggle {


    public static String getJsonPath(Response response, String key) {
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getString(key);

    }

    public static String iSO_CurrentTime(){
        Date date = new Date(System.currentTimeMillis());

        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getDefault());
        String iSO8601_time = sdf.format(date);


        return iSO8601_time;
    }
    public static Fields bodyForPost(String message, String timestamp){
        Fields body=new Fields(message,timestamp);
        return body;


    }


}
