package pl.rafalmag.xmasgiftsdrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import javax.inject.Inject;

public class DrawerFragment extends Fragment {

    Table<Person, Person, Boolean> table = HashBasedTable.create();

    public static DrawerFragment newInstance() {
        return new DrawerFragment();
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

    //TODO use "Toast" when checkbox changes
    //TODO use ProgressDialog when calculating stuff
}