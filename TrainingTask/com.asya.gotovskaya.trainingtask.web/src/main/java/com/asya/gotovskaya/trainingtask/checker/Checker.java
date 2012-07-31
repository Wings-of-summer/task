package com.asya.gotovskaya.trainingtask.checker;

import java.util.List;

/**
 * @author asya
 */
public class Checker {
    public static boolean checkValues (List<String> values){
        boolean answer = true;
        for (int i = 1; i < values.size(); i++){
            if(values.get(i).equals("")){
                return false;
            }
        }
        return answer;
    }
}
