package com.ola.m_ui;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ola.R;

public class MyTasksViewHolder extends RecyclerView.ViewHolder {
    TextView tasks_view_model,bla;
    CheckBox checkBox;
    public MyTasksViewHolder(View itemView) {
        super(itemView);
        tasks_view_model=(TextView) itemView.findViewById(R.id.tasks_view_model);
        bla=(TextView) itemView.findViewById(R.id.blabla);
        checkBox=(CheckBox)itemView.findViewById(R.id.checkBox1);
    }
}
