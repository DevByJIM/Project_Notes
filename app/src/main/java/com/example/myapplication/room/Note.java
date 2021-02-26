package com.example.myapplication.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(tableName = NotesContract.TABLE_NAME)
public class Note {


    @NonNull
    @ColumnInfo(name = NotesContract.TITLE)
    private String title;

    @NonNull
    @ColumnInfo(name = NotesContract.CONTENT)
    private String content;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = NotesContract.CREATION_DATE)
    private long creationDate;

    @NonNull
    @ColumnInfo(name = NotesContract.UPDATE_DATE)
    private long updateDate;


    public Note(String title,String content)
    {
        this.setTitle(title);
        this.setContent(content);
        setCreationDate(Calendar.getInstance().getTime().getTime());
        setUpdateDate(getCreationDate());
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
        setUpdateDate(Calendar.getInstance().getTime().getTime());
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
        setUpdateDate(Calendar.getInstance().getTime().getTime());
    }

    public long getCreationDate() {
        return creationDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}
