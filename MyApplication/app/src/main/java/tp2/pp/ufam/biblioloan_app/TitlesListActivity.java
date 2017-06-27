package tp2.pp.ufam.biblioloan_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;

public class TitlesListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titles_list);
    }
}
