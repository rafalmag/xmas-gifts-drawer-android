package pl.rafalmag.xmasgiftsdrawer;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
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

        Contact contact = getItem(position);
        Bitmap bmp = getThumbnail(contact);
        ((ImageView) view.findViewById(R.id.icon)).setImageBitmap(bmp);
        ((TextView) view.findViewById(R.id.text)).setText(contact.getFirstName());
        return view;
    }

    private Bitmap getThumbnail(Contact contact) {
        InputStream thumbnailInputStream = ContactsContract.Contacts.openContactPhotoInputStream(
                context.getContentResolver(),
                ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contact.get_id()));
        Bitmap bmp = BitmapFactory.decodeStream(thumbnailInputStream);
        if (bmp == null) {
            bmp = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.ic_launcher_shortcut_contact);
        }
        return bmp;
    }

}