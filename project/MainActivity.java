package com.example.sqltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button driveButton, logbookButton, filterButton, optionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupDriveButton();
        setupLogbookButton();
        setupFilterButton();
        setupOptionsButton();

    }

    private void setupDriveButton() {
        driveButton = findViewById(R.id.btn_drive);
        driveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DriveActivity.class);
                startActivity(i);
            }
        });
    }

    private void setupLogbookButton() {
        logbookButton = findViewById(R.id.btn_logbook);
        logbookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LogBookActivity.class);
                startActivity(i);
            }
        });
    }

    private void setupFilterButton() {
        filterButton = findViewById(R.id.btn_filter);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FilterActivity.class);
                startActivity(i);
            }
        });
    }

    private void setupOptionsButton() {
        optionsButton = findViewById(R.id.btn_options);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, OptionsActivity.class);
                startActivity(i);
            }
        });
    }

}
