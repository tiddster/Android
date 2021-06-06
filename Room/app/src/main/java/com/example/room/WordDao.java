package com.example.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//用于增加，修改，删除，查询
@Dao
public interface WordDao {
    @Insert
    void InsertWords(Word... words);

    @Update
    void updateWords(Word... words);

    @Delete
    void DeleteWords(Word... words);

    @Query("DELETE FROM WORD")
    void deleteAllWords();

    @Query("SELECT * FROM WORD ORDER BY ID")
    //List<Word> getAllWords();
    LiveData<List<Word>> getAllWordsLive();
    //LiveData...可以使界面自动刷新
}
