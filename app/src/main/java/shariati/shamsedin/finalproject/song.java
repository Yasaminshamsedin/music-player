package shariati.shamsedin.finalproject;


import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import shariati.shamsedin.finalproject.data.Song;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class song extends AppCompatActivity {

    private TextView txtsong, txtname, txtcount, txtdate;
    private ImageView btnPlay;
    private ImageView imgArtist;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    int time = 0;
    Boolean flagPause = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        txtsong = findViewById(R.id.txtsong);
        txtname = findViewById(R.id.txtnameartist);
        btnPlay = findViewById(R.id.btnplay);
        ImageView btnstop = findViewById(R.id.btnstop);
        ImageView btnpause = findViewById(R.id.btnpause);
        imgArtist = findViewById(R.id.imgArtist);
        seekBar = findViewById(R.id.seekBar);
        txtcount = findViewById(R.id.txtcount);
        txtdate = findViewById(R.id.txtdate);
        mediaPlayer = new MediaPlayer();

        btnpause.setOnClickListener(view -> pauseMusic());
        btnstop.setOnClickListener(view -> stopMusic());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("id");
            RequestManager manager = new RequestManager(this);
            SongRequestListener listener = new SongRequestListener() {
                @Override
                public void didFetch(Song response) {
                    playMusic(response.getAudio().getMedium().getUrl());
                    txtsong.setText(response.getTitle());
                    txtname.setText(response.getArtists().get(0).getFullName());
                    txtcount.setText(response.getDownloadCount());
                    txtdate.setText((response.getReleaseDate()));
                    Glide.with(song.this)
                            .load(response.getImage().getCover().getUrl())
                            .into(imgArtist);
                }

                @Override
                public void didError(String errorMessage) {
                    txtsong.setText(errorMessage);
                }
            };
            manager.getSongById(listener, value);
            seekBar.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }
    }

    private void playMusic(String url) {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
            seekBar.setMax(mediaPlayer.getDuration()/1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition()/1000);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }}, 0, 1000);

        btnPlay.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            } else {
                mediaPlayer.start();
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
            }
        });
    }
    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onBackPressed();
    }

    void pauseMusic() {
        mediaPlayer.pause();
        time = mediaPlayer.getCurrentPosition();
        flagPause = true;
    }

    void stopMusic() {
        flagPause = false;
        mediaPlayer.stop();
        mediaPlayer.reset();
    }

}
