package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    int i = 1;
    WordViewModel mWordViewModel;
    RecyclerView mRecyclerView;
    MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycleView);

        mMyAdapter = new MyAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyAdapter);

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        // 当数据库改变时，自动刷新页面
        mWordViewModel.getAllWordLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                mMyAdapter.setAllWORDS(words);
                mMyAdapter.notifyDataSetChanged();
            }
        });
    }

    public void onClickInsert(View view){
        Word word1 = new Word("Hello","你好",i);
        Word word2 = new Word("World","世界",i+1);
        i += 2;

        /*
        在主线程操作是不规范的；下同
        mWordDao.InsertWords(word1,word2);
        */
        //在子线程里实现操作，下同
       /*
       这个构造被写入WordViewModel中
        new InsertAsyncTask(mWordDao).execute(word1,word2);
        */

        mWordViewModel.insertWords(word1,word2);
    }

    public void onClickClear(View view){
        mWordViewModel.clearWords();
    }

    public void onClickUpdate(View view){
        Word word = new Word("Hello","你好啊",i);
        word.setId(i-1);
        mWordViewModel.updateWords(word);
    }

    public void onClickDelete(View view){
        Word word = new Word();
        word.setId(i-1);
        i = i-1;
        mWordViewModel.deleteWords(word);
    }
}