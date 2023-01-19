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
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Neww extends AppCompatActivity {

        private TextView results;
        private RecyclerView recyclernew;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_neww);

            results = findViewById(R.id.results);
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


