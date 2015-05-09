package androidhive.info.materialdesign.fragment;

/**
 * Created by sonpx on 5/5/15.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidhive.info.materialdesign.R;
import androidhive.info.materialdesign.model.App;
import androidhive.info.materialdesign.model.HistoryExchange;
import androidhive.info.materialdesign.util.AppController;
import androidhive.info.materialdesign.util.CustomListAdapter;
import androidhive.info.materialdesign.util.CustomListHistoryAdapter;

public class HistoryFragment  extends Fragment {

    private static final String TAG = HistoryFragment.class.getSimpleName();

    // Apps json url
    private static final String url = "http://ytube301.com/api/check_history";
    private ProgressDialog pDialog;
    private List<HistoryExchange> hisExchange = new ArrayList<HistoryExchange>();
    private ListView listView;
    private CustomListHistoryAdapter adapter;


    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        listView = (ListView) v.findViewById(R.id.list_history);
        adapter = new CustomListHistoryAdapter(getActivity(), hisExchange);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Đang tải nội dung...");
        pDialog.show();

        TelephonyManager telephonyManager = (TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String imeiID = telephonyManager.getDeviceId();

        // Creating volley request obj
        JsonArrayRequest AppReq = new JsonArrayRequest(url + "/" +imeiID,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.w(">>>", response.toString());
                        hidePDialog();
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                HistoryExchange App = new HistoryExchange();
                                App.setAmount(obj.getString("amount"));
                                App.setCardname(obj.getString("card_name"));
                                App.setExchangedate(obj.getString("exchangedate"));
                                App.setStatus(obj.getString("status"));
                                hisExchange.add(App);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(AppReq);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}

