package com.jetec.jtc_ble.SupportFunction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.jetec.jtc_ble.R;

public class RunningFlash {

    private Context context;
    private Dialog processing = null;
    private Screen screen;
    private boolean check = false;

    public RunningFlash(Context context){
        this.context = context;
        screen = new Screen(context);
    }

    public void startFlash(String message){
        processing = showDialog(context, message);
        processing.show();
        processing.setCanceledOnTouchOutside(false);
    }

    public void closeFlash(){
        check = false;
        processing.dismiss();
    }

    private Dialog showDialog(Context context, String message){
        Dialog progressDialog = new Dialog(context);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams")
        View v = inflater.inflate(R.layout.running, null);

        LinearLayout layout = v.findViewById(R.id.ll_dialog);
        ProgressBar pb_progress_bar = v.findViewById(R.id.pb_progress_bar);
        pb_progress_bar.setVisibility(View.VISIBLE);
        TextView tv = v.findViewById(R.id.tv_loading);

        if (message == null || message.equals("")) {
            tv.setVisibility(View.GONE);
        } else {
            tv.setText(message);
            tv.setTextColor(context.getResources().getColor(R.color.colorDialog));
        }

        DisplayMetrics dm = screen.size();
        double all_Width = dm.widthPixels;
        double all_Height = dm.heightPixels;

        if(all_Height > all_Width) {
            progressDialog.setContentView(layout, new LinearLayout.LayoutParams((int) (all_Width / 2),
                    (int) (all_Height / 5)));
        }
        else {
            progressDialog.setContentView(layout, new LinearLayout.LayoutParams((int) (all_Width / 4),
                    (int) (all_Height / 3)));
        }

        check = true;

        return progressDialog;
    }

    public boolean isCheck() {
        return check;
    }
}
