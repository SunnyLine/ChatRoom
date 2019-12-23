package com.pullein.server;

public class StringUtil {
    public static boolean equals(String s1,String s2){
        if (s1 == null || s2 == null){
            return false;
        }
        return s1.equals(s2);
    }
}
