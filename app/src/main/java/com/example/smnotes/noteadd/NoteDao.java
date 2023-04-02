package com.example.smnotes.noteadd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Notes note);
    @Update
    void update(Notes note);


    @Delete
    void delete(Notes note);


    @Query("DELETE  FROM notes_table")
    void deleteAll();

    @Query("DELETE FROM notes_table WHERE name == names AND topic == topics")
    void deletefname(String names,String topics);



    @Query("SELECT * FROM notes_table ORDER BY topic ASC")
    LiveData<List<Notes>> getAlphabetizedWords();

}
