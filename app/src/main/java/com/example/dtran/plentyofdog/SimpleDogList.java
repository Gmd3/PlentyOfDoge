package com.example.dtran.plentyofdog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SimpleDogList extends ArrayAdapter<Dog>{
    private final Activity context;
    private final Integer[] imageId;
    private List<Dog> dogs;

    public SimpleDogList(Activity context, Integer[] imageId, List<Dog> dogs) {
        super(context, R.layout.dog_single_simple, dogs);
        this.context = context;
        this.imageId = imageId;
        this.dogs = dogs;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.dog_single_simple, null, true);

        TextView txtName = (TextView) rowView.findViewById(R.id.dog_simple_name);
        TextView txtBreed = (TextView) rowView.findViewById(R.id.breed);


        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        txtName.setText(dogs.get(position).name);
        txtBreed.setText(dogs.get(position).breed);
        imageView.setImageResource(imageId[position]);

        return rowView;
    }
}