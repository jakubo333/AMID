package com.example.lab2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos);


        String odebranyUrl = getIntent().getStringExtra("KLUCZ_URL");

        TextView tvInfo = findViewById(R.id.tvInfoDos);
        Button btnLink = findViewById(R.id.btnOtworzLink);

        tvInfo.setText("Przygotowany adres: " + odebranyUrl);

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intencjaPrzegladarki = new Intent(Intent.ACTION_VIEW, Uri.parse(odebranyUrl));
                startActivity(intencjaPrzegladarki);
            }
        });
    }
}
