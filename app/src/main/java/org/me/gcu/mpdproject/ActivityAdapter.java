package org.me.gcu.mpdproject;
/* Matthew Johnston S1824385 */

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;

/*
 * Custom Adapter class that is responsible for holding the list of sites after they
 * get parsed out of XML and building row views to display them on the screen.
 */
public class ActivityAdapter extends ArrayAdapter<StackTraffic> {


    public ActivityAdapter(Context ctx, int textViewResourceId, List<StackTraffic> sites) {
        super(ctx, textViewResourceId, sites);

    }

    /*
     * (non-Javadoc)
     * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
     *
     * This method is responsible for creating row views out of a StackTraffic object that can be put
     * into our ListView
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        RelativeLayout row = (RelativeLayout)convertView;
        Log.i("StackTraffic", "getView pos = " + pos);
        if(null == row){
            //No recycled View, we have to inflate one.
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = (RelativeLayout)inflater.inflate(R.layout.row_site, null);
        }

        //Get our View References
        TextView titleTxt = (TextView)row.findViewById(R.id.titleTxt);
        TextView descriptionTxt = (TextView)row.findViewById(R.id.descriptionTxt);
        TextView periodTxt = (TextView)row.findViewById(R.id.periodTxt);
        Button showButton = (Button)row.findViewById(R.id.showButton);
        View orange = (View)row.findViewById(R.id.Orange);
        View red = (View)row.findViewById(R.id.Red);
        View green = (View)row.findViewById(R.id.Green);

        titleTxt.setText(getItem(pos).getTitle());
        descriptionTxt.setText(getItem(pos).getDescription());
        descriptionTxt.setVisibility(View.INVISIBLE);
        periodTxt.setText("This shall be happening for " + getItem(pos).getPeriod() + " days");
        periodTxt.setVisibility(View.INVISIBLE);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descriptionTxt.setVisibility(View.VISIBLE);
                if (getItem(pos).getPeriod() >= 1) {
                    periodTxt.setVisibility(View.VISIBLE);
                }
            }
        });

        return row;

    }

}