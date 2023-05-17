package com.example.smnotes.noteadd;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Notes.class}, version = 5, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {

    // Это создание таблицы!!!
    public abstract NoteDao noteDao();

    private static volatile NoteRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 5;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static NoteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    NoteRoomDatabase.class, "note_database")
                            .addCallback(sRoomDatabaseCallback)
                            //.fallbackToDestructiveMigration()// <- drop!
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                NoteDao dao = INSTANCE.noteDao();
                dao.deleteAll();

                Notes notes = new Notes("Добро пожаловать", "Приветствие", "Приятного использования. \nПо вопросам пишите на почту: a.stelletskiy@ya.ru\nЕсли вы удолили эту заметку, воспользуйтесь справкой(находиться в конце тем или названий всех заметок)\nderected Andrey Stelletskiy", "cc");
                try {
                    dao.insert(notes);
                } catch (Exception e) {
                    Log.d("RRR" , "123");
                }
            });
        }
    };

}
