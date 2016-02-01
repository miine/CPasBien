package com.example.miine.compagnon;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

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
 * {@link List_view_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link List_view_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class List_view_fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    GridView mListView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Top_film_adapteur mAdapter;
    ArrayList<ContentAffiche> res = new ArrayList<ContentAffiche>();
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment List_view_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static List_view_fragment newInstance() {
        List_view_fragment fragment = new List_view_fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }




    public List_view_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swipe_listview_top_films, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);
        setAppearance();
        mSwipeRefreshLayout.setOnRefreshListener(this);

        new LongOperation().execute("");

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    private void setAppearance() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorPrimaryDark);
    }
    public void showSwipeProgress() {

        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {

        new LongOperation(false).execute("norefresh");
    }


    private class LongOperation extends AsyncTask<String, Void, String> {

        private boolean refresh;
        public LongOperation(boolean refresh)
        {
            this.refresh = refresh;
        }
        public LongOperation()
        {
           this.refresh = true;
        }

        @Override
        protected String doInBackground(String... params) {
            final String pa;

            try {
                res = GetCpasBien.TopFilm();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NonInternetExeption nonInternetExeption) {
                nonInternetExeption.printStackTrace();
            }

            return "ok";

        }

        @Override
        protected void onPostExecute(String result) {

            mListView = (GridView) getActivity().findViewById(R.id.gridView1);

            mAdapter = new Top_film_adapteur(getActivity().getBaseContext(), res);
            AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(mAdapter);
            animationAdapter.setAbsListView(mListView);
            mListView.setAdapter(animationAdapter);

            //mListView.setAdapter(mAdapter);
            mSwipeRefreshLayout.setRefreshing(false);
            if(this.refresh) MainActivity.mprogress.setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onPreExecute() {

            if(refresh) MainActivity.mprogress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
