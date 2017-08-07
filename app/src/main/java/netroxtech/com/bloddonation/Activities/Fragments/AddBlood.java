package netroxtech.com.bloddonation.Activities.Fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import netroxtech.com.bloddonation.Activities.Applications.HandleVolleyRequests;
import netroxtech.com.bloddonation.Activities.Applications.InitializeSharePref;
import netroxtech.com.bloddonation.Activities.Models.CreateAccount;
import netroxtech.com.bloddonation.Activities.Models.UploadPost;
import netroxtech.com.bloddonation.Activities.Utils.Constants;
import netroxtech.com.bloddonation.R;
import netroxtech.com.bloddonation.databinding.ActivityPostBloodBinding;


public class AddBlood extends Fragment implements  View.OnClickListener {
    HandleVolleyRequests handleVolleyRequests;
    ActivityPostBloodBinding binding;
    InitializeSharePref sharePref;
    EditText titleb,phoneNumebrb,addressb;
    Spinner cityb,bloodGroupb;
    Button postRequest;
    CoordinatorLayout coordinatorLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_post_blood, container, false);
               setXML(view);

        return view;
    }
    public void setXML(View  view){
        handleVolleyRequests = new HandleVolleyRequests(getActivity());
        sharePref = new InitializeSharePref(getActivity());
        titleb =  (EditText) view.findViewById(R.id.activity_post_blood_Title);
        phoneNumebrb = (EditText)view.findViewById(R.id.activity_post_blood_phoneNumber);
        addressb =  (EditText)view.findViewById(R.id.postBlood_user_address);
        cityb = (Spinner) view.findViewById(R.id.postBlood_user_city);
        bloodGroupb = (Spinner)view.findViewById(R.id.postBlood_bloodgroup);
        postRequest = (Button) view.findViewById(R.id.post_request_btn);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.activity_post_blood);
        postRequest.setOnClickListener(this);
    }
    public void uploadPostButton(){
        TakeFormData();
    }
    public void TakeFormData(){

        String title = titleb.getText().toString();
        String  phoneNumber=  phoneNumebrb.getText().toString();
        String address =addressb.getText().toString();
        String  city= cityb.getSelectedItem().toString();
        String bloodGroup = bloodGroupb.getSelectedItem().toString();
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
                Toast.makeText(getActivity(),"phone Pattren Not Match",Toast.LENGTH_LONG).show();
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
            handleVolleyRequests.showMessage(coordinatorLayout,"Please Try Again","Retry");
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.post_request_btn){
            uploadPostButton();
        }
    }
}
