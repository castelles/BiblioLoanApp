package tp2.pp.ufam.biblioloan_app;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;

import org.w3c.dom.Text;

public class RegistroActivity extends ListActivity {

    private SimpleCursorAdapter data;
    private EmprestimoDAO loanDAO;
    private TitulosDAO titlesDAO;
    private UsuarioDAO userDAO;
    private TextView mkLoan;

    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loanDAO = new EmprestimoDAO(this);

        Intent intent = getIntent();
        user = (Usuario) intent.getSerializableExtra("user");

        if (loanDAO.getTitles(user).getCount() > 0)
        {
            data = new SimpleCursorAdapter(this, R.layout.register_row, loanDAO.getTitles(user), new String[] {"idTitle", "idEdition", "returned"},
                    new int[] { R.id.titleRegister, R.id.editionRegister, R.id.statusRegister}, 0);
            setListAdapter(data);
        }
        else
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Realize algum empréstimo!").setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    finish();
                }
            }).show();

        }
    }

    @Override
    public void onListItemClick(ListView list, View view, int pos, long id)
    {
        TextView statusTxt = (TextView) findViewById(R.id.statusRegister);
        String status = statusTxt.getText().toString();

        if (status.equals("Pendente"))
        {
            titlesDAO = new TitulosDAO(this);
            userDAO = new UsuarioDAO(this);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Deseja realizar a devolução?").setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                    int edition = Integer.parseInt(((TextView) findViewById(R.id.editionRegister)).getText().toString());
                    String titleStrg = ((TextView) findViewById(R.id.titleRegister)).getText().toString();

                    Titulos title = titlesDAO.getTitle(titleStrg, edition);

                    titlesDAO.alterLoanTitle(title, 0);
                    userDAO.updateAvailability(user, 0);
                    loanDAO.updateReturnedTitles(user);
                    finish();
                }
            }).setNegativeButton("Cancelar", null).show();

        }
    }
}
