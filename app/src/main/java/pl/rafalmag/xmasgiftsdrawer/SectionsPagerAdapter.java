package pl.rafalmag.xmasgiftsdrawer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PlaceholderFragment.newInstance(position + 1);
            case 1:
                return ContactsFragment.newInstance();
            case 2:
                return PlaceholderFragment.newInstance(position + 1);
            default:
                throw new IllegalArgumentException("Unsupported position " + position);
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Welcome";
            case 1:
                return "Select Contacts";
            case 2:
                return "Drawer";
            default:
                throw new IllegalArgumentException("Unsupported position " + position);
        }
    }
}
