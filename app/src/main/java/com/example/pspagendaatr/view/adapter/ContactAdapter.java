package com.example.pspagendaatr.view.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.pspagendaatr.model.entity.Contact;
import com.example.pspagendaatr.viewmodel.EditListener;

public class ContactAdapter extends ListAdapter<Contact, ContactViewHolder> {

    private EditListener listener;


    public ContactAdapter(@NonNull DiffUtil.ItemCallback<Contact> diffCallback, EditListener listener) {
        super(diffCallback);
        this.listener=listener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ContactViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact current=getItem(position);
        holder.bind(current, listener);
    }




    public static class ContactDiff extends DiffUtil.ItemCallback<Contact> {

        @Override
        public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.equals(newItem);
        }
    }
}
