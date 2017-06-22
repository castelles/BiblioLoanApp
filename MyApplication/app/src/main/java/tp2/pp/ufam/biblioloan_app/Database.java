package tp2.pp.ufam.biblioloan_app;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/*
 * Created by caiotelles on 21/06/17.
 */

public class Database extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LibraryDB.db";
    public static final String SQL_CREATE_TABLES = "CREATE TABLE Usuarios(" +
            "login TEXT PRIMARY KEY, nome TEXT, senha TEXT, tipo INT)";
    public static final String SQL_DELETE_TABLES = "DROP TABLE IF EXISTS Usuarios";

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_TABLES);
        onCreate(db);
    }

}
