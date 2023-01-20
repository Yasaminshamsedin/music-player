package shariati.shamsedin.finalproject;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import shariati.shamsedin.finalproject.adapters.SongAdapter;
import shariati.shamsedin.finalproject.data.SongResponse;

public class Day extends AppCompatActivity {

    private RecyclerView recyclerday;
    private TextView txterrord;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        recyclerday = findViewById(R.id.recyclerday);
        txterrord = findViewById(R.id.txterrord);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerday.setLayoutManager(layoutManager);

        RequestManager manager = new RequestManager(this);
        SongListRequestListener todayListener = new SongListRequestListener() {
            @Override
            public void didFetch(SongResponse response) {
                recyclerday.setAdapter(new SongAdapter(Day.this, response.getResults(),new SongAdapter.ClickListener() {
                            @Override
                            public void onSongClick(int position, View v, String id) {
                                Intent intent = new Intent(Day.this, song.class);
                                intent.putExtra("id", id);
                                startActivity(intent);
                            }
                        }));
            }

            @Override
            public void didError(String errorMessage) {
                txterrord.setText(errorMessage);
            }
        };

        manager.getTopHitsToday(todayListener);

    }
}