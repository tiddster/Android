package com.example.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//用于管理数据
public class WordViewModel extends AndroidViewModel {
    private WordRepository mWordRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mWordRepository = new WordRepository(application);
        //将database初始化
        /*放入仓库
        WordDatabase wordDatabase = WordDatabase.getDataBase(application);
        mWordDao = wordDatabase.getWordDao();
        allWordLive = mWordDao.getAllWordsLive();
         */
    }

    public LiveData<List<Word>> getAllWordLive() {
        return mWordRepository.getAllWordsLive();
    }

    //通过仓库读取的添改删清，四种方法------------------------------------------------------------------------------
    void insertWords(Word... words) {
        mWordRepository.insertWords(words);
    }

    void updateWords(Word... words) {
        mWordRepository.updateWords(words);
    }

    void deleteWords(Word... words) {
        mWordRepository.deleteWords(words);
    }

    void clearWords() {
        mWordRepository.clearWords();
    }
    //ViewModel是用于管理数据而不是获取数据，所以将以下方法放入仓库--WordsRepository中

/*
    //添改删清，四个AsyncTask类，放入子线程中----------------------------------------------------------
    static class InsertAsyncTask extends AsyncTask<Word,Void,Void> {
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

    static class UpdateAsyncTask extends AsyncTask<Word,Void,Void>{
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

    static class DeleteAsyncTask extends AsyncTask<Word,Void,Void>{
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

    static class ClearAsyncTask extends AsyncTask<Void,Void,Void>{
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

 */
}
