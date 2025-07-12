package com.example.fruitrecycler.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface FruitItemDao {
    @Query("SELECT * FROM FruitItem")
    List<FruitItem> getAll();

    @Insert
    void insert(FruitItem item);

    @Delete
    void delete(FruitItem item);
}
