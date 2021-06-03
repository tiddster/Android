package com.example.rxjavaandretrofit.bean;

public class SearchingItems {

    /**
     * id : 63476337                            仓库id
     * language : Python                        所用语言
     * full_name : TheAlgorithms/Python         仓库名字
     * forks : 30075
     * stargazers_count : 108596                 收藏数
     * git_url : git://github.com/TheAlgorithms/Python.git     地址
     *
     * description : My Python Examples        仓库描述
     *
     */
    /**
     * login : TheAlgorithms
     */

    private int id;
    private String language;
    private String full_name;
    private int forks;
    private int stargazers_count;
    private String git_url;
    private OwnerBean owner;
    private String description;

    private boolean show_details = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OwnerBean getOwner() {
        return owner;
    }

    public void setOwner(OwnerBean owner) {
        this.owner = owner;
    }

    public static class OwnerBean {
        private String login;
        private String avatar_url;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }

    public boolean isShow_details() {
        return show_details;
    }

    public void setShow_details(boolean show_details) {
        this.show_details = show_details;
    }

}
