package mx.itesm.eibt.vacationcosts;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBase db = new DataBase(getApplicationContext());
        //db.insertIntoVacation("Cuerna","Diciembre");
        //Log.d("INSERT_VACATION", "POSSIBLE");
        Cursor cursor = db.selectAllFromVacation();
        //Log.d("GOT",cursor.getString(1));
    }
}
