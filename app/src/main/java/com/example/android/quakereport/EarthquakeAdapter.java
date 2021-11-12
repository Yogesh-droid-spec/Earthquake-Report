package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter<E> extends ArrayAdapter<Earthquake> {

    private String primaryLocation;
    private String LocationOffset;
    private static final String LOCATION_SEPARATOR = " of ";

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int Magnitudefloor = (int) Math.floor(magnitude);
        switch (Magnitudefloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
       return   ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMag(double mag){
        DecimalFormat formatter = new DecimalFormat("0.00");
        return formatter.format(mag);
    }


    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        String location = currentEarthquake.getMlocation();

        if (location.contains(LOCATION_SEPARATOR)) {
            String[] parts = location.split(LOCATION_SEPARATOR);
            LocationOffset=parts[0]+LOCATION_SEPARATOR;
            primaryLocation=parts[1];
        } else {
          LocationOffset= "Near the";
          primaryLocation=location;
        }

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView magTextView = (TextView) listItemView.findViewById(R.id.magview);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView

        String formattedmag = formatMag(currentEarthquake.getMag());
        magTextView.setText(formattedmag);

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView  offsetlocationTextView = (TextView) listItemView.findViewById(R.id.locationoffset);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        offsetlocationTextView.setText(LocationOffset);

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView primarylocationTextView = (TextView) listItemView.findViewById(R.id.primarylocation);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        primarylocationTextView.setText(primaryLocation);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getmTimeinmillisec());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.dateview);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.timeview);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);



        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
