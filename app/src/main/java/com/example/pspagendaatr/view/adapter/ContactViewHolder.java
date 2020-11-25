package com.example.pspagendaatr.view.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pspagendaatr.R;
import com.example.pspagendaatr.model.entity.Contact;
import com.example.pspagendaatr.view.ContactsActivity;
import com.example.pspagendaatr.view.NewEditContactActivity;
import com.example.pspagendaatr.viewmodel.EditListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvName;
    private final TextView tvSurname;
    private final TextView tvPhone;
    private final TextView tvAddress;
    private final TextView tvDate;
    private final ImageButton btDelete;
    private final ImageButton btEdit;



    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);

        tvName=itemView.findViewById(R.id.tvName);
        tvSurname=itemView.findViewById(R.id.tvSurname);
        tvPhone=itemView.findViewById(R.id.tvPhone);
        tvAddress=itemView.findViewById(R.id.tvAddress);
        tvDate=itemView.findViewById(R.id.tvDate);

        btDelete=itemView.findViewById(R.id.btDelete);
        btEdit=itemView.findViewById(R.id.btEdit);


        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }


    public void bind(Contact contact, EditListener listener){

        tvName.setText(contact.getName());
        tvSurname.setText(contact.getSurname());
        tvAddress.setText(contact.getAddress()+", "+contact.getNumber()+"\n"+contact.getCity());
        tvPhone.setText(contact.getPhone());
        tvDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(contact.getBirthdate()));
        Log.v("xyzyx",contact.getBirthdate().toString());

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditContact(contact);
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteContact(contact);
            }
        });


    }



    static ContactViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new ContactViewHolder(view);
    }
}
