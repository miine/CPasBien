package com.example.miine.compagnon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import cpasbien.ContentAffiche;
import cpasbien.ContentListe;
import cpasbien.GetCpasBien;


/**
 * Created by Miine on 26/01/2016.
 */
public class Top_film_adapteur extends BaseAdapter  {
    private ArrayList <ContentAffiche> listData;
    private Context context;
    private LayoutInflater layoutInflater;
    private DisplayImageOptions options;




    public Top_film_adapteur(Context aContext, ArrayList<ContentAffiche>  listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        this.context = aContext;
        options = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(10))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();


    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final ViewHolder holder;
        View gridView;

        if (convertView == null) {
            holder = new ViewHolder();
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.mobile, null);
            gridView.setClickable(true);


            holder.img  = (ImageView) gridView
                    .findViewById(R.id.Affiche);

            gridView.setTag(holder);
        } else {
            gridView = (View) convertView;
            holder = (ViewHolder) convertView.getTag();
        }


//        ImageLoader.getInstance().loadImage(listData.get(position).getImg(),new ImageSize(0,0),options, new SimpleImageLoadingListener() {
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//             //Bitmap bm = Bitmap.createScaledBitmap(loadedImage, 250, 250, true);
//               holder.img.setImageBitmap(loadedImage);
//            }
//        });
        ImageLoader.getInstance().displayImage(listData.get(position).getImg(),holder.img , options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });






        return gridView;
    }



    static class ViewHolder {

        ImageView img;

    }

   }
