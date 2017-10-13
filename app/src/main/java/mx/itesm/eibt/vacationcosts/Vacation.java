package mx.itesm.eibt.vacationcosts;

import android.content.Context;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ethan on 12/10/2017.
 */

public class Vacation extends LinearLayout {
    private String name;
    private String date;
    public Vacation(Context context, String name, String date) {
        super(context);
        this.name = name;
        this.date = date;
        setStyle();
        loadInfo(name, date);
    }

    private void loadInfo(String name, String date) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(900,100);

        TextView eName = new TextView(getContext());
        eName.setText("Lugar: " + name);
        eName.setTextSize(20);
        eName.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        eName.setLayoutParams(params);
        addView(eName);

        TextView eDate = new TextView(getContext());
        eDate.setText("Fecha: " + date);
        eDate.setTextSize(20);
        eDate.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        eDate.setLayoutParams(params);
        addView(eDate);

    }

    private void setStyle() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutParams params = new LayoutParams(900,250);
        params.setMargins(0,0,25,25);
        setLayoutParams(params);
        setBackgroundResource(R.drawable.border);
    }
    public String getName(){
        return this.name;
    }
    public String getDate(){
        return this.date;
    }
}
