package netroxtech.com.bloddonation.Activities.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import netroxtech.com.bloddonation.Activities.Applications.HandleVolleyRequests;
import netroxtech.com.bloddonation.Activities.Applications.InitializeSharePref;
import netroxtech.com.bloddonation.Activities.Models.CreateAccount;
import netroxtech.com.bloddonation.Activities.Models.UploadPost;
import netroxtech.com.bloddonation.R;


public class ViewStatus extends Fragment {

    ListView list;
    HandleVolleyRequests  handleVolleyRequests;
    InitializeSharePref sharePref;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view =  inflater.inflate(R.layout.fragment_view_status, container, false);
        context = getActivity();
        setXML(view);
        return view;

    }
    public  void setXML(View view){
        sharePref = new InitializeSharePref(context);
        handleVolleyRequests = new HandleVolleyRequests(context);
        list = (ListView)view.findViewById(R.id.activity_history_list);
        CreateAccount account = sharePref.getUserData();
        handleVolleyRequests.readHistory(list,String.valueOf(account.getId()));

    }
}
