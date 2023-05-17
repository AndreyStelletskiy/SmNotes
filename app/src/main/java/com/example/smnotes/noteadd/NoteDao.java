package com.example.smnotes.noteadd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {


    //метод добавления заметок
    //@Insert
    //void insert(Notes note);
    @Insert(onConflict = OnConflictStrategy.ABORT )
    void insert(Notes note) throws Exception;


    @Delete
    void delete(Notes note);


    //метод удаления всех земеток
    @Query("DELETE  FROM notes_table")
    void deleteAll();

    @Query("DELETE from notes_table WHERE nodiff IN (:ndiff)")
    int deleteByname(String ndiff);


    //метод получения заметок из бд по теме
    @Query("SELECT * FROM notes_table WHERE topic IN (:topic) ORDER BY id DESC")
    LiveData<List<Notes>> getNotet(String topic);


    //метод получения заметок из бд по Названию
    @Query("SELECT * FROM notes_table WHERE name IN (:name) ORDER BY id DESC")
    LiveData<List<Notes>> getNoten(String name);


    //метод получения всех тем из бд
    @Query("SELECT topic FROM notes_table ORDER BY id DESC")
    LiveData<List<String>> getALLtopic();


    //метод получения всех названий из бд
    @Query("SELECT name FROM notes_table ORDER BY id DESC")
    LiveData<List<String>> getALLnames();


    //метод получения всех заметок из бд
    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    LiveData<List<Notes>> getAlphabetizedWords();

}
