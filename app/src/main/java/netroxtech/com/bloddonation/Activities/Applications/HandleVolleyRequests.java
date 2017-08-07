package netroxtech.com.bloddonation.Activities.Applications;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import netroxtech.com.bloddonation.Activities.HandleOptions;
import netroxtech.com.bloddonation.Activities.Login;
import netroxtech.com.bloddonation.Activities.MainTabs;
import netroxtech.com.bloddonation.Activities.Models.CreateAccount;
import netroxtech.com.bloddonation.Activities.Models.MyStatus;
import netroxtech.com.bloddonation.Activities.Models.UploadPost;
import netroxtech.com.bloddonation.Activities.PostBlood;
import netroxtech.com.bloddonation.Activities.Utils.Constants;
import netroxtech.com.bloddonation.Activities.adapters.HistoryStatusAdapter;
import netroxtech.com.bloddonation.Activities.adapters.PostAdapter;
import netroxtech.com.bloddonation.R;

/**
 * Created by mac on 7/24/2017.
 */

public class HandleVolleyRequests {
    String status;
    Context context;
    JSONObject reader;
    InitializeSharePref prefInit;
    PostAdapter postAdapter;
    public  HandleVolleyRequests(Context  context){
        this.context  = context;
        prefInit= new InitializeSharePref(context);

    }
    /**
     *      create User
     * @param account
     * @param coordinatorLayout
     * @return
     */
    public String createUser(final CreateAccount account, final CoordinatorLayout coordinatorLayout){
        final ProgressDialog pDialog = new ProgressDialog(this.context,R.style.DialogTheme);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Wait  few seconds ");
        pDialog.show();

        StringRequest sendResqurst = new StringRequest(Request.Method.POST, Constants.CREATE_ACCOUNT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    reader = new JSONObject(response);
                    JSONObject data1 = reader.getJSONObject("result");
                    Log.v("data",data1.getString("success"));
                    String value = data1.getString("success");
                    if (value.equalsIgnoreCase("1")) {
                        pDialog.dismiss();
                        Toast.makeText(context,"Account Create Successfully  :)",Toast.LENGTH_LONG).show();
                        //showMessage(coordinatorLayout,"Account Create Successfully  :) ","close");
                        goTologin();
                    }else{
                        status = value;
                        pDialog.dismiss();
                      //  showMessage(coordinatorLayout,"sorry Try Again :( ","Retry");
                        Toast.makeText(context,"sorry Try Again :(",Toast.LENGTH_LONG).show();
                    }
                }

                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error ",error.toString());
                pDialog.hide();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params =  new HashMap<>();
                params.put(Constants.userName,account.getUserName());
                params.put(Constants.email,account.getEmail());
                params.put(Constants.password,account.getPassword());
                params.put(Constants.phoneNumber,account.getPhoneNumber());
                params.put(Constants.city,account.getCity());
                params.put(Constants.address,account.getAddress());
                params.put(Constants.bloodGroup,account.getBloodGroup());
                CreateAccount accountt = prefInit.getUserData();
                Log.v(Constants.APP_LOG,accountt.getLatitude());
                params.put(Constants.latitude,accountt.getLatitude());
                params.put(Constants.longitude,accountt.getLongitude());
                return params;
            }
        };
        VolleyInitializer.getmInstance(this.context).addToRequestQueue(sendResqurst);
        return status;
    }

    /**
     *    ShowMessage  on Snack Bar
     * @param coordinatorLayout
     * @param message
     * @param action
     */
    public void  showMessage(CoordinatorLayout coordinatorLayout, String message,String action){

        Snackbar snackbar =      Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_LONG)
                .setAction(action, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    /**
     *  Go to  Login Activity
     */
    public void goTologin(){
        Intent intent = new Intent(context, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
    public void GoUserAccount(){
        Intent intent = new Intent(context, MainTabs.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    /**
     *    Login  User Function
     * @param coordinatorLayout
     */

     public void  loginUser(final CreateAccount account, final CoordinatorLayout coordinatorLayout){
        final ProgressDialog pDialog = new ProgressDialog(this.context, R.style.DialogTheme);
         pDialog.setCancelable(false);
         pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Wait  few seconds ");
        pDialog.show();

        StringRequest sendResqurst = new StringRequest(Request.Method.POST, Constants.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    reader = new JSONObject(response);
                    if(reader.length() > 0) {
                        JSONObject data1 = reader.getJSONObject("user");
                        Log.v("datajson", data1.toString());
                        String value = data1.getString("success");
                        if (value.equalsIgnoreCase("1")) {
                            pDialog.dismiss();
                            Log.v("PhoneNumber:  ", data1.getString("userName"));
                            CreateAccount userData =  new CreateAccount();
                            userData.setId(data1.getInt("id"));
                            userData.setUserName(data1.getString("userName"));
                            userData.setEmail(data1.getString("email"));
                            userData.setPhoneNumber(data1.getString("phoneNumber"));
                            userData.setCity(data1.getString("city"));
                            userData.setAddress(data1.getString("Address"));
                            userData.setBloodGroup(data1.getString("bloodgroup"));
                            userData.setLatitude(data1.getString("latitude"));
                            userData.setLongitude(data1.getString("longitude"));
                            userData.setLongitude(data1.getString("longitude"));
                             prefInit.saveUserData(userData);
                           // showMessage(coordinatorLayout, "Login Successfully  :) ", "close");
                            Toast.makeText(context,"Login Successfully  :) ",Toast.LENGTH_LONG).show();
                            GoUserAccount();
                        } else {

                            pDialog.dismiss();
                           // showMessage(coordinatorLayout, "sorry Try Again :( ", "Retry");
                            Toast.makeText(context,"sorry Try Again :( ",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        showMessage(coordinatorLayout, "Sorry no Data Found :( ", "Retry");
                    }
                }

                catch(JSONException e){
                    e.printStackTrace();
                    pDialog.dismiss();
                    showMessage(coordinatorLayout,"sorry Try Again :( ","Retry");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error ",error.toString());
                pDialog.hide();
                Toast.makeText(context,"sorry Try Again :( ",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params =  new HashMap<>();
                params.put(Constants.email,account.getEmail());
                params.put(Constants.password,account.getPassword());
                return params;
            }
        };
        VolleyInitializer.getmInstance(this.context).addToRequestQueue(sendResqurst);

    }

    /**
     * Uplaod  Post Blood Request
     * @param data
     * @return
     */
    public String UploadData(final UploadPost data){
        final ProgressDialog pDialog = new ProgressDialog(this.context,R.style.DialogTheme);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Wait  few seconds ");
        pDialog.show();

        StringRequest sendResqurst = new StringRequest(Request.Method.POST, Constants.POST_BLOOD_NEED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.v(Constants.APP_LOG,response.toString());
               //     Toast.makeText(context,data.getTitle(),Toast.LENGTH_LONG).show();
                    reader = new JSONObject(response);
                    JSONObject data1 = reader.getJSONObject("result");
                    Log.v("data",data1.getString("success"));
                    String value = data1.getString("success");
                   // v pDialog.dismiss();
                    if (value.equalsIgnoreCase("1")) {
                        pDialog.dismiss();
                        Toast.makeText(context,"Your Request Successfully Uploaded",Toast.LENGTH_LONG).show();
                        //showMessage(coordinatorLayout,"Account Create Successfully  :) ","close");
                     //   goTologin();
                    }else{
                        status = value;
                        pDialog.dismiss();
                        //  showMessage(coordinatorLayout,"sorry Try Again :( ","Retry");
                        Toast.makeText(context,"sorry Try Again :(",Toast.LENGTH_LONG).show();
                    }
                }

                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error ",error.toString());
                pDialog.hide();
            }
        }){
            @Override
            protected Map<String,String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put(Constants.post_title, data.getTitle());
                params.put(Constants.post_phoneNumber, data.getPhoneNumber());
                params.put(Constants.post_address, data.getAddress());
                params.put(Constants.post_bloodGroup, data.getBloodGroup());
                params.put(Constants.post_city, data.getCity());
                params.put(Constants.post_donerId, String.valueOf(data.getDonerId()));
                CreateAccount account = prefInit.getUserData();
                Log.v(Constants.APP_LOG,account.getLatitude());
                params.put(Constants.post_latitude, account.getLatitude());
                params.put(Constants.post_longitude, account.getLongitude());
                return params;
            }
        };
        VolleyInitializer.getmInstance(this.context).addToRequestQueue(sendResqurst);
        return status;
    }

    /**
     * Read Post Requests
     */
    public void  ReadPostRequest(final ListView listView,String city){

        Log.v(Constants.APP_LOG+"::",city);
        final ProgressDialog pDialog = new ProgressDialog(this.context, R.style.DialogTheme);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Wait  few seconds ");
        pDialog.show();
        final List<UploadPost> uploadPostsList = new ArrayList<>();
        StringRequest sendResqurst = new StringRequest(Request.Method.POST, Constants.VIEW_POST_BLOOD_NEED+"?city="+city, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    reader = new JSONObject(response);
                    if(reader.length() > 0) {
                        JSONObject data1 = reader.getJSONObject("result");
                        Log.v("datajson", data1.toString());
                        String value = data1.getString("success");
                        if (value.equalsIgnoreCase("1")) {
                            pDialog.dismiss();
                            JSONArray array  = reader.getJSONArray("data");
                            for(int i=0;i<array.length();i++){
                                UploadPost uploadPost = new UploadPost();
                                JSONObject  data = array.getJSONObject(i);
                                uploadPost.setTitle(data.getString("title"));
                                uploadPost.setDonerId(data.getInt("donerId"));
                                uploadPost.setId(data.getInt("postId"));
                                uploadPost.setCity(data.getString("city"));
                                uploadPost.setAddress(data.getString("address"));
                                uploadPost.setPhoneNumber(data.getString("phoneNumber")) ;
                                uploadPost.setBloodGroup(data.getString("bgroup"));
                                uploadPost.setLatitude(data.getString("latitude"));
                                uploadPost.setLongitude(data.getString("longitude"));
                                uploadPostsList.add(uploadPost);

                            }
                            //Log.v("PhoneNumber:  ", data1.getString("donerId"));
                            ListAdapter list = postAdapter = new PostAdapter(context,uploadPostsList);
                            listView.setAdapter(list);
                            // showMessage(coordinatorLayout, "Login Successfully  :) ", "close");
                          //  Toast.makeText(context," Post Load ",Toast.LENGTH_LONG).show();

                        } else {

                            pDialog.dismiss();
                            // showMessage(coordinatorLayout, "sorry Try Again :( ", "Retry");
                            Toast.makeText(context,"sorry Try Again :( ",Toast.LENGTH_LONG).show();
                        }
                    }else{

                        Toast.makeText(context,"Sorry no Data Found :( ",Toast.LENGTH_LONG).show();
                    }
                }

                catch(JSONException e){
                    e.printStackTrace();
                    pDialog.dismiss();
                    Toast.makeText(context,"sorry Try Again :(  ",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error ",error.toString());
                pDialog.hide();
                Toast.makeText(context,"sorry Try Again :( ",Toast.LENGTH_LONG).show();
            }
        });
        VolleyInitializer.getmInstance(this.context).addToRequestQueue(sendResqurst);

    }

    /**
     * check Email Pattren
     * @param email
     * @return
     */

    public final static boolean  isEmail(CharSequence email){

        if(email==null){
            return  false;
        }
        else{
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
    public  final  static boolean isPhoneNumber(CharSequence phone){
        if(phone==null){
            return false;
        }

        else{
          return  Patterns.PHONE.matcher(phone).matches();
        }
    }

    /**
     * Uplaod  History Status
     * @param status
     * @return
     */

    public void  uploadStatus(final MyStatus status){
        final ProgressDialog pDialog = new ProgressDialog(this.context,R.style.DialogTheme);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Wait  few seconds ");
        pDialog.show();

        StringRequest sendResqurst = new StringRequest(Request.Method.POST, Constants.POST_BLOOD_STATUS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.v(Constants.APP_LOG,response.toString());
                    //     Toast.makeText(context,data.getTitle(),Toast.LENGTH_LONG).show();
                    reader = new JSONObject(response);
                    JSONObject data1 = reader.getJSONObject("result");
                    Log.v("data",data1.getString("success"));
                    String value = data1.getString("success");
                    // v pDialog.dismiss();
                    if (value.equalsIgnoreCase("1")) {
                        pDialog.dismiss();
                        Toast.makeText(context,"Your Request Successfully Uploaded",Toast.LENGTH_LONG).show();
                        //showMessage(coordinatorLayout,"Account Create Successfully  :) ","close");
                        //   goTologin();
                    }else{
                        pDialog.dismiss();
                        //  showMessage(coordinatorLayout,"sorry Try Again :( ","Retry");
                        Toast.makeText(context,"sorry Try Again :(",Toast.LENGTH_LONG).show();
                    }
                }

                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error ",error.toString());
                pDialog.hide();
            }
        }){
            @Override
            protected Map<String,String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put(Constants.status_title, status.getTitle());
                params.put(Constants.status_address, status.getAddress());
                params.put(Constants.status_bG, status.getBloodGroup());
                params.put(Constants.status_city, status.getCity());
                params.put(Constants.status_date, status.getDate());

                CreateAccount account = prefInit.getUserData();
                Log.v(Constants.APP_LOG,String.valueOf(account.getId()));
                params.put(Constants.status_userId, String.valueOf(account.getId()));
                return params;
            }
        };
        VolleyInitializer.getmInstance(this.context).addToRequestQueue(sendResqurst);

    }

    /**
     * Read Post Requests
     */
    public void  readHistory(final ListView listView,String id){

        Log.v(Constants.APP_LOG+"::",id);
        final ProgressDialog pDialog = new ProgressDialog(this.context, R.style.DialogTheme);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Wait  few seconds ");
        pDialog.show();
        final List<MyStatus> myStatusList = new ArrayList<>();
        StringRequest sendResqurst = new StringRequest(Request.Method.POST, Constants.VIEW_BLOOD_HISTORY+"?id="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    reader = new JSONObject(response);
                    if(reader.length() > 0) {
                        JSONObject data1 = reader.getJSONObject("result");
                        Log.v("datajson", data1.toString());
                        String value = data1.getString("success");
                        if (value.equalsIgnoreCase("1")) {
                            pDialog.dismiss();
                            JSONArray array  = reader.getJSONArray("data");
                            for(int i=0;i<array.length();i++){
                                MyStatus myStatus = new MyStatus();
                                JSONObject  data = array.getJSONObject(i);
                                myStatus.setTitle(data.getString("id"));

                                myStatus.setTitle(data.getString("title"));
                                myStatus.setAddress(data.getString("address"));
                                myStatus.setDate(data.getString("date"));
                                myStatus.setCity(data.getString("city")); ;
                                myStatus.setBloodGroup(data.getString("bloodgroup"));

                                myStatusList.add(myStatus);

                            }
                            //Log.v("PhoneNumber:  ", data1.getString("donerId"));
                            ListAdapter list = new HistoryStatusAdapter(context,myStatusList);
                            listView.setAdapter(list);
                            // showMessage(coordinatorLayout, "Login Successfully  :) ", "close");
                            //  Toast.makeText(context," Post Load ",Toast.LENGTH_LONG).show();

                        } else {

                            pDialog.dismiss();
                            // showMessage(coordinatorLayout, "sorry Try Again :( ", "Retry");
                            Toast.makeText(context,"sorry Try Again :( ",Toast.LENGTH_LONG).show();
                        }
                    }else{

                        Toast.makeText(context,"Sorry no Data Found :( ",Toast.LENGTH_LONG).show();
                    }
                }

                catch(JSONException e){
                    e.printStackTrace();
                    pDialog.dismiss();
                    Toast.makeText(context,"sorry Try Again :(  ",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error ",error.toString());
                pDialog.hide();
                Toast.makeText(context,"sorry Try Again :( ",Toast.LENGTH_LONG).show();
            }
        });
        VolleyInitializer.getmInstance(this.context).addToRequestQueue(sendResqurst);

    }

    /**
     * Update Number
     * @param number
     * @param id
     */
    public void  updateProfileNumber(final String number,String id,String URL,String type){
        final ProgressDialog pDialog = new ProgressDialog(this.context,R.style.DialogTheme);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Wait  few seconds ");
        pDialog.show();

        StringRequest sendResqurst = new StringRequest(Request.Method.POST, URL+"?"+type+"="+number+
                "&id="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.v(Constants.APP_LOG,response.toString());
                    //     Toast.makeText(context,data.getTitle(),Toast.LENGTH_LONG).show();
                    reader = new JSONObject(response);
                    JSONObject data1 = reader.getJSONObject("result");
                    Log.v("data",data1.getString("success"));
                    String value = data1.getString("success");
                    // v pDialog.dismiss();
                    if (value.equalsIgnoreCase("1")) {
                        pDialog.dismiss();
                        Toast.makeText(context,"Please Logout for Refresh",Toast.LENGTH_LONG).show();
                        //showMessage(coordinatorLayout,"Account Create Successfully  :) ","close");
                        //   goTologin();
                    }else{
                        pDialog.dismiss();
                        //  showMessage(coordinatorLayout,"sorry Try Again :( ","Retry");
                        Toast.makeText(context,"sorry Try Again :(",Toast.LENGTH_LONG).show();
                    }
                }

                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error ",error.toString());
                pDialog.hide();
            }
        });
        VolleyInitializer.getmInstance(this.context).addToRequestQueue(sendResqurst);

    }
}
