package com.example.pavilion.androidschool.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Pavilion on 07.06.2015.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = "com..example.pavilion.androidschool.db.DBHelper";
    private static final String DB_NAME = "profile.db";
    private static final int DB_VERSION = 1;
    private Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    private void testfunction() {
        try {
            Dao<Profile, Integer> dao =
                    DaoManager.createDao(this.getConnectionSource(),
                            Profile.class);
            dao.create(new Profile("contact1"));
            dao.create(new Profile("contact2"));
            dao.create(new Profile("contact3"));
            dao.create(new Profile("contact4"));
            dao.create(new Profile("contact5"));
            dao.create(new Profile("contact6"));

        } catch(SQLException ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource sourse) {
        try {
            TableUtils.createTableIfNotExists(sourse, Profile.class);

            testfunction();
        } catch(java.sql.SQLException ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Context getContext() {
        return this.context;
    }
}
