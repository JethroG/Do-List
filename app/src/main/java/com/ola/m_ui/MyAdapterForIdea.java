package com.ola.m_ui;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaurav.cdsrecyclerview.CdsRecyclerViewAdapter;
import com.ola.R;
import com.ola.m_realm.ModelIdeaObject;

import java.util.List;

public class MyAdapterForIdea extends CdsRecyclerViewAdapter<ModelIdeaObject,MyIdeaViewHolder> {
    private Context c;
    private List<ModelIdeaObject> objectDreams;
    public MyAdapterForIdea(Context c, List<ModelIdeaObject> objectDreams){
          super(c,objectDreams);
          this.c=c;
          this.objectDreams=objectDreams;
      }
    @Override
    public MyIdeaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.model_for_idea,parent,false);
        return new MyIdeaViewHolder(v);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ModelIdeaObject modelDreamsObject= objectDreams.get(position);
        ((MyIdeaViewHolder) holder).enterDreams.setText(modelDreamsObject.getText());
    }
    @Override
    public int getItemCount() {
        return objectDreams.size();
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
