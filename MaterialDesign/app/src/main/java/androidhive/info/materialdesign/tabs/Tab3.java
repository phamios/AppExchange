package androidhive.info.materialdesign.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidhive.info.materialdesign.R;
import android.widget.ArrayAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Toast;


/**
 * Created by sonpx on 5/6/15.
 */
public class Tab3 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_3,container,false);

        //Khởi tạo ListView
        final ListView listView = (ListView)v.findViewById(R.id.list_exchange);

        //Định nghĩa các mảng, item trong list
        String[] values = new String[]{
                getString(R.string.tab3_list_invite),
                getString(R.string.tab3_list_sharefb),
                getString(R.string.tab3_list_sharegplus),
                getString(R.string.tab3_list_shareemail)
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1,values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;
                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);
                // Show Alert
                Toast.makeText(getActivity().getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemPosition, Toast.LENGTH_SHORT)
                        .show();
            }

        });
        return v;
    }




}
