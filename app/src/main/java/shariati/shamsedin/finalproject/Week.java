package shariati.shamsedin.finalproject;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import shariati.shamsedin.finalproject.adapters.SongAdapter;
import shariati.shamsedin.finalproject.data.Song;
import shariati.shamsedin.finalproject.data.SongResponse;

public class Week extends AppCompatActivity {

    private RecyclerView recyclerweek;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        recyclerweek = findViewById(R.id.recyclerweek);
        error = findViewById(R.id.txterrorw);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerweek.setLayoutManager(layoutManager1);

        RequestManager manager = new RequestManager(this);
        SongListRequestListener thisWeekListener = new SongListRequestListener() {
            @Override
            public void didFetch(SongResponse response) {
                recyclerweek.setAdapter(new SongAdapter(Week.this, response.getResults(),
                        (position, v, id) -> {
                            Intent intent = new Intent(Week.this, Song.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                        }));
            }

            @Override
            public void didError(String errorMessage) {
                error.setText(errorMessage);
            }
        };
        manager.getTopHitsThisWeek(thisWeekListener);
    }
}