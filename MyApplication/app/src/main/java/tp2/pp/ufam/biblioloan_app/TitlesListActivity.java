package tp2.pp.ufam.biblioloan_app;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;

public class TitlesListActivity extends ListActivity {

    private TitulosDAO titlesDAO;
    private SimpleCursorAdapter data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.titlesDAO = new TitulosDAO(this);

        data = new SimpleCursorAdapter(this, R.layout.title_row, titlesDAO.getTitles(), new String[] {"title", "author", "edition"},
                new int[] { R.id.titleText, R.id.authorText, R.id.editionText}, 0);
        setListAdapter(data);
    }
}
