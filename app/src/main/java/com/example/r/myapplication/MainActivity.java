package com.example.r.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);


        String[] myDataset = {
                "Уральск",
                "Атырау",
        };

        mRecyclerView = (RecyclerView) findViewById(R.id.rc);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new  LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RcAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}
