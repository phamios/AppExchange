package androidhive.info.materialdesign.tabs;

/**
 * Created by Pham Xuan Son on 5/3/2015.
 */
import android.os.Bundle;
import androidhive.info.materialdesign.R;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_2,container,false);
        return v;
    }
}
