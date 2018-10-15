package com.example.r.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RcAdapter extends RecyclerView.Adapter<RcAdapter.RcViewHolder> {

    private String[] mDataset;

    public static class RcViewHolder extends RecyclerView.ViewHolder {
        public TextView mTexView, ww;

        public RcViewHolder(View itemView) {
            super(itemView);
            mTexView = (TextView) itemView.findViewById(R.id.textview);
            ww = (TextView) itemView.findViewById(R.id.ww);
        }
    }
    public RcAdapter(String[] dataset) {
        mDataset = dataset;
    }
    @Override
    public RcAdapter.RcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_adapter, parent, false);

        RcViewHolder vh = new RcViewHolder(v);
        return vh;
    }
    
    @Override
    public void onBindViewHolder(RcAdapter.RcViewHolder holder, int position) {
        holder.mTexView.setText(mDataset[position]);
    }


    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
