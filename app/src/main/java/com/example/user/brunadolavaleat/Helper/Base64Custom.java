package com.example.user.brunadolavaleat.Helper;

import android.util.Base64;

public class Base64Custom {

    public static String codificateBase64(String text) {
        return Base64.encodeToString(text.getBytes(), Base64.DEFAULT).replaceAll("(\\n\\r)", "");
    }

    public static String decodificateBase64(String codificatedText){
        return new String(Base64.decode(codificatedText, Base64.DEFAULT));
    }
}

