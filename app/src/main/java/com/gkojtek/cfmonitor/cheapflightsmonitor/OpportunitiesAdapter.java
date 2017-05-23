package com.gkojtek.cfmonitor.cheapflightsmonitor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class OpportunitiesAdapter extends RecyclerView.Adapter<OpportunitiesAdapter.OpportunitiesViewHolder> {
    public static final String POSITION = "position";
    ArrayList<String> opportunitiesList;
    private int lastPosition = -1;

    public OpportunitiesAdapter(ArrayList<String> opportunitiesList, Context context) {
        this.opportunitiesList = opportunitiesList;
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }

    @Override
    public OpportunitiesAdapter.OpportunitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.opportunity, parent, false);
        OpportunitiesViewHolder viewHolder = new OpportunitiesViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OpportunitiesAdapter.OpportunitiesViewHolder holder, int position) {

        holder.countryText.setText(MainActivity.opportunities.get(position).getArrivalCountryOutbound());
        holder.airportText.setText(MainActivity.opportunities.get(position).getArrivalAirportOutbound());

        holder.image.setImageResource(R.drawable.ic_local_airport_black_24dp);
        holder.priceText.setText(opportunitiesList.get(position));
        holder.dateOutbound.setText(MainActivity.opportunities.get(position).getDepartureDateOutbound());
        holder.dateInbound.setText(MainActivity.opportunities.get(position).getDepartureDateInbound());
        setAnimation(holder.itemView, position);

    }


    @Override
    public int getItemCount() {
        return opportunitiesList.size();
    }


    public static class OpportunitiesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView image;
        protected TextView priceText;
        protected TextView countryText;
        protected TextView airportText;
        protected TextView dateOutbound;
        protected TextView dateInbound;

        public OpportunitiesViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            image = (ImageView) itemView.findViewById(R.id.image_id);
            priceText = (TextView) itemView.findViewById(R.id.price_text);
            countryText = (TextView) itemView.findViewById(R.id.country);
            airportText = (TextView) itemView.findViewById(R.id.airport);
            priceText = (TextView) itemView.findViewById(R.id.price_text);
            dateOutbound = (TextView) itemView.findViewById(R.id.departure_date_outbound);
            dateInbound = (TextView) itemView.findViewById(R.id.departure_date_inbound);
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            priceText.setTextColor(color);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ItemDetails2.class);
            intent.putExtra(POSITION, getAdapterPosition());
            v.getContext().startActivity(intent);
        }
    }
}