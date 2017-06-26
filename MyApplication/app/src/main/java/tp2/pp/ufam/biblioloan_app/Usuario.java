package tp2.pp.ufam.biblioloan_app;

import java.io.Serializable;

/*
 * Created by caiotelles on 21/06/17.
 */

public class Usuario implements Serializable
{
    private String userName, pass, fullName;
    private int canLoan;

    public Usuario(String userName, String pass, String fullName, int canLoan)
    {
        this.userName = userName;
        this.pass = pass;
        this.fullName = fullName;
        this.canLoan = canLoan;
    }

    public int getCanLoan() {
        return canLoan;
    }

    public void setCanLoan(int canLoan) {
        this.canLoan = canLoan;
    }

    public String getFullName() {
        return fullName;
    }

    public void setNomeCompleto(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
