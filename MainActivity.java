package com.example.laboop;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editBrand, editModel, editYear;
    Button buttonAdd;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editBrand = findViewById(R.id.editBrand);
        editModel = findViewById(R.id.editModel);
        editYear = findViewById(R.id.editYear);

        buttonAdd = findViewById(R.id.buttonAdd);
        textResult = findViewById(R.id.textResult);


        buttonAdd.setOnClickListener(v -> {

            String brand = editBrand.getText().toString();
            String model = editModel.getText().toString();
            String yearText = editYear.getText().toString();




            int year = Integer.parseInt(yearText);


            Car car = new Car(brand, model, year);


            textResult.setText(car.getCarDetails());
        });
    }
}
