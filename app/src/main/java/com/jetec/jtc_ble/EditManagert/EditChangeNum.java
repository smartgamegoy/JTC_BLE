package com.jetec.jtc_ble.EditManagert;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;
import com.jetec.jtc_ble.SupportFunction.LogMessage;

public class EditChangeNum implements TextWatcher {

    private String TAG = "EditChangeNum";
    private EditText editText;
    private String name;
    private LogMessage logMessage = new LogMessage();
    private boolean last;

    public EditChangeNum(EditText editText, String name) {
        this.editText = editText;
        this.name = name;
        logMessage.showmessage(TAG, "name = " + name);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void afterTextChanged(Editable editable) {
        String num = editText.getText().toString().trim();

        if (name.matches("T")) {
            if (!num.matches("-") && !num.matches("") && num.lastIndexOf(".") != -1){
                if(Float.valueOf(num) > 65){
                    last = true;
                    editText.setText("65");
                }
                else if(Float.valueOf(num) < -10){
                    last = true;
                    editText.setText("-10");
                }
                else {
                    if(last){
                        last = false;
                        int selEndIndex = editText.getText().length();
                        Selection.setSelection(editable, selEndIndex);
                    }
                    else {
                        int index = editText.getSelectionStart();
                        Selection.setSelection(editable, index);
                    }
                }
            }
        }
        else if (name.matches("H")) {
            if (!num.matches("-") && !num.matches("")){
                if (Integer.valueOf(num) > 100) {
                    last = true;
                    editText.setText("100");
                }
                else if (Integer.valueOf(num) < 0) {
                    last = true;
                    editText.setText("0");
                }
                else {
                    if(last){
                        last = false;
                        int selEndIndex = editText.getText().length();
                        Selection.setSelection(editable, selEndIndex);
                    }
                    else {
                        int index = editText.getSelectionStart();
                        Selection.setSelection(editable, index);
                    }
                }
            }
        }
        else if(name.matches("C")){
            if (!num.matches("-") && !num.matches("")){
                if (Integer.valueOf(num) > 2000) {
                    last = true;
                    editText.setText("2000");
                }
                else if (Integer.valueOf(num) < 0) {
                    last = true;
                    editText.setText("0");
                }
                else {
                    if(last){
                        last = false;
                        int selEndIndex = editText.getText().length();
                        Selection.setSelection(editable, selEndIndex);
                    }
                    else {
                        int index = editText.getSelectionStart();
                        Selection.setSelection(editable, index);
                    }
                }
            }
        }
        else if(name.matches("D")){
            if (!num.matches("-") && !num.matches("")){
                if (Integer.valueOf(num) > 3000) {
                    last = true;
                    editText.setText("3000");
                }
                else if (Integer.valueOf(num) < 0) {
                    last = true;
                    editText.setText("0");
                }
                else {
                    if(last){
                        last = false;
                        int selEndIndex = editText.getText().length();
                        Selection.setSelection(editable, selEndIndex);
                    }
                    else {
                        int index = editText.getSelectionStart();
                        Selection.setSelection(editable, index);
                    }
                }
            }
        }
        else if(name.matches("E")){
            if (!num.matches("-") && !num.matches("")){
                if (Integer.valueOf(num) > 5000) {
                    last = true;
                    editText.setText("5000");
                }
                else if (Integer.valueOf(num) < 0) {
                    last = true;
                    editText.setText("0");
                }
                else {
                    if(last){
                        last = false;
                        int selEndIndex = editText.getText().length();
                        Selection.setSelection(editable, selEndIndex);
                    }
                    else {
                        int index = editText.getSelectionStart();
                        Selection.setSelection(editable, index);
                    }
                }
            }
        }
        else if(name.matches("P")){
            if (!num.matches("-") && !num.matches("")){
                if (Integer.valueOf(num) > 1000) {
                    last = true;
                    editText.setText("1000");
                }
                else if (Integer.valueOf(num) < 0) {
                    last = true;
                    editText.setText("0");
                }
                else {
                    if(last){
                        last = false;
                        int selEndIndex = editText.getText().length();
                        Selection.setSelection(editable, selEndIndex);
                    }
                    else {
                        int index = editText.getSelectionStart();
                        Selection.setSelection(editable, index);
                    }
                }
            }
        }
        else if(name.matches("M")){
            if (!num.matches("-") && !num.matches("")){
                if (Integer.valueOf(num) > 999) {
                    last = true;
                    editText.setText("999");
                }
                else if (Integer.valueOf(num) < 0) {
                    last = true;
                    editText.setText("0");
                }
                else {
                    if(last){
                        last = false;
                        int selEndIndex = editText.getText().length();
                        Selection.setSelection(editable, selEndIndex);
                    }
                    else {
                        int index = editText.getSelectionStart();
                        Selection.setSelection(editable, index);
                    }
                }
            }
        }
        else if(name.matches("Z")){
            if (!num.matches("-") && !num.matches("")){
                if (Integer.valueOf(num) > 100) {
                    last = true;
                    editText.setText("100");
                }
                else if (Integer.valueOf(num) < 0) {
                    last = true;
                    editText.setText("0");
                }
                else {
                    if(last){
                        last = false;
                        int selEndIndex = editText.getText().length();
                        Selection.setSelection(editable, selEndIndex);
                    }
                    else {
                        int index = editText.getSelectionStart();
                        Selection.setSelection(editable, index);
                    }
                }
            }
        }
        else if(name.matches("ADR")){
            if (!num.matches("-") && !num.matches("")){
                if (Integer.valueOf(num) > 255) {
                    last = true;
                    editText.setText("255");
                }
                else if (Integer.valueOf(num) < 1) {
                    last = true;
                    editText.setText("1");
                }
                else {
                    if(last){
                        last = false;
                        int selEndIndex = editText.getText().length();
                        Selection.setSelection(editable, selEndIndex);
                    }
                    else {
                        int index = editText.getSelectionStart();
                        Selection.setSelection(editable, index);
                    }
                }
            }
        }
    }
}
