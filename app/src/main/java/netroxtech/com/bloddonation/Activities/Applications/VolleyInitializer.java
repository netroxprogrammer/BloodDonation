package netroxtech.com.bloddonation.Activities.Applications;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by mac on 7/24/2017.
 */

public class VolleyInitializer {

    private  static Context mcontext;
    private  static VolleyInitializer mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public VolleyInitializer(Context context){
        mcontext = context;
        mRequestQueue = getRequestQueue();
    }

    public static  synchronized   VolleyInitializer getmInstance(Context context){
        if(mInstance==null){
            mInstance= new VolleyInitializer(context);
        }
        return mInstance;
    }
    public RequestQueue getRequestQueue(){
        if(mRequestQueue==null){
            mRequestQueue = Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return  mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
    public ImageLoader getImageLoader(){
        return this.mImageLoader;
    }
}
