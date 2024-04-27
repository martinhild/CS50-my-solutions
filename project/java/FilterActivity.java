package project.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;


public class FilterActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button filterButton;
    MyDatabaseHelper myDB;
    ArrayList<String> trip_id, trip_distance, trip_driver, trip_start_time, trip_stop_time, trip_date;
    CustomAdapter customAdapter;
    EditText nameInput;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        myDB = new MyDatabaseHelper(FilterActivity.this);
        trip_id = new ArrayList<>();
        trip_distance = new ArrayList<>();
        trip_driver = new ArrayList<>();
        trip_start_time = new ArrayList<>();
        trip_stop_time = new ArrayList<>();
        trip_date = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        nameInput = findViewById(R.id.name_id);

        setup_okButton();
    }

    private void setup_okButton() {
        filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString().trim();
                storeDataInArrays();
                customAdapter = new CustomAdapter(FilterActivity.this, trip_id, trip_distance, trip_driver,
                        trip_start_time, trip_stop_time, trip_date);
                recyclerView.setAdapter(customAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(FilterActivity.this));
            }
        });
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readPersonsData(name);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
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
