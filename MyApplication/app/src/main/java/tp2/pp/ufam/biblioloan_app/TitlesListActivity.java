package tp2.pp.ufam.biblioloan_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TitlesListActivity extends ListActivity {

    private TitulosDAO titlesDAO;
    private UsuarioDAO userDAO;
    private EmprestimoDAO loanDAO;
    private SimpleCursorAdapter data;
    private Usuario user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.titlesDAO = new TitulosDAO(this);

        Intent intent = getIntent();
        user = (Usuario) intent.getSerializableExtra("user");

        data = new SimpleCursorAdapter(this, R.layout.title_row, titlesDAO.getTitles(), new String[] {"title", "author", "edition"},
                new int[] { R.id.titleText, R.id.authorText, R.id.editionText}, 0);
        setListAdapter(data);
    }

    @Override
    public void onListItemClick(ListView list, View view, int pos, long id)
    {
        userDAO = new UsuarioDAO(this);
        loanDAO = new EmprestimoDAO(this);

        TextView titleName = (TextView) view.findViewById(R.id.titleText);
        TextView editionName= (TextView) view.findViewById(R.id.editionText);

        String title = titleName.getText().toString();
        int editionNumber = Integer.parseInt(editionName.getText().toString());
        final Titulos titleToLoan = titlesDAO.getTitle(title, editionNumber);

        user = userDAO.getUser(user.getUserName(), user.getPass());

        if (user.getCanLoan() == 1)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Encerre seu atual ao empréstimo para poder realizar outro!").setNeutralButton("OK", null).show();
        }

        else
        {
            Log.e("debugManual", titleToLoan.getTitle() + "\n" + user.getUserName());

            if (titleToLoan != null && user != null)
            {

                loanDAO.addTitle(titleToLoan, user);

                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setMessage("Empréstimo realizado com sucesso!").setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        titlesDAO.alterLoanTitle(titleToLoan, 1);
                        userDAO.updateAvailability(user, 1);

                        Log.i("debugManual", "Emprestimo realizado  " + user.getCanLoan());
                        finish();
                    }
                }).show();
            }
            else
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("Erro ao acessar arquivos").setNeutralButton("OK", null).show();
            }

            Log.i("debugManual", user.getCanLoan() + "");
        }
    }
}
