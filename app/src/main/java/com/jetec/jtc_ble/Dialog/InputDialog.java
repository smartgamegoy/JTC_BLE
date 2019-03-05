package com.jetec.jtc_ble.Dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jetec.jtc_ble.DialogFunction.*;
import com.jetec.jtc_ble.EditManagert.EditChangeName;
import com.jetec.jtc_ble.EditManagert.EditChangeNum;
import com.jetec.jtc_ble.R;
import com.jetec.jtc_ble.Service.BluetoothLeService;
import com.jetec.jtc_ble.SupportFunction.LogMessage;
import com.jetec.jtc_ble.SupportFunction.Screen;
import com.jetec.jtc_ble.SupportFunction.SendValue;
import com.jetec.jtc_ble.SupportFunction.Value;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.VIBRATOR_SERVICE;

public class InputDialog {

    private Context context;
    private String TAG = "InputDialog";
    private Dialog processing = null;
    private LogMessage logMessage = new LogMessage();
    private List<String> nameList;
    private ArrayList<String> selectItem, deviceNumList;
    private PR pr = new PR();
    private EH eh = new EH();
    private EL el = new EL();
    private ADR adr = new ADR();

    public InputDialog() {
        super();
        nameList = new ArrayList<>();
        nameList.clear();
        for (int i = 0; i < Value.model_name.length(); i++) {
            nameList.add(String.valueOf(Value.model_name.charAt(i)));
        }
    }

    public void setAlert(Context context, BluetoothLeService mBluetoothLeService, String chose,
                         String title, ArrayList<String> selectItem, ArrayList<String> deviceNumList) {
        this.selectItem = selectItem;
        this.deviceNumList = deviceNumList;
        processing = input(context, mBluetoothLeService, chose, title);
        processing.show();
        processing.setCanceledOnTouchOutside(false);
    }

    private Dialog input(Context context, BluetoothLeService mBluetoothLeService, String chose, String title) {
        SendValue sendValue = new SendValue(mBluetoothLeService);
        Screen screen = new Screen(context);
        DisplayMetrics dm = screen.size();
        Vibrator vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        Dialog progressDialog = new Dialog(context);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.alterdialog, null);

        ConstraintLayout layout = v.findViewById(R.id.input_dialog);
        TextView textView = v.findViewById(R.id.textView1);
        Button by = v.findViewById(R.id.button2);
        Button bn = v.findViewById(R.id.button1);
        final EditText editText = v.findViewById(R.id.editText1);
        textView.setText(title);
        by.setText(context.getString(R.string.butoon_yes));
        bn.setText(context.getString(R.string.butoon_no));

        switch (chose) {
            case ("NAME"): {
                editText.setHint(" " + context.getString(R.string.changename));
                editText.setKeyListener(DigitsKeyListener.getInstance(".,$%&^!()-_=+';:|}{[]*→←↘↖、，。?~～#€￠" +
                        "￡￥abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@>/<"));
                editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                editText.addTextChangedListener(new EditChangeName(editText));
            }
            break;
            case ("EH1"): {
                if (nameList.get(0).matches("T")) {
                    editText.setHint(" - 10 ~ 65");
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED |
                            InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    editText.addTextChangedListener(new EditChangeNum(editText, "T"));
                } else if (nameList.get(0).matches("H")) {
                    editText.setHint(" 0 ~ 100");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "H"));
                } else if (nameList.get(0).matches("C")) {
                    editText.setHint(" 0 ~ 2000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "C"));
                } else if (nameList.get(0).matches("D")) {
                    editText.setHint(" 0 ~ 3000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "D"));
                } else if (nameList.get(0).matches("E")) {
                    editText.setHint(" 0 ~ 5000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "E"));
                } else if (nameList.get(0).matches("M")) {
                    editText.setHint(" 0 ~ 999");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "M"));
                } else if (nameList.get(0).matches("Z")) {
                    editText.setHint(" 0 ~ 100");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "Z"));
                }
            }
            break;
            case ("EH2"): {
                if (nameList.get(1).matches("T")) {
                    editText.setHint(" - 10 ~ 65");
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED |
                            InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    editText.addTextChangedListener(new EditChangeNum(editText, "T"));
                } else if (nameList.get(1).matches("H")) {
                    editText.setHint(" 0 ~ 100");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "H"));
                } else if (nameList.get(1).matches("C")) {
                    editText.setHint(" 0 ~ 2000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "C"));
                } else if (nameList.get(1).matches("D")) {
                    editText.setHint(" 0 ~ 3000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "D"));
                } else if (nameList.get(1).matches("E")) {
                    editText.setHint(" 0 ~ 5000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "E"));
                } else if (nameList.get(1).matches("M")) {
                    editText.setHint(" 0 ~ 999");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "M"));
                } else if (nameList.get(1).matches("Z")) {
                    editText.setHint(" 0 ~ 100");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "Z"));
                }
            }
            break;
            case ("EH3"): {
                if (nameList.get(2).matches("T")) {
                    editText.setHint(" - 10 ~ 65");
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED |
                            InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    editText.addTextChangedListener(new EditChangeNum(editText, "T"));
                } else if (nameList.get(2).matches("H")) {
                    editText.setHint(" 0 ~ 100");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "H"));
                } else if (nameList.get(2).matches("C")) {
                    editText.setHint(" 0 ~ 2000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "C"));
                } else if (nameList.get(2).matches("D")) {
                    editText.setHint(" 0 ~ 3000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "D"));
                } else if (nameList.get(2).matches("E")) {
                    editText.setHint(" 0 ~ 5000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "E"));
                } else if (nameList.get(2).matches("M")) {
                    editText.setHint(" 0 ~ 999");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "M"));
                } else if (nameList.get(2).matches("Z")) {
                    editText.setHint(" 0 ~ 100");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "Z"));
                }
            }
            break;
            case ("EL1"): {
                if (nameList.get(0).matches("T")) {
                    editText.setHint(" - 10 ~ 65");
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED |
                            InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    editText.addTextChangedListener(new EditChangeNum(editText, "T"));
                } else if (nameList.get(0).matches("H")) {
                    editText.setHint(" 0 ~ 100");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "H"));
                } else if (nameList.get(0).matches("C")) {
                    editText.setHint(" 0 ~ 2000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "C"));
                } else if (nameList.get(0).matches("D")) {
                    editText.setHint(" 0 ~ 3000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "D"));
                } else if (nameList.get(0).matches("E")) {
                    editText.setHint(" 0 ~ 5000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "E"));
                } else if (nameList.get(0).matches("M")) {
                    editText.setHint(" 0 ~ 999");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "M"));
                } else if (nameList.get(0).matches("Z")) {
                    editText.setHint(" 0 ~ 100");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "Z"));
                }
            }
            break;
            case ("EL2"): {
                if (nameList.get(1).matches("T")) {
                    editText.setHint(" - 10 ~ 65");
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED |
                            InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    editText.addTextChangedListener(new EditChangeNum(editText, "T"));
                } else if (nameList.get(1).matches("H")) {
                    editText.setHint(" 0 ~ 100");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "H"));
                } else if (nameList.get(1).matches("C")) {
                    editText.setHint(" 0 ~ 2000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "C"));
                } else if (nameList.get(1).matches("D")) {
                    editText.setHint(" 0 ~ 3000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "D"));
                } else if (nameList.get(1).matches("E")) {
                    editText.setHint(" 0 ~ 5000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "E"));
                } else if (nameList.get(1).matches("M")) {
                    editText.setHint(" 0 ~ 999");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "M"));
                } else if (nameList.get(1).matches("Z")) {
                    editText.setHint(" 0 ~ 100");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "Z"));
                }
            }
            break;
            case ("EL3"): {
                if (nameList.get(2).matches("T")) {
                    editText.setHint(" - 10 ~ 65");
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED |
                            InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    editText.addTextChangedListener(new EditChangeNum(editText, "T"));
                } else if (nameList.get(2).matches("H")) {
                    editText.setHint(" 0 ~ 100");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "H"));
                } else if (nameList.get(2).matches("C")) {
                    editText.setHint(" 0 ~ 2000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "C"));
                } else if (nameList.get(2).matches("D")) {
                    editText.setHint(" 0 ~ 3000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "D"));
                } else if (nameList.get(2).matches("E")) {
                    editText.setHint(" 0 ~ 5000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "E"));
                }else if (nameList.get(2).matches("P")) {
                    editText.setHint(" 0 ~ 1000");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "M"));
                }
                else if (nameList.get(2).matches("M")) {
                    editText.setHint(" 0 ~ 999");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "M"));
                } else if (nameList.get(2).matches("Z")) {
                    editText.setHint(" 0 ~ 100");
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new EditChangeNum(editText, "Z"));
                }
            }
            break;
            case ("PR1"): {
                editText.setHint(" 0 ~ 100");
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.addTextChangedListener(new EditChangeNum(editText, "Z"));
            }
            break;
            case ("PR2"): {
                editText.setHint(" 0 ~ 100");
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.addTextChangedListener(new EditChangeNum(editText, "Z"));
            }
            break;
            case ("PR3"): {
                editText.setHint(" 0 ~ 100");
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.addTextChangedListener(new EditChangeNum(editText, "Z"));
            }
            break;
            case("ADR"):{
                editText.setHint(" 1 ~ 255");
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.addTextChangedListener(new EditChangeNum(editText, "ADR"));
            }
            break;
            /*case("RL1"):{

            }
            break;
            case("RL2"):{

            }
            break;
            case("RL3"):{

            }
            break;
            case("SPK"):{

            }
            break;*/
            default:
                break;
        }

        bn.setOnClickListener(v1 -> {
            assert vibrator != null;
            vibrator.vibrate(100);
            processing.dismiss();
        });

        by.setOnClickListener(v12 -> {
            assert vibrator != null;
            vibrator.vibrate(100);
            String gets = editText.getText().toString().trim();
            //sendValue.send();
            logMessage.showmessage(TAG, "chose = " + chose);
            if (!gets.matches("")) {
                switch (chose) {
                    case ("NAME"): {
                        String out = "NAME" + gets;
                        sendValue.send(out);
                        processing.dismiss();
                    }
                    break;
                    case ("EH1"): {
                        float t = Float.valueOf(gets);
                        int i = selectItem.indexOf(chose);
                        String value = deviceNumList.get(i + 1);
                        Float min = Float.valueOf(value);
                        if (t > min) {
                            eh.todo(t, chose, processing, mBluetoothLeService, gets, nameList);
                        } else {
                            Toast.makeText(context, context.getString(R.string.max), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                    case ("EH2"): {
                        float t = Float.valueOf(gets);
                        int i = selectItem.indexOf(chose);
                        String value = deviceNumList.get(i + 1);
                        Float min = Float.valueOf(value);
                        if (t > min) {
                            eh.todo(t, chose, processing, mBluetoothLeService, gets, nameList);
                        } else {
                            Toast.makeText(context, context.getString(R.string.max), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                    case ("EH3"): {
                        float t = Float.valueOf(gets);
                        int i = selectItem.indexOf(chose);
                        String value = deviceNumList.get(i + 1);
                        Float min = Float.valueOf(value);
                        if (t > min) {
                            eh.todo(t, chose, processing, mBluetoothLeService, gets, nameList);
                        } else {
                            Toast.makeText(context, context.getString(R.string.max), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                    case ("EL1"): {
                        float t = Float.valueOf(gets);
                        int i = selectItem.indexOf(chose);
                        String value = deviceNumList.get(i - 1);
                        Float max = Float.valueOf(value);
                        if (t < max) {
                            el.todo(t, chose, processing, mBluetoothLeService, gets, nameList);
                        } else {
                            Toast.makeText(context, context.getString(R.string.min), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                    case ("EL2"): {
                        float t = Float.valueOf(gets);
                        int i = selectItem.indexOf(chose);
                        String value = deviceNumList.get(i - 1);
                        Float max = Float.valueOf(value);
                        if (t < max) {
                            el.todo(t, chose, processing, mBluetoothLeService, gets, nameList);
                        } else {
                            Toast.makeText(context, context.getString(R.string.min), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                    case ("EL3"): {
                        float t = Float.valueOf(gets);
                        int i = selectItem.indexOf(chose);
                        String value = deviceNumList.get(i - 1);
                        Float max = Float.valueOf(value);
                        if (t < max) {
                            el.todo(t, chose, processing, mBluetoothLeService, gets, nameList);
                        } else {
                            Toast.makeText(context, context.getString(R.string.min), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                    case ("PR1"): {
                        float t = Float.valueOf(gets);
                        pr.todo(t, chose, processing, mBluetoothLeService);
                    }
                    break;
                    case ("PR2"): {
                        float t = Float.valueOf(gets);
                        pr.todo(t, chose, processing, mBluetoothLeService);
                    }
                    break;
                    case ("PR3"): {
                        float t = Float.valueOf(gets);
                        pr.todo(t, chose, processing, mBluetoothLeService);
                    }
                    break;
                    case ("ADR"): {
                        float t = Float.valueOf(gets);
                        adr.todo(t, chose, processing, mBluetoothLeService);
                    }
                    default:
                        break;
                }
            }
            else {
                Toast.makeText(context, context.getString(R.string.wrong), Toast.LENGTH_SHORT).show();
            }
        });

        progressDialog.setContentView(layout, new ConstraintLayout.LayoutParams((3 * dm.widthPixels / 5),
                (dm.heightPixels / 4)));

        return progressDialog;
    }
}
