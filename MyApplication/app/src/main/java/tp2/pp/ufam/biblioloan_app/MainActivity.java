package tp2.pp.ufam.biblioloan_app;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.new_user:
                Intent intentNewUser = new Intent(this, NewUserActivity.class);
                startActivity(intentNewUser);
                return true;
            case R.id.about:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("BiblioLoan created by Caio Telles").setNeutralButton("OK", null).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void entrarApp(View view)
    {
        Intent intent = new Intent(this, UserActivity.class);

        EditText editText = (EditText) findViewById(R.id.usernameArea);
        EditText editTextPass = (EditText) findViewById(R.id.passArea);

        String login = editText.getText().toString();
        String pass = editTextPass.getText().toString();

        Log.e("debugManual", "Antes do bd inicializar");

        UsuarioDAO userDAO = new UsuarioDAO(this);
        Usuario user = userDAO.getUser(login, pass);

        Log.e("debugManual", "Depois do bd inicializar");

        if (user != null)
        {
            intent.putExtra("user", user);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Usuário e/ou senha incorretos!", Toast.LENGTH_SHORT).show();
        }
    }

}
