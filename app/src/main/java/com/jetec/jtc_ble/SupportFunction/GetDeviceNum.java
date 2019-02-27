package com.jetec.jtc_ble.SupportFunction;

import android.content.Context;
import android.util.Log;

import com.jetec.jtc_ble.R;

public class GetDeviceNum {

    private String TAG = "GetDeviceNum";
    private Context context;

    public GetDeviceNum(Context context){
        this.context = context;
    }

    public String get(String str){

        String renum = "";

        /*if(str.startsWith("DP1")){
            if(str.substring(str.indexOf("+") + 1, str.length()).matches("0000.0")){
                Value.IDP1 = false;
                Log.e("getDEVICE","Value.IDP1 = " + Value.IDP1);
                renum = "Off";
            }
            else if(str.substring(str.indexOf("+") + 1, str.length()).matches("0001.0")){
                Value.IDP1 = true;
                Log.e("getDEVICE","Value.IDP1 = " + Value.IDP1);
                renum = "On";
            }
        }
        else if(str.startsWith("DP2")){
            if(str.substring(str.indexOf("+") + 1, str.length()).matches("0000.0")){
                Value.IDP2 = false;
                renum = "Off";
            }
            else if(str.substring(str.indexOf("+") + 1, str.length()).matches("0001.0")){
                Value.IDP2 = true;
                renum = "On";
            }
        }
        else if(str.startsWith("DP3")){
            if(str.substring(str.indexOf("+") + 1, str.length()).matches("0000.0")){
                Value.IDP3 = false;
                renum = "Off";
            }
            else if(str.substring(str.indexOf("+") + 1, str.length()).matches("0001.0")){
                Value.IDP3 = true;
                renum = "On";
            }
        }*/
        if(str.startsWith("SPK")){
            if(str.substring(str.indexOf("+") + 1, str.length()).matches("0001.0")){
                renum = "On";
            }
            else{
                renum = "Off";
            }
        }
        else if(str.startsWith("RL")){
            char model = 0;
            String newStr = str.substring(str.indexOf("+") + 1, str.indexOf("."))
                    .replaceFirst("^0*", "");
            Log.d(TAG, "newStr = " + newStr);
            if(!newStr.matches("")) {
                model = Value.model_name.charAt(Integer.valueOf(newStr) - 1);
            }
            Log.d(TAG, "model = " + model);
            if(String.valueOf(model).matches("P")){
                renum = context.getString(R.string.P);
            }
            else if(String.valueOf(model).matches("M")){
                renum = context.getString(R.string.M);
            }
            else if(String.valueOf(model).matches("C")){
                renum = context.getString(R.string.C);
            }
            else if(String.valueOf(model).matches("T")){
                renum = context.getString(R.string.T);
            }
            else if(String.valueOf(model).matches("H")){
                renum = context.getString(R.string.H);
            }
            else if(String.valueOf(model).matches("Z")){
                renum = context.getString(R.string.percent);
            }
            else{
                renum = "";
            }
        }
        else if(str.startsWith("ADR")){
            renum = str.substring(str.indexOf("+") + 1, str.indexOf("."))
                    .replaceFirst("^0*", "");
        }
        else if(str.startsWith("PR")){
            renum = str.substring(str.indexOf("+") + 1, str.indexOf("."))
                    .replaceFirst("^0*", "");
        }
        else{
            if(str.startsWith("IH") || str.startsWith("IL") || str.startsWith("EH") || str.startsWith("EL")) {
                    if (str.contains("+")) {
                        if (str.substring(str.indexOf("+") + 1, str.length()).matches("0000.0")) {
                            renum = "0";
                        }
                        else {
                            String newStr = str.substring(str.indexOf("+") + 1, str.length())
                                    .replaceFirst("^0*", "");
                            if (newStr.startsWith(".")) {
                                renum = "0" + newStr;
                            } else {
                                renum = newStr;
                            }
                        }
                    }
                    else {
                        if (str.substring(str.indexOf("-") + 1, str.length()).matches("0000.0")) {
                            renum = "0";
                        }
                        else {
                            String newStr = str.substring(str.indexOf("-") + 1, str.length())
                                    .replaceFirst("^0*", "");
                            if (newStr.startsWith(".")) {
                                renum = "-" + "0" + newStr;
                            } else {
                                renum = "-" + newStr;
                            }
                        }
                    }
                /*else {
                    if(Value.name.get(0).toString().matches("T")) {
                        if (str.contains("+")) {
                            if (str.substring(str.indexOf("+") + 1, str.length()).matches("0000.0")) {
                                renum = "0";
                            } else {
                                String newStr = str.substring(str.indexOf("+") + 1, str.length())
                                        .replaceFirst("^0*", "");
                                Log.e("這裡","newStr = " + newStr);
                                if (newStr.matches("")) {
                                    renum = "0";
                                } else {
                                    if(newStr.startsWith(".")){
                                        renum = "0" + newStr;
                                    }
                                    else {
                                        renum = newStr;
                                        Log.e(TAG, "renum = " + renum);
                                    }
                                }
                            }
                        } else {
                            if (str.substring(str.indexOf("-") + 1, str.length()).matches("0000.0")) {
                                renum = "0";
                            } else {
                                String newStr = str.substring(str.indexOf("-") + 1, str.length())
                                        .replaceFirst("^0*", "");
                                if (newStr.matches("")) {
                                    renum = "0";
                                } else {
                                    if(newStr.startsWith(".")){
                                        renum = "-" + "0" + newStr;
                                    }
                                    else {
                                        renum = "-" + newStr;
                                    }
                                }
                            }
                        }
                    }
                    else if(Value.name.get(0).toString().matches("H")){
                        if (str.contains("+")) {
                            if (str.substring(str.indexOf("+") + 1, str.length()).matches("0000.0")) {
                                renum = "0";
                            }
                            else {
                                String newStr = str.substring(str.indexOf("+") + 1, str.indexOf("."))
                                        .replaceFirst("^0*", "");
                                if (newStr.matches("")) {
                                    renum = "0";
                                } else {
                                    renum = newStr;
                                }
                            }
                        }
                        else {
                            if (str.substring(str.indexOf("-") + 1, str.length()).matches("0000.0")) {
                                renum = "0";
                            }
                            else {
                                String newStr = str.substring(str.indexOf("-") + 1, str.indexOf("."))
                                        .replaceFirst("^0*", "");
                                if (newStr.matches("")) {
                                    renum = "0";
                                } else {
                                    renum = "-" + newStr;
                                }
                            }
                        }
                    }
                    else if(Value.name.get(0).toString().matches("C") ||
                            Value.name.get(0).toString().matches("D") ||
                            Value.name.get(0).toString().matches("E")){
                        if (str.contains("+")) {
                            if (str.substring(str.indexOf("+") + 1, str.length()).matches("0000.0")) {
                                renum = "0";
                            }
                            else {
                                String newStr = str.substring(str.indexOf("+") + 1, str.indexOf("."))
                                        .replaceFirst("^0*", "");
                                if (newStr.matches("")) {
                                    renum = "0";
                                } else {
                                    renum = newStr;
                                }
                            }
                        }
                        else {
                            if (str.substring(str.indexOf("-") + 1, str.length()).matches("0000.0")) {
                                renum = "0";
                            }
                            else {
                                String newStr = str.substring(str.indexOf("-") + 1, str.indexOf("."))
                                        .replaceFirst("^0*", "");
                                if (newStr.matches("")) {
                                    renum = "0";
                                } else {
                                    renum = "-" + newStr;
                                }
                            }
                        }
                    }
                    else if(Value.name.get(0).toString().matches("I")){
                        if (str.contains("+")) {
                            if (str.substring(str.indexOf("+") + 1, str.length()).matches("0000.0")) {
                                renum = "0";
                            }
                            else {
                                String newStr = str.substring(str.indexOf("+") + 1, str.indexOf("."))
                                        .replaceFirst("^0*", "");
                                if (newStr.matches("")) {
                                    renum = "0";
                                } else {
                                    renum = newStr;
                                }
                            }
                        }
                        else {
                            if (str.substring(str.indexOf("-") + 1, str.length()).matches("0000.0")) {
                                renum = "0";
                            }
                            else {
                                String newStr = str.substring(str.indexOf("-") + 1, str.indexOf("."))
                                        .replaceFirst("^0*", "");
                                if (newStr.matches("")) {
                                    renum = "0";
                                } else {
                                    renum = "-" + newStr;
                                }
                            }
                        }
                    }
                }*/
            }
            else {
                if(str.startsWith("INTER")){
                    str = str.substring(str.indexOf("INTER") + 5, str.length());
                    int i = Integer.valueOf(str);
                    if(i == 3600)
                        renum = "1h";
                    else if(60 <= i && i < 3600){
                        int j = i / 60;
                        int k = i % 60;
                        if(k == 0)
                            renum = String.valueOf(j) + "m";
                        else
                            renum = String.valueOf(j) + "m" + String.valueOf(k) + "s";
                    }
                    else
                        renum = String.valueOf(i) + "s";
                }
            }
        }
        return renum;
    }
}
