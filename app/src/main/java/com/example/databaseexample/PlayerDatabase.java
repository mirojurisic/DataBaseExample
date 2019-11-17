package com.example.databaseexample;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PlayerTask.class},version =1 ,exportSchema = false)
public abstract class PlayerDatabase extends RoomDatabase {

    private static final String LOG_TAG = PlayerDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "TOP Players";
    private static PlayerDatabase sInstance ;

    public static  PlayerDatabase getInstance(Context context){
        if(sInstance ==null)
        {
            synchronized (LOCK)
            {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        PlayerDatabase.class,PlayerDatabase.DATABASE_NAME)
                       // .allowMainThreadQueries()
                        .build();
            }
        }
        return sInstance;
    }
    public abstract PlayerDao getPlayerDao();

}
