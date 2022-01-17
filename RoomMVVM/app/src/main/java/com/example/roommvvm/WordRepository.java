package com.example.roommvvm;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

//用于本地或者网络请求对数据进行处理
public class WordRepository {
    private List<Word> list;
    private WordDao mWordDao;
    public WordRepository(Context context) {
        WordDatabase wordDatabase = WordDatabase.getDatabase(context.getApplicationContext());
        mWordDao = wordDatabase.getWordDao();
        list = mWordDao.getAllWords();
    }

    void Insert(Word...words){
        new InsertAsyncTask(mWordDao).execute(words);
    }

    void Delete(Word...words){
        new DeleteAsyncTask(mWordDao).execute(words);
    }

    public List<Word> getList() {
        return list;
    }

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
}
