package pl.rafalmag.xmasgiftsdrawer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import javax.inject.Inject;

import butterknife.BindView;

public class DrawerFragment extends Fragment {

    @Inject
    ModelHolder modelHolder;

    private boolean mFirstAttach = true;

    @BindView(R.id.testText)
    TextView testText;

    public static DrawerFragment newInstance() {
        return new DrawerFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // dagger
        if (mFirstAttach) {
            MainApplication.getComponent(context).inject(this);
            mFirstAttach = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drawer_fragment, container, false);
        GridView drawerGrid = (GridView) view.findViewById(R.id.drawer_grid);
        drawerGrid.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //TODO / FIXME NPE here
        testText.setText(modelHolder.toString());
    }

    //TODO use "Toast" when checkbox changes
    //TODO use ProgressDialog when calculating stuff
}
