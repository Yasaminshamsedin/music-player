package shariati.shamsedin.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import shariati.shamsedin.finalproject.adapters.SongAdapter;
import shariati.shamsedin.finalproject.data.SongResponse;

public class Neww extends AppCompatActivity {

        private TextView results;
        private RecyclerView recyclernew;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_neww);

            results = findViewById(R.id.txterrord);
            recyclernew = findViewById(R.id.recyclernew);

            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclernew.setLayoutManager(layoutManager);


            RequestManager manager = new RequestManager(this);

            SongListRequestListener latestListener = new SongListRequestListener() {
                @Override
                public void didFetch(SongResponse response) {
                    recyclernew.setAdapter(new SongAdapter(getApplicationContext(), response.getResults(), (position, v,id) -> {
                        Intent intent = new Intent(Neww.this,song.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }));
                }
                @Override
                public void didError(String errorMessage) {
                    results.setText(errorMessage);
                }
            };
            manager.getLatestSongs(latestListener);
        }
    }


