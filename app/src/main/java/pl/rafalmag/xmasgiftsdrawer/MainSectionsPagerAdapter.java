package pl.rafalmag.xmasgiftsdrawer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class MainSectionsPagerAdapter extends FragmentPagerAdapter {

    public MainSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return WelcomeFragment.newInstance();
            case 1:
                return ContactsFragment.newInstance();
            case 2:
                //TODO drawer instance
                return WelcomeFragment.newInstance();
            default:
                throw new IllegalArgumentException("Unsupported position " + position);
        }
    }

    @Override
    public int getCount() {
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
