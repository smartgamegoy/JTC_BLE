package com.jetec.jtc_ble.ScanRecord;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import com.jetec.jtc_ble.SupportFunction.LogMessage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DeviceParse {

    private String TAG = "DeviceParse";
    private RecordParse recordParse = new RecordParse();
    private LogMessage logMessage = new LogMessage();
    //private Context context;
    private Map<Integer, List<String>> newList;
    private List<String> list;
    private Map<Integer, Integer> checkdevice;
    private int key = 0;
    //private Thread thread;
    private Boolean ckeckco;
    private static final int EBLE_MANDATA = 0xFF;//«Manufacturer Specific Data»	Bluetooth Core Specification:
    private static final int EBLE_128BitUUIDCom = 0x07;//«Complete List of 128-bit Service Class UUIDs»	Bluetooth Core Specification:

    @SuppressLint("UseSparseArrays")
    public DeviceParse() {
        //this.context = context;
        newList = new HashMap<>();
        checkdevice = new HashMap<>();
    }

    public Map<Integer, List<String>> regetList(List<BluetoothDevice> devices, List<byte[]> record){
        @SuppressLint("UseSparseArrays")
        Map<Integer, List<String>> setList = new HashMap<>();
        List<BluetoothDevice> newdevicesList = new ArrayList<>();
        List<byte[]> newrecord = new ArrayList<>();
        setList.clear();
        newdevicesList.clear();
        newrecord.clear();
        for (int i = 0; i < devices.size(); i++) {
            list = new ArrayList<>();
            Map<Integer, String> parse = recordParse.ParseRecord(record.get(i));

            if (parse.containsKey(EBLE_MANDATA)) {
                String tmpString = parse.get(EBLE_MANDATA);
                assert tmpString != null;
                if (tmpString.substring(0, 4).matches("2028")) {
                    newdevicesList.add(devices.get(i));
                    newrecord.add(record.get(i));
                }
            }
        }

        setList = parseList(newdevicesList, newrecord);

        return setList;
    }

    private Map<Integer, List<String>> parseList(List<BluetoothDevice> devices, List<byte[]> record) {

        newList.clear();
        for (int i = 0; i < devices.size(); i++) {
            int k = 0;
            list = new ArrayList<>();
            Map<Integer, String> parse = recordParse.ParseRecord(record.get(i));

//            logMessage.showmessage(TAG,"devices = " + devices.get(i).getName());
//            logMessage.showmessage(TAG,"parse = " + parse);
//            logMessage.showmessage(TAG,"parse = ----------------------------------------");
            if (parse.containsKey(EBLE_MANDATA)) {
                String tmpString = parse.get(EBLE_MANDATA);
                assert tmpString != null;
                if (tmpString.substring(0, 4).matches("2028")) {    //jetec company id
//                    logMessage.showmessage(TAG,"devices.size = " + devices.size());
                    if (devices.get(i).getName() == null) {
                        list.add("N/A");
                    } else {
                        if(devices.get(i) != null) {
                            list.add(devices.get(i).getName());
                        }
                    }
                    list.add(devices.get(i).getAddress());
                    tmpString = tmpString.substring(6, tmpString.length());
                    if (parse.containsKey(EBLE_128BitUUIDCom)) {
                        String getuuid = parse.get(EBLE_128BitUUIDCom);
                        assert getuuid != null;
                        String count = getuuid.substring(0, 2);
                        getuuid = getuuid.substring(2, getuuid.length());
                        list.add(count);
                        int quantity = Integer.valueOf(count);
                        for (int j = 0, m = 0; m < quantity; j = j + 2, m++) {
                            String unit;
                            String num;
                            String overflow;
                            String value;
                            if (j == 0) {
                                unit = getunit(getuuid.substring(j, j + 2));
                                list.add(setflag(unit));
                                num = getuuid.substring(j + 2, j + 4);
                                value = tmpString.substring(k, k + 4);
                                overflow = tmpString.substring(k + 4, k + 8);
                                k++;
                            } else {
                                unit = getunit(getuuid.substring(2 * j, 2 * j + 2));
                                //logMessage.showmessage(TAG,"unit = " + unit);
                                list.add(setflag(unit));
                                num = getuuid.substring(2 * j + 2, 2 * j + 4);
                                value = tmpString.substring(8 * k, 8 * k + 4);
                                overflow = tmpString.substring(8 * k + 4, 8 * k + 8);
                                k++;
                            }
                            double p = Math.pow(10, Integer.valueOf(num));
                            int n = Integer.parseInt(value, 16);
                            double cal = n / p;
                            DecimalFormat decimalFormat = new DecimalFormat("#.#########");
                            String showtext = decimalFormat.format(cal) + unit;
                            int o = Integer.parseInt(overflow, 16);
                            double over = o / 10;
                            String showover = decimalFormat.format(over) + unit;
                            list.add(showtext);
                            list.add(showover);
                            if(cal >= over){
                                list.add("true");
                            }else {
                                list.add("false");
                            }
                        }
                        if (newList.size() == 0) {
                            key = 0;
                            newList.put(key, list);
                            if (checkdevice.size() != 0) {
                                checkdevice.put(key, 0);
                            } else {
                                checkdevice.put(0, 0);
                            }
                            key++;
                        } else {
                            Boolean check = false;
                            for (int n = 0; n < devices.size(); n++) {
                                if(newList.get(n) != null && devices.get(i).getName() != null) {
                                    if (!Objects.requireNonNull(newList.get(n)).get(1).matches(devices.get(i).getAddress())) {
//                                        newList.put(key, list);
//                                        key++;
                                        check = true;
                                    }
                                }
//                                else {
//                                    newList.put(n, list);
//                                    key++;
//                                }
                                //newList.put(key, list);

//                                if(newList.get(n) != null && devices.get(i).getName() != null) {
//                                    if (!Objects.requireNonNull(newList.get(n)).get(0).matches(devices.get(i).getName())) {
//                                        check = true;
//                                    }
//                                }
                            }
                            if (check) {
                                newList.put(key, list);
                                key++;
                            } else {
                                for (int n = 0; n < key; n++) {
                                    if(newList.get(n) != null && devices.get(i).getName() != null) {
                                        if (Objects.requireNonNull(newList.get(n)).get(0).matches(devices.get(i).getName())) {
                                            newList.put(n, list);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
//        logMessage.showmessage(TAG, "lastnewList = " + newList);
        return newList;
    }

    private String setflag(String str) {
        String flag = "";

        switch (str) {
            case ("Mpa"):
                flag = "0";
                break;
            case ("Kpa"):
                flag = "1";
                break;
            case ("Pa"):
                flag = "2";
                break;
            case ("Bar"):
                flag = "3";
                break;
            case ("Mbar"):
                flag = "4";
                break;
            case ("Kg/cm" + (char) (178)):
                flag = "5";
                break;
            case ("psi"):
                flag = "6";
                break;
            case ("mh" + (char) (178) + "O"):
                flag = "7";
                break;
            case ("mmh" + (char) (178) + "O"):
                flag = "8";
                break;
            case ("˚C"):
                flag = "9";
                break;
            case ("˚F"):
                flag = "10";
                break;
            case ("%"):
                flag = "11";
                break;
            case ("ppm"):
                if (!ckeckco)
                    flag = "12";
                else
                    flag = "13";
                break;
            case ((char) (956) + "g/m" + (char) (179)):
                flag = "14";
                break;
            default:
                break;

        }

        return flag;
    }

    private String getunit(String str) {

        if (str.matches("00")) {
            str = "Mpa";
        } else if (str.matches("01")) {
            str = "Kpa";
        } else if (str.matches("02")) {
            str = "Pa";
        } else if (str.matches("03")) {
            str = "Bar";
        } else if (str.matches("04")) {
            str = "Mbar";
        } else if (str.matches("05")) {
            str = "Kg/cm" + (char) (178);
        } else if (str.matches("06")) {
            str = "psi";
        } else if (str.matches("07")) {
            str = "mh" + (char) (178) + "O";
        } else if (str.matches("08")) {
            str = "mmh" + (char) (178) + "O";
        } else if (str.matches("09")) {
            str = "˚C";
        } else if (str.matches("0A")) {
            str = "˚F";
        } else if (str.matches("0B")) {
            str = "%";
        } else if (str.matches("0C")) { //CO
            ckeckco = false;
            str = "ppm";
        } else if (str.matches("0D")) { //CO2
            ckeckco = true;
            str = "ppm";
        } else if(str.matches("0E")){   //pm2.5 μg/m³
            str = (char) (956) + "g/m" + (char) (179);
        }
        return str;
    }
}
