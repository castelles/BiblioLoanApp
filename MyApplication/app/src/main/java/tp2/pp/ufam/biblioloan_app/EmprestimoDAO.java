package tp2.pp.ufam.biblioloan_app;
/*
 * Created by caiotelles on 29/06/17.
 *
 * Classe que realiza o tratamento dos titulos que foram emprestados, tanto os que ja foram devolvi-
 * dos quanto os que ainda estao pendentes.
 */

import android.content.Context;
import android.database.Cursor;
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
    /*
     * Atualiza o banco de dados quando e realizado uma devolucao
     * A coluna "returned" da tabela Emprestimos e responsavel por armazenar essa informacao
     */
    public void updateReturnedTitles(Usuario user)
    {
        String sqlCmd = "UPDATE Emprestimos SET returned=0 WHERE idName='" + user.getUserName() + "'";
        database.execSQL(sqlCmd);
    }
    /*
     * Método visto em sala
     * Cria uma "lista" dos itens presentes na tabela referida
     */
    public Cursor getTitles(Usuario user)
    {
        return this.database.rawQuery("SELECT rowid AS _id, idTitle, idEdition, CASE WHEN returned=0 THEN 'Devolvido' " +
                "ELSE 'Pendente' END AS returned FROM Emprestimos WHERE idName='" + user.getUserName() + "' ORDER BY idTitle", null);
    }
}
