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
    List<PlayerTask> loadAllTasks();

    @Insert
    void insertTask(PlayerTask playerEntry);

    @Delete
    void deleteTask(PlayerTask playerEntry);

    @Query("DELETE FROM PlayerTask")
    public void nukeTable();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(PlayerTask playerEntry);

    @Query("SELECT * FROM PlayerTask WHERE _id=:id")
    PlayerTask loadTaskById(int id);
    

}
