package sample.cyrano;

import android.app.Application;
import android.content.Context;

public class CyranoApplication extends Application{

    private static Context context;

    public void onCreate(){
        super.onCreate();
        CyranoApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return CyranoApplication.context;
    }
}