package com.example.fruitrecycler.data;

import android.content.Context;

import com.example.fruitrecycler.R;

import java.util.List;

public class FruitRepository {
    private final FruitItemDao dao;

    public FruitRepository(Context context) {
        this.dao = FruitDatabase.getDatabase(context).fruitItemDao();
    }

    public void getAll(FruitCallback<List<FruitItem>> callback) {
        new Thread(() -> {
            List<FruitItem> list = dao.getAll();
            if (list == null || list.isEmpty()) {
                dao.insert(new FruitItem(R.drawable.apple, "Apple", "Rich in fiber and vitamin C."));
                dao.insert(new FruitItem(R.drawable.banana, "Banana", "Great source of potassium."));
                dao.insert(new FruitItem(R.drawable.orange, "Orange", "Loaded with vitamin C."));
                dao.insert(new FruitItem(R.drawable.strawberry, "Strawberry", "Full of antioxidants."));
                dao.insert(new FruitItem(R.drawable.watermelon, "Watermelon", "Very refreshing and hydrating."));

                list = dao.getAll(); // Fetch again after inserting initial items
            }
            callback.onResult(list);
        }).start();
    }

    public void insert(FruitItem item, FruitCallback<List<FruitItem>> callback) {
        new Thread(() -> {
            dao.insert(item);
            List<FruitItem> list = dao.getAll();
            callback.onResult(list);
        }).start();
    }

    public void delete(FruitItem item, FruitCallback<List<FruitItem>> callback) {
        new Thread(() -> {
            dao.delete(item);
            List<FruitItem> list = dao.getAll();
            callback.onResult(list);
        }).start();
    }

    public interface FruitCallback<T> {
        void onResult(T result);
    }
}
