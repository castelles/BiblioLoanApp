package tp2.pp.ufam.biblioloan_app;
/*
 * Created by caiotelles on 26/06/17.
 * Classe que realiza todas as manipulacoes necessarias ao longo do funcinamento do app na tabela de
 * Usuarios
 *
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

    /*
     * Acessa um usuario a partir das informacoes passadas nos parametros
     */
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
    /*
     * Adiciona um novo usuario ao banco de dados
     */
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
    /*
     * Retorna a lista de usuarios cadastrados no sistema
     *             *Nao utilizada ao longo do aplicativo*
     */
    public Cursor getUsuarios()
    {
        return this.database.rawQuery("SELECT rowid AS _id, login, password, name, canloan FROM Usuarios ORDER BY login", null);
    }

    /*
     * Altera o autorizacao do usuario para emprestar um novo titulo
     *    - 0 pode emprestar
     *    - 1 nao pode emprestar
     */
    public void updateAvailability(Usuario user, int canLoan)
    {
        user.setCanLoan(canLoan);
        String sqlCmd = "UPDATE Usuarios SET canloan=" + canLoan + " WHERE login='" + user.getUserName() + "'";
        database.execSQL(sqlCmd);
    }

    public boolean updatePass(String username, String pass)
    {
        String sqlCmd = "SELECT * FROM Usuarios WHERE login='" + username + "'";
        Cursor cursor = this.database.rawQuery(sqlCmd, null);
        if (cursor.moveToNext())
        {
            sqlCmd = "UPDATE Usuarios SET password='" + pass + "' WHERE login='" + username + "'";
            database.execSQL(sqlCmd);
        }
        else
        {
            return false;
        }
        return true;
    }
}
