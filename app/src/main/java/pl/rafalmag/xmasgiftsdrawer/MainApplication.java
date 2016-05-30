package pl.rafalmag.xmasgiftsdrawer;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class MainApplication extends Application {
    private static MainApplication instance;

    public MainApplication getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
