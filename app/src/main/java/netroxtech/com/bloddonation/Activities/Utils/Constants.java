package netroxtech.com.bloddonation.Activities.Utils;

/**
 * Created by mac on 7/24/2017.
 */

public class Constants {

    public  static final String APP_LOG="Blood Donation App: ";

    public  static final String WEBSITE_NAME="http://blooddonationtest.000webhostapp.com/";
    public  static final String CREATE_ACCOUNT=WEBSITE_NAME+"blooddoner/blooddoner/sendData/createAccount.php";
    public  static final String LOGIN_URL=WEBSITE_NAME+"blooddoner/blooddoner/sendData/loginUser.php";
    public  static final String POST_BLOOD_NEED=WEBSITE_NAME+"blooddoner/blooddoner/sendData/uploadPost.php";
    public  static final String POST_BLOOD_STATUS=WEBSITE_NAME+"blooddoner/blooddoner/sendData/uploadStatus.php";
    public  static final String VIEW_POST_BLOOD_NEED=WEBSITE_NAME+"blooddoner/blooddoner/get/readAllPosts.php";
    public  static final String VIEW_BLOOD_HISTORY=WEBSITE_NAME+"blooddoner/blooddoner/get/readStatus.php";
    public  static final String UPDATE_NUMBER=WEBSITE_NAME+"blooddoner/blooddoner/update/updatenumber.php";
    public  static final String UPDATE_BLOODGROUP=WEBSITE_NAME+"blooddoner/blooddoner/update/updatebg.php";
    public  static final String UPDATE_CITY=WEBSITE_NAME+"blooddoner/blooddoner/update/updatecity.php";
    public  static final String UPDATE_EMAIL=WEBSITE_NAME+"blooddoner/blooddoner/update/updateemail.php";

    //  @Model CreateAccount

    public static final String userName ="userName";
    public static final String email="email";
    public static final String password="password";
    public static final String phoneNumber="phoneNumber";
    public static final String city="city";
    public static final String address="address";
    public static final String bloodGroup="bloodGroup";
    public static final String latitude="latitude";
    public static final String longitude="longitude";

    //  Static  Variabe for  Location Save
    public static  String LANGITUDE;
    public static  String LATITUDE;

    // Share  Pref Setting
    public static final String  PREF_ID = "id";
    public static final String  PREF_NAME = "blooddonation";
    public static final String  PREF_USERNAME = "username";
    public static final String  PREF_EMAIL = "email";
    public static final String  PREF_PHONE_NUMBER= "phonenumber";
    public static final String  PREF_CITY = "city";
    public static final String  PREF_ADDRESS = "address";
    public static final String  PREF_BLOOD_GROUP = "bloodgroup";
    public static final String  PREF_LATITUDE = "latitude";
    public static final String  PREF_LONGITUDE = "longitude";

    //  @Model Upload Post
    public static final String post_phoneNumber ="phoneNumber";
    public static final String post_city ="city";
    public static final String post_address="address";
    public static final String post_title ="title";
    public static final String post_donerId ="donerId";
    public static final String post_latitude="latitude";
    public static final String post_longitude="longitude";
    public static final String post_bloodGroup="bgroup";

    // @Model  MyStatus
    public static final String status_title ="title";
    public static final String status_address ="address";
    public static final String status_userId ="userId";
    public static final String status_date ="date";
    public static final String status_city ="city";
    public static final String status_bG ="bloodGroup";

}
