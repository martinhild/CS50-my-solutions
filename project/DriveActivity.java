package com.example.sqltest;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.time.LocalDateTime;


public class DriveActivity extends AppCompatActivity {

    int hour, minute;
    String start, stop;
    EditText date_input, driver_input, km_start_input, km_stop_input, time_start_input, time_stop_input;
    Button drive_button, stop_button, save_button;
    ImageButton date_button, btn_switchToOcr_1 , btn_switchToOcr_2, timeButton_1, timeButton_2;
    ImageView animation;
    LocalDateTime now;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);

        // Buttons
        date_button = findViewById(R.id.date_button);
        drive_button = findViewById(R.id.drive_button);
        stop_button = findViewById(R.id.stop_button);
        save_button = findViewById(R.id.save_button);
        timeButton_1 = findViewById(R.id.timeButton_1);
        timeButton_2 = findViewById(R.id.timeButton_2);

        // User inputs
        date_input = findViewById(R.id.date_input);
        driver_input = findViewById(R.id.driver_input);
        km_start_input = findViewById(R.id.km_start_input);
        km_stop_input = findViewById(R.id.km_stop_input);
        time_start_input = findViewById(R.id.time_start_input);
        time_stop_input = findViewById(R.id.time_stop_input);

        // Animation
        animation = (findViewById(R.id.driveAnimation));
        animation.setVisibility(View.INVISIBLE);

        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

        setup_dateInput();
        setup_dateButton();
        setup_saveButton();
        setup_driveButton();
        setup_stop_button();
        setup_OcrButtons();
        setup_timeInputs();
        setup_timeButtons();
        getUserInputs();

//        if (isDriving()){
//            if (checkStartInputs()) {
//                drive();
//            }
//        }

        if (isDriving() && checkStartInputs()) {
            drive();
        }
    }

    private void setup_dateInput() {
        date_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date_input.setText(todaysDate());
            }
        });
    }

    private void setup_dateButton() {
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatePicker();
            }
        });
    }

    private void setup_saveButton() {
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(DriveActivity.this);

                if (checkStopInputs()) {
                    String start = km_start_input.getText().toString();
                    String stop = km_stop_input.getText().toString().trim();
                    int distance = Integer.parseInt(stop) - Integer.parseInt(start);
                    String distanceString = distance + " km";

                    myDB.addTrip(driver_input.getText().toString().trim(), distanceString,
                            time_start_input.getText().toString().trim(),
                            time_stop_input.getText().toString().trim(),
                            date_input.getText().toString().trim());

                    Intent  i = new Intent(DriveActivity.this, LogBookActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    private void setup_driveButton() {
        drive_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(DriveActivity.this);

                if (checkStartInputs()) {
                    drive();

                }
            }
        });
    }

    private void setup_stop_button() {
        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(DriveActivity.this);

                animation.setVisibility(View.INVISIBLE);
                stop_button.setVisibility(View.INVISIBLE);
                km_stop_input.setVisibility(View.VISIBLE);
                time_stop_input.setVisibility(View.VISIBLE);
                btn_switchToOcr_2.setVisibility(View.VISIBLE);

                drive_button.setVisibility(View.VISIBLE);
                timeButton_2.setVisibility(View.VISIBLE);
                save_button.setVisibility(View.VISIBLE);

                editor = sp.edit();
                editor.putString("driving","false");

            }
        });
    }

    private void setup_OcrButtons() {
        Bundle extras = new Bundle();
        extras.putString("EXTRA_BUTTON",null);

        btn_switchToOcr_1 = findViewById(R.id.btn_switchToOcr);
        btn_switchToOcr_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i = new Intent(DriveActivity.this, OcrActivity.class);
                extras.putString("EXTRA_BUTTON","button_1");
                i.putExtras(extras);
                startActivity(i);
                editor = sp.edit();
                editor.putString("driver", driver_input.getText().toString().trim());
                editor.putString("date", date_input.getText().toString().trim());
                editor.putString("startKm", km_start_input.getText().toString().trim());
                editor.putString("startTime", time_start_input.getText().toString().trim());
                editor.putString("stopKm", km_stop_input.getText().toString().trim());
                editor.putString("stopTime", time_stop_input.getText().toString().trim());
                editor.commit();
            }
        });
        btn_switchToOcr_2 = findViewById(R.id.btn_switchToOcr_2);
        btn_switchToOcr_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DriveActivity.this, OcrActivity.class);
                extras.putString("EXTRA_BUTTON","button_2");
                i.putExtras(extras);
                startActivity(i);
                editor = sp.edit();
                editor.putString("driver", driver_input.getText().toString().trim());
                editor.putString("date", date_input.getText().toString().trim());
                editor.putString("startKm", km_start_input.getText().toString().trim());
                editor.putString("startTime", time_start_input.getText().toString().trim());
                editor.putString("stopKm", km_stop_input.getText().toString().trim());
                editor.putString("stopTime", time_stop_input.getText().toString().trim());
                editor.commit();
            }
        });
    }

    private void setup_timeInputs() {
        time_start_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_start_input.setText(currentTime());
            }
        });
        time_stop_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_stop_input.setText(currentTime());
            }
        });
    }

    private void setup_timeButtons() {
        timeButton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                    {
                        hour = selectedHour;
                        minute = selectedMinute;
                        time_start_input.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(DriveActivity.this, /*skin,*/ onTimeSetListener, hour, minute, true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });

        timeButton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                    {
                        hour = selectedHour;
                        minute = selectedMinute;
                        time_stop_input.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(DriveActivity.this, /*skin,*/ onTimeSetListener, hour, minute, true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
    }

    private void getUserInputs() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String driverSave = sp.getString("driver", "");
            String dateSave = sp.getString("date", "");
            String startKmSave = sp.getString("startKm", "");
            String startTimeSave = sp.getString("startTime", "");
            String stopKmSave = sp.getString("stopKm", "");
            String stopTimeSave = sp.getString("stopTime", "");
            if (!driverSave.equals("")) {
                driver_input.setText(driverSave);
            }
            if (!dateSave.equals("")) {
                date_input.setText(driverSave);
            }
            if (!startKmSave.equals("")) {
                km_start_input.setText(startKmSave);
            }
            if (!startTimeSave.equals("")) {
                time_start_input.setText(startTimeSave);
            }
            if (!stopKmSave.equals("")) {
                km_stop_input.setText(stopKmSave);
            }
            if (!startTimeSave.equals("")) {
                time_stop_input.setText(stopTimeSave);
            }
        }
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = buildDate(day, month, year);
                date_input.setText(date);

            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        DatePickerDialog datePickerDialog;
        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private String todaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return buildDate(day, month, year);
    }

    private String buildDate(int day, int month, int year) {
        return  day + " " + translateMonth(month) + " " + year;
    }

    private String translateMonth(int month) {
        if (month == 1) {
            return "JAN";
        }
        if (month == 2) {
            return "FEB ";
        }
        if (month == 3) {
            return "MAR";
        }
        if (month == 4) {
            return "APR";
        }
        if (month == 5) {
            return "MAY";
        }
        if (month == 6) {
            return "JUN";
        }
        if (month == 7) {
            return "JUL";
        }
        if (month == 8) {
            return "AUG";
        }
        if (month == 9) {
            return "SEP";
        }
        if (month == 10) {
            return "OCT";
        }
        if (month == 11) {
            return "NOV";
        }
        if (month == 12) {
            return "DEC";
        }
        return "error";
    }

    private boolean isDriving() {
        return (sp.getString("driving", "").equals("true"));
    }

    private void drive() {
        start = km_start_input.getText().toString();
        stop = km_stop_input.getText().toString().trim();

        animation.setImageResource(R.drawable.turning_wheel_2);
        animation.setVisibility(View.VISIBLE);

        stop_button.setVisibility(View.VISIBLE);
        drive_button.setVisibility(View.INVISIBLE);
        km_stop_input.setVisibility(View.INVISIBLE);
        time_stop_input.setVisibility(View.INVISIBLE);
        btn_switchToOcr_2.setVisibility(View.INVISIBLE);
        timeButton_2.setVisibility(View.INVISIBLE);
        save_button.setVisibility(View.INVISIBLE);

        driver_input.setFocusable(false);
        driver_input.setCursorVisible(false);
        driver_input.setClickable(false);
        date_input.setFocusable(false);
        date_input.setCursorVisible(false);
        date_input.setClickable(false);
        date_button.setClickable(false);
        km_start_input.setFocusable(false);
        km_start_input.setCursorVisible(false);
        km_start_input.setClickable(false);
        btn_switchToOcr_1.setClickable(false);
        time_start_input.setFocusable(false);
        time_start_input.setCursorVisible(false);
        time_start_input.setClickable(false);
        timeButton_1.setClickable(false);

        driver_input.setEnabled(false);
        date_input.setEnabled(false);
        km_start_input.setEnabled(false);
        time_start_input.setEnabled(false);

        editor = sp.edit();
        editor.putString("driving","true");
    }

    private boolean checkStartInputs() {
        if (driver_input.getText().toString().equals("")){
            Toast.makeText(DriveActivity.this, "Driver missing", Toast.LENGTH_LONG).show();
            return false;
        } else if (km_start_input.getText().toString().equals("")) {
            Toast.makeText(DriveActivity.this, "Start km missing", Toast.LENGTH_LONG).show();
            return false;
        } else if (time_start_input.getText().toString().equals("")) {
            Toast.makeText(DriveActivity.this, "Start time missing", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean checkStopInputs() {
        if (driver_input.getText().toString().equals("")){
            Toast.makeText(DriveActivity.this, "Driver missing", Toast.LENGTH_LONG).show();
            return false;
        } else if (km_start_input.getText().toString().equals("")) {
            Toast.makeText(DriveActivity.this, "Start km missing", Toast.LENGTH_LONG).show();
            return false;
        } else if (km_stop_input.getText().toString().equals("")) {
            Toast.makeText(DriveActivity.this, "Stop km missing", Toast.LENGTH_LONG).show();
            return false;
        } else if (time_start_input.getText().toString().equals("")) {
            Toast.makeText(DriveActivity.this, "Start time missing", Toast.LENGTH_LONG).show();
            return false;
        } else if (time_stop_input.getText().toString().equals("")) {
            Toast.makeText(DriveActivity.this, "Stop time missing", Toast.LENGTH_LONG).show();
            return false;
        }

        String start = km_start_input.getText().toString();
        String stop = km_stop_input.getText().toString().trim();
        int distance = Integer.parseInt(stop) - Integer.parseInt(start);

        if (distance < 0) {
            Toast.makeText(DriveActivity.this, "Stop km can't be smaller than Start km - Check inputs.\"", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private String currentTime() {
        now = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("HH:mm");
        return now.format(pattern);
    }

}



