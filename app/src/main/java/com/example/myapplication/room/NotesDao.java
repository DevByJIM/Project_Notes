package com.example.myapplication.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM " + NotesContract.TABLE_NAME)
    List<Note> obtainAllNotes();

    @Query("SELECT COUNT(*) FROM " + NotesContract.TABLE_NAME + " WHERE " + NotesContract.CREATION_DATE + " = :creationDate")
    int existsNote(long creationDate);

    @Query("SELECT * FROM " + NotesContract.TABLE_NAME + " WHERE " + NotesContract.CREATION_DATE + "= :creationDate")
    Note obtainNote(long creationDate);

    @Insert
    long insertNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Update
    int updateNote(Note note);

}
