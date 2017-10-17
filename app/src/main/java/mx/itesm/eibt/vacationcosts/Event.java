package mx.itesm.eibt.vacationcosts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by ethan on 12/10/2017.
 */

public class Event extends LinearLayout {
    private int id;
    private String name;
    private int cost;
    private String description;
    private String link;
    private TextView eName;
    private TextView eCost;
    private TextView eDescription;
    private TextView eLink;
    public Event(Context context, int id, String name, int cost, String description, String link) {
        super(context);
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.link = link;
        setStyle();
        createObjects(name, cost, description, link);
    }

    private void createObjects(String name, int cost, String description, final String link) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(10,20,10,20);

        eName = new TextView(getContext());
        setName(name);
        eName.setTextSize(20);
        eName.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        eName.setLayoutParams(params);
        addView(eName);

        eCost = new TextView(getContext());
        setCost(cost);
        eCost.setTextSize(20);
        eCost.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        eCost.setLayoutParams(params);
        addView(eCost);

        eDescription = new TextView(getContext());
        setDescription(description);
        eDescription.setTextSize(20);
        eDescription.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        eDescription.setHorizontallyScrolling(false);
        eDescription.setLayoutParams(params);
        addView(eDescription);

        eLink = new TextView(getContext());
        setLink(link);
        eLink.setTextSize(20);
        eLink.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        eLink.setLayoutParams(params);
        addView(eLink);

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
    public int getCost(){
        return this.cost;
    }
    public void setName(String name){
        this.name = name;
        eName.setText("Nombre: " + name);
    }
    public void setDescription(String description){
        this.description = description;
        eDescription.setText("Descripción:\n" + description);
    }

    public int getEventId() {
        return this.id;
    }

    public void setCost(int cost) {
        this.cost = cost;
        eCost.setText("Costo: $" + cost);
    }

    public void setLink(String link) {
        this.link = link;
        eLink.setText("Link: " + link);
    }

    public String getLink() {
        return link;
    }
}
