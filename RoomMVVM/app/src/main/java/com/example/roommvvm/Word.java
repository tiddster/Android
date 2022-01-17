package com.example.roommvvm;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "Chinese")
    String Chinese;
    @ColumnInfo(name = "English")
    String English;

    public Word(String Chinese, String English) {
        this.Chinese = Chinese;
        this.English = English;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChinese() {
        return Chinese;
    }

    public void setChinese(String chinese) {
        Chinese = chinese;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }
}
