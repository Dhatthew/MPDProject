package org.me.gcu.mpdproject;
/* Matthew Johnston S1824385 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class StackTraffic {

    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private long period;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "StackTraffic [title=" + title + ", description=" + description + "]";
    }

    public void setDates(String DescriptionWBR) {
        String[] tokens = DescriptionWBR.split(" <br />");

        for (String token : tokens) {

            if (token.contains("Start Date")) {
                String date = token.split("Start Date: ")[1];
                try {
                    this.startDate = new SimpleDateFormat("E, dd MMM yyyy - HH:mm", Locale.UK).parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (token.contains("End Date")) {
                String date = token.split("End Date: ")[1];
                try {
                    this.endDate = new SimpleDateFormat("E, dd MMM yyyy - HH:mm", Locale.UK).parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        if (this.startDate != null && this.endDate != null) {
            setPeriod();
        }

    }

    private void setPeriod() {
       long time = this.endDate.getTime() - this.startDate.getTime();
       this.period = TimeUnit.MILLISECONDS.toDays(time) % 365;
    }

    public long getPeriod() { return this.period; }
    public Date getStartDate() { return this.startDate; }
    public Date getEndDate() { return this.endDate; }
}
