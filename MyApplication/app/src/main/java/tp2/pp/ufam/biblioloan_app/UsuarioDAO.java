package tp2.pp.ufam.biblioloan_app;
/*
 * Created by caiotelles on 26/06/17.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UsuarioDAO
{
    private SQLiteDatabase database;

    public UsuarioDAO(Context context)
    {
        this.database = (new Database(context)).getWritableDatabase();
    }

    public Usuario getUser(String login, String pass)
    {
        Usuario user = null;

        String sqlQuery = "SELECT * FROM Usuarios WHERE login='" + login + "' AND password='" + pass + "'";
        Cursor cursor = this.database.rawQuery(sqlQuery, null);

        if (cursor.moveToNext())
        {
            user = new Usuario(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            Log.i("userfound", "usuario encontrado");
        }
        cursor.close();
        return user;
    }

    public boolean addUser(Usuario user)
    {
        try
        {
            String sqlCmd = "INSERT INTO Usuarios VALUES ('" + user.getUserName() + "', '"
                        + user.getPass() + "', '"  + user.getFullName() + "', " + user.getCanLoan() + ")";
            this.database.execSQL(sqlCmd);
            return true;
        }
        catch (SQLException error)
        {
            Log.e("insertionUser", error.getMessage());
            return false;
        }
    }

    public Cursor getUsuarios()
    {
        return this.database.rawQuery("SELECT rowid AS _id, login, password, name, canloan FROM Usuarios ORDER BY login", null);
    }

    public void updateAvailability(Usuario user)
    {
        user.setCanLoan(1);
        String sqlCmd = "UPDATE Usuarios SET canloan=1 WHERE login='" + user.getUserName() + "'";
        database.execSQL(sqlCmd);
    }
}
