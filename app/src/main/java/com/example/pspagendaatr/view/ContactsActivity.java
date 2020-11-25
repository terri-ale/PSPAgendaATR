package com.example.pspagendaatr.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.pspagendaatr.R;
import com.example.pspagendaatr.model.entity.Contact;
import com.example.pspagendaatr.view.adapter.ContactAdapter;
import com.example.pspagendaatr.viewmodel.AndroidContactViewModel;
import com.example.pspagendaatr.viewmodel.EditListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

public class ContactsActivity extends AppCompatActivity implements EditListener {

    public final static int ADD_CONTACT_REQUEST_CODE=1;
    public final static int EDIT_CONTACT_REQUEST_CODE=2;
    private AndroidContactViewModel androidContactViewModel;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==ADD_CONTACT_REQUEST_CODE && resultCode==RESULT_OK){
            Contact add=(Contact) data.getSerializableExtra("addContact");
            if(add!=null) androidContactViewModel.insert(add);

        }else if(requestCode==EDIT_CONTACT_REQUEST_CODE && resultCode==RESULT_OK){
            Contact edited=(Contact) data.getSerializableExtra("editContact");
            if(edited!=null) androidContactViewModel.update(edited);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidContactViewModel=new ViewModelProvider(this).get(AndroidContactViewModel.class);

        init();
    }

    private void init() {
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        final ContactAdapter contactAdapter=new ContactAdapter(new ContactAdapter.ContactDiff(), this);
        recyclerView.setAdapter(contactAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContactsActivity.this, NewEditContactActivity.class);
                intent.putExtra("requestCode",ADD_CONTACT_REQUEST_CODE);
                startActivityForResult(intent, ADD_CONTACT_REQUEST_CODE);
            }
        });


        androidContactViewModel.getLiveContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                contactAdapter.submitList(contacts);
            }
        });

    }



    @Override
    public void onEditContact(Contact contact) {
        Intent intent=new Intent(ContactsActivity.this, NewEditContactActivity.class);
        intent.putExtra("requestCode",EDIT_CONTACT_REQUEST_CODE);
        intent.putExtra("editContact", contact);
        startActivityForResult(intent, EDIT_CONTACT_REQUEST_CODE);
    }


    @Override
    public void onDeleteContact(Contact contact) {
        androidContactViewModel.delete(contact);
    }
}