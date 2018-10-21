package com.ola.m_realm;


import io.realm.RealmObject;
public class ModelIdeaObject extends RealmObject {
    private String text;
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
