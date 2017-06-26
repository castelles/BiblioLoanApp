package tp2.pp.ufam.biblioloan_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser);
    }

    public void createUser(View view)
    {
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String pass = ((EditText) findViewById(R.id.password)).getText().toString();
        String fullname = ((EditText) findViewById(R.id.fullname)).getText().toString();

        Usuario user = new Usuario(username, pass, fullname, 0);
        UsuarioDAO userDAO = new UsuarioDAO(this);

        if (userDAO.addUser(user))
        {
            Toast.makeText(this, "Usuário criado!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(this, "Erro ao cadastrar!", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack(View view)
    {
        finish();
    }
}
