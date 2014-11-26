package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SimpleDogList extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    private List<Dog> dogs;

    public SimpleDogList(Activity context,
                      String[] web, Integer[] imageId, List<Dog> dogs) {
        super(context, R.layout.dog_single_simple, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
        this.dogs = dogs;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.dog_single_simple, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.dog_simple_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(dogs.get(position).breed);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}