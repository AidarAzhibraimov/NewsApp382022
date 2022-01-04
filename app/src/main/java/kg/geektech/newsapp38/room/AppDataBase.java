package kg.geektech.newsapp38.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kg.geektech.newsapp38.models.News;

@Database(entities = {News.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract NewsDao newsDao();
}
