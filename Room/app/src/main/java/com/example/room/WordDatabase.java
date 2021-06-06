package com.example.room;

import android.content.Context;
import android.os.strictmode.InstanceCountViolation;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//singleton  单例模式
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;
    static WordDatabase getDataBase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WordDatabase.class,"WorldDatabase")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract  WordDao getWordDao();
    //若有多个Entity，要写多个Dao
}
