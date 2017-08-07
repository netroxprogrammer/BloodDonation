package netroxtech.com.bloddonation.Activities;

import android.databinding.DataBindingUtil;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import netroxtech.com.bloddonation.Activities.Applications.GPSTracker;
import netroxtech.com.bloddonation.Activities.Applications.HandleVolleyRequests;
import netroxtech.com.bloddonation.Activities.Models.CreateAccount;
import netroxtech.com.bloddonation.Activities.Utils.Constants;
import netroxtech.com.bloddonation.R;
import netroxtech.com.bloddonation.databinding.ActivityCreateUserBinding;

public class CreateUser extends AppCompatActivity {
    HandleVolleyRequests handleVolleyRequests;
    ActivityCreateUserBinding binding;
    CoordinatorLayout  coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_create_user);
      //  Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + Constants.LATITUDE + "\nLong: " + Constants.LANGITUDE, Toast.LENGTH_LONG).show();
        setXML();
    }
    public void  setXML(){


        handleVolleyRequests = new HandleVolleyRequests(this);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.activity_create_user);
    }
    public void createAccountClick(View v){
        takeFormData();
    }
    public  void takeFormData(){

        String userName = binding.activityCreateUserUserName.getText().toString();
        String email =   binding.activityCreateUserEmail.getText().toString();
        String password = binding.activityCreateUserPassword.getText().toString();
        String city = binding.activityCreateUserCity.getSelectedItem().toString();
        String address = binding.activityCreateUserAddress.getText().toString();
        String bloodGroup = binding.bloodGroup.getSelectedItem().toString();
        String phoneNumber = binding.activityCreateUserPhoneNumber.getText().toString();

        if((!userName.isEmpty() && userName!=null && !userName.equalsIgnoreCase("")) &&
                (!email.isEmpty() && email!=null && !email.equalsIgnoreCase("") ) &&
                (!password.isEmpty() && password!=null && !password.equalsIgnoreCase("")) &&
                (!city.isEmpty() && city!=null && !city.equalsIgnoreCase("")) &&
                (!address.isEmpty() && address!=null && !address.equalsIgnoreCase("")) &&
                (!bloodGroup.isEmpty() && bloodGroup!=null && !bloodGroup.equalsIgnoreCase("")) &&
                (!phoneNumber.isEmpty() && phoneNumber!=null && !phoneNumber.equalsIgnoreCase("") )){
            if(!isPassword(password)){
                handleVolleyRequests.showMessage(binding.activityCreateUser,"Password more than 4","Retry");
            }
            if(!handleVolleyRequests.isEmail(email)){
                handleVolleyRequests.showMessage(binding.activityCreateUser,"Email Pattren Not Match","Retry");
            }
            if(!handleVolleyRequests.isPhoneNumber(phoneNumber)){
                handleVolleyRequests.showMessage(binding.activityCreateUser,"phone Number Not Match","Retry");
            }
            else{
                CreateAccount account = new CreateAccount();
                account.setUserName(userName);
                account.setPassword(password);
                account.setEmail(email);
                account.setCity(city);
                account.setAddress(address);
                account.setPhoneNumber(phoneNumber);
                account.setBloodGroup(bloodGroup);
                account.setLatitude(Constants.LATITUDE);
                account.setLongitude(Constants.LANGITUDE);
                handleVolleyRequests.createUser(account,coordinatorLayout);
            }
        }else{
            handleVolleyRequests.showMessage(binding.activityCreateUser,"Please Fill Form","Retry");
        }
     }
    /**
     *   Check Password is   Correct Format
     * @param password
     * @return
     */
    public  boolean isPassword(String password){
        if(password.length()>4){
            return  true;
        }
        else
        {
            return false;
        }

    }


}
