package br.com.packapps.librarypackappsombr;

import android.app.Application;
import android.content.Context;

/**
 * Created by paulolinhares on 07/06/17.
 */

public class LibraryApplication extends Application {
    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;

    }

    public static Context getContext() {
        return applicationContext;
    }
}
