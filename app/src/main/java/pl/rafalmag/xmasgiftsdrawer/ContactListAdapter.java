package pl.rafalmag.xmasgiftsdrawer;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

// ListAdapter
public class ContactListAdapter extends BaseAdapter {

    final ModelHolder modelHolder;

    private LayoutInflater mInflater;
    private Context context;
    private final List<Contact> data = new ArrayList<>();

    public ContactListAdapter(Context ctx, List<Contact> data, ModelHolder modelHolder) {
        this.modelHolder = modelHolder;
        mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = ctx;
        this.data.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.contact_item, parent, false);
        } else {
            view = convertView;
        }

        final Contact contact = data.get(position);
        Bitmap bmp = getThumbnail(contact);
        ((ImageView) view.findViewById(R.id.icon)).setImageBitmap(bmp);
        ((TextView) view.findViewById(R.id.text)).setText(contact.getFirstName());

        Button removeButton = (Button) view.findViewById(R.id.removeBtn);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(contact);
                modelHolder.removePerson(contact);
                notifyDataSetChanged();
            }
        });
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

    public void add(Contact contact) {
        data.add(contact);
        modelHolder.addPerson(contact);
        notifyDataSetChanged();
    }

    public List<Contact> getItems() {
        return data;
    }
}