package com.example.myapplication.room;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class NotesLab {

    private static NotesLab notesLab;

    private NotesDao notesDao;


    private NotesLab(Context context) {
        Context appContext = context.getApplicationContext();
        NotesBBDD database = Room.databaseBuilder(appContext, NotesBBDD.class, NotesContract.TABLE_NAME)
                .allowMainThreadQueries().build();
        notesDao = database.getNotesDao();
    }

    public static NotesLab get(Context context) {
        if (notesLab == null) {
            notesLab = new NotesLab(context);
        }
        return notesLab;
    }

    public List<Note> obtainAllNotes() {
        return notesDao.obtainAllNotes();
    }

    public Note obtainNote(long creationDate)
    {
        return notesDao.obtainNote(creationDate);
    }

    public boolean existsViaje(long creationDate)
    {
        return notesDao.existsNote(creationDate) == 1;
    }

    public boolean insertNote(Note note) {
        if(!existsViaje(note.getCreationDate())) {
            notesDao.insertNote(note);
            return true;
        }else
        {
            return false;
        }

    }

    public int updateNote(Note note) {
        return notesDao.updateNote(note);
    }

    public void deleteNote(Note note) {
        notesDao.deleteNote(note);
    }


}
