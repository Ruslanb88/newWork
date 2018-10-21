package com.example.r.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RcAdapter extends RecyclerView.Adapter<RcAdapter.RcViewHolder> {

    private List<NewObject.Workers> mDataset;

    public static class RcViewHolder extends RecyclerView.ViewHolder {
        public TextView mTexView, ww;

        public void setText(String text){
            ww.setText(text);
        }
        public void setTitle(String title){
            mTexView.setText(title);
        }

        public RcViewHolder(View itemView) {
            super(itemView);

            mTexView = (TextView) itemView.findViewById(R.id.textview);
            ww = (TextView) itemView.findViewById(R.id.ww);
        }
    }



    public RcAdapter(List<NewObject.Workers> dataset) {
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
        NewObject.Workers workers = mDataset.get(position);

        holder.mTexView.setText(workers.name);
        holder.ww.setText(workers.femeli);
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
