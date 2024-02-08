package com.example.icmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText weightInput;
    private EditText sizeInput;
    private Button calculateButton;
    private TextView textViewResult;
    private ImageView imageViewResult;
    private ImageView imageLogo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        weightInput = findViewById(R.id.weightInput);
        sizeInput = findViewById(R.id.sizeInput);
        calculateButton = findViewById(R.id.calculateButton);
        textViewResult = findViewById(R.id.textViewResult);
        imageViewResult = findViewById(R.id.imageViewResult);
        imageLogo = findViewById(R.id.logo);

        imageLogo.setImageResource(R.drawable.imc);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndUpdateResult();
            }
        });
    }

    private void calculateAndUpdateResult() {
        double weight = Double.parseDouble(weightInput.getText().toString());
        double height = Double.parseDouble(sizeInput.getText().toString()) / 100.0;

        double bmiResult = calculateBMI(weight, height);
        updateResultViews(bmiResult);
    }

    private double calculateBMI(double weightInKg, double heightInMeters) {
        return weightInKg / (heightInMeters * heightInMeters);
    }

    private void updateResultViews(double bmiResult) {
        String textResult;
        if (bmiResult < 18.5) {
            textResult = "Underweight";
            imageViewResult.setImageResource(R.drawable.underweight_image);
        } else if (bmiResult >= 18.5 && bmiResult < 25) {
            textResult = "Normal weight";
            imageViewResult.setImageResource(R.drawable.normal_weight_image);
        } else if (bmiResult >= 25 && bmiResult < 30) {
            textResult = "Overweight";
            imageViewResult.setImageResource(R.drawable.overweight_image);
        } else {
            textResult = "Obese";
            imageViewResult.setImageResource(R.drawable.obese_image);
        }
        textViewResult.setText(textResult);
    }
}
