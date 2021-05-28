package com.example.rxjavaandretrofit;

import java.util.List;

public class TrendingRepositoryItems {

    /**
     * avatars : ["https://avatars0.githubusercontent.com/u/16903644?v=3&s=40","https://avatars2.githubusercontent.com/u/8622362?v=3&s=40","https://avatars0.githubusercontent.com/u/10773353?v=3&s=40","https://avatars3.githubusercontent.com/u/6392550?v=3&s=40","https://avatars1.githubusercontent.com/u/3837836?v=3&s=40"]
     * repo_link : https://github.com/kdn251/interviews
     * desc : Everything you need to know to get the job.
     * repo : kdn251/interviews
     * stars : 5,772
     * forks : 539
     * lang : Java
     * added_stars : 4,591 stars this week
     */

    private String repo_link;
    private String desc;
    private String repo;
    private String stars;
    private String forks;
    private String lang;
    private String added_stars;
    private List<String> avatars;

    public String getRepo_link() {
        return repo_link;
    }

    public void setRepo_link(String repo_link) {
        this.repo_link = repo_link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getAdded_stars() {
        return added_stars;
    }

    public void setAdded_stars(String added_stars) {
        this.added_stars = added_stars;
    }

    public List<String> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<String> avatars) {
        this.avatars = avatars;
    }
}
