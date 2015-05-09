package androidhive.info.materialdesign.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import androidhive.info.materialdesign.R;
import androidhive.info.materialdesign.model.App;
import androidhive.info.materialdesign.model.AppInstalled;
import androidhive.info.materialdesign.model.HistoryExchange;

/**
 * Created by sonpx on 5/9/15.
 */
public class CustomListInstalledAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<AppInstalled> AppItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListInstalledAdapter(Activity activity, List<AppInstalled> AppItems) {
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
            convertView = inflater.inflate(R.layout.list_row_appinstalled, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView appname = (TextView) convertView.findViewById(R.id.appname);
        TextView appbalance = (TextView) convertView.findViewById(R.id.appbalance);

        // getting App data for the row
        final AppInstalled m = AppItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getIcon(), imageLoader);

        // title
        appname.setText(m.getName());

        // rating
        appbalance.setText("Đã nhận: " + String.valueOf(m.getBalance()) + " xu");



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