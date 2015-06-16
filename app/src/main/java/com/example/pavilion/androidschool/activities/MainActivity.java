package com.example.pavilion.androidschool.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pavilion.androidschool.R;
import com.example.pavilion.androidschool.adapter.VideoListAdapter;
import com.example.pavilion.androidschool.model.VideoEntry;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements Drawer.OnDrawerListener, Drawer.OnDrawerItemClickListener {
    private VideoListAdapter adapter;
    private ListView listView;
    private Toolbar toolbar;
    private String mTitle;
    private Drawer.Result mDrawer = null;
    private GoogleApiClient mClient;

    @Override
    protected void onStart() {
        super.onStart();
        mClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mClient != null && mClient.isConnected())
            mClient.disconnect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();
        final List<VideoEntry> list = new ArrayList<VideoEntry>();
        list.add(new VideoEntry("Урок 1. Введение.", "zfUZ-D6tWxU"));
        list.add(new VideoEntry("Урок 2. Установка и настройка Android Studio. Установка JDK. Настройка Android SDK", "9ucX3UlCT6E"));
        list.add(new VideoEntry("Урок 3. Первое андроид-приложение. Структура android проекта. Создание эмулятора Android (AVD)", "SbWzaPtvzJA"));
        list.add(new VideoEntry("Урок 4. Activity, Layout, View, ViewGroup Элементы экрана в android, их свойства", "goESBP6iUuw"));
        list.add(new VideoEntry("Урок 5. Файл макета экрана android-приложения в XML виде. Поворот устройства", "DLsKPE9NviA"));
        list.add(new VideoEntry("Урок 6. LinearLayout и RelativeLayout - особенности макетов экранов android", "gm0gCY2qA54"));
        list.add(new VideoEntry("Урок 6(2).TableLayout - особенности макетов экранов в андроид", "Uspml6OP3tE"));

        adapter = new VideoListAdapter(getApplicationContext(), list);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String textInte = list.get(position).Text;
                String videoIdInte = list.get(position).VideoId;
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), VideoActivity.class);
                intent.putExtra("textKey", textInte);
                intent.putExtra("idKey", videoIdInte);
                startActivity(intent);
            }
        });

        mClient = new GoogleApiClient.Builder(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_PROFILE)
                .build();
    }

    private void initDrawer() {
        mDrawer = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .withOnDrawerListener(this)
                .withOnDrawerItemClickListener(this)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(getResources().getDrawable(R.drawable.ic_launcher)).withBadge("99").withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_profile).withIcon(getResources().getDrawable(R.drawable.ic_launcher)),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_saved_video).withIcon(getResources().getDrawable(R.drawable.ic_launcher)).withBadge("6").withIdentifier(2),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(getResources().getDrawable(R.drawable.ic_launcher)),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_sign_out).withIcon(getResources().getDrawable(R.drawable.ic_launcher)))
                .build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, VideoActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerOpened(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onDrawerClosed(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l, IDrawerItem iDrawerItem) {
        switch (pos) {
            case 5:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case 6:
                if(mClient != null && mClient.isConnected()) {
                    mClient.clearDefaultAccountAndReconnect();
                    Plus.AccountApi.clearDefaultAccount(mClient);
                    mClient.disconnect();
                }
                finish();
                break;
        }
        Toast.makeText(this, "pos " + pos, Toast.LENGTH_SHORT).show();
    }

}
