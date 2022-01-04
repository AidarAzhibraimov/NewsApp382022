package kg.geektech.newsapp38.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kg.geektech.newsapp38.models.News;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM News order by createDate DESC")
    List<News> getAll();


    @Query("SELECT * FROM News order by title ASC")
    List<News> getAllSortByTitle();

    @Insert
    void insert(News news);

    @Delete
    void delete(News news);

    @Update
    void update(News news);
}
