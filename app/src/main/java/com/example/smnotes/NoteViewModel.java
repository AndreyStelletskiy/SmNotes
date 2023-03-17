package com.example.smnotes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository mRepository;

    private final LiveData<List<Notes>> mAllNotes;

    public NoteViewModel (Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    LiveData<List<Notes>> getAllWords() { return mAllNotes; }

    public void insert(Notes note) { mRepository.insert(note); }
    public void delete(Notes note) { mRepository.delet(note); }

}
