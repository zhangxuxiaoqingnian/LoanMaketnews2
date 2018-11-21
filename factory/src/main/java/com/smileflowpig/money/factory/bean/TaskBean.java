package com.smileflowpig.money.factory.bean;

import java.util.ArrayList;

public class TaskBean {



    private ArrayList<TaskJson> task_other;
    private ArrayList<TaskHeadJson> task_head;

    private ArrayList<TaskJson> task_now;
    private ArrayList<TaskJson> task_completed;

    public ArrayList<TaskJson> getTask_other() {
        return task_other;
    }

    public void setTask_other(ArrayList<TaskJson> task_other) {
        this.task_other = task_other;
    }

    public ArrayList<TaskHeadJson> getTask_head() {
        return task_head;
    }

    public void setTask_head(ArrayList<TaskHeadJson> task_head) {
        this.task_head = task_head;
    }

    public ArrayList<TaskJson> getTask_now() {
        return task_now;
    }

    public void setTask_now(ArrayList<TaskJson> task_now) {
        this.task_now = task_now;
    }

    public ArrayList<TaskJson> getTask_completed() {
        return task_completed;
    }

    public void setTask_completed(ArrayList<TaskJson> task_completed) {
        this.task_completed = task_completed;
    }
}
