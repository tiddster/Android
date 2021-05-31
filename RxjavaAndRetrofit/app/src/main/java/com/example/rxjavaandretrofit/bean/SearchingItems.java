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

    private int id;
    private String language;
    private String full_name;
    private int forks;
    private int stargazers_count;
    private String git_url;

    private String description;

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
}
