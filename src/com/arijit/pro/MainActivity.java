package com.arijit.pro;

import android.app.Activity ;
import android.os.Bundle;
import android.content.*;
import android.widget.*;
import android.view.*;
import android.view.View;
import android.app.ActionBar;
import android.net.*;
import android.os.BatteryManager;

public class MainActivity extends Activity 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public final static String EXTRA_MESSAGE = "com.arijit.pro.MESSAGE";

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        int left = editText.getWidth();
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void checkNetwork(View view) {
        String message;
        /* Check the network connection */
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            message = "Connected! :D";
        } else {
            message = "Disconnected. :(";
        }
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);   
    }
    
    /* Check charging status */ 
    public void batteryStatus(View view) { 
        Context context = view.getContext();
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        // Are we charging / charged?
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                             status == BatteryManager.BATTERY_STATUS_FULL;

        // How are we charging?
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        String message;
        if(isCharging)
            message = "Charging";
        else
            message = "Not Charging";
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }

    public void getPosition(View view) {
        String message;

        /* get position of this button */
        Button btn_pressed = (Button) findViewById(R.id.button_get_position);
        int left = btn_pressed.getLeft();
        int top = btn_pressed.getTop();

        message = "The button position is: ("+ left + ", " + top + ")";

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void openGrid(View view) {        
        Intent intent = new Intent(this, FlickrGallery.class);
        startActivity(intent);
    }

    public void showToast(View view){        
        Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
    }
}
