package com.example.smnotes.noteadd;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Notes>> mAllNotes;

    NoteRepository(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Notes>> getAllNotes() {
        return mAllNotes;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Notes note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.insert(note);
        });
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                mWordDao.insert(word);
            }
        }).start();*/
        // mWordDao.insert(word);
    }

    void delet(Notes note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.delete(note);
        });}

    void deletALL() {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.deleteAll();
        });}

    void deleteByname(String name, String topic, String note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.deleteByname(name, topic, note);
        });}


}
