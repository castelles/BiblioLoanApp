package tp2.pp.ufam.biblioloan_app;

/*
 * Created by caiotelles on 26/06/17.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

public class TitulosDAO
{
    private SQLiteDatabase database;

    public TitulosDAO(Context context)
    {
        this.database = (new Database(context)).getWritableDatabase();
    }

    public TitulosDAO(){}

    public Titulos getTitle(String title, int edition)
    {
        Titulos titles = null;

        String sqlQuery = "SELECT * FROM Titulos WHERE title='" + title + "' AND edicao='" + edition + "'";
        Cursor cursor = this.database.rawQuery(sqlQuery, null);

        if (cursor.moveToNext())
        {
            titles = new Titulos(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
            Log.i("titlefound", "titulo encontrado");
        }
        cursor.close();
        return titles;
    }

    public boolean addTitle(Titulos title)
    {
        try
        {
            String sqlCmd = "INSERT INTO Titulos VALUES (NULL, '" + title.getTitle() + "', '"
                    + title.getAuthor() + "', "  + title.getEdition() + ", " + title.getAvailable() + ")";
            this.database.execSQL(sqlCmd);
            return true;
        }
        catch (SQLException error)
        {
            Log.e("insertionTitle", error.getMessage());
            return false;
        }
    }

    public Cursor getTitles()
    {
        return this.database.rawQuery("SELECT rowid AS _id, title, author, edition, available FROM Titulos ORDER BY title", null);
    }

    public void populateTitlesTable()
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
        Titulos title16 = new Titulos("Origens", "Neil D'Egrasse Tyson", 1, 0);

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
        while (iterator.hasNext())
        {
            this.addTitle(iterator.next());
        }
    }

}
