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
import android.widget.RadioButton;

import com.gaurav.cdsrecyclerview.CdsItemTouchCallback;
import com.gaurav.cdsrecyclerview.CdsRecyclerView;
import com.ola.R;
import com.ola.m_realm.ModelTasksObject;
import com.ola.m_realm.RealmHelper;
import com.ola.m_ui.MyAdepterForTasks;
import com.ola.m_ui.TaskPriority;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class FragmentOne extends Fragment {
    FloatingActionButton fab;
    Realm realm= null;
    CdsRecyclerView rv;
    RealmResults<ModelTasksObject> space;
    MyAdepterForTasks adapter;
    EditText tasksEdit;
    Dialog d, dialog_notification;
    RadioButton r1, r2, r3;
    private CdsRecyclerView.ItemClickListener mItemClickListener;
    private CdsRecyclerView.ItemLongPressListener mItemLongPressListener;
    private CdsItemTouchCallback.ItemDragCompleteListener mItemDragCompleteListener;
    private CdsItemTouchCallback.ItemSwipeCompleteListener mItemSwipeCompleteListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        d = new Dialog(getActivity(), android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
        dialog_notification = new Dialog(getActivity());
        rv = new CdsRecyclerView(getActivity());
        rv = (CdsRecyclerView) view.findViewById(R.id.rv);
        rv.setHasFixedSize(false);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rv.getLayoutManager();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                linearLayoutManager.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);
        //SETUP REALM
 //       RealmConfiguration config = new RealmConfiguration.Builder(getActivity()).deleteRealmIfMigrationNeeded().build();
  //      realm = Realm.getInstance();
        Realm.init(getContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm= Realm.getInstance(realmConfiguration);
        //RETRIEVE
        RealmHelper helper = new RealmHelper(realm);
        space = helper.retrieve(ModelTasksObject.class);
        List<ModelTasksObject> modelTasksObjects = new ArrayList<>(space);
        //BIND
        adapter = new MyAdepterForTasks(getActivity(), modelTasksObjects);
        rv.setAdapter(adapter);
        rv.setItemClickListener(mItemClickListener);
        rv.setItemLongPressListener(mItemLongPressListener);
        rv.enableItemSwipe();
        rv.enableItemDrag();
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();
                Log.i("Ok", "Cool");
            }
        });
        //tasks_view_model.setPaintFlags(tasks_view_model.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
    //SHOW DIALOG
    private void displayInputDialog() {
        d.setContentView(R.layout.edittodolist);
        tasksEdit = (EditText) d.findViewById(R.id.entertasks);
        tasksEdit.requestFocus();
        r1 = (RadioButton) d.findViewById(R.id.radio_oneId);
        r2 = (RadioButton) d.findViewById(R.id.radio_twoId);
        r3 = (RadioButton) d.findViewById(R.id.radio_threeId);
        Button saveBtn = (Button) d.findViewById(R.id.saveBtn3);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                ModelTasksObject s = new ModelTasksObject(tasksEdit.getText().toString());
                if (r1.isChecked()) {
                    s.setPriority(TaskPriority.HIGH);
                }else if (r2.isChecked()) {
                    s.setPriority(TaskPriority.MEDIUM);
                }
                else if (r3.isChecked()) {
                    s.setPriority(TaskPriority.LOW);
                }
                //SAVE
                RealmHelper helper = new RealmHelper(realm);
                space = helper.retrieve(ModelTasksObject.class);
                helper.save(s);
                adapter = new MyAdepterForTasks(getActivity(), space);
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