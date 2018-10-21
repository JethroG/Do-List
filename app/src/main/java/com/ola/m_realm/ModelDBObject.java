package com.ola.m_realm;


import io.realm.RealmObject;

public class ModelDBObject extends RealmObject {

    private String name;
    private String textOfMessage1;

    public String getTextOfMessage1() {
        return textOfMessage1;
    }

    public void setTextOfMessage1(String textOfMessage1) {
        this.textOfMessage1 = textOfMessage1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


