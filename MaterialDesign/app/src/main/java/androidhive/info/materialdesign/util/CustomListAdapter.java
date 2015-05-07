package androidhive.info.materialdesign.util;

/**
 * Created by sonpx on 5/4/15.
 */
import androidhive.info.materialdesign.R;
import androidhive.info.materialdesign.util.AppController;
import androidhive.info.materialdesign.model.App;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Button;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.util.Log;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.ClientProtocolException;



public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<App> AppItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<App> AppItems) {
        this.activity = activity;
        this.AppItems = AppItems;
    }

    @Override
    public int getCount() {
        return AppItems.size();
    }

    @Override
    public Object getItem(int location) {
        return AppItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        Button bttInstall = (Button) convertView.findViewById(R.id.bttInstall);

        // getting App data for the row
        final App m = AppItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        title.setText(m.getTitle());
        bttInstall.setText("Cài đặt");
        // rating
        rating.setText("Nhận: " + String.valueOf(m.getYear()) + " xu");

        Button Button_1 = (Button)convertView.findViewById(R.id.bttInstall);
        Button_1.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Log.d(">>>>>",m.getRating().toString());

                //GET IMEI ANDROID
                TelephonyManager telephonyManager = (TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE);

                //String URL = "http://ytube301.com/api/insert_app/2323/" + String.valueOf(m.getRating()) + "/" + String.valueOf(m.getYear());
                String URL = "http://ytube301.com/api/insert_app" ;

                PostJson postinfo = new PostJson();
                postinfo.PostNow(URL,telephonyManager.getDeviceId(),String.valueOf(m.getRating()),String.valueOf(m.getYear()));
                PackageManager pm = activity.getPackageManager();

                try{
                    pm.getPackageInfo(m.getRating().toString(), PackageManager.GET_ACTIVITIES);
                    Log.i("MyApp", "Đã cài đặt app này rồi nhé !");
                    Toast.makeText(activity.getApplicationContext(), "Bạn đã cài đặt ứng dụng này !", Toast.LENGTH_LONG).show();

                }
                catch (NameNotFoundException e){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id="+m.getRating().toString()));
                    activity.startActivity(intent);
                }
            }

        });

        // genre
//        String genreStr = "";
//        for (String str : m.getGenre()) {
//            genreStr += str + ", ";
//        }
//        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
//                genreStr.length() - 2) : genreStr;
//        genre.setText(genreStr);

        // release year
//        year.setText(String.valueOf(m.getYear()));

        return convertView;
    }


}
