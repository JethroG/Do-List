package com.ola.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gaurav.cdsrecyclerview.CdsItemTouchCallback;
import com.gaurav.cdsrecyclerview.CdsRecyclerView;
import com.ola.R;
import com.ola.m_realm.ModelDBObject;
import com.ola.m_realm.RealmHelper;
import com.ola.m_ui.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class FragmentTwo extends Fragment {

    FloatingActionButton fab;
    Realm realm;
    CdsRecyclerView rv;
    RealmResults<ModelDBObject> space;
    RecyclerViewAdapter adapter;
    EditText nameEditTxt,titleEditTxt;
    Dialog d;
    private CdsRecyclerView.ItemClickListener mItemClickListener;
    private CdsRecyclerView.ItemLongPressListener mItemLongPressListener;
    private CdsItemTouchCallback.ItemDragCompleteListener mItemDragCompleteListener;
    private CdsItemTouchCallback.ItemSwipeCompleteListener mItemSwipeCompleteListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        //SETUP RECYCLERVIEW
        rv = new CdsRecyclerView(getActivity());
        rv= (CdsRecyclerView) view.findViewById(R.id.rv);
        rv.setHasFixedSize(false);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rv.getLayoutManager();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                linearLayoutManager.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);
        //SETUP REEALM
//        RealmConfiguration config=new RealmConfiguration.Builder(getActivity()).deleteRealmIfMigrationNeeded().build();
//        realm= Realm.getInstance(config);
        Realm.init(getContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm= Realm.getInstance(realmConfiguration);
        //RETRIEVE
        RealmHelper helper=new RealmHelper(realm);
        space=helper.retrieve(ModelDBObject.class);

        List<ModelDBObject> modelDBObjects = new ArrayList<>(space);

        //BIND
        adapter=new RecyclerViewAdapter(getActivity(), modelDBObjects);
        rv.setAdapter(adapter);
        rv.setItemClickListener(mItemClickListener);
        rv.setItemLongPressListener(mItemLongPressListener);
        rv.enableItemSwipe();
        rv.enableItemDrag();

        d=new Dialog(getActivity(),android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
        fab=(FloatingActionButton) view.findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();
                Log.i("Ok","Cool");
            }
        });
    }
    //SHOW DIALOG
    private void displayInputDialog() {
        d.setContentView(R.layout.editindaybook);
        nameEditTxt= (EditText) d.findViewById(R.id.nameEditText);
        titleEditTxt= (EditText) d.findViewById(R.id.titleEditText);
        Button saveBtn= (Button) d.findViewById(R.id.saveBtn1);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                ModelDBObject s=new ModelDBObject();
                s.setName(nameEditTxt.getText().toString());
                s.setTextOfMessage1(titleEditTxt.getText().toString());
                //SAVE
                RealmHelper helper=new RealmHelper(realm);
                helper.save(s);
                nameEditTxt.setText("");
                titleEditTxt.setText("");
                //REFRESH
                space=helper.retrieve(ModelDBObject.class);
                adapter=new RecyclerViewAdapter(getActivity(),space);
                rv.setAdapter(adapter);
                d.hide();
            }
        });
        d.show();
    }
    private void initListeners() {
        mItemClickListener = new CdsRecyclerView.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        };
        mItemLongPressListener = new CdsRecyclerView.ItemLongPressListener() {
            @Override
            public void onItemLongClick(int position) {

            }
        };
        mItemDragCompleteListener = new CdsItemTouchCallback.ItemDragCompleteListener() {
            @Override
            public void onItemDragComplete(int fromPosition, int toPosition) {

            }
        };
        mItemSwipeCompleteListener = new CdsItemTouchCallback.ItemSwipeCompleteListener() {
            @Override
            public void onItemSwipeComplete(int position) {

            }
        };
    }
}