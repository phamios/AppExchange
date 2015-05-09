package androidhive.info.materialdesign.tabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import androidhive.info.materialdesign.model.AppInstalled;
import androidhive.info.materialdesign.util.AppController;
import androidhive.info.materialdesign.util.CustomListAdapter;
import androidhive.info.materialdesign.util.CustomListInstalledAdapter;

/**
 * Created by sonpx on 5/9/15.
 */
public class Tab4 extends  Fragment {

    private static final String TAG = Tab1.class.getSimpleName();

    // Apps json url
    private static final String url = "http://ytube301.com/api/list_app_by_user";
    private ProgressDialog pDialog;
    private List<AppInstalled> AppList = new ArrayList<AppInstalled>();
    private ListView listView;
    private CustomListInstalledAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_4, container, false);

        listView = (ListView) v.findViewById(R.id.list_app_installed);


        adapter = new CustomListInstalledAdapter(getActivity(), AppList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Đang tải nội dung...");
        pDialog.show();

        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        Log.w("URL_USER>>>>>", url + "/" + telephonyManager.getDeviceId());
        // Creating volley request obj
        JsonArrayRequest AppReq = new JsonArrayRequest(url + "/" + telephonyManager.getDeviceId(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.w("LISTAPP>>>>>", response.toString());
                        hidePDialog();
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                AppInstalled App = new AppInstalled();
                                App.setName(obj.getString("name"));
                                App.setIcon(obj.getString("icon"));

                                if (obj.getString("balance") != null) {
                                    App.setBalance(obj.getString("balance"));
                                } else {
                                    App.setBalance("0");
                                }
                                AppList.add(App);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                        //updateResults(AppList);

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

    public void updateResults(List<AppInstalled> results) {
        AppList = results;
        //Triggers the list update
        adapter.notifyDataSetChanged();
    }



}