package com.example.ytnotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_Database1")
    LiveData<List<Notes>> getallNotes();

    @Query("SELECT * FROM Notes_Database1 ORDER BY notes_priority DESC")
    LiveData<List<Notes>> hightolow();

    @Query("SELECT * FROM Notes_Database1 ORDER BY notes_priority ASC")
    LiveData<List<Notes>> lowtohigh();

    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM Notes_Database1 WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);


}
