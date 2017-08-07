package netroxtech.com.bloddonation.Activities.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import netroxtech.com.bloddonation.Activities.Applications.HandleVolleyRequests;
import netroxtech.com.bloddonation.Activities.Models.MyStatus;
import netroxtech.com.bloddonation.Activities.Utils.Constants;
import netroxtech.com.bloddonation.R;


public class Status extends Fragment  implements  View.OnClickListener {

    EditText   title,address;
    Spinner  city,bloodGroup;
    DatePicker   datePicker;
       Context  context;
    Button postStatus;
    HandleVolleyRequests handleVolleyRequests;
    public Status() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_status, container, false);
        context = getActivity();
        setXml(view);
        return view;

    }
    public  void setXml(View view){
        handleVolleyRequests = new HandleVolleyRequests(context);
         title = (EditText)view.findViewById(R.id.activity_status_blood_Title);
        address = (EditText)view.findViewById(R.id.postBlood_status_address);
        city = (Spinner) view.findViewById(R.id.postBlood_status_city);
        bloodGroup = (Spinner) view.findViewById(R.id.postBlood_status);
        datePicker = (DatePicker)view.findViewById(R.id.datePicker_status);
        postStatus = (Button)view.findViewById(R.id.post_status_btn);
        postStatus.setOnClickListener(this);
    }

    public  void TakeFormData(){
        String myTitle = title.getText().toString();
        String myAddress = address.getText().toString();
        String myCity = city.getSelectedItem().toString();
        String myBGroup = bloodGroup.getSelectedItem().toString();
        String date =String.valueOf(datePicker.getDayOfMonth())+"-"
                +String.valueOf(datePicker.getMonth())+"-"+
                String.valueOf(datePicker.getYear());
        Log.v(Constants.APP_LOG+"::",date);
        if(!myTitle.equalsIgnoreCase("") && !myAddress.equalsIgnoreCase("")
                && !myCity.equalsIgnoreCase("") && !myBGroup.equalsIgnoreCase("")
                && !date.equalsIgnoreCase("") ){
            MyStatus  mstatus = new MyStatus();
            mstatus.setTitle(myTitle);
            mstatus.setAddress(myAddress);
            mstatus.setCity(myCity);
            mstatus.setBloodGroup(myBGroup);
            mstatus.setDate(date);
           handleVolleyRequests.uploadStatus(mstatus);
        }
        else{
            Toast.makeText(context,"Please fill Form",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.post_status_btn){
            TakeFormData();
        }
    }
}
