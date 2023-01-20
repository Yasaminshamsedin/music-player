package shariati.shamsedin.finalproject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import shariati.shamsedin.finalproject.adapters.SearchAdapter;
import shariati.shamsedin.finalproject.adapters.SongAdapter;
import shariati.shamsedin.finalproject.data.SearchResultItem;
import shariati.shamsedin.finalproject.data.Song;
import shariati.shamsedin.finalproject.data.SongResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

public class Search extends AppCompatActivity {
    private EditText boxsearch;
    private RecyclerView searchRecycler;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchRecycler = findViewById(R.id.searchRecycler);
        error = findViewById(R.id.txterror);
        boxsearch = findViewById(R.id.tesearch);
        FloatingActionButton btnSearch = findViewById(R.id.btnsearchs);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search.this, Search.class);
                intent.putExtra("query",boxsearch.getText().toString());
                startActivity(intent);
            }
        });

        searchRecycler.setLayoutManager(new GridLayoutManager(this,2));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String query = extras.getString("query");
            RequestManager manager = new RequestManager(Search.this);
            SearchResultsListener listener = new SearchResultsListener() {
                @Override
                public void didFetch(List<SearchResultItem> response) {
                    searchRecycler.setAdapter(new SearchAdapter(getApplicationContext(), response, (position, v, id) -> {
                        Intent intent = new Intent(Search.this,song.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }));
                }

                @Override
                public void didError(String errorMessage) {
                    error.setText(errorMessage);
                }
            };
            manager.search(listener,query);
        }
    }

}