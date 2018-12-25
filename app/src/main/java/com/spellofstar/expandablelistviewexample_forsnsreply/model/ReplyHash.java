package com.spellofstar.expandablelistviewexample_forsnsreply.model;

public class ReplyHash {
    private int parentId;
    private int page;
    private int count; // child reply counts

    public ReplyHash(int parentId, int page, int count){
        this.parentId = parentId;
        this.page = page;
        this.count = count;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
