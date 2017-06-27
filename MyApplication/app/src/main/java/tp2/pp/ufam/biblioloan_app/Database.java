package tp2.pp.ufam.biblioloan_app;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.style.TtsSpan;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Created by caiotelles on 21/06/17.
 */

public class Database extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "LibraryDB.db";
    public static final String SQL_CREATE_TABLE_USER = "CREATE TABLE Usuarios(" +
            "login TEXT PRIMARY KEY, password TEXT, name TEXT, canloan INT)";
    public static final String SQL_DELETE_TABLES = "DROP TABLE IF EXISTS Usuarios";
    public static final String SQL_INSERT_ROOTUSER = "INSERT INTO Usuarios VALUES('root', 'root', 'Caio Arthur', 1)";

    public static final String SQL_CREATE_TABLE_TITLES = "CREATE TABLE Titulos(" +
            "title TEXT PRIMARY KEY, author TEXT, edition INT, available INT)";

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
        populateTitlesTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_TABLES);
        onCreate(db);
    }

    public void populateTitlesTable(SQLiteDatabase db)
    {
        ArrayList<Titulos> listTitles = new ArrayList<Titulos>();
        Titulos title = new Titulos("Conversão de Energia I", "Alessandro Trindade", 2, 0);
        Titulos title1 = new Titulos("Sistemas Operacionais", "João", 4, 0);
        Titulos title2 = new Titulos("Projeto de Programas", "Horácio", 1, 0);
        Titulos title3 = new Titulos("Harry Potter e a Pedra Filosofal", "J.K. Rowling", 9, 0);
        Titulos title4 = new Titulos("Harry Potter e a Câmara Secreta", "J.K. Rowling", 9, 0);
        Titulos title5 = new Titulos("Harry Potter e o Prisioneiro de Azkaban", "J.K. Rowling", 9, 0);
        Titulos title6 = new Titulos("Harry Potter e o Cálice de Fogo", "J.K. Rowling", 9, 0);
        Titulos title7 = new Titulos("Harry Potter e a Ordem da Fênix", "J.K. Rowling", 9, 0);
        Titulos title8 = new Titulos("Harry Potter e o Enigma do Príncipe", "J.K. Rowling", 9, 0);
        Titulos title9 = new Titulos("Harry Potter e as Relíquias da Morte", "J.K. Rowling", 9, 0);
        Titulos title10 = new Titulos("Antifrágil", "Nassim Taleb", 9, 0);
        Titulos title11 = new Titulos("Rápido e Devagar", "Daniel Khaneman", 9, 0);
        Titulos title12 = new Titulos("Este Livro Veio do Futuro", "Marie D. Jones e Larry Flaxman", 9, 0);
        Titulos title13 = new Titulos("Senhor dos Anéis: A Sociedade do Anel", "J.R.R. Tolkien", 1, 0);
        Titulos title14 = new Titulos("Senhor dos Anéis: As Duas Torres", "J.R.R. Tolkien", 1, 0);
        Titulos title15 = new Titulos("Senhor dos Anéis: O Retorno do Rei", "J.R.R. Tolkien", 1, 0);
        Titulos title16 = new Titulos("Origens", "Neil D Egrasse Tyson", 1, 0);

        listTitles.add(title);
        listTitles.add(title1);
        listTitles.add(title2);
        listTitles.add(title3);
        listTitles.add(title4);
        listTitles.add(title5);
        listTitles.add(title6);
        listTitles.add(title7);
        listTitles.add(title8);
        listTitles.add(title9);
        listTitles.add(title10);
        listTitles.add(title11);
        listTitles.add(title12);
        listTitles.add(title13);
        listTitles.add(title14);
        listTitles.add(title15);
        listTitles.add(title16);

        Iterator<Titulos> iterator = listTitles.iterator();
        Titulos titlee = null;
        while (iterator.hasNext())
        {
            titlee = iterator.next();
            Log.i("error_title", titlee.getTitle());
            String sqlCmd = "INSERT INTO Titulos VALUES('" + titlee.getTitle() + "', '"
                    + titlee.getAuthor() + "', "  + titlee.getEdition() + ", " + titlee.getAvailable() + ")";
            db.execSQL(sqlCmd);
        }

    }

}
