package com.example.sqlite_app;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public int id;
    public String name;
    public String phone;
    public String birthdate;

    public User(int id, String name, String phone, String birthdate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birthdate = birthdate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
        out.writeString(phone);
        out.writeString(birthdate);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            int id = in.readInt();
            String name = in.readString();
            String phone = in.readString();
            String birthdate = in.readString();
            return new User(id, name, phone, birthdate);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}