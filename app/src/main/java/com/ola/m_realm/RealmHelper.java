package com.ola.m_realm;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;


public class RealmHelper {

    private Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //WRITE
    public <T extends RealmObject> void save(final T t)
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(t);
                
            }
        });
    }

    //READ
    public <T extends RealmObject> RealmResults<T> retrieve(Class<T> clazz)
    {
        return realm.where(clazz).findAll();
    }




}







