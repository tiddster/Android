
package com.example.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private LiveData<List<Word>> allWordsLive;
    private WordDao mWordDao;

    public WordRepository(Context context) {
        //将database初始化
        WordDatabase wordDatabase = WordDatabase.getDataBase(context.getApplicationContext());
        mWordDao = wordDatabase.getWordDao();
        allWordsLive = mWordDao.getAllWordsLive();
    }

    public LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }

    //添改删清，四种方法------------------------------------------------------------------------------
    void insertWords(Word... words) {
        new InsertAsyncTask(mWordDao).execute(words);
    }

    void updateWords(Word... words) {
        new UpdateAsyncTask(mWordDao).execute(words);
    }

    void deleteWords(Word... words) {
        new DeleteAsyncTask(mWordDao).execute(words);
    }

    void clearWords() {
        new ClearAsyncTask(mWordDao).execute();
    }

    //添改删清，四个AsyncTask类，放入子线程中----------------------------------------------------------
    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mWordDao;

        public InsertAsyncTask(WordDao wordDao) {
            mWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.InsertWords(words);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mWordDao;

        public UpdateAsyncTask(WordDao wordDao) {
            mWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.updateWords(words);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mWordDao;

        public DeleteAsyncTask(WordDao wordDao) {
            mWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.DeleteWords(words);
            return null;
        }
    }

    static class ClearAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao mWordDao;

        public ClearAsyncTask(WordDao wordDao) {
            mWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordDao.deleteAllWords();
            return null;
        }
    }
}
