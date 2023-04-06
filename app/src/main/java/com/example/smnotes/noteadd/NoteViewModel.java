package com.example.smnotes.noteadd;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository mRepository;

    private final LiveData<List<Notes>> mAllNotes;
    private  LiveData<List<Notes>> mNotes;
    private  LiveData<List<String>> mtopic;

    public NoteViewModel (Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
        mNotes = mRepository.getNote("дз");
    }


    public LiveData<List<Notes>> getAllNotes() { return mAllNotes; }

    public void insert(Notes note) { mRepository.insert(note); }
    public void delete(Notes note) { mRepository.delet(note); }
    public void deleteAll(){mRepository.deletALL();}
    public void deleteByname(String name, String topic, String note){mRepository.deleteByname(name,topic, note);}

    public LiveData<List<String>> getALLtopic(){mtopic = mRepository.getALLtopic();
    return mtopic;}

    public LiveData<List<Notes>> getNote(String topic) {
        mNotes = mRepository.getNote(topic);
        return mNotes; }


}
