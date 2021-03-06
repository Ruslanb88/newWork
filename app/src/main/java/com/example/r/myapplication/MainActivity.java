package com.example.r.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    List<NewObject.Workers> w = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        for (int i = 0;i < 10; i++){
            NewObject.Workers name = new NewObject.Workers();
            NewObject.Workers femeli = new NewObject.Workers();
            name.name = "Уральск: " + String.valueOf(i);
            femeli.femeli = "Атырау: " + String.valueOf(i);
            w.add(femeli);
            w.add(name);
            Log.d("Добавления в массив 1: ",  String.valueOf(femeli.femeli));
            Log.d("Добавления в массив 2: ",  String.valueOf(name.name));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.rc);
        mRecyclerView.setHasFixedSize(true);

//        mLayoutManager = new  LinearLayoutManager(this);
//        mLayoutManager = new  LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RcAdapter(w);
        mRecyclerView.setAdapter(mAdapter);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        Intent intent = new Intent(getApplicationContext(), FormRegistration.class);
        Intent intent2 = new Intent(getApplicationContext(), FormAdd.class);
        switch (id) {
            case R.id.action_settings:
//                startActivityForResult(intent, 1);
                return true;
            case R.id.open_settings:
                return true;
            case R.id.save_settings:
                startActivityForResult(intent2, 1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
