package tp2.pp.ufam.biblioloan_app;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PassReconfigActivity extends AppCompatActivity {

    UsuarioDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pass_reconfig);
    }

    public void updatePass(View view)
    {
        String username = ((TextView) findViewById(R.id.loginReconfig)).getText().toString();
        String pass = ((TextView) findViewById(R.id.passReconfig)).getText().toString();
        String newPass = ((TextView) findViewById(R.id.newPassReconfig)).getText().toString();

        userDAO = new UsuarioDAO(this);

        if (pass.equals(newPass))
        {
            if (userDAO.updatePass(username, pass))
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("Senha atualizada com sucesso!").setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        finish();
                    }
                }).show();
            }
        }
        else
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("As senhas não correspondem!").setNeutralButton("OK", null).show();
        }
    }
}
