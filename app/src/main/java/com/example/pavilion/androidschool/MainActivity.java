package com.example.pavilion.androidschool;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pavilion.androidschool.adapter.VideoListAdapter;
import com.example.pavilion.androidschool.model.VideoEntry;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity{
    private VideoListAdapter adapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                intent.putExtra("textKey",textInte);
                intent.putExtra("idKey", videoIdInte);
                startActivity(intent);
            }
        });
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
}
