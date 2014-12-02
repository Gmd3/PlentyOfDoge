package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-12-01.
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

public class MatchList extends ArrayAdapter<Owner> {
    private final Activity context;
    private List<Dog> dogs;
    private List<Owner> owners;

    public MatchList(Activity context,  List<Dog> dogs, List<Owner> owners) {
        super(context, R.layout.match_single, owners);
        this.context = context;
        this.dogs = dogs;
        this.owners = owners;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.match_single, null, true);

        TextView txtName = (TextView) rowView.findViewById(R.id.dog_simple_name);
        TextView txtOwner = (TextView) rowView.findViewById(R.id.owner);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        txtName.setText(dogs.get(position).name);
        txtOwner.setText(owners.get(position).firstName + " " + owners.get(position).lastName);

        imageView.setImageURI(Uri.parse(dogs.get(position).image));

        return rowView;
    }
}