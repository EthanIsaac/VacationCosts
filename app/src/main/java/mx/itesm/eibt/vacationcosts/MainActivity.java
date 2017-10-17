package mx.itesm.eibt.vacationcosts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    DataBase db;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBase(getApplicationContext());
        loadVacations();
        Button addNewVacation = (Button)findViewById(R.id.addNewVacation);
        addNewVacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog d = onCreateDialog();
                d.show();

            }
        });

    }

    private void loadVacations() {
        LinearLayout c = (LinearLayout)findViewById(R.id.container);
        c.removeAllViews();
        Cursor vacations = db.selectAllFromVacation();
        if(vacations.moveToFirst())
        {
            do {
                final Vacation v = new Vacation(this, vacations.getString(1), vacations.getString(2), vacations.getInt(0));
                Button delete = (Button)v.getChildAt(v.getChildCount()-1);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.deleteFromVacacion(v.getVacationId());
                        loadVacations();
                    }
                });
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showEvents(v.getVacationId());
                    }
                });
                c.addView(v);
            }while(vacations.moveToNext());
        }
    }

    private void showEvents(int id) {
        Intent intent = new Intent(this, ShowEvents.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public Dialog onCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_addnewvacation, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText place = (EditText) dialogView.findViewById(R.id.place);
                        EditText date = (EditText) dialogView.findViewById(R.id.date);
                        db.insertIntoVacation(place.getText().toString(),date.getText().toString());
                        loadVacations();

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }
}

