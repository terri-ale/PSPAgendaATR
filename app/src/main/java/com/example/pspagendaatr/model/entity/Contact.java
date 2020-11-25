package com.example.pspagendaatr.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(tableName = "contact")
public class Contact implements Serializable {
    /*
    tabla: palabro
    campos: id integer autonumeric primary key, palabroes varchar not null, palabroen varchar not null
    sqlite: varchar, text
    */


    @PrimaryKey(autoGenerate = true)
    public int id;


    @NonNull
    @ColumnInfo(name = "name")
    private String name;



    @NonNull
    @ColumnInfo(name = "surname")
    private String surname;


    @NonNull
    @ColumnInfo(name = "phone")
    private String phone;


    @TypeConverters(DateConverter.class)
    private Date birthdate;


    @NonNull
    @ColumnInfo(name = "city")
    private String city;


    @NonNull
    @ColumnInfo(name = "address")
    private String address;


    @NonNull
    @ColumnInfo(name = "number")
    private int number;



    public Contact(String name, String surname, String phone, Date birthdate, String address, String city, int number){
        this.name=name;
        this.surname=surname;
        this.phone=phone;
        this.birthdate=birthdate;
        this.address=address;
        this.city=city;
        this.number=number;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return id == contact.id &&
                number == contact.number &&
                name.equals(contact.name) &&
                surname.equals(contact.surname) &&
                phone.equals(contact.phone) &&
                Objects.equals(birthdate, contact.birthdate) &&
                city.equals(contact.city) &&
                address.equals(contact.address);
    }


    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", birthdate=" + birthdate +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", number=" + number +
                '}';
    }
}
