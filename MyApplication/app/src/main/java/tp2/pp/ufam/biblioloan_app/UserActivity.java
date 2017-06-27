package tp2.pp.ufam.biblioloan_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.view.View;

public class UserActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();

        Usuario user = (Usuario) intent.getSerializableExtra("user");

        TextView textBemVindo = (TextView) findViewById(R.id.welcomeText);

        textBemVindo.setText(Html.fromHtml("Seja Bem-Vindo <b>" + user.getFullName() + "</b>!"));
    }

    public void openListAvailableTitles(View view)
    {
        Intent intent = new Intent(this, TitlesListActivity.class);
        startActivity(intent);
    }

}
