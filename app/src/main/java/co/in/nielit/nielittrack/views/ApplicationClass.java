package co.in.nielit.nielittrack.views;

import android.app.Application;
import androidx.appcompat.app.AppCompatDelegate;

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Always use Light Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}
