package com.example.roommvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void Insert(Word... words);
    @Delete
    void Delete(Word... words);
    @Query("SELECT * FROM WORD ORDER BY ID")
    List<Word> getAllWords();
    /*动态加载数据
    LiveData<List<Word>> getAllWordsLive();
     */
}
