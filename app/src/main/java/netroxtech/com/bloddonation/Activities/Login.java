package netroxtech.com.bloddonation.Activities;

import android.databinding.DataBindingUtil;
import android.os.Debug;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import netroxtech.com.bloddonation.Activities.Applications.HandleVolleyRequests;
import netroxtech.com.bloddonation.Activities.Models.CreateAccount;
import netroxtech.com.bloddonation.R;
import netroxtech.com.bloddonation.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    HandleVolleyRequests handleVolleyRequests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        setXML();
    }

    /**
     * Make All XML Setting
     */
    public void  setXML(){
        handleVolleyRequests = new HandleVolleyRequests(this);
    }

    public void makePushNotification(){

    }

    /**
     *   Check Form  is ok
     *   Make Login Request
     */

    public void isLoginForm(){
        CreateAccount account   = new CreateAccount();
        String  email = binding.activityLoginEmail.getText().toString();
        String  password = binding.activityLoginPassword.getText().toString();
        if((!email.isEmpty() && email!=null && !email.equalsIgnoreCase("") ) &&
                (!password.isEmpty() && password!=null && !password.equalsIgnoreCase(""))){
            if(isPassword(password)){
                account.setEmail(email);
                account.setPassword(password);
                Log.v("email:",account.getEmail());

                handleVolleyRequests.loginUser(account,binding.activityLogin);
            }
           else {
                Toast.makeText(this,"Password more than 4 ",Toast.LENGTH_LONG).show();
             //   handleVolleyRequests.showMessage(binding.activityLogin,"Password more than 4","Retry");
            }
            if(!handleVolleyRequests.isEmail(email)){
                Toast.makeText(this,"Email Pattren Not Match",Toast.LENGTH_LONG).show();

            }
        }else{
           // handleVolleyRequests.showMessage(binding.activityLogin,"Please Fill Form","Retry");
            Toast.makeText(this,"Please Fill Form ",Toast.LENGTH_LONG).show();
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

    /**
     *      make call Login Button
     * @param v
     */
    public void loginButtonClick(View v){
        isLoginForm();
    }
}
