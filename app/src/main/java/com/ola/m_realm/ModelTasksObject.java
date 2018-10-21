package com.ola.m_realm;


import io.realm.RealmObject;

public class ModelTasksObject  extends RealmObject{
    private String tasks;
    private String  priority;
    public ModelTasksObject() {
    }
    public ModelTasksObject(String tasks) {
        this.tasks = tasks;
    }
    public String getTasks() {
        return tasks;
    }
    public void setTasks(String tasks) {
        this.tasks = tasks;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
}
