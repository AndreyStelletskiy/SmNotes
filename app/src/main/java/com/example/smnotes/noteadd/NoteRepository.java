package com.example.smnotes.noteadd;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.smnotes.MainActivity;
import com.example.smnotes.R;

import java.util.List;

public class NoteRepository {
    //метод передающий данные в NoteDao

    private NoteDao mNoteDao;
    private LiveData<List<Notes>> mAllNotes;
    private LiveData<List<Notes>> mNotes;
    private LiveData<List<String>> mtopic;
    private LiveData<List<String>> mname;

    private Application application;
    NoteRepository(Application application) {
        this.application = application;
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
    void insert(Notes note, int ttt) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            try {
                mNoteDao.insert(note);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(ttt == 1){
                            Toast.makeText(application.getApplicationContext(),application.getApplicationContext().getResources().getString(R.string.noteadd), Toast.LENGTH_LONG).show();
                        }
                        if(ttt == 2){
                            Toast.makeText(application.getApplicationContext(),application.getApplicationContext().getResources().getString(R.string.cnotes), Toast.LENGTH_LONG).show();
                        }
                        if(ttt == 3){
                            Toast.makeText(application.getApplicationContext(),application.getApplicationContext().getResources().getString(R.string.ttt), Toast.LENGTH_LONG).show();
                        }
                        else {

                        }
                        //Toast.makeText(application.getApplicationContext(), "Заметка добавлена", Toast.LENGTH_LONG).show();
                    }
                });
            } catch (Exception e) {
                Log.d("RRR" , "123");
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // write your code here
                        Toast.makeText(application.getApplicationContext(), "Такая заметка уже существует", Toast.LENGTH_LONG).show();
                    }
                });

            }
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

    void deleteByname(String ndiff) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.deleteByname(ndiff);
        });}

    LiveData<List<String>> getALLtopic() {
        mtopic = mNoteDao.getALLtopic();
        return mtopic;
    }
    LiveData<List<String>> getALLnames() {
        mname = mNoteDao.getALLnames();
        return mname;
    }



    LiveData<List<Notes>> getNotet(String topic) {
        mNotes = mNoteDao.getNotet(topic);
        return mNotes;
    }
    LiveData<List<Notes>> getNoten(String name) {
        mNotes = mNoteDao.getNoten(name);
        return mNotes;
    }



}
