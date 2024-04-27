package project.java;

import android.database.Cursor;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import androidx.annotation.Nullable;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "LogbookLibrary";
    private static final int  DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DISTANCE = "distance";
    private static final String COLUMN_DRIVER = "driver";
    private static final String COLUMN_START_TIME = "start_time";
    private static final String COLUMN_STOP_TIME = "stop_time";
    private static final String COLUMN_DATE = "date";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_DRIVER + " TEXT, " +
                COLUMN_DISTANCE + " TEXT, " +
                COLUMN_START_TIME + " TEXT, " +
                COLUMN_STOP_TIME + " TEXT, " +
                COLUMN_DATE + " TEXT)" +";";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addTrip(String driver, String distance, String start_time, String stop_time, String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DRIVER,driver);
        cv.put(COLUMN_DISTANCE,distance);
        cv.put(COLUMN_START_TIME,start_time);
        cv.put(COLUMN_STOP_TIME,stop_time);
        cv.put(COLUMN_DATE,date);
        long result = db.insert(TABLE_NAME, null,cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        // SQL query
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return  cursor;
    }

    Cursor readPersonsData(String name) {
        String string = "\"" + name + "\"";
        // SQL query
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE driver = " + string;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return  cursor;
    }

}
