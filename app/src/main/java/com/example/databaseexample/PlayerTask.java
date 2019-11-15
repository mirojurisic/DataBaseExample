package com.example.databaseexample;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class PlayerTask {

    private String name;
    private String lastName;
    private String birthday;
    @PrimaryKey(autoGenerate = true)//will be added
    private int _id;
    private String sport;
    public PlayerTask(String name, String lastName,String birthday, int _id, String sport) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this._id = _id;
        this.sport = sport;
    }
    @Ignore //ignore this constructor
    public PlayerTask(String name, String lastName, String birthday, String sport) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }


}
