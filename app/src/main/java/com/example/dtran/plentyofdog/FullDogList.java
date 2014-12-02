package com.example.dtran.plentyofdog;

/**
 * Created by a00718912 on 11/29/2014.
 */

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FullDogList extends ArrayAdapter<Dog> {
    private final Activity context;
    private int userID;
    private List<Dog> dogs;
    private MatchHelper db;

    public FullDogList(Activity context, List<Dog> dogs) {
        super(context, R.layout.dog_single_full, dogs);
        this.context = context;
        this.dogs = dogs;
    }

    public FullDogList(Activity context, List<Dog> dogs, int userID) {
        super(context, R.layout.dog_single_full, dogs);
        this.context = context;
        this.dogs = dogs;
        this.userID = userID;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.dog_single_full, null, true);

        TextView txtName = (TextView) rowView.findViewById(R.id.Name);
        TextView txtBreed = (TextView) rowView.findViewById(R.id.Breed);
        TextView txtAge= (TextView) rowView.findViewById(R.id.Age);
        TextView txtGender = (TextView) rowView.findViewById(R.id.Gender);
        TextView txtSize = (TextView) rowView.findViewById(R.id.Size);
        TextView txtTraining= (TextView) rowView.findViewById(R.id.Training);
        TextView txtActivity = (TextView) rowView.findViewById(R.id.Temperament);
        TextView txtArea= (TextView) rowView.findViewById(R.id.Area);
        TextView txtDesc= (TextView) rowView.findViewById(R.id.dogDesc);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        txtName.setText(dogs.get(position).name);
        txtBreed.setText(dogs.get(position).breed);
        txtAge.setText(((Integer) dogs.get(position).age).toString());
        txtGender.setText(dogs.get(position).gender);
        txtSize.setText(dogs.get(position).size);
        txtTraining.setText(dogs.get(position).training);
        txtActivity.setText(dogs.get(position).activitylevel);
        txtArea.setText(dogs.get(position).area);
        txtDesc.setText(dogs.get(position).description);
        imageView.setImageURI(Uri.parse(dogs.get(position).image));

        Button btnYes = (Button) rowView.findViewById(R.id.btnYes);
        Button btnNo = (Button) rowView.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db = new MatchHelper(context);
                db.addMatch(new Match(userID, dogs.get(position).id, 0, "Today"));
                Toast.makeText(context, "userID " + userID + " liked dog " + dogs.get(position).id, Toast.LENGTH_SHORT).show();
                dogs.remove(position);

                notifyDataSetChanged();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "You hate " + dogs.get(position).name, Toast.LENGTH_SHORT).show();
                dogs.remove(position);
                notifyDataSetChanged();

            }
        });


        return rowView;
    }


}