package androidhive.info.materialdesign.tabs;

import android.os.Bundle;

import androidhive.info.materialdesign.R;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidhive.info.materialdesign.util.CustomListAdapter;
import androidhive.info.materialdesign.util.AppController;
import androidhive.info.materialdesign.model.App;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
/**
 * Created by Pham Xuan Son on 5/3/2015.
 */

public class Tab1 extends Fragment {

    private static final String TAG = Tab1.class.getSimpleName();

    // Apps json url
    private static final String url = "http://ytube301.com/api/admega";
    private ProgressDialog pDialog;
    private List<App> AppList = new ArrayList<App>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1, container, false);

        listView = (ListView) v.findViewById(R.id.list);
        adapter = new CustomListAdapter(getActivity(), AppList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Đang tải nội dung...");
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest AppReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.w(">>>", response.toString());
                        hidePDialog();
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                App App = new App();
                                App.setTitle(obj.getString("name"));
                                App.setThumbnailUrl(obj.getString("icon"));
                                App.setRating(obj.getString("link"));
                                if(obj.getString("balance") !=  null) {
                                    App.setYear(obj.getInt("balance"));
                                }else{
                                    App.setYear(0);
                                }
                                AppList.add(App);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
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
