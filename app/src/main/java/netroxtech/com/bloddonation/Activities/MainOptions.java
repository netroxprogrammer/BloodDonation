package netroxtech.com.bloddonation.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import netroxtech.com.bloddonation.Activities.Applications.GPSTracker;
import netroxtech.com.bloddonation.Activities.Applications.InitializeSharePref;
import netroxtech.com.bloddonation.Activities.Models.CreateAccount;
import netroxtech.com.bloddonation.Activities.Utils.Config;
import netroxtech.com.bloddonation.Activities.Utils.Constants;
import netroxtech.com.bloddonation.Activities.Utils.NotificationUtils;
import netroxtech.com.bloddonation.R;

public class MainOptions extends AppCompatActivity {
    GPSTracker gps;
    InitializeSharePref sharePref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_options);
        setXML();


        CreateAccount  account  = sharePref.getUserData();
       /* if(account.getLatitude()!=null ){
            Intent  handleOption=  new Intent(MainOptions.this,HandleOptions.class);
            startActivity(handleOption);
            finish();
        }*/


    }


    public  void setXML(){

        sharePref  = new InitializeSharePref(this);
        gps = new GPSTracker(this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Constants.LATITUDE = String.valueOf(latitude);
            Constants.LANGITUDE= String.valueOf(longitude);
            // \n is for new line

        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }
    public void goLoginActivity(View v){
        Intent  loginIntent=  new Intent(MainOptions.this,Login.class);
        startActivity(loginIntent);
    }
    public void goSignupActivity(View v){
        Intent  signUpIntent=  new Intent(MainOptions.this,CreateUser.class);
        startActivity(signUpIntent);
    }

}
