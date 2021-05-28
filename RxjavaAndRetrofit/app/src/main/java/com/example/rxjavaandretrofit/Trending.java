package com.example.rxjavaandretrofit;

import java.util.List;

public class Trending<T> {

    /**
     * msg : Unavailable.
     * count : 0
     * items : []
     */

    private String msg;
    private int count;
    private List<T> items;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

        public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
