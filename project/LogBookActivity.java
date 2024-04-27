package com.example.sqltest;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import android.content.Intent;
import android.database.Cursor;


public class LogBookActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<String> trip_id, trip_distance, trip_driver, trip_start_time, trip_stop_time, trip_date;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        myDB = new MyDatabaseHelper(LogBookActivity.this);
        trip_id = new ArrayList<>();
        trip_distance = new ArrayList<>();
        trip_driver = new ArrayList<>();
        trip_start_time = new ArrayList<>();
        trip_stop_time = new ArrayList<>();
        trip_date = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);

        storeDataInArrays();

        customAdapter = new CustomAdapter(LogBookActivity.this, trip_id, trip_distance, trip_driver,
                trip_start_time, trip_stop_time, trip_date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(LogBookActivity.this));

    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                trip_id.add(cursor.getString(0));
                trip_distance.add(cursor.getString(1));
                trip_driver.add(cursor.getString(2));
                trip_start_time.add(cursor.getString(3));
                trip_stop_time.add(cursor.getString(4));
                trip_date.add(cursor.getString(5));
            }
        }
    }
}
