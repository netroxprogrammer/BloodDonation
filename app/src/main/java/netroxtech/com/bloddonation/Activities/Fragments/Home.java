package netroxtech.com.bloddonation.Activities.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import netroxtech.com.bloddonation.Activities.Applications.HandleVolleyRequests;
import netroxtech.com.bloddonation.Activities.Applications.InitializeSharePref;
import netroxtech.com.bloddonation.Activities.Models.CreateAccount;
import netroxtech.com.bloddonation.R;


public class Home extends Fragment  implements View.OnClickListener {


    Spinner city;
     ImageView search;
    ListView list;
    HandleVolleyRequests handleVolleyRequests;
    InitializeSharePref sharePref;
    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_view_blood_requests, container, false);
        setXML(view);
        CreateAccount ac =  sharePref.getUserData();
        handleVolleyRequests.ReadPostRequest(list,ac.getCity());
        return view;

    }
    public void setXML(View view){
        handleVolleyRequests = new HandleVolleyRequests(getActivity());
        sharePref = new InitializeSharePref(getActivity());
        list = (ListView)view.findViewById(R.id.activity_new_blood_request_list);
        city = (Spinner)view.findViewById(R.id.activity_ViewPost_city);
        search  = (ImageView)view.findViewById(R.id.serach_bloodNeed);
        search.setOnClickListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               }
        });
    }
    public void searchClick(){
        String cityName = city.getSelectedItem().toString();
        handleVolleyRequests.ReadPostRequest(list,cityName);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.serach_bloodNeed){
            searchClick();
        }
    }
}
