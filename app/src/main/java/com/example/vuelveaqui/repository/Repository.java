package com.example.vuelveaqui.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Repository extends SQLiteOpenHelper {

    private static final String DATABASE = "vuelveaqui.db";
    private static final int VERSION = 1;
    private static final String CREATE_USER_TABLE = "CREATE TABLE usuarios " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "nombres VARCHAR (300) NOT NULL, " +
            "telefono INTEGER NOT NULL, " +
            "correo VARCHAR (150) NOT NULL UNIQUE, " +
            "contrasena VARCHAR (10) NOT NULL)";
    private static final String DELETE_USER_TABLE = "DROP TABLE usuarios";

    public Repository(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_USER_TABLE);
        onCreate(db);
    }
}
