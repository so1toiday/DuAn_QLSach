package com.example.duan_qlsach.function;

import android.widget.EditText;

public class checkform {
    public static boolean isEmpty(EditText... editText) {
        for (int i = 0; i < editText.length; i++) {
            if (editText[i].getText().toString().equals("")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isphone(EditText... editText) {
        for (int i = 0; i < editText.length; i++) {
            String phone = "0[0-9]{9,10}";
            if (editText[i].getText().toString().matches(phone)) return true;
        }
        return false;
    }
}
