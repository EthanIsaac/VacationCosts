package mx.itesm.eibt.vacationcosts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ethan on 12/10/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "vacationCosts.db";

    public static final String TABLE_VACATION = "vacations";
    public static final String COLUMN_VACATION_ID = "id";
    public static final String COLUMN_VACATION_PLACE = "place";
    public static final String COLUMN_VACATION_DATE = "date";

    public static final String TABLE_EVENT = "events";
    public static final String COLUMN_EVENT_ID = "id";
    public static final String COLUMN_EVENT_ID_VACATION = "id_vacation";
    public static final String COLUMN_EVENT_NAME = "name";
    public static final String COLUMN_EVENT_COST = "cost";
    public static final String COLUMN_EVENT_DESCRIPTION = "description";
    public static final String COLUMN_EVENT_LINK = "link";

    private static final String SQL_CREATE_TABLE_VACATION = "CREATE TABLE "
            + TABLE_VACATION + "(" + COLUMN_VACATION_ID
            + " integer PRIMARY KEY AUTOINCREMENT, " + COLUMN_VACATION_PLACE
            + " text NOT NULL, " + COLUMN_VACATION_DATE
            + " text NOT NULL);";

    private static final String SQL_CREATE_TABLE_EVENT = "CREATE TABLE "
            + TABLE_EVENT + "(" + COLUMN_EVENT_ID
            + " integer PRIMARY KEY AUTOINCREMENT, " + COLUMN_EVENT_ID_VACATION
            + " integer, " + COLUMN_EVENT_NAME
            + " text NOT NULL, " + COLUMN_EVENT_COST
            + " integer, " + COLUMN_EVENT_DESCRIPTION
            + " text, " + COLUMN_EVENT_LINK
            + " text, FOREIGN KEY (" + COLUMN_EVENT_ID_VACATION + ") REFERENCES " + TABLE_VACATION + "("
            + COLUMN_VACATION_ID + ") ON DELETE CASCADE ON UPDATE CASCADE);";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean deleteAllFromVacacion()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete(TABLE_VACATION,
                    null,
                    null);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteFromVacacion(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete(TABLE_VACATION,
                    " id = ?",
                    new String[] {String.valueOf(id)});
            return true;

        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteFromEvent(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete(TABLE_EVENT,
                    " id = ?",
                    new String[] {String.valueOf(id)});
            return true;

        }catch (Exception e){
            return false;
        }
    }

    public void updateVacation(int id, String place, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VACATION_PLACE, place);
        values.put(COLUMN_VACATION_DATE, date);

        int i = db.update(TABLE_VACATION,
                values,
                " id = ?",
                new String[] {String.valueOf(id)});
    }

    public void updateEvent(int id, String name, int cost, String description, String link)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_NAME, name);
        values.put(COLUMN_EVENT_COST, cost);
        values.put(COLUMN_EVENT_DESCRIPTION, description);
        values.put(COLUMN_EVENT_LINK, link);

        int i = db.update(TABLE_EVENT,
                values,
                " id = ?",
                new String[] {String.valueOf(id)});
    }

    public void insertIntoVacation(String place, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VACATION_PLACE, place);
        values.put(COLUMN_VACATION_DATE, date);
        db.insert(TABLE_VACATION, null, values);
    }

    public void insertIntoEvent(int id_vacation, String name, int cost, String description, String link)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_ID_VACATION, id_vacation);
        values.put(COLUMN_EVENT_NAME, name);
        values.put(COLUMN_EVENT_COST, cost);
        values.put(COLUMN_EVENT_DESCRIPTION, description);
        values.put(COLUMN_EVENT_LINK, link);
        db.insert(TABLE_EVENT, null, values);
    }

    public Cursor selectAllFromVacation()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_VACATION_ID, COLUMN_VACATION_PLACE, COLUMN_VACATION_DATE};

        Cursor cursor = db.query(TABLE_VACATION,
                projection,
                null,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }

    public Cursor selectFromEvent(int id_vacation)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_EVENT_ID, COLUMN_EVENT_ID_VACATION, COLUMN_EVENT_NAME, COLUMN_EVENT_COST, COLUMN_EVENT_DESCRIPTION, COLUMN_EVENT_LINK};

        Cursor cursor = db.query(TABLE_EVENT,
                projection,
                " id_vacation = ?",
                new String[]{String.valueOf(id_vacation)},
                null,
                null,
                null,
                null);
        if(cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_VACATION);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_EVENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
