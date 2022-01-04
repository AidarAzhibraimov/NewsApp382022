package kg.geektech.newsapp38;

import android.app.Application;

import androidx.room.Room;

import kg.geektech.newsapp38.room.AppDataBase;

public class App extends Application {

    private AppDataBase dataBase;

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dataBase = Room.databaseBuilder(this, AppDataBase.class, "database.db").allowMainThreadQueries()
                .build();
    }

    public AppDataBase getDataBase() {
        return dataBase;
    }

    public static App getInstance() {
        return instance;
    }
}
