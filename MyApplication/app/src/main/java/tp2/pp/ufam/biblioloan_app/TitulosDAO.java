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

    public Titulos getTitle(String title, int edition)
    {
        Titulos titles = null;

        String sqlQuery = "SELECT * FROM Titulos WHERE title='" + title + "' AND edition='" + edition + "'";
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
            String sqlCmd = "INSERT INTO Titulos VALUES('" + title.getTitle() + "', '"
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
        return this.database.rawQuery("SELECT rowid AS _id, title, author, edition, available FROM Titulos WHERE available=0 ORDER BY title", null);
    }

    public void alterLoanTitle(Titulos titulo, int availability)
    {
        titulo.setAvailable(availability);
        String sqlCmd = "UPDATE  Titulos SET available=" + availability + " WHERE title='" + titulo.getTitle() + "' AND edition=" + titulo.getEdition();
        database.execSQL(sqlCmd);
    }
}
