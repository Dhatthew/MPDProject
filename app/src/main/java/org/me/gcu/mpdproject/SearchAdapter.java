package org.me.gcu.mpdproject;
/* Matthew Johnston S1824385 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
 * Custom Adapter class that is responsible for holding the list of sites after they
 * get parsed out of XML and building row views to display them on the screen.
 */
public class SearchAdapter  {

    private String text;
    private List<StackTraffic> filteredList = new ArrayList<>();

    public SearchAdapter(List<StackTraffic> traffic, String text) {
        this.text = text;

        for (StackTraffic i: traffic) {

            if (i.getTitle().toLowerCase().contains(text)) {
                filteredList.add(i);
            }

        }

    }

    public List<StackTraffic> getFilterList() { return filteredList;}
}



