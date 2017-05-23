package com.gkojtek.cfmonitor.cheapflightsmonitor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import static com.gkojtek.cfmonitor.cheapflightsmonitor.MainActivity.df;

public class ItemDetails2 extends Activity {

    protected ImageView image;
    protected TextView priceText;
    protected TextView countryText;
    protected TextView airportText;
    protected TextView dateOutbound;
    protected TextView dateInbound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opportunity);

        Intent intent = getIntent();
        int position = intent.getIntExtra(OpportunitiesAdapter.POSITION, 0);
        String text = MainActivity.opportunities.get(position).getArrivalAirportOutbound() + ": " + MainActivity.opportunities.get(position).getDepartureDateOutbound() + " - " + MainActivity.opportunities.get(position).getDepartureDateInbound();


        image = (ImageView) findViewById(R.id.image_id);
        priceText = (TextView) findViewById(R.id.price_text);
        countryText = (TextView) findViewById(R.id.country);
        airportText = (TextView) findViewById(R.id.airport);
        priceText = (TextView) findViewById(R.id.price_text);
        dateOutbound = (TextView) findViewById(R.id.departure_date_outbound);
        dateInbound = (TextView) findViewById(R.id.departure_date_inbound);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        priceText.setTextColor(color);

        countryText.setText(MainActivity.opportunities.get(position).getArrivalCountryOutbound());
        airportText.setText(MainActivity.opportunities.get(position).getArrivalAirportOutbound());

        image.setImageResource(R.drawable.ic_local_airport_black_24dp);
        priceText.setText(String.valueOf(df.format(MainActivity.opportunities.get(position).getPriceTotal())));
        dateOutbound.setText(MainActivity.opportunities.get(position).getDepartureDateOutbound());
        dateInbound.setText(MainActivity.opportunities.get(position).getDepartureDateInbound());

    }

}

