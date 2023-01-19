package shariati.shamsedin.finalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView btnsearch;
    LinearLayout btnneww;
    LinearLayout btnbest;
    LinearLayout btnday;
    LinearLayout btnweek;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btnsearch= findViewById(R.id.btnsearch);
        btnsearch.setOnClickListener(view -> {
            Intent a =new Intent(MainActivity.this,Search.class);
            startActivity(a);
        });
        btnneww= findViewById(R.id.btnneww);
        btnneww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b =new Intent(MainActivity.this,Neww.class);
                startActivity(b);
            }
        });
        btnbest= findViewById(R.id.btnbest);
        btnbest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c =new Intent(MainActivity.this,Best.class);
                startActivity(c);
            }
        });
        btnday= findViewById(R.id.btnday);
        btnday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d =new Intent(MainActivity.this,Day.class);
                startActivity(d);
            }
        });
        btnweek= findViewById(R.id.btnweek);
        btnweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent e =new Intent(MainActivity.this,Week.class);
                startActivity(e);
            }
        });
    }
}