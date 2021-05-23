package com.mahad.task.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    void insert(Fav_Entities fav_entities);

    @Query("SELECT * FROM favourite")
    List<Fav_Entities> fetch();

    @Query("SELECT * FROM favourite ORDER BY random() LIMIT 1")
    Fav_Entities fetchRandom();

}
