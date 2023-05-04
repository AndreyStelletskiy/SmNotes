package com.example.smnotes.noteadd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Notes {


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



    public Notes(@NonNull String name, @NonNull String topic, @NonNull String note) {
        this.mName = name;
        this.mTopic = topic;
        this.mNote = note;

    }

    public String getName(){return this.mName;}
    public String getTopic(){return this.mTopic;}
    public String getNote(){return this.mNote;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
