package pl.rafalmag.xmasgiftsdrawer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DrawerFragment extends Fragment {

    Unbinder unbinder;
    @Inject
    ModelHolder modelHolder;

    private boolean mFirstAttach = true;

    @BindView(R.id.testText)
    TextView testText;

    @BindView(R.id.drawer_grid)
    GridView drawerGrid;

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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drawer_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        drawerGrid.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                int columns = modelHolder.getPersonCount() + 1;
                return columns * columns;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (position == 0) {
                    TextView textView = getOrCreateTextView(convertView, parent);
                    textView.setText("");
                    return textView;
                }
                // header row
                if (position <= modelHolder.getPersonCount()) {
                    TextView textView = getOrCreateTextView(convertView, parent);
                    int contactNumber = position - 1;
                    textView.setText(modelHolder.getContact(contactNumber).getFirstName());
                    return textView;
                }
                // header column
                if (position % (modelHolder.getPersonCount() + 1) == 0) {
                    TextView textView = getOrCreateTextView(convertView, parent);
                    int contactNumber = position / (modelHolder.getPersonCount() + 1) - 1;
                    textView.setText(modelHolder.getContact(contactNumber).getFirstName());
                    return textView;
                }
                // data
                CheckBox checkBox = new CheckBox(inflater.getContext());
                checkBox.setChecked(true);
                return checkBox;
            }

            @NonNull
            private TextView getOrCreateTextView(View convertView, ViewGroup parent) {
                TextView textView;
                if (convertView instanceof TextView) {
                    textView = (TextView) convertView;
                } else {
                    textView = new TextView(parent.getContext());
                }
                return textView;
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        testText.setText(modelHolder.toString());
        drawerGrid.setNumColumns(modelHolder.getPersonCount() + 1);
    }

    //TODO use "Toast" when checkbox changes
    //TODO use ProgressDialog when calculating stuff
}
