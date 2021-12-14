package kg.geektech.newsapp38.models;

import java.io.Serializable;

public class News implements Serializable {
    private String title;
    private long createDate;

    public News(String title, long createDate) {
        this.title = title;
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }
}
