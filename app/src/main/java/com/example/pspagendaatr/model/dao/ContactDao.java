package com.example.pspagendaatr.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pspagendaatr.model.entity.Contact;

import java.util.List;

@Dao
public interface ContactDao {


    @Delete
    int delete(Contact contact);


    @Insert
    long insert(Contact contact);


    @Query("select * from contact order by name")
    LiveData<List<Contact>> getAllLive();


    @Update
    int update(Contact contact);

}
