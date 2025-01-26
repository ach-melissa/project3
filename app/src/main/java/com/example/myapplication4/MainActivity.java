package com.example.myapplication4;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etAmount;
    private RadioGroup rgConversionDirection;
    private RadioButton rbEUROToDZ, rbDZToEURO;
    private Button btnConvert;
    private TextView tvResult;

    // Conversion rates (example rates, adjust as needed)
    private static final double DZ_TO_EURO_RATE = 83.0;
    private static final double EURO_TO_DZ_RATE = 1 / DZ_TO_EURO_RATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etAmount = findViewById(R.id.etAmount);
        rgConversionDirection = findViewById(R.id.rgConversionDirection);
        rbEUROToDZ = findViewById(R.id.rbInrToUsd);
        rbDZToEURO = findViewById(R.id.rbUsdToInr);
        btnConvert = findViewById(R.id.btnConvert);
        tvResult = findViewById(R.id.tvResult);

        // Set button click listener
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        // Get the amount input
        String amountStr = etAmount.getText().toString();

        if (TextUtils.isEmpty(amountStr)) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);

        // Determine the conversion direction
        int selectedId = rgConversionDirection.getCheckedRadioButtonId();
        if (selectedId == R.id.rbInrToUsd) {
            // Convert INR to USD
            double result = amount * EURO_TO_DZ_RATE;
            tvResult.setText(String.format("₹%.2f = $%.2f", amount, result));
        } else if (selectedId == R.id.rbUsdToInr) {
            // Convert USD to INR
            double result = amount * DZ_TO_EURO_RATE;
            tvResult.setText(String.format("%.2f da = %.2f $", amount, result));
        } else {
            Toast.makeText(this, "Please select a conversion direction", Toast.LENGTH_SHORT).show();
        }
    }
}







