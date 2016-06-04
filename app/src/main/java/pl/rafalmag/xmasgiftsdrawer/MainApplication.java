package pl.rafalmag.xmasgiftsdrawer;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class MainApplication extends Application {

    private MainApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
//        component = DaggerMainApplicationComponent.builder()
//                .xmasGiftsDrawerModule(new XmasGiftsDrawerModule())
//                .build();
        component = DaggerMainApplicationComponent.create();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static MainApplicationComponent getComponent(Context context) {
        return ((MainApplication) context.getApplicationContext()).component;
    }
}
