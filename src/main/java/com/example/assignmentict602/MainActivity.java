package com.example.assignmentict602;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText unitsEditText;
    private Button calculateButton;
    private TextView resultTextView;
    private ImageView helpIcon;
    private Button aboutUsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitsEditText = findViewById(R.id.unitsEditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        helpIcon = findViewById(R.id.helpIcon);
        aboutUsButton = findViewById(R.id.aboutUsButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });

        helpIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelpDialog();
            }
        });

        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAboutUs();
            }
        });
    }

    private void calculateBill() {
        String unitsStr = unitsEditText.getText().toString().trim();

        if (unitsStr.isEmpty()) {
            Toast.makeText(this, "Please enter the number of units", Toast.LENGTH_SHORT).show();
            return;
        }

        double units = Double.parseDouble(unitsStr);
        double totalCharges;

        if (units <= 200) {
            totalCharges = units * 0.218;
        } else if (units <= 300) {
            totalCharges = (200 * 0.218) + ((units - 200) * 0.334);
        } else if (units <= 600) {
            totalCharges = (200 * 0.218) + (100 * 0.334) + ((units - 300) * 0.516);
        } else {
            totalCharges = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((units - 600) * 0.546);
        }

        resultTextView.setText(String.format("Total Charges: RM %.2f", totalCharges));
    }

    public void showHelpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Help");
        builder.setMessage("Enter the number of units consumed and click Calculate to determine the total charges.");
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    private void navigateToAboutUs() {
        Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
        startActivity(intent);
    }
}
