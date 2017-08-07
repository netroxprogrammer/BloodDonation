package netroxtech.com.bloddonation.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;

import netroxtech.com.bloddonation.Activities.Applications.HandleVolleyRequests;
import netroxtech.com.bloddonation.Activities.Applications.InitializeSharePref;
import netroxtech.com.bloddonation.Activities.Models.CreateAccount;
import netroxtech.com.bloddonation.R;

public class ViewBloodRequests extends AppCompatActivity {

    Spinner city;
    ListView   list;
    HandleVolleyRequests handleVolleyRequests;
    InitializeSharePref sharePref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_blood_requests);
        setXML();
        CreateAccount ac =  sharePref.getUserData();
        handleVolleyRequests.ReadPostRequest(list,ac.getCity());
    }
    public void setXML(){
        handleVolleyRequests = new HandleVolleyRequests(this);
        sharePref = new InitializeSharePref(this);
        list = (ListView)findViewById(R.id.activity_new_blood_request_list);
        city = (Spinner)findViewById(R.id.activity_ViewPost_city);
    }
    public void searchClick(View view){
        String cityName = city.getSelectedItem().toString();
        handleVolleyRequests.ReadPostRequest(list,cityName);
    }
}
