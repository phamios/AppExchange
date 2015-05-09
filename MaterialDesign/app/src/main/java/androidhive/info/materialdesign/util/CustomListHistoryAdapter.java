package androidhive.info.materialdesign.util;

import androidhive.info.materialdesign.R;
import androidhive.info.materialdesign.model.HistoryExchange;
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



/**
 * Created by sonpx on 5/8/15.
 */
public class CustomListHistoryAdapter extends BaseAdapter  {

    private Activity activity;
    private LayoutInflater inflater;
    private List<HistoryExchange> historyExchangeList;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListHistoryAdapter(Activity activity, List<HistoryExchange> AppItems) {
        this.activity = activity;
        this.historyExchangeList = AppItems;
    }

    @Override
    public int getCount() {
        return historyExchangeList.size();
    }

    @Override
    public Object getItem(int location) {
        return historyExchangeList.get(location);
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
            convertView = inflater.inflate(R.layout.list_row_history, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        TextView cardname = (TextView) convertView.findViewById(R.id.card_name);
        TextView amount = (TextView) convertView.findViewById(R.id.amount);
        TextView exchangedate = (TextView) convertView.findViewById(R.id.exchangedate);
        TextView statusexchange = (TextView) convertView.findViewById(R.id.statusexchange);

        // getting App data for the row
        final HistoryExchange m = historyExchangeList.get(position);

        // title
        cardname.setText(m.getCardname());
        amount.setText(m.getAmount());
        exchangedate.setText(m.getExchangedate());
        statusexchange.setText(m.getStatus());

        // rating
        //rating.setText("Nhận: " + String.valueOf(m.getYear()) + " xu");

        return convertView;
    }

}
