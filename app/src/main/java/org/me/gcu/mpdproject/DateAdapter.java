package org.me.gcu.mpdproject;

/* Matthew Johnston S1824385 */

import android.util.Log;

import androidx.fragment.app.DialogFragment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateAdapter {

    private Date date;
    private List<StackTraffic> filteredList = new ArrayList<>();

    public DateAdapter(java.util.List<StackTraffic> traffic, Date date) {
        this.date = date;
        for (StackTraffic i: traffic) {

            if(i.getEndDate().after(date) && i.getStartDate().before(date)) {
                filteredList.add(i);
            }

        }

    }

    public List<StackTraffic> getFilterList() { return filteredList;}
}

