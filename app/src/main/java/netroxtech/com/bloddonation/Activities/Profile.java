package netroxtech.com.bloddonation.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.antlr.v4.automata.ATNFactory;

import netroxtech.com.bloddonation.Activities.Applications.HandleVolleyRequests;
import netroxtech.com.bloddonation.Activities.Applications.InitializeSharePref;
import netroxtech.com.bloddonation.Activities.Models.CreateAccount;
import netroxtech.com.bloddonation.Activities.Utils.Constants;
import netroxtech.com.bloddonation.R;

public class Profile extends AppCompatActivity {

    TextView userName,number,email,city,bloodg;
    Context context;
    CreateAccount  account;
    InitializeSharePref sharePref;
    HandleVolleyRequests handleVolleyRequests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        context = this;
        setXML();
    }
    public void setXML(){
        sharePref = new InitializeSharePref(context);
        handleVolleyRequests= new HandleVolleyRequests(context);
        userName= (TextView)findViewById(R.id.userName_profile);
        number= (TextView)findViewById(R.id.mobile_numbe_profile);
        email= (TextView)findViewById(R.id.email_profile);
        city= (TextView)findViewById(R.id.city_profile);
        bloodg= (TextView)findViewById(R.id.bg_profile);
        account = sharePref.getUserData();
        //  set profile data
        userName.setText(account.getUserName());
        number.setText(account.getPhoneNumber());
        email.setText(account.getEmail());
        city.setText(account.getCity());
        bloodg.setText(account.getBloodGroup());

    }

    /**,
     *  Change Number Button
     * @param view
     */
    public void changeNumberButton(View view){

        ChangeNumber("Change Number");

    }
    /**,
     *  Change email Button
     * @param view
     */
    public void changeEmailButton(View view){

       changeEmail("Change Email");

    }
    /**,
     *  Change City Button
     * @param view
     */
    public void changeCityButton(View view){

        changeCity("Change City");

    }
    /**,
     *  Change City Button
     * @param view
     */
    public void changeBGButton(View view){

        changeBg("Change Blood Group");

    }

    /**
     *   Change  Number
     * @param title
     * @return
     */
    public String ChangeNumber(String title){
        String m_Text = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);

// Set up the input
        final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text  = input.getText().toString();
                Log.v(Constants.APP_LOG,m_Text);
                handleVolleyRequests.updateProfileNumber(m_Text,String.valueOf(account.getId()),Constants.UPDATE_NUMBER,"phoneNumber");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
        return m_Text;
    }

    /**
     *   Change  Email
     * @param title
     * @return
     */
    public String changeEmail(String title){
        String m_Text = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);

// Set up the input
        final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text  = input.getText().toString();
                Log.v(Constants.APP_LOG,m_Text);
                handleVolleyRequests.updateProfileNumber(m_Text,String.valueOf(account.getId()),Constants.UPDATE_EMAIL,"email");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
        return m_Text;
    }
    /**
     *   Change  city
     * @param title
     * @return
     */
    public String changeCity(String title){
        String m_Text = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);

// Set up the input
        final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text  = input.getText().toString();
                Log.v(Constants.APP_LOG,m_Text);
                handleVolleyRequests.updateProfileNumber(m_Text,String.valueOf(account.getId()),Constants.UPDATE_CITY,"city");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
        return m_Text;
    }
    /**
     *   Change  city
     * @param title
     * @return
     */
    public String changeBg(String title){
        String m_Text = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);

// Set up the input
        final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text  = input.getText().toString();
                Log.v(Constants.APP_LOG,m_Text);
                handleVolleyRequests.updateProfileNumber(m_Text,String.valueOf(account.getId()),Constants.UPDATE_BLOODGROUP,"bloodgroup");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
        return m_Text;
    }
}
