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

import com.jetec.jtc_ble.EditManagert.EditChangeName;
import com.jetec.jtc_ble.R;
import com.jetec.jtc_ble.Service.BluetoothLeService;
import com.jetec.jtc_ble.SupportFunction.Screen;

import static android.content.Context.VIBRATOR_SERVICE;

public class InputDialog {

    private Context context;
    private BluetoothLeService mBluetoothLeService;
    private String chose, title;
    private Dialog processing = null;

    public InputDialog(){
        super();
    }

    public void setAlert(Context context, BluetoothLeService mBluetoothLeService, String chose, String title){
        processing = input(context, mBluetoothLeService, chose, title);
        processing.show();
        processing.setCanceledOnTouchOutside(false);
    }

    private Dialog input(Context context, BluetoothLeService mBluetoothLeService, String chose, String title){
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

        /*if(chose.startsWith("EH")){

        }
        else if(chose.startsWith("EL")){

        }*/

        switch (chose) {
            case("NAME"):{
                editText.setHint(context.getString(R.string.changename));
                editText.setKeyListener(DigitsKeyListener.getInstance(".,$%&^!()-_=+';:|}{[]*→←↘↖、，。?~～#€￠" +
                        "￡￥abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@>/<"));
                editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                editText.addTextChangedListener(new EditChangeName(editText));
            }
            break;
            case("EH1"):{

            }
            break;
            case("EH2"):{

            }
            break;
            case("EH3"):{

            }
            break;
            case("EL1"):{

            }
            break;
            case("EL2"):{

            }
            break;
            case("EL3"):{

            }
            break;
            case("RL1"):{

            }
            break;
            case("RL2"):{

            }
            break;
            case("RL3"):{

            }
            break;
            case("ADR"):{

            }
            break;
            case("SPK"):{

            }
            break;
            default:
                break;
        }

        bn.setOnClickListener(v1 -> {
            assert vibrator != null;
            vibrator.vibrate(100);
            processing.dismiss();
        });


        progressDialog.setContentView(layout, new ConstraintLayout.LayoutParams((3 * dm.widthPixels / 5),
                (dm.heightPixels / 4)));

        return progressDialog;
    }
}
