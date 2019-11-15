package com.example.databaseexample;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM PlayerTask ORDER BY lastName")
    List<PlayerTask> loadAllTask();

    @Insert
    void insertTask(PlayerTask playerEntry);

    @Delete
    void deleteTask(PlayerTask playerEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(PlayerTask playerEntry);

}
