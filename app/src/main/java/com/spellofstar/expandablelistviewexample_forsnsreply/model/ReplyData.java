package com.spellofstar.expandablelistviewexample_forsnsreply.model;

public class ReplyData {
    public static final int PARENT = 1;
    public static final int CHILD = 2;
    public static final int MORE = 3;

    private int type;
    private int id;
    private int childCounts; // from server how many reply have

    private String userName;
    private String content;


    public ReplyData(int id, String userName, String content, int childCounts){
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.childCounts = childCounts;
        this.type = 1;
    }

    public ReplyData(int id, String userName, String content){
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.childCounts = 0;
        this.type = 2;
    }

    public ReplyData(int type){
        this.type = type;
    }

    public int getChildCounts() {
        return childCounts;
    }

    public String getUserName() {
        return userName;
    }


    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

}
