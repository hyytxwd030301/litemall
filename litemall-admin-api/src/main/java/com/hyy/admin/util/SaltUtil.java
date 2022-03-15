package com.hyy.admin.util;

import java.util.Random;

public class SaltUtil {
    private String salt;

//    public static void main(String[] args) {
//        SaltUtil saltUtil = new SaltUtil();
//        String salt = saltUtil.getSalt();
//        System.out.println(salt);
//    }
    public String getSalt(){
        char[] content={'1','2','3','4','5','6','7','8','9','0','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String salt=new String();
        for (int i=0;i<10;i++){
            Random random = new Random();
            int index = random.nextInt(content.length);
            salt=salt+content[index];
        }
        this.salt=salt;
        return salt;
    }
}
