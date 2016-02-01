package com.example.miine.compagnon;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import cpasbien.ContentAffiche;
import cpasbien.GetCpasBien;


/**
 * Created by Miine on 26/01/2016.
 */
public class Top_film_adapteur extends ArrayAdapter<ContentAffiche>  {
    public Top_film_adapteur(Context context, ArrayList<ContentAffiche> films) {
        super(context, 0, films);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
         ContentAffiche content = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.top_film_adapteur, parent, false);
        }

        //ImageView img1 = (ImageView)convertView.findViewById(R.id.icon1);
        URL newurl = null;
        System.out.println(content.getTitre());
        TextView lol = (TextView)convertView.findViewById(R.id.textView);
        lol.setText(content.getTitre());
        try {
            newurl = new URL(content.getImg());
            //img1.setImageBitmap(BitmapFactory.decodeStream(newurl.openConnection().getInputStream()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();;
        }



        // Populate the data into the template view using the data object

        // Return the completed view to render on screen
        return convertView;
    }
}
