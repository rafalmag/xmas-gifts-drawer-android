package pl.rafalmag.xmasgiftsdrawer;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {XmasGiftsDrawerModule.class})
public interface MainApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(ContactsFragment mainActivity);

    void inject(DrawerFragment mainActivity);

    ModelHolder provideModelHolder();
}
