package com.jetec.jtc_ble.Activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jetec.jtc_ble.R;
import com.jetec.jtc_ble.Service.BluetoothLeService;
import com.jetec.jtc_ble.SupportFunction.*;
import com.jetec.jtc_ble.SupportFunction.ViewAdapter.*;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static com.jetec.jtc_ble.Activity.FirstActivity.getManager;
import static com.jetec.jtc_ble.Activity.FirstActivity.makeGattUpdateIntentFilter;

public class DeviceEngineer extends AppCompatActivity {

    private String TAG = "DeviceEngineer";
    private LogMessage logMessage = new LogMessage();
    private GetDeviceName getDeviceName;
    private GetDeviceNum getDeviceNum;
    private Vibrator vibrator;
    private BluetoothLeService mBluetoothLeService;
    private BluetoothAdapter mBluetoothAdapter;
    private boolean s_connect = false;
    private Intent intents;
    private String BID;
    private ArrayList<String> selectItem, reList, dataList, deviceNameList, deviceNumList;
    private Function function;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }

        BluetoothManager bluetoothManager = getManager(this);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        if (mBluetoothLeService == null) {
            Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
            s_connect = bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
            if (s_connect)
                registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
            else
                Log.e(TAG, "連線失敗");
        }
        ConfigurationChange();
    }

    private void ConfigurationChange() {

        selectItem = new ArrayList<>();
        reList = new ArrayList<>();
        dataList = new ArrayList<>();
        deviceNameList = new ArrayList<>();
        deviceNumList = new ArrayList<>();
        selectItem.clear();
        reList.clear();
        dataList.clear();
        deviceNameList.clear();
        deviceNumList.clear();

        Intent intent = getIntent();

        BID = intent.getStringExtra("BID");
        selectItem = intent.getStringArrayListExtra("selectItem");
        reList = intent.getStringArrayListExtra("reList");
        dataList = intent.getStringArrayListExtra("dataList");

        logMessage.showmessage(TAG, "selectItem = " + selectItem);
        logMessage.showmessage(TAG, "reList = " + reList);
        logMessage.showmessage(TAG, "dataList = " + dataList);

        show_device_function();
    }

    private void show_device_function() {
        setContentView(R.layout.device_title);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        ListView listname = findViewById(R.id.list_name_function);

        deviceNameList.add(getString(R.string.device_name));
        deviceNumList.add(Value.device_name);

        getDeviceName = new GetDeviceName(this, Value.model_name);
        getDeviceNum = new GetDeviceNum(this);

        for (int i = 0; i < reList.size(); i++) {
            deviceNameList.add(getDeviceName.get(reList.get(i)));
            deviceNumList.add(getDeviceNum.get(reList.get(i)));
        }

        function = new Function(this, deviceNameList, deviceNumList);
        listname.setAdapter(function);
        listname.setOnItemClickListener(mSelectClickListener);

        logMessage.showmessage(TAG, "deviceNameList = " + deviceNameList);
        logMessage.showmessage(TAG, "deviceNumList = " + deviceNumList);
    }

    private AdapterView.OnItemClickListener mSelectClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            vibrator.vibrate(100);
            String select = selectItem.get(position);
            //noinspection deprecation
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
            //String output = switch_dialog(select, List_d_function.get(position));
            if (select.startsWith("ADR")) {
                    /*String description = getString(R.string.description);
                    interval = new Interval(DeviceEngineer.this, Value.all_Width, Value.all_Height, mBluetoothLeService, description);
                    interval.showDialog();*/
            } else if (select.startsWith("DP") || select.startsWith("SPK")) {
                    /*switchDialog = new SwitchDialog(DeviceEngineer.this, mBluetoothLeService);
                    choseDialog = switchDialog.chose(select, List_d_num.get(position), List_d_function.get(position), vibrator);
                    switchDialog.setDialog(choseDialog);
                    choseDialog.show();
                    choseDialog.setCanceledOnTouchOutside(false);*/
            } else {
                    /*inDialog = inputDialog(DeviceEngineer.this, List_d_function.get(position), select);
                    inDialog.show();
                    inDialog.setCanceledOnTouchOutside(false);*/
            }

        }
    };

    public final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            intents = intent;
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                logMessage.showmessage(TAG, "連線成功");
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                //s_connect = false;
                logMessage.showmessage(TAG, "連線中斷" + Value.connected);
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // Show all the supported services and characteristics on the user interface.
                //displayGattServices(mBluetoothLeService.getSupportedGattServices());
                logMessage.showmessage(TAG, "連線狀態改變");
                mBluetoothLeService.enableTXNotification();
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                runOnUiThread(() -> {
                    try {
                        String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                        byte[] txValue = intents.getByteArrayExtra(BluetoothLeService.EXTRA_DATA);
                        String text = new String(txValue, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    };

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            //https://github.com/googlesamples/android-BluetoothLeGatt/tree/master/Application/src/main/java/com/example/android/bluetoothlegatt
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                logMessage.showmessage(TAG, "初始化失敗");
            }
            mBluetoothLeService.connect(BID);
            logMessage.showmessage(TAG, "進入連線");
        }

        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
            logMessage.showmessage(TAG, "失去連線端");
        }
    };

    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            (device, rssi, scanRecord) -> runOnUiThread(() -> runOnUiThread(this::addDevice));

    private void addDevice() {
    }

    public boolean onKeyDown(int key, KeyEvent event) {
        switch (key) {
            case KeyEvent.KEYCODE_SEARCH:
                break;
            case KeyEvent.KEYCODE_BACK: {
                vibrator.vibrate(100);
                new AlertDialog.Builder(this)
                        .setTitle("結束連線")
                        .setMessage("斷開藍牙")
                        .setPositiveButton(R.string.butoon_yes, (dialog, which) -> {
                            vibrator.vibrate(100);
                            if (mBluetoothAdapter != null)
                                //noinspection deprecation
                                mBluetoothAdapter.stopLeScan(mLeScanCallback);
                            Service_close();
                            Value.connected = false;
                            disconnect();
                        })
                        .setNeutralButton(R.string.butoon_no, (dialog, which) -> {
                        })
                        .show();
            }
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                break;
            default:
                return false;
        }
        return false;
    }

    public void Service_close() {
        if (mBluetoothLeService == null) {
            Log.e(TAG, "masaga");
            return;
        }
        mBluetoothLeService.disconnect();
    }

    private void disconnect() {
        Intent intent = new Intent(this, StartActivity.class);
        //Value.Jsonlist.clear();
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        logMessage.showmessage(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logMessage.showmessage(TAG, "onDestroy()");
        if (Value.connected) {
            Value.connected = false;
        }
        if (mBluetoothLeService != null) {
            if (s_connect) {
                unbindService(mServiceConnection);
                s_connect = false;
            }
            mBluetoothLeService.stopSelf();
            mBluetoothLeService = null;
        }
        if (mBluetoothAdapter != null)
            //noinspection deprecation
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        logMessage.showmessage(TAG, "onResume");
        logMessage.showmessage(TAG, "s_connect = " + s_connect);

        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(BID);
            Log.d(TAG, "Connect request result=" + result);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        logMessage.showmessage(TAG, "onPause");

        if (mBluetoothAdapter != null) {
            //noinspection deprecation
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
            s_connect = true;
        }

        /*if (s_connect) {
            unregisterReceiver(mGattUpdateReceiver);
        }*/
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // land do nothing is ok
            //show_device();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // port do nothing is ok
            //show_device();
        }
    }
}
