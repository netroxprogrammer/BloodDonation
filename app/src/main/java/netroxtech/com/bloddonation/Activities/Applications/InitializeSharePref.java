package netroxtech.com.bloddonation.Activities.Applications;

import android.content.Context;
import android.content.SharedPreferences;

import netroxtech.com.bloddonation.Activities.Models.CreateAccount;
import netroxtech.com.bloddonation.Activities.Utils.Constants;

/**
 * Created by mac on 7/26/2017.
 */

public class InitializeSharePref {

    Context context;
    SharedPreferences msharePref;
    public InitializeSharePref(Context context){
        this.context = context;
        msharePref = context.getSharedPreferences(Constants.PREF_NAME,Context.MODE_PRIVATE);
    }
    public void saveUserData(CreateAccount account){
        SharedPreferences.Editor editor  = msharePref.edit();
        editor.putInt(Constants.PREF_ID,account.getId());
        editor.putString(Constants.PREF_USERNAME,account.getUserName());
        editor.putString(Constants.PREF_EMAIL,account.getEmail());
        editor.putString(Constants.PREF_PHONE_NUMBER,account.getPhoneNumber());
        editor.putString(Constants.PREF_CITY,account.getCity());
        editor.putString(Constants.PREF_ADDRESS,account.getAddress());
        editor.putString(Constants.PREF_BLOOD_GROUP,account.getBloodGroup());
        editor.putString(Constants.PREF_LATITUDE,account.getLatitude());
        editor.putString(Constants.PREF_LONGITUDE,account.getLongitude());
        editor.commit();
    }
    public  CreateAccount getUserData(){
        CreateAccount account = new CreateAccount();
        account.setId(msharePref.getInt(Constants.PREF_ID,0));
        account.setUserName(msharePref.getString(Constants.PREF_USERNAME,""));
        account.setCity(msharePref.getString(Constants.PREF_CITY,""));
        account.setAddress(msharePref.getString(Constants.PREF_ADDRESS,""));
        account.setEmail(msharePref.getString(Constants.PREF_EMAIL,""));
        account.setBloodGroup(msharePref.getString(Constants.PREF_BLOOD_GROUP,""));
        account.setLatitude(msharePref.getString(Constants.PREF_LATITUDE,""));
        account.setLongitude(msharePref.getString(Constants.PREF_LONGITUDE,""));
        account.setPhoneNumber(msharePref.getString(Constants.PREF_PHONE_NUMBER,""));
        return account;
    }
    public void logout(){
        SharedPreferences.Editor  editor  = msharePref.edit();
        editor.clear();
        editor.apply();
    }
}
