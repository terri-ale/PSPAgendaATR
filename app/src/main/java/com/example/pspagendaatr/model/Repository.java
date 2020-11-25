package com.example.pspagendaatr.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.pspagendaatr.ContactsApplication;
import com.example.pspagendaatr.model.dao.ContactDao;
import com.example.pspagendaatr.model.entity.Contact;
import com.example.pspagendaatr.model.room.ContactDatabase;

import java.util.List;

public class Repository {

    private ContactDatabase db;

    private ContactDao dao;

    private LiveData<List<Contact>> liveContacts;


    public Repository(Application context){
        db=ContactDatabase.getDb(context);
        dao=db.getContactDao();
        liveContacts=dao.getAllLive();
    }


    public LiveData<List<Contact>> getLiveContacts(){
        return liveContacts;
    }


    public void insert(Contact contact){
        ContactsApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(contact);
            }
        });
    }


    public void delete(Contact contact){
        ContactsApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete(contact);
            }
        });
    }


    public void update(Contact contact){
        ContactsApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.update(contact);
            }
        });
    }


}
