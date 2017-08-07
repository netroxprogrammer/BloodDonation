package netroxtech.com.bloddonation.Activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import netroxtech.com.bloddonation.Activities.Applications.HandleVolleyRequests;
import netroxtech.com.bloddonation.Activities.Applications.InitializeSharePref;
import netroxtech.com.bloddonation.Activities.Models.CreateAccount;
import netroxtech.com.bloddonation.Activities.Models.UploadPost;
import netroxtech.com.bloddonation.Activities.Utils.Constants;
import netroxtech.com.bloddonation.R;
import netroxtech.com.bloddonation.databinding.ActivityPostBloodBinding;

public class PostBlood extends AppCompatActivity {
    HandleVolleyRequests handleVolleyRequests;
    ActivityPostBloodBinding binding;
    InitializeSharePref sharePref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_post_blood);
        setXML();

    }
    public void setXML(){
        handleVolleyRequests = new HandleVolleyRequests(this);
        sharePref = new InitializeSharePref(this);
    }
    public void uploadPostButton(View view){
        TakeFormData();
    }
    public void TakeFormData(){
        String title = binding.activityPostBloodTitle.getText().toString();
        String  phoneNumber=  binding.activityPostBloodPhoneNumber.getText().toString();
        String address = binding.postBloodUserAddress.getText().toString();
        String  city= binding.postBloodUserCity.getSelectedItem().toString();
        String bloodGroup = binding.postBloodBloodgroup.getSelectedItem().toString();
        CreateAccount account = sharePref.getUserData();
        String  donderId = String.valueOf(account.getId());
        String latitude  = account.getLatitude();
        String longitude = account.getLongitude();

        if((!title.equalsIgnoreCase("") && title!=null) &&
                (!phoneNumber.equalsIgnoreCase("") && phoneNumber!=null) &&
                (!address.equalsIgnoreCase("") && address!=null)&&
                (!city.equalsIgnoreCase("") && city!=null) &&
                (!bloodGroup.equalsIgnoreCase("") && bloodGroup!=null) &&
                (!latitude.equalsIgnoreCase("") && latitude!=null) &&
                (!longitude.equalsIgnoreCase("") &&longitude!=null)){
            if(!handleVolleyRequests.isPhoneNumber(phoneNumber)){
                Toast.makeText(this,"phone Pattren Not Match",Toast.LENGTH_LONG).show();

            }
            else{
                UploadPost post= new UploadPost();
                post.setDonerId(account.getId());
                post.setTitle(title);
                post.setPhoneNumber(phoneNumber);
                post.setAddress(address);
                post.setCity(city);
                post.setBloodGroup(bloodGroup);

                post.setLongitude(account.getLatitude());
                post.setLongitude(account.getLongitude());
                Log.v(Constants.APP_LOG+ this.getClass().getSimpleName(),account.getLatitude()+" " +longitude +" "+donderId );
                handleVolleyRequests.UploadData(post);

            }

    }
        else{
            handleVolleyRequests.showMessage(binding.activityPostBlood,"Please Try Again","Retry");
        }
    }
}
