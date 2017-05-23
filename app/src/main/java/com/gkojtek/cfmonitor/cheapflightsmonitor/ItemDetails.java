package com.gkojtek.cfmonitor.cheapflightsmonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ItemDetails extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detaills);

        Intent intent = getIntent();
        int position = intent.getIntExtra(OpportunitiesAdapter.POSITION, 0);
        String text = MainActivity.opportunities.get(position).getArrivalAirportOutbound() + ": " + MainActivity.opportunities.get(position).getDepartureDateOutbound() + " - " + MainActivity.opportunities.get(position).getDepartureDateInbound();
        textView = (TextView) findViewById(R.id.text_view);
        textView.setText(text);
        String country = null;
        try {
            country = URLEncoder.encode(MainActivity.opportunities.get(position).getArrivalCountryOutbound(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String baseUrl = "https://pl.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";
        String jsonUrl = baseUrl + country;
        AsyncTask jsonDownloader = new JsonDownloader().execute(jsonUrl);


    }

    private void show(String server_response) {
        textView.setText(server_response);
    }

    public class JsonDownloader extends AsyncTask<String, Void, String> {
        String server_response;

        @Override
        protected String doInBackground(String... strings) {

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int responseCode = urlConnection.getResponseCode();


                if (responseCode == HttpURLConnection.HTTP_OK) {
                    server_response = StreamReader.read(urlConnection.getInputStream());
                    byte server_response_bytes[] = server_response.getBytes();
                    String text = new String(server_response_bytes, "UTF-8");
                    Log.v("CatalogClient", text);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.e("Response", "" + server_response);


            show(server_response);


        }


    }
}
