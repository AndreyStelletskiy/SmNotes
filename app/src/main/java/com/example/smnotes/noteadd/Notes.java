package com.example.smnotes.noteadd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table", indices = {@Index(value = {"nodiff"},
        unique = true)})
public class Notes {
    //структура таблицы


    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;


    @NonNull
    @ColumnInfo(name = "topic")
    private String mTopic;

    @NonNull
    @ColumnInfo(name = "note")
    private String mNote;



    @NonNull
    @ColumnInfo(name = "nodiff")
    private String mNodiff;


    public Notes(@NonNull String mName, @NonNull String mTopic, @NonNull String mNote, @NonNull String mNodiff) {
        this.mName = mName;
        this.mTopic = mTopic;
        this.mNote = mNote;
        this.mNodiff = mNodiff;
    }


    public String getName(){return this.mName;}
    public String getTopic(){return this.mTopic;}
    public String getNote(){return this.mNote;}
    public String getNodiff() {
        return this.mNodiff;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
