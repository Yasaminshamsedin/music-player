package shariati.shamsedin.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import shariati.shamsedin.finalproject.adapters.ArtistAdapter;
import shariati.shamsedin.finalproject.adapters.SongAdapter;
import shariati.shamsedin.finalproject.data.ArtistResponse;
import shariati.shamsedin.finalproject.data.SongResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Best extends AppCompatActivity{

    private RecyclerView recyclerbest;
    private TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best);

        recyclerbest = findViewById(R.id.recyclerbest);
        results = findViewById(R.id.results);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerbest.setLayoutManager(layoutManager1);

        RequestManager manager = new RequestManager(this);

        ArtistsRequestListener artistsListener = new ArtistsRequestListener() {

            @Override
            public void didFetch(ArtistResponse response) {
                recyclerbest.setAdapter(new ArtistAdapter(Best.this, response.getResults()));
            }
            @Override
            public void didError(String errorMessage) {
                results.setText(errorMessage);
            }
        };
        manager.getTrendingArtist(artistsListener);
    }
}