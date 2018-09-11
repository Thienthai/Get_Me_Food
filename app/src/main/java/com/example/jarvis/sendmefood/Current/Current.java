package com.example.jarvis.sendmefood.Current;

import com.example.jarvis.sendmefood.Model.User;

public class Current {
    public static User usrCurrent;

    public static String statusCode(String code){
        if(code.equals("0")){
            return "your order is placed";
        }else if(code.equals("1")){
            return "your order is on the way";
        }else{
            return "your order is already shipped";
        }
    }

}
