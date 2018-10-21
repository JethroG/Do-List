package com.ola.m_ui;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ola.R;
class MyIdeaViewHolder extends RecyclerView.ViewHolder{
    TextView enterDreams;
    MyIdeaViewHolder(View itemView) {
        super(itemView);
        enterDreams=(TextView) itemView.findViewById(R.id.textOfDreams);
    }
}
