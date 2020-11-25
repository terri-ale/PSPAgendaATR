package com.example.pspagendaatr.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pspagendaatr.model.dao.ContactDao;
import com.example.pspagendaatr.model.entity.Contact;

@Database(entities = {Contact.class}, exportSchema = false, version = 1)
public abstract class ContactDatabase extends RoomDatabase {


    public abstract ContactDao getContactDao();

    private static ContactDatabase INSTANCE;


    public static ContactDatabase getDb(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDatabase.class, "contactdb").build();
        }
        return INSTANCE;
    }
}
