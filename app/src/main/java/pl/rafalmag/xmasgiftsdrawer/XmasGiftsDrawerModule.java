package pl.rafalmag.xmasgiftsdrawer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class XmasGiftsDrawerModule {

    @Provides
    @Singleton
    ModelHolder provideModelHolder() {
        return new ModelHolder();
    }

}
