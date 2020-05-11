package com.ansiyida.cgjl.util;

import android.widget.EditText;

/**
 * Created by chenyaoxiang on 2017/12/26.
 */
public class EditTextUtil {
    public static String getEditTextStr(EditText editText) {
        return editText.getText().toString().trim();
    }
    public static String getEditTextpass(EditText editText) {
        return editText.getText().toString();
    }
    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }
}
