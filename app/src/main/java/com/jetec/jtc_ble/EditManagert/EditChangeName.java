package com.jetec.jtc_ble.EditManagert;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EditChangeName implements TextWatcher {

    private EditText editText;

    public EditChangeName(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        int len = 15;
        byte[] bytes = String.valueOf(editable).getBytes();
        if (bytes.length > len) {
            byte[] newBytes = new byte[len];
            System.arraycopy(bytes, 0, newBytes, 0, len);
            String newStr = new String(newBytes);
            editText.setText(newStr);
        }
    }
}
