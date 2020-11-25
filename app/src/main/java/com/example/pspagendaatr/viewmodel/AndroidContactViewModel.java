package com.example.pspagendaatr.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pspagendaatr.model.Repository;
import com.example.pspagendaatr.model.entity.Contact;

import java.util.List;

public class AndroidContactViewModel extends AndroidViewModel {


    private Repository repository;
    private LiveData<List<Contact>> liveContacts;


    public AndroidContactViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        liveContacts=repository.getLiveContacts();
    }


    public LiveData<List<Contact>> getLiveContacts() {
        return repository.getLiveContacts();
    }

    public void insert(Contact contact) {
        repository.insert(contact);
    }

    public void delete(Contact contact) {
        repository.delete(contact);
    }

    public void update(Contact contact) { repository.update(contact); }
}
