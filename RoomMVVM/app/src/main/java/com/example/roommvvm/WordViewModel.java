package com.example.roommvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordDao mWordDao;
    private List<Word> list;
    private WordRepository mWordRepository;
    public WordViewModel(Application application){
        super(application);
        /*写入repository中
        WordDatabase wordDatabase = WordDatabase.getDatabase(application);
        mWordDao = wordDatabase.getWordDao();
        list = mWordDao.getAllWords();
         */
        mWordRepository = new WordRepository(application);
    }

    void Insert(Word...words){
        mWordRepository.Insert(words);
        //new InsertAsyncTask(mWordDao).execute(words);
    }

    void Delete(Word...words){
        mWordRepository.Delete(words);
        //new DeleteAsyncTask(mWordDao).execute(words);
    }


/* 放入仓库中
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

    static class DeleteAsyncTask extends AsyncTask<Word,Void, Void> {
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
