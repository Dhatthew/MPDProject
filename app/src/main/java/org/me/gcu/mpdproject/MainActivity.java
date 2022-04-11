package org.me.gcu.mpdproject;
/* Matthew Johnston S1824385 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerFragment.DatePickerFragmentListener, OnClickListener {

    private ActivityAdapter mAdapter;
    private SearchAdapter sAdapter;
    private DateAdapter dAdapter;
    private ListView TrafficList;
    private TextView searchTxt;
    private Button showPicker;
    private Button startButton;
    private Button worksButton;
    private Button plannedButton;
    private String text;
    private EditText search;
    private List<StackTraffic> filteredList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("StackTraffic", "OnCreate()");
        setContentView(R.layout.activity_main);
        plannedButton = (Button)findViewById(R.id.plannedButton);
        plannedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mAdapter = new ActivityAdapter(MainActivity.this, -1, TrafficXmlPullParser.getStackTrafficFromFile(MainActivity.this, "StackTraffic.xml"));
                TrafficList.setAdapter(mAdapter);
                Log.i("StackTraffic", "adapter size = "+ mAdapter.getCount());
            }
        });
        Log.e("MyTag","after startButton");
        searchTxt = (TextView)findViewById(R.id.searchTxt);
        search = (EditText)findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text = search.getText().toString();
                Log.i("typing", text);
                sAdapter = new SearchAdapter(SearchXmlPullParser.getStackTrafficFromFile(MainActivity.this), text);
                filteredList = sAdapter.getFilterList();
                mAdapter = new ActivityAdapter(MainActivity.this, -1, filteredList);
                TrafficList.setAdapter(mAdapter);
                Log.i("StackTraffic", "adapter size = "+ mAdapter.getCount());

            }
        });

        showPicker = (Button)findViewById(R.id.showPicker);
        showPicker.setOnClickListener(this);


        startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ActivityAdapter(MainActivity.this, -1, TrafficXmlPullParser.getStackTrafficFromFile(MainActivity.this, "StackIncident.xml"));
                TrafficList.setAdapter(mAdapter);
                Log.i("StackTraffic", "adapter size = "+ mAdapter.getCount());
            }
        });

        worksButton = (Button)findViewById(R.id.worksButton);
        worksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ActivityAdapter(MainActivity.this, -1, TrafficXmlPullParser.getStackTrafficFromFile(MainActivity.this, "StackWorks.xml"));
                TrafficList.setAdapter(mAdapter);
                Log.i("StackTraffic", "adapter size = "+ mAdapter.getCount());
            }
        });

        //Get reference to our ListView
        TrafficList = (ListView)findViewById(R.id.TrafficList);

        //Set the click listener to launch the browser when a row is clicked.


        /*
         * If network is available download the xml from the Internet.
         * If not then try to use the local file from last time.
         */
        Log.i("StackTraffic", "starting download Task");
        SitesDownloadTask download = new SitesDownloadTask();
        download.execute();

    }



@Override
    public void onClick(View view) {
            DatePickerFragment fragment = DatePickerFragment.newInstance(this);
            fragment.show(getSupportFragmentManager(), "datePicker");
}

    @Override
    public void onDateSet(Date date) {
        Log.e("dates", date.toString());
        dAdapter = new DateAdapter(DatesXMLPullParser.getStackTrafficFromFile(MainActivity.this), date);
        filteredList = dAdapter.getFilterList();
        mAdapter = new ActivityAdapter(MainActivity.this, -1, filteredList);
        TrafficList.setAdapter(mAdapter);
        Log.i("StackTraffic", "adapter size = "+ mAdapter.getCount());
    }

    /*
     * AsyncTask that will download the xml file for us and store it locally.
     * After the download is done we'll parse the local file.
     */
    private class SitesDownloadTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... arg0) {
            //Download the file
            try {
                Downloader.DownloadFromUrl("https://trafficscotland.org/rss/feeds/plannedroadworks.aspx", openFileOutput("StackTraffic.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d("Downloads", "Download failed");
            }

            try {
                Downloader.DownloadFromUrl("https://trafficscotland.org/rss/feeds/currentincidents.aspx", openFileOutput("StackIncident.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d("Downloads", "Download failed");
            }

            try {
                Downloader.DownloadFromUrl("https://trafficscotland.org/rss/feeds/roadworks.aspx", openFileOutput("StackWorks.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d("Downloads", "Download failed");
            }


            return null;
        }

    }

}