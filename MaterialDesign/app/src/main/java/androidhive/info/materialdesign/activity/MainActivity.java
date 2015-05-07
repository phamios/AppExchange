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


import androidhive.info.materialdesign.R;
import androidhive.info.materialdesign.util.PostJson;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.os.Build;


public class MainActivity extends ActionBarActivity implements FragmentDrawer.FragmentDrawerListener {

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences pre;

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private String URL = "http://ytube301.com/api/authenmobile";

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

        //GET IMEI ANDROID
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        //Lưu dữ liệu nội bộ trên app
        pre = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=pre.edit();
        edit.putString("USERID",telephonyManager.getDeviceId());
        edit.commit();

        PostJson postinfo = new PostJson();
        String[] phoneState = PhoneState();
        postinfo.CheckorReg(URL + "/" + telephonyManager.getDeviceId(), telephonyManager.getDeviceId(),
                phoneState[1],
                phoneState[2],
                phoneState[3],
                phoneState[4],
                phoneState[5]);


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
               //fragment = new MessagesFragment();
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
}
