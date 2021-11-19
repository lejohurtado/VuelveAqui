package com.example.vuelveaqui.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.vuelveaqui.data.Result;
import com.example.vuelveaqui.data.model.LoggedInUser;
import com.example.vuelveaqui.data.model.User;

public class UserRepository extends Repository {

    private Context context;

    public UserRepository(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insert_user(User user) {
        long result = 0;

        Repository repository = new Repository(this.context);
        SQLiteDatabase db = repository.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nombres", user.getNombres());
        valores.put("telefono", user.getTelefono());
        valores.put("correo", user.getCorreo());
        valores.put("contrasena", user.getContrasena());

        result = db.insert("usuarios", "null", valores);

        return result;
    }

    public Result<LoggedInUser> login_user(User user){
        Result<LoggedInUser> result = null;

        Repository repository = new Repository(this.context);
        SQLiteDatabase db = repository.getReadableDatabase();
        String queryLogin = "SELECT id, nombres FROM usuarios where correo = ? and contrasena = ?";
        Cursor cursor = db.rawQuery(queryLogin, new String[]{user.getCorreo(), user.getContrasena()});
        if (cursor.moveToFirst()){
            do {
                result = new Result.Success<LoggedInUser>(new LoggedInUser(""+cursor.getLong(0),cursor.getString(1)));
            }while (cursor.moveToNext());

            cursor.close();
        }

        return result;
    }
}
