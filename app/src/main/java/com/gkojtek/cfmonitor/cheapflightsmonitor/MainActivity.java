package com.gkojtek.cfmonitor.cheapflightsmonitor;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gkojtek.cfmonitor.cheapflightsmonitor.model.Flight;
import com.gkojtek.cfmonitor.cheapflightsmonitor.model.FlightDetails;

import net.danlew.android.joda.JodaTimeAndroid;

import org.apache.commons.collections4.ListUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.view.View.GONE;

public class MainActivity extends Activity {
    public static List<Opportunity> opportunities;
    public static int maxPrice;
    public static DecimalFormat df = new DecimalFormat("#.00");
    private final String czumaj = "czumaj";
    private ProgressBar progressBar;
    private EditText editText;
    private TextView infoText;
    private Button button;
    private LinearLayout editTextLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private JsonHandler jsonHandler;
    private ArrayList<String> opportunitiesToPrint;
    private List<Flight> outboundFlights;
    private List<Flight> inboundFlights;
    private boolean flightIsProcessedCurrently = false;
    private Flight currentOutboundFlight;
    private FlightDetails currentOutboundFlightDetails;
    private String currentFlightProcessedIata;
    private int currentNumberOfJsonsToProcess;
    //    private CircularProgressButton circularProgressButton;


    @Override
    public void onBackPressed() {
        if (recyclerView.getVisibility() == View.VISIBLE) {
            goBackToMenu();
            return;
        }
        super.onBackPressed();

    }

    private void goBackToMenu() {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JodaTimeAndroid.init(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        editTextLayout = (LinearLayout) findViewById(R.id.linear_layout);
        editText = (EditText) findViewById(R.id.edit_text);
        infoText = (TextView) findViewById(R.id.info);
        button = (Button) findViewById(R.id.ok_button);
        jsonHandler = new JsonHandler();
        opportunities = new ArrayList<>();
        opportunitiesToPrint = new ArrayList<>();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "wprowadź maksymalną cenę", Toast.LENGTH_SHORT).show();
                } else {
                    getWindow().setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                    );
                    maxPrice = Integer.parseInt(editText.getText().toString());
                    FlightPreferences outbound = new FlightPreferences("WMI", "2017-12-29", "2017-12-31", 270);
                    startAsyncTask(outbound);
                    button.setVisibility(GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    infoText.setVisibility(View.VISIBLE);
                    infoText.setText("Pobieranie danych o lotach...");
                }
            }
        });


//        circularProgressButton = (CircularProgressButton) findViewById(R.id.btnWithText);
//        circularProgressButton.setProgress((currentNumberOfJsonsToProcess / outboundFlights.size() - 1) * 100);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OpportunitiesAdapter(opportunitiesToPrint, getApplicationContext());
        recyclerView.setAdapter(adapter);

        for (String text : opportunitiesToPrint) {
            Toast.makeText(this, text + "1", Toast.LENGTH_SHORT).show();
        }
    }


    private void hideSearchMenu() {
        editTextLayout.setVisibility(GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void startAsyncTask(FlightPreferences outbound) {
        String jsonUrl = jsonHandler.getJsonUrl(outbound);
        AsyncTask jsonDownloader = new JsonDownloader().execute(jsonUrl);
    }


    private void createOutboundFlights(String json) {
        JsonHandler jsonHandler = new JsonHandler();
        outboundFlights = jsonHandler.getListOfFlights(json);
        currentNumberOfJsonsToProcess = outboundFlights.size() - 1;
        Log.d(czumaj, json);
        manageFlights(null);
    }

    private void manageFlights(String json) {
        int flightId;

        if (currentNumberOfJsonsToProcess > 0 && !flightIsProcessedCurrently) {
            currentOutboundFlight = outboundFlights.get(currentNumberOfJsonsToProcess);
            currentOutboundFlightDetails = currentOutboundFlight.getDetails();
            currentFlightProcessedIata = currentOutboundFlightDetails.getArrivalAirport().getIataCode();

            FlightPreferences inbound = new FlightPreferences(currentFlightProcessedIata, "2018-01-01", "2018-01-04", 270);
            String jsonUrl = jsonHandler.getJsonUrl(inbound);
            AsyncTask jsonDownloader = new JsonDownloader().execute(jsonUrl);
            flightIsProcessedCurrently = true;
        } else {

            List<Flight> inboundFlightsTemp = jsonHandler.getListOfFlights(json);
            if (inboundFlights == null) {
                inboundFlights = inboundFlightsTemp;
            } else {
                inboundFlights = ListUtils.union(inboundFlights, inboundFlightsTemp);
            }

            for (Flight flight : inboundFlights) {
                Log.d(czumaj, flight.getDetails().getDepartureAirport().getName() + currentNumberOfJsonsToProcess);
            }

            currentNumberOfJsonsToProcess--;
            flightIsProcessedCurrently = false;


        }

        if (currentNumberOfJsonsToProcess > 0 && !flightIsProcessedCurrently) {
            currentOutboundFlight = outboundFlights.get(currentNumberOfJsonsToProcess);
            currentOutboundFlightDetails = currentOutboundFlight.getDetails();
            currentFlightProcessedIata = currentOutboundFlightDetails.getArrivalAirport().getIataCode();

            FlightPreferences inbound = new FlightPreferences(currentFlightProcessedIata, "2018-01-01", "2018-01-09", 270);
            String jsonUrl = jsonHandler.getJsonUrl(inbound);
            AsyncTask jsonDownloader = new JsonDownloader().execute(jsonUrl);
            flightIsProcessedCurrently = true;
        }

        if (currentNumberOfJsonsToProcess == 0) {
            infoText.setText("Analizowanie lotów...");
            for (Flight outboundFlight : outboundFlights) {
                for (Flight inboundFlight : inboundFlights) {
                    Opportunity opportunity = new Opportunity(inboundFlight, outboundFlight);

                    if (opportunity.getArrivalCountryOutbound().equals(opportunity.getDepartureCountryInbound())) {
                        Log.d("opp dep", opportunity.getArrivalCountryOutbound() + ": " + opportunity.getPriceTotal());
                    }

                    if (opportunity.isGoodDeal()) {
                        opportunities.add(opportunity);
                    }
                }

            }

            Collections.sort(opportunities);

            for (Opportunity opportunity : opportunities) {
                String price = df.format(opportunity.getPriceTotal()) + " zł";
                opportunitiesToPrint.add(price);
            }

            for (Opportunity opportunity : opportunities) {

                Log.d(opportunity.getArrivalAirportOutbound(), opportunity.getDepartureDateOutbound() + " - " + opportunity.getDepartureDateInbound() + "; " + df.format(opportunity.getPriceTotal()) + " (" + opportunity.getPriceOutbound() + " + " + opportunity.getPriceInbound() + ")");
            }
            if (opportunities.size() != 0) {
                hideSearchMenu();
                recyclerView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            } else {
                progressBar.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);

                Toast.makeText(this, "Nie udało się znaleźć żadnych lotów", Toast.LENGTH_LONG).show();
                goBackToMenu();

            }

        }


//        for (Flight flight : inboundFlights) {
//
//
//            FlightDetails flightDetails = flight.getDetails();
//            flightId = flightDetails.hashCode();
//
//            String arrivalAirportName = flightDetails.getArrivalAirport().getName();
//            double price = flightDetails.getPrice().getValue();
//            String currency = flightDetails.getPrice().getCurrencySymbol();
//            String priceAndCurrency = price + " " + currency;
//
//            sendNotification(flightId, arrivalAirportName, priceAndCurrency);
//        }
    }

    public void sendNotification(int notificationId, String title, String text) {
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_local_airport_black_24dp)
                        .setContentTitle(title)
                        .setContentText(text);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification.build());
    }

    private void cannotDownloadData() {
        Toast.makeText(this, "Nie udało się pobrać danych", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(GONE);
        button.setVisibility(View.VISIBLE);
        infoText.setText("");
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
                    Log.v("CatalogClient", server_response);
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
            if (server_response != null) {

                if (outboundFlights == null) {
                    createOutboundFlights(server_response);
                } else {
                    manageFlights(server_response);
                }
            } else {
                cannotDownloadData();
            }

        }


    }
}
