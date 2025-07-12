package com.example.fruitrecycler.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FruitItem.class}, version = 1)
public abstract class FruitDatabase extends RoomDatabase {
    private static volatile FruitDatabase INSTANCE;

    public abstract FruitItemDao fruitItemDao();

    // Singleton pattern for database instance
    public static FruitDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FruitDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    FruitDatabase.class, "fruit_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

