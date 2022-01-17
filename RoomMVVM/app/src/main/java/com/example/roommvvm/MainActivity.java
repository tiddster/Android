package com.example.roommvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;
    Button button;
    WordDao mWordDao;
    WordDatabase mWordDatabase;
    WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        initView();
        listener();
    }

    private void listener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word1 = new Word("你好","Hello");
                Word word2 = new Word("世界","Word");
                mWordViewModel.Insert(word1,word2);
                //new InsertAsyncTask(mWordDao).execute(word1,word2);   No2
                //Insert();   No1
            }
        });
    }

    void initView(){
        mWordDatabase = WordDatabase.getDatabase(this);
        mWordDao = mWordDatabase.getWordDao();
        mTextView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
    }

    /*最最普通的、允许在主线程操作的方法    No1
    void Query(){
        List<Word> list;
        list = mWordDao.getAllWords();
        StringBuilder stringBuilder = new StringBuilder();
        for(Word word : list){
            stringBuilder.append(word.getId()).append("+").append(word.getChinese()).append("+").append(word.getEnglish()).append('\n');
        }
        mTextView.setText(stringBuilder);
    }

    void Insert(){
        mWordDao.Insert(new Word("你好","Hello"));
        mWordDao.Insert(new Word("世界","Word"));
        Query();
    }
     */

    /*放入ViewModel中    No2
    //异步<操作类，用于报告进度的类，用于报告结果的类>
    static class InsertAsyncTask extends AsyncTask<Word,Void, Void>{
        WordDao wordDao;
        InsertAsyncTask(WordDao wordDao){
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.Insert(words);
            publishProgress();    //推送进度
            return null;
        }

        //将结果带回给主线程
        @Override
        protected void onPostExecute(Void v){
        }

        //当进度发生更新时的操作，返回进度
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Word,Void, Void>{
        WordDao wordDao;
        DeleteAsyncTask(WordDao wordDao){
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.Delete(words);
            publishProgress();    //推送进度
            return null;
        }
    }
     */
}