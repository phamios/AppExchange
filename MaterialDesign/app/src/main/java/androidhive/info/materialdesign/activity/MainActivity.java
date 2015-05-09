package androidhive.info.materialdesign.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v4.view.ViewPager;

import androidhive.info.materialdesign.fragment.HistoryFragment;
import androidhive.info.materialdesign.fragment.IntroFragment;
import androidhive.info.materialdesign.fragment.RateFragment;
import androidhive.info.materialdesign.fragment.ShareFragment;
import androidhive.info.materialdesign.tabs.SlidingTabLayout;
import androidhive.info.materialdesign.tabs.ViewPagerAdapter;

import android.util.Log;
import androidhive.info.materialdesign.R;
import androidhive.info.materialdesign.util.PostJson;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.os.Build;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Notification;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends ActionBarActivity implements FragmentDrawer.FragmentDrawerListener {

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences pre;

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private String URL_REG = "http://ytube301.com/api/insert_mobile_user/";
    private String URL_AUTHEN = "http://ytube301.com/api/authenmobile/";
   // Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Home","Events"};
    int Numboftabs =2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notifyUser();

        //GET IMEI ANDROID
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        //Lưu dữ liệu nội bộ trên app
        pre = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=pre.edit();
        edit.putString("USERID",telephonyManager.getDeviceId());
        edit.commit();

        PostJson postinfo = new PostJson();
        String[] phoneState = PhoneState();
        String checkResult = postinfo.CheckExit(URL_AUTHEN + telephonyManager.getDeviceId());
        Log.d("RESULT_REGISTER>>>>",checkResult);
        if(Integer.parseInt(checkResult) != 0){

        } else {
            String userid = postinfo.CheckorReg(URL_REG, telephonyManager.getDeviceId(),
                    phoneState[1],
                    phoneState[2],
                    phoneState[3],
                    phoneState[4],
                    phoneState[5]);
            Log.d(">>>>> REGINFO ",userid);
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);

    }

    public String[] PhoneState(){
        TelephonyManager manager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        String carrierName = manager.getNetworkOperatorName();
        String phoneNumber =manager.getLine1Number();
        String simOperator = manager.getSimOperatorName();

        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;

        String[] phoneState = new String[6];
        phoneState[1] = carrierName;
        phoneState[2] = phoneNumber;
        phoneState[3] = simOperator;
        phoneState[4] = manufacturer;
        phoneState[5] = model;

        return phoneState;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
            displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_appexchange);
                break;
            case 1:
                fragment = new HistoryFragment();
                title = getString(R.string.title_history);
                break;
            case 2:
                fragment = new ProfileFragment();
                title = getString(R.string.title_messages);
                break;
            case 3:
                fragment = new ShareFragment();
                title = getString(R.string.title_share);
                break;
            case 4:
                fragment = new RateFragment();
                title = getString(R.string.title_rate);
                break;
            case 5:
                fragment = new IntroFragment();
                title = getString(R.string.title_intro);
                break;
            case 6:
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

                // Setting Dialog Title
                alertDialog.setTitle("Thoát ứng dụng ?");

                // Setting Dialog Message
                alertDialog.setMessage("Bạn có chắc muốn thoát khỏi ứng dụng này ?");

                // Setting Icon to Dialog
               // alertDialog.setIcon(R.drawable.delete);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertDialog.show();

                title = getString(R.string.title_exit);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }


    public void notifyUser() {

        NotificationManager notificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(MainActivity.this, HomeFragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        // use the flag FLAG_UPDATE_CURRENT to override any notification already
        // there
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification(R.drawable.ic_profile,
                "Có ứng dụng mới, nhanh tay cài đặt để kiếm tiền ...", System.currentTimeMillis());
        notification.flags = Notification.FLAG_AUTO_CANCEL
                | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND;

        notification.setLatestEventInfo(this, getString(R.string.app_name),
                "Ứng dụng mới , cài đặt ngay !", pendingIntent);
        // 10 is a random number I chose to act as the id for this notification
        notificationManager.notify(10, notification);
    }


}
