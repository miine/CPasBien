package com.example.miine.compagnon;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import java.io.IOException;
import java.util.ArrayList;

import cpasbien.ContentAffiche;
import cpasbien.ContentListe;
import cpasbien.GetCpasBien;
import cpasbien.NonInternetExeption;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Content_List_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Content_List_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Content_List_Fragment extends Fragment  {



    //private  GetCpasBien.Categories cate;
    private OnFragmentInteractionListener mListener;
    private ArrayList<ContentListe> res;
    private ListView mListView;
    private BaseAdapter mAdapter;
    public  GetCpasBien.Categories cate;
    private ProgressBar mprogress;


    // TODO: Rename and change types and number of parameters
    public static Content_List_Fragment newInstance(String param1) {
        Content_List_Fragment fragment = new Content_List_Fragment();
        Bundle args = new Bundle();
        args.putString("cate",param1);
        fragment.setArguments(args);

        return fragment;
    }

    public Content_List_Fragment() {

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
        return inflater.inflate(R.layout.list_view_torrent, container, false);
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
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    public interface OnFragmentInteractionListener {
        public void onFragmentInteractionHome(Uri uri);
        public void openHome(View view);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
                String ret ="";
            try {

                if (params[0].equals("search")){
                    res = GetCpasBien.Search(params[1],0);
                    ret =params[1];
                } else{
                    res = GetCpasBien.ListeTorrents(GetCpasBien.Categories.valueOf(getArguments().getString("cate")),0);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return ret;
        }

        @Override
        protected void onPostExecute(String result) {

            mListView = (ListView)getActivity().findViewById(R.id.myList);

            if (!result.equals("")){
            mAdapter = new List_torrent_adapteur(getActivity().getBaseContext(),res,result);
            } else{
                mAdapter = new List_torrent_adapteur(getActivity().getBaseContext(), res,GetCpasBien.Categories.valueOf(getArguments().getString("cate")));
            }


            AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(mAdapter);
            animationAdapter.setAbsListView(mListView);
            mListView.setAdapter(animationAdapter);
            MainActivity.mprogress.setVisibility(View.INVISIBLE);
            //mprogress.setVisibility(mListView.GONE);

        }

        @Override
        protected void onPreExecute() {
         MainActivity.mprogress.setVisibility(View.VISIBLE);

        }
        @Override
        protected void onProgressUpdate(Void... values) {}


    }


    public void search(String search){
        new LongOperation().execute("search",search);
    }


}
