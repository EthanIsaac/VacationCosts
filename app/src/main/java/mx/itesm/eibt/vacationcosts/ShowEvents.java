package mx.itesm.eibt.vacationcosts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowEvents extends AppCompatActivity {
    DataBase db;
    int costoTotal;
    int costoRestante;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);
        db = new DataBase(getApplicationContext());
        loadEvents();
        Button addNewVacation = (Button)findViewById(R.id.addNewEvent);
        addNewVacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog d = onCreateDialog();
                d.show();

            }
        });
    }
    private void loadEvents() {
        LinearLayout c = (LinearLayout)findViewById(R.id.container);
        c.removeAllViews();
        final Cursor events = db.selectFromEvent(getIntent().getIntExtra("id",0));
        costoTotal = 0;
        costoRestante = 0;
        if(events.moveToFirst())
        {
            do {
                final Event e = new Event(this, events.getInt(0), events.getString(2), events.getInt(3), events.getString(4), events.getString(5), events.getInt(6));
                e.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(e.isPaid())
                        {
                            e.setBackgroundColor(Color.WHITE);
                            e.setPaid(false);
                            db.unpayEvent(e.getEventId());
                            loadEvents();
                        }
                        else
                        {
                            e.setBackgroundColor(ContextCompat.getColor(e.getContext(),R.color.colorLight));
                            e.setPaid(true);
                            db.payEvent(e.getEventId());
                            loadEvents();
                        }
                    }
                });
                Button delete = (Button)e.getChildAt(e.getChildCount()-1);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.deleteFromEvent(e.getEventId());
                        loadEvents();
                    }
                });
                TextView linkTV = (TextView)e.getChildAt(e.getChildCount()-2);
                linkTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String text;
                        text = e.getLink();
                        openLink(text);
                        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        myClip = ClipData.newPlainText("text", text);
                        myClipboard.setPrimaryClip(myClip);
                    }
                });
                c.addView(e);
                costoTotal+=events.getInt(3);
                if(!e.isPaid())
                {
                    costoRestante+=events.getInt(3);
                }
            }while(events.moveToNext());
        }
        TextView costo = (TextView)findViewById(R.id.costoTotal);
        costo.setText("$"+costoTotal);
        TextView restante = (TextView)findViewById(R.id.costoRestante);
        restante.setText("$"+costoRestante);
    }
    public Dialog onCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_addnewevent, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        int id_vacation = getIntent().getIntExtra("id",0);
                        EditText name = (EditText) dialogView.findViewById(R.id.name);
                        EditText cost = (EditText) dialogView.findViewById(R.id.cost);
                        EditText description = (EditText) dialogView.findViewById(R.id.description);
                        EditText link = (EditText) dialogView.findViewById(R.id.link);
                        db.insertIntoEvent(id_vacation,name.getText().toString(),Integer.parseInt(cost.getText().toString()),description.getText().toString(),link.getText().toString());
                        loadEvents();

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    public void openLink(String link) {
        String linkToFollow;
        if(link.toLowerCase().startsWith("http://")||link.toLowerCase().startsWith("https://"))
        {
            linkToFollow=link;
        }
        else
        {
            linkToFollow="http://"+link;
        }
        Uri uri = Uri.parse(linkToFollow);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
