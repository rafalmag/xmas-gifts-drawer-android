package pl.rafalmag.xmasgiftsdrawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// ListAdapter
public class ContactListAdapter extends ArrayAdapter<Contact> implements Filterable {
    private LayoutInflater mInflater;
    private Context context;

    public ContactListAdapter(Context ctx, List<Contact> data) {
        super(ctx, android.R.layout.simple_list_item_2);
        mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = ctx;
        addAll(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.contact_item, parent, false);
        } else {
            view = convertView;
        }

        Contact item = getItem(position);

        Bitmap bmp = MainActivity.cacheManager.get(item.get_id());
        if (bmp != null) {
            ((ImageView) view.findViewById(R.id.icon)).setImageBitmap(bmp);
            Log.d(this.getClass().getSimpleName(), "CACHE HIT at :" + item.get_id());
        } else {
            Log.d(this.getClass().getSimpleName(), "CACHE MISS at :" + item.get_id());
            bmp = BitmapFactory.decodeStream(item.getThumbnail());
            if (bmp == null) {
                bmp = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.ic_launcher_shortcut_contact);
                ((ImageView) view.findViewById(R.id.icon)).setImageBitmap(bmp);
            } else {
                MainActivity.cacheManager.put(item.get_id(), bmp);
            }
        }
        ((TextView) view.findViewById(R.id.text)).setText(item.getFirstName());
        return view;
    }

}