package mx.itesm.eibt.vacationcosts;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ethan on 12/10/2017.
 */

public class Vacation extends LinearLayout {
    private int id;
    private String name;
    private String date;
    private TextView eName;
    private TextView eDate;
    public Vacation(Context context, String name, String date, int id) {
        super(context);
        this.id = id;
        setStyle();
        createObjects(name, date);
    }

    private void createObjects(String name, String date) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(10,20,10,20);

        eName = new TextView(getContext());
        setName(name);
        eName.setTextSize(20);
        eName.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        eName.setLayoutParams(params);
        addView(eName);

        eDate = new TextView(getContext());
        setDate(date);
        eDate.setTextSize(20);
        eDate.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        eDate.setLayoutParams(params);
        addView(eDate);

        LayoutParams buttonParams = new LayoutParams(300,100);
        buttonParams.setMargins(10,25,10,25);
        Button delete = new Button(getContext());
        delete.setLayoutParams(buttonParams);
        delete.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        delete.setTextColor(Color.WHITE);
        delete.setText("Eliminar");
        addView(delete);

    }

    private void setStyle() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(20,20,20,20);
        setLayoutParams(params);
        setBackgroundResource(R.drawable.border);
    }
    public String getName(){
        return this.name;
    }
    public String getDate(){
        return this.date;
    }
    public void setName(String name){
        this.name = name;
        eName.setText("Lugar: " + name);
    }
    public void setDate(String date){
        this.date = date;
        eDate.setText("Fecha: " + date);
    }

    public int getVacationId() {
        return this.id;
    }
}
