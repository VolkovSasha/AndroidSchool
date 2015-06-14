package com.example.pavilion.androidschool;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.pavilion.androidschool.db.DBHelper;
import com.example.pavilion.androidschool.db.Profile;
import com.example.pavilion.androidschool.manager.ProfileManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by Pavilion on 07.06.2015.
 */
public class AndroidSchoolApp extends Application {
    private SharedPreferences preferences;
    private Locale locale;
    private String lang;

    private DBHelper dbHelper = null;
    private ProfileManager profilesManager = null;
    private static final String TAG = "com.example.pavilion.androidschool.AndroidSchoolApp";

    public AndroidSchoolApp() {
        super();
        dbHelper = new DBHelper(this);
    }

    public ProfileManager getContactManager() {
        if(profilesManager == null) {
            try {
                profilesManager = new ProfileManager();
                Dao<Profile, Integer> contactDao =
                        DaoManager.createDao(dbHelper.getConnectionSource(),
                                Profile.class);
                profilesManager.setContactDao(contactDao);
            } catch(SQLException ex) {
                Log.e(TAG, ex.getMessage());
            }
        }
        return profilesManager;
    }

    @Override
    public void onCreate() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        lang = preferences.getString("lang", "default");
        if (lang.equals("default")) {lang=getResources().getConfiguration().locale.getCountry();}
        locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        Log.i("Lang change", "Locale="+locale);
        getBaseContext().getResources().updateConfiguration(config, null);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, null);
    }
}
