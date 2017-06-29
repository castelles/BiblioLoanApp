package tp2.pp.ufam.biblioloan_app;
/*
 * Created by caiotelles on 29/06/17.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EmprestimoDAO
{
    private SQLiteDatabase database;

    public EmprestimoDAO(Context context)
    {
        this.database = (new Database(context)).getWritableDatabase();
    }

    public boolean addTitle(Titulos title, Usuario user)
    {
        try
        {
            String sqlCmd = "INSERT INTO Emprestimos VALUES('" + title.getTitle() + "', "  + title.getEdition() + ", '" + user.getUserName() + "', 1)";

            this.database.execSQL(sqlCmd);
            return true;
        }
        catch (SQLException error)
        {
            Log.e("insertionTitle", error.getMessage());
            return false;
        }
    }

    public void updateReturnedTitles(Usuario user)
    {
        String sqlCmd = "UPDATE Emprestimos SER returned=0 WHERE idName='" + user.getUserName() + "'";
        database.execSQL(sqlCmd);
    }
}
