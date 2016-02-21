package com.example.miine.compagnon;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import java.io.IOException;
import java.util.ArrayList;

import cpasbien.ContentListe;
import cpasbien.GetCpasBien;

/**
 * Created by Miine on 20/02/2016.
 */
public class View_Torrent_Fragment extends Fragment {



    //private  GetCpasBien.Categories cate;
    private OnFragmentInteractionListener mListener;

    private ListView mListView;
    private BaseAdapter mAdapter;
    public  GetCpasBien.Categories cate;
    private ProgressBar mprogress;
    private TextView titre;
    private TextView desc;
    private Button telecharger;
    private ImageView image;


    // TODO: Rename and change types and number of parameters
    public static View_Torrent_Fragment newInstance(String param1) {
        View_Torrent_Fragment fragment = new View_Torrent_Fragment();
        Bundle args = new Bundle();
        args.putString("url",param1);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        new LongOperation().execute("");

        return inflater.inflate(R.layout.fiche_torrent, container, false);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteractionHome(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }
    public interface OnFragmentInteractionListener {
        public void onFragmentInteractionHome(Uri uri);
        public void openHome(View view);
    }
    @Override
    public void onDetach() {
        super.onDetach();

    }
    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {


            return"";
        }

        @Override
        protected void onPostExecute(String result) {



        }

        @Override
        protected void onPreExecute() {
            MainActivity.mprogress.setVisibility(View.VISIBLE);

        }
        @Override
        protected void onProgressUpdate(Void... values) {}


    }





}


