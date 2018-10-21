package com.ola.m_ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ola.R;


class MyViewHolder extends RecyclerView.ViewHolder {

     TextView nameTxt,titleTxt;

    MyViewHolder(View itemView) {
        super(itemView);
        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        titleTxt= (TextView) itemView.findViewById(R.id.titleTxt);
    }
}
