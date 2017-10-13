package mx.itesm.eibt.vacationcosts;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout c = (LinearLayout)findViewById(R.id.container);
        DataBase db = new DataBase(getApplicationContext());
        Cursor vacations = db.selectAllFromVacation();
        if(vacations!=null)
        {
            vacations.moveToFirst();
            do {
                Vacation v = new Vacation(this, vacations.getString(1), vacations.getString(2));
                c.addView(v);
            }while(vacations.moveToNext());
        }
    }
}
