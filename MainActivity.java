package com.example.lab2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnUno, btnDos, btnTres;
    private TextView tvURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        btnUno = findViewById(R.id.btnUno);
        btnDos = findViewById(R.id.btnDos);
        btnTres = findViewById(R.id.btnTres);
        tvURL = findViewById(R.id.tvURL);


        btnUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, UnoActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Błąd: Czy UnoActivity jest w Manifest?", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adres = tvURL.getText().toString();

                if (!adres.startsWith("http://") && !adres.startsWith("https://")) {
                    adres = "https://" + adres;
                }

                Intent intentDos = new Intent(Intent.ACTION_VIEW, Uri.parse(adres));
                startActivity(intentDos);
            }
        });


        btnTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTres = new Intent(MainActivity.this, TresActivity.class);
                startActivity(intentTres);
            }
        });
    }
}
