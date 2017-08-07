package netroxtech.com.bloddonation.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import netroxtech.com.bloddonation.Activities.Applications.InitializeSharePref;
import netroxtech.com.bloddonation.Activities.Models.CreateAccount;
import netroxtech.com.bloddonation.Activities.Utils.Config;
import netroxtech.com.bloddonation.Activities.Utils.NotificationUtils;
import netroxtech.com.bloddonation.R;

public class SplashScreen extends AppCompatActivity {
    private static int  SPLASH_TIME_OUT = 1500;
    InitializeSharePref preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        preferences = new InitializeSharePref(this);
        CreateAccount account = preferences.getUserData();
        if(account.getId()!=0 && !account.getUserName().equalsIgnoreCase("")){
            Intent intent = new Intent(this,MainTabs.class);
            startActivity(intent);
            finish();
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToMainScreen();
                }
            }, SPLASH_TIME_OUT);
        }
    }
    private void goToMainScreen(){
        Intent intent = new Intent(SplashScreen.this,MainOptions.class);
        startActivity(intent);
        finish();
    }

}
