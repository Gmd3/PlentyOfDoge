package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-12-02.
 * The controller that shows the approved matches
 */
import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ApprovedMatchList extends ArrayAdapter<Match> {
    private final Activity context;
    private List<Match> matches;
    private List<Dog> dogs;

    public ApprovedMatchList(Activity context,  List<Match> matches, List<Dog> dogs) {
        super(context, R.layout.match_status_single, matches);
        this.context = context;
        this.matches = matches;
        this.dogs = dogs;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.match_status_single, null, true);

        TextView txtName = (TextView) rowView.findViewById(R.id.dog_simple_name);
        TextView txtStatus = (TextView) rowView.findViewById(R.id.matched_status);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        txtName.setText(dogs.get(position).name);

        String statusText = "     ";
        if (matches.get(position).matched == 1){
            statusText += "Matched!";
        } else statusText += "Pending...";

        txtStatus.setText(statusText);

        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageURI(Uri.parse(dogs.get(position).image));

        return rowView;
    }
}
