package com.example.elearningpemrogramanmobile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText dateEditText = findViewById(R.id.date_edit_text);
        dateEditText.setOnClickListener(v -> showDatePickerDialog());

        Button showAlertButton = findViewById(R.id.show_alert_button);
        showAlertButton.setEnabled(false);

        showAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDate = dateEditText.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ini contoh Alert");
                builder.setMessage("Kamu memilih tanggal: " +selectedDate);
                builder.setPositiveButton("OK", (dialog, which) -> {
                    Toast.makeText(MainActivity.this, "Kamu mengklik OK", Toast.LENGTH_SHORT).show();
                });
                builder.setNegativeButton("Cancel", (dialog, which) -> {
                    Toast.makeText(MainActivity.this, "Kamu mengklik Cancel", Toast.LENGTH_SHORT).show();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void showDatePickerDialog() {
        Button showAlertButton = findViewById(R.id.show_alert_button);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                    EditText dateEditText = findViewById(R.id.date_edit_text);
                    dateEditText.setText(selectedDate);
                    showAlertButton.setEnabled(true);
                },
                year, month, day);
        showAlertButton.setEnabled(false);
        datePickerDialog.show();
    }
}