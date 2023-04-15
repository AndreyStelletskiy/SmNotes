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


    @Delete
    void delete(Notes note);


    @Query("DELETE  FROM notes_table")
    void deleteAll();

    @Query("DELETE from notes_table WHERE name IN (:name) AND topic IN(:topic) AND note IN(:note)")
    int deleteByname(String name, String topic, String note);

    @Query("SELECT * FROM notes_table WHERE topic IN (:topic)")
    LiveData<List<Notes>> getNotet(String topic);

    @Query("SELECT * FROM notes_table WHERE name IN (:name)")
    LiveData<List<Notes>> getNoten(String name);

    @Query("SELECT topic FROM notes_table ORDER BY topic ASC")
    LiveData<List<String>> getALLtopic();

    @Query("SELECT name FROM notes_table ORDER BY name ASC")
    LiveData<List<String>> getALLnames();


    @Query("SELECT * FROM notes_table ORDER BY topic ASC")
    LiveData<List<Notes>> getAlphabetizedWords();

}
