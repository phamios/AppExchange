package androidhive.info.materialdesign.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentTabHost;
import androidhive.info.materialdesign.tabs.Tab1;
import androidhive.info.materialdesign.tabs.Tab2;
import androidhive.info.materialdesign.tabs.Tab3;
import androidhive.info.materialdesign.R;


public class HomeFragment extends Fragment {

    private FragmentTabHost mTabHost;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mTabHost = (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator(getString(R.string.tab_earnmoney), null),
                Tab1.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator(getString(R.string.tab_cardchange), null),
                Tab2.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator(getString(R.string.tab_giftchange), null),
                Tab3.class, null);
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
