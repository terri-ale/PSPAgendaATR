package com.example.pspagendaatr.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.example.pspagendaatr.R;
import com.example.pspagendaatr.model.entity.Contact;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NewEditContactActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    private TextInputLayout tiName, tiSurname, tiPhone, tiAddress, tiCity, tiNumber;
    private Date selectedDate;
    private Button btSave;
    private CalendarView cvDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_edit_contact);
        if(savedInstanceState==null) init();
    }

    private void init() {

        tiName=findViewById(R.id.tiName);
        tiSurname=findViewById(R.id.tiSurname);
        tiPhone=findViewById(R.id.tiPhone);
        tiCity=findViewById(R.id.tiCity);
        tiAddress=findViewById(R.id.tiAddress);
        tiNumber=findViewById(R.id.tiNumber);
        cvDate=findViewById(R.id.cvDate);

        cvDate.setOnDateChangeListener(this);

        btSave=findViewById(R.id.btSave);

        selectedDate=new Date();

        int requestCode=getIntent().getIntExtra("requestCode",ContactsActivity.ADD_CONTACT_REQUEST_CODE);

        if(requestCode==ContactsActivity.ADD_CONTACT_REQUEST_CODE){
            setActivityForInsert();
        }else if(requestCode==ContactsActivity.EDIT_CONTACT_REQUEST_CODE){
            Contact contact=(Contact) getIntent().getSerializableExtra("editContact");
            if(contact!=null) setActivityForEdit(contact);
        }






    }




    private void setActivityForInsert(){
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent=new Intent();


                if(checkEmptyFields()){
                    setResult(RESULT_CANCELED, replyIntent);
                }else{
                    String name=tiName.getEditText().getText().toString();
                    String surname=tiSurname.getEditText().getText().toString();
                    String phone=tiPhone.getEditText().getText().toString();
                    String city=tiCity.getEditText().getText().toString();
                    String address=tiAddress.getEditText().getText().toString();
                    int number=Integer.valueOf(tiNumber.getEditText().getText().toString());
                    Contact add=new Contact(name, surname, phone, selectedDate, address, city, number);

                    replyIntent.putExtra("addContact",add);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();

            }
        });
    }


    private void setActivityForEdit(Contact contact){
        Contact c=contact;
        selectedDate=c.getBirthdate();
        tiName.getEditText().setText(contact.getName());
        tiSurname.getEditText().setText(contact.getSurname());
        tiPhone.getEditText().setText(contact.getPhone());
        tiAddress.getEditText().setText(contact.getAddress());
        tiCity.getEditText().setText(contact.getCity());
        tiNumber.getEditText().setText(String.valueOf(contact.getNumber()));
        cvDate.setDate(contact.getBirthdate().getTime());

        Intent replyIntent=new Intent();
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEmptyFields()){
                    setResult(RESULT_CANCELED, replyIntent);
                }else{
                    c.setName(tiName.getEditText().getText().toString());
                    c.setSurname(tiSurname.getEditText().getText().toString());
                    c.setPhone(tiPhone.getEditText().getText().toString());
                    c.setAddress(tiAddress.getEditText().getText().toString());
                    c.setCity(tiCity.getEditText().getText().toString());
                    c.setBirthdate(selectedDate);
                    c.setNumber(Integer.valueOf(tiNumber.getEditText().getText().toString()));

                    replyIntent.putExtra("editContact", c);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }



    private boolean checkEmptyFields(){
        if(TextUtils.isEmpty(tiName.getEditText().getText().toString()) ||
                TextUtils.isEmpty(tiSurname.getEditText().getText().toString()) ||
                TextUtils.isEmpty(tiPhone.getEditText().getText().toString()) ||
                TextUtils.isEmpty(tiCity.getEditText().getText().toString()) ||
                TextUtils.isEmpty(tiAddress.getEditText().getText().toString()) ||
                TextUtils.isEmpty(tiNumber.getEditText().getText().toString()))
            return true;
        return false;
    }


    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        Calendar tmp=new GregorianCalendar();
        tmp.set(year,month,dayOfMonth);
        selectedDate=tmp.getTime();
    }
}