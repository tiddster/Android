package com.example.rxjavaandretrofit.bean;

public class TrendingDeveloperItems {

    /**
     * user : google
     * user_link : https://github.com/google
     * full_name : (Google)
     * developer_avatar : https://avatars1.githubusercontent.com/u/1342004?v=3&s=96
     */

    private String user;
    private String user_link;
    private String full_name;
    private String developer_avatar;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser_link() {
        return user_link;
    }

    public void setUser_link(String user_link) {
        this.user_link = user_link;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDeveloper_avatar() {
        return developer_avatar;
    }

    public void setDeveloper_avatar(String developer_avatar) {
        this.developer_avatar = developer_avatar;
    }
}
