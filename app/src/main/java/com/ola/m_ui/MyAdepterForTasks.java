package com.ola.m_ui;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.gaurav.cdsrecyclerview.CdsRecyclerViewAdapter;
import com.ola.R;
import com.ola.m_realm.ModelTasksObject;

import java.util.List;

public class MyAdepterForTasks extends CdsRecyclerViewAdapter<ModelTasksObject,MyTasksViewHolder> {
    private Context mContext;
    private List<ModelTasksObject> tasksObjects;
    private boolean[] checked;
    public MyAdepterForTasks(Context context, List<ModelTasksObject> tasksObjects) {
        super(context, tasksObjects);
        mContext = context;
        this.tasksObjects = tasksObjects;
        checked = new boolean[tasksObjects.size()];
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyTasksViewHolder(LayoutInflater.from(mContext).inflate(R.layout.model_for_tasks, parent, false));
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        ModelTasksObject modelTasksObject= tasksObjects.get(position);
        ((MyTasksViewHolder) holder).tasks_view_model.setText(modelTasksObject.getTasks());
        Drawable drawable = ((MyTasksViewHolder) holder).bla.getBackground();
        drawable.setColorFilter(Color.parseColor(modelTasksObject.getPriority()), PorterDuff.Mode.SRC_IN);
        ((MyTasksViewHolder) holder).checkBox.setChecked(checked[position]);
        ((MyTasksViewHolder) holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checked[position] = !checked[position];
                if(isChecked){
                    ((MyTasksViewHolder) holder).tasks_view_model.setPaintFlags
                            (((MyTasksViewHolder) holder).tasks_view_model.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                }if(!isChecked){
                    ((MyTasksViewHolder) holder).tasks_view_model.setPaintFlags(0);
                }
            }
        });
    }
    @Override
    public int getItemCount() {return tasksObjects.size();}
    @Override
    public void moveItem(int fromPos, int toPos) {super.moveItem(fromPos, toPos);}
    @Override
    public void removeItem(int position) {super.removeItem(position);}
}
