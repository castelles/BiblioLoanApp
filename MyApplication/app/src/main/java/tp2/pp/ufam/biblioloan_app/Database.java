package tp2.pp.ufam.biblioloan_app;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/*
 * Created by caiotelles on 21/06/17.
 */

public class Database extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "LibraryDB.db";
    public static final String SQL_CREATE_TABLE_USER = "CREATE TABLE Usuarios(" +
            "login TEXT PRIMARY KEY, password TEXT, name TEXT, canloan INT)";
    public static final String SQL_DELETE_TABLES = "DROP TABLE IF EXISTS Usuarios";
    public static final String SQL_INSERT_ROOTUSER = "INSERT INTO Usuarios VALUES('root', 'root', 'Caio Arthur', 1)";

    public static final String SQL_CREATE_TABLE_TITLES = "CREATE TABLE Titulos(" +
            "code INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
            "title TEXT, " +
            "author TEXT, " +
            "edition INT, " +
            "available INT)";

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /*
     *  INSERIR TITULOS NA BIBLIOTECA
     */



    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_TITLES);
        db.execSQL(SQL_INSERT_ROOTUSER);

        TitulosDAO titlesDAO = new TitulosDAO();
        titlesDAO.populateTitlesTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_TABLES);
        onCreate(db);
    }

}
