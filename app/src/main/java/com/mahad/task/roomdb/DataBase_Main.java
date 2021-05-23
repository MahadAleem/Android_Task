package com.mahad.task.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Fav_Entities.class,version = 1,exportSchema = false)
public abstract class DataBase_Main extends RoomDatabase {

    public abstract DAO dao();
}
