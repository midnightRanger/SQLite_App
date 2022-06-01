package com.example.sqlite_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE = "sqlite_CRUD_example.db";
    private static final String USER_TABLE = "Users";
    private static final String USER_ID = "_id";
    private static final String USER_NAME = "first_name";
    private static final String USER_PHONE = "phone_number";
    private static final String USER_BIRTHDATE = "birthdate";

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format(
                "create table %s(%s INTEGER primary key, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT)",
                USER_TABLE, USER_ID, USER_NAME, USER_PHONE, USER_BIRTHDATE);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = String.format("drop table if exists %s", USER_TABLE);
        db.execSQL(sql);
    }

    /**
     * Добавляет нового пользователя в таблицу Users
     *
     * @param first_name   - имя пользователя
     * @param phone_number - номер телефона пользователя
     * @param birthdate    - дата рождения пользователя
     * @return была ли строка добавлена в таблицу
     */
    public Boolean insert(String first_name, String phone_number, String birthdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, first_name);
        contentValues.put(USER_PHONE, phone_number);
        contentValues.put(USER_BIRTHDATE, birthdate);
        long result = db.insert(USER_TABLE, null, contentValues);
        return result != -1;
    }

    /**
     * Обновляет существующего пользователя по id
     *
     * @param id           - уникальный идентификатор пользователя
     * @param first_name   - имя пользователя
     * @param phone_number - номер телефона пользователя
     * @param birthdate    - дата рождения пользователя
     * @return была ли строка изменена
     */
    public Boolean edit(int id, String first_name, String phone_number, String birthdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, first_name);
        values.put(USER_PHONE, phone_number);
        values.put(USER_BIRTHDATE, birthdate);
        String where = String.format("%s = %d", USER_ID, id);
        int result = db.update(USER_TABLE, values, where, null);
        return result > 0;
    }

    /**
     * Удаляет пользователя по id
     *
     * @param id - уникальный идентификатор пользователя
     * @return была ли строка удалена
     */
    public Boolean delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = String.format("%s = %d", USER_ID, id);
        int result = db.delete(USER_TABLE, where, null);
        return result > 0;
    }

    /**
     * Получает всех пользователей из таблицы
     *
     * @return Cursor с данными всех пользователей
     */
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("select * from %s", USER_TABLE);
        return db.rawQuery(sql, null);
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> all = new ArrayList<>();
        Cursor data = getData();
        while (data.moveToNext()) {
            int id = data.getInt(0);
            String name = data.getString(1);
            String phone = data.getString(2);
            String birthdate = data.getString(3);
            all.add(new User(id, name, phone, birthdate));
        }
        return all;
    }
}
