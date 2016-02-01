package com.example.miine.compagnon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
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
public class List_torrent_adapteur extends BaseAdapter {
    private ArrayList <ContentListe> listData;
    private GetCpasBien.Categories cate;
    private Context context;
    private LayoutInflater layoutInflater;
    private int currentPage =0;
    private String search ;
    private boolean finish=false;






    public List_torrent_adapteur(Context aContext, ArrayList<ContentListe>  listData, GetCpasBien.Categories cate) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        this.context = aContext;
        this.cate = cate;
        this.search ="";

    }
    public List_torrent_adapteur(Context aContext, ArrayList<ContentListe>  listData,String search) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        this.context = aContext;
        this.search = search;
        this.cate = null;

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
        View ListView;

        if (convertView == null) {
            holder = new ViewHolder();
            ListView = new View(context);
            ListView = inflater.inflate(R.layout.mobile_torrent, null);

            holder.titre  = (TextView) ListView
                    .findViewById(R.id.title);
            holder.leechers  = (TextView) ListView
                    .findViewById(R.id.leech);
            holder.seeders  = (TextView) ListView
                    .findViewById(R.id.seed);
            holder.taille  = (TextView) ListView
                    .findViewById(R.id.taille);


            ListView.setTag(holder);
        } else {
            ListView = (View) convertView;
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titre.setText(listData.get(position).getTitre());
        holder.leechers.setText(String.valueOf(listData.get(position).getLeechers())+" Lecheers" );
        holder.seeders.setText(String.valueOf(listData.get(position).getSeeders())+" Seeders");
        holder.taille.setText(listData.get(position).getTailles());
        if (finish) return  ListView;
        if (position == getCount() - 1) {
            currentPage++;


            if (this.search.equals("")){
                new LongOperation().execute("","",String.valueOf(currentPage));
            }else {
                new LongOperation().execute("search",search,String.valueOf(currentPage));
            }


        }







        return ListView;
    }
    public void add(ArrayList<ContentListe> data){
        if (data.size()==0) finish =true;
       for (ContentListe e : data){
           listData.add(e);
       }
        notifyDataSetChanged();
    }

    static class ViewHolder {

        TextView titre;
        TextView leechers;
        TextView seeders;
        TextView taille;

    }

    private class LongOperation extends AsyncTask<String, Void, String> {
        ArrayList<ContentListe> res = new ArrayList<ContentListe>();
        @Override
        protected String doInBackground(String... params) {

            try {
                if (params[0].equals("search")){
                    res = GetCpasBien.Search(params[1],Integer.parseInt(params[2]));
                }else {
                    res = GetCpasBien.ListeTorrents(cate,Integer.parseInt(params[2]));
                }



            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            add(res);
            MainActivity.mprogress.setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onPreExecute() {
        MainActivity.mprogress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
