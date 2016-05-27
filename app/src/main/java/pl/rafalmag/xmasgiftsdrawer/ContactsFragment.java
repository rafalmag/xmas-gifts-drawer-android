package pl.rafalmag.xmasgiftsdrawer;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    private static final int RESULT_PICK_CONTACT = 123;

    private AppListAdapter mListAdapter;
    private List<Contact> selectedContacts;

    public static Fragment newInstance() {
        return new ContactsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // save fragment state when configuration changes - like screen rotated
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contacts_fragment, container, false);
        Button addContact = (Button) rootView.findViewById(R.id.add_contact);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(Contacts.CONTENT_URI);
                startActivityForResult(intent, RESULT_PICK_CONTACT);
            }
        });

        ListView mlistView = (ListView) rootView.findViewById(R.id.listView1);
        if (selectedContacts == null) {
            selectedContacts = new ArrayList<>();
            mListAdapter = new AppListAdapter(getActivity(), selectedContacts, true);
        } else {
            mListAdapter = new AppListAdapter(getActivity(), selectedContacts, true);
            mListAdapter.setData(selectedContacts);
        }
        mlistView.setAdapter(mListAdapter);
        return rootView;
    }

    static final String[] CONTACTS_SUMMARY_PROJECTION = new String[]{
            Contacts._ID,
            Contacts.DISPLAY_NAME,
            Contacts.CONTACT_STATUS,
            Contacts.CONTACT_PRESENCE,
            Contacts.PHOTO_ID,
            Contacts.LOOKUP_KEY
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == RESULT_PICK_CONTACT) {
            Log.i(getClass().getSimpleName(), "onActivityResult: " + data.getDataString());
            data.getData();
            ContentResolver cr = getActivity().getContentResolver();
            Cursor cursor = cr.query(data.getData(), CONTACTS_SUMMARY_PROJECTION,
                    null, null, null);
            if (cursor == null || !cursor.moveToFirst()) {
                throw new IllegalStateException("Activity result cannot be found ");
            }
            Contact contact = new Contact();
            contact.setFirstName(cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)));
            contact.set_id(cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID)));
            contact.setThumbnail(ContactsContract.Contacts.openContactPhotoInputStream(
                    cr,
                    ContentUris.withAppendedId(Contacts.CONTENT_URI,
                            cursor.getLong(cursor.getColumnIndex(Contacts._ID)))));
            selectedContacts.add(contact);
            mListAdapter.add(contact);
            cursor.close();
        }
    }

}
