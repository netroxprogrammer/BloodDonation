package netroxtech.com.bloddonation.Activities.adapters;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import netroxtech.com.bloddonation.Activities.MapsActivity;
import netroxtech.com.bloddonation.Activities.Models.UploadPost;
import netroxtech.com.bloddonation.Activities.PostBlood;
import netroxtech.com.bloddonation.Activities.Utils.Constants;
import netroxtech.com.bloddonation.R;

/**
 * Created by mac on 7/28/2017.
 */

public class PostAdapter extends BaseAdapter {
    private Context context;

    private LayoutInflater mlayoutinfluate;
    List<UploadPost> uploadPost;

    /**
     *  Constructor
     * @param context
     * @param uploadPost
     */

    public PostAdapter(Context context, List<UploadPost> uploadPost) {
        this.context = context;
        this.uploadPost = uploadPost;
        this.mlayoutinfluate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return uploadPost.size();
    }

    @Override
    public Object getItem(int i) {
        return uploadPost.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final String latitude;
        final String longitude, number;
        ViewHolder holder = new ViewHolder();
        if (view == null) {
            view = mlayoutinfluate.inflate(R.layout.post_list_data, null);
            holder.title = view.findViewById(R.id.bloodList_title);
            holder.address = view.findViewById(R.id.address_message);
            holder.number = view.findViewById(R.id.postList_call_number);
            holder.latitude = view.findViewById(R.id.postList_latitude);
            holder.longitude = view.findViewById(R.id.postList_longitude);
            holder.location = view.findViewById(R.id.bloodList_location);
            holder.call = view.findViewById(R.id.bloodList_call);
            holder.message = view.findViewById(R.id.bloodList_Message);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        UploadPost postb = uploadPost.get(i);
        holder.title.setText(postb.getTitle());
        holder.address.setText(postb.getAddress());
        holder.number.setText(postb.getPhoneNumber());
        holder.latitude.setText(postb.getLatitude());
        holder.longitude.setText(postb.getLongitude());
        latitude = holder.latitude.getText().toString();
        ;
        longitude = holder.longitude.getText().toString();
        number = holder.number.getText().toString();
        /**
         *  Location Image Click Listener
         */
        holder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,location,Toast.LENGTH_LONG).show();
                if (latitude != null && !latitude.equalsIgnoreCase("")) {

                    Intent intent = new Intent(context, MapsActivity.class);
                    intent.putExtra(Constants.latitude, latitude);
                    intent.putExtra(Constants.longitude, longitude);

                    context.startActivity(intent);

                }
            }
        });

        /**
         *   Make Call
         */
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!number.equalsIgnoreCase("") && number != null) {

                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }

                    context.startActivity(intent);
                }
            }
        });

        /**
         *  Send Message box
         */
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeMessageBox(number);
            }
        });
        return view;
    }
    class ViewHolder{
        TextView title,address,number,latitude,longitude;
        ImageView location,message,call;
    }
    public void makeMessageBox(final String number){
//         / String m_Text = "";


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Write Message");

// Set up the input
        final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text  = input.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number, null, m_Text, null, null);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
