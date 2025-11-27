package com.example.lab2;


import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Panel chessPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chessPanel = findViewById(R.id.chess_panel);


        registerForContextMenu(chessPanel);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_reset) {
            chessPanel.resetBoard();
            Toast.makeText(this, "Szachownica zresetowana", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_invert) {
            chessPanel.invertColors();
            Toast.makeText(this, "Kolory odwrócone", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_about) {
            Toast.makeText(this, "Szachownica - laboratorium", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        menu.setHeaderTitle("Menu kontekstowe");
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_reset) {
            chessPanel.resetBoard();
            Toast.makeText(this, "Szachownica zresetowana ", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_invert) {
            chessPanel.invertColors();
            Toast.makeText(this, "Kolory odwrócone ", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_about) {
            Toast.makeText(this, "Szachownica - laboratorium", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
