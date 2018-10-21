package com.ola.m_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gaurav.cdsrecyclerview.CdsRecyclerViewAdapter;
import com.ola.R;
import com.ola.m_realm.ModelDBObject;

import java.util.List;


public class RecyclerViewAdapter extends CdsRecyclerViewAdapter<ModelDBObject, MyViewHolder> {
    private Context mContext;
    private List<ModelDBObject> objectDB;
    public RecyclerViewAdapter(Context context, List<ModelDBObject> objectDreams) {
        super(context, objectDreams);
        mContext = context;
        this.objectDB =objectDreams;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.model, parent, false));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ModelDBObject modelDreamsObject= objectDB.get(position);
        ((MyViewHolder) holder).nameTxt.setText(modelDreamsObject.getName());
        ((MyViewHolder) holder).titleTxt.setText(modelDreamsObject.getTextOfMessage1());
    }
    @Override
    public int getItemCount() {
        return objectDB.size();
    }
    @Override
    public void moveItem(int fromPos, int toPos) {
        super.moveItem(fromPos, toPos);
    }
    @Override
    public void removeItem(int position) {
        super.removeItem(position);
    }
}
