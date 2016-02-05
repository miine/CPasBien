package com.example.miine.compagnon;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;

import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cpasbien.ContentListe;
import cpasbien.GetCpasBien;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,List_view_fragment.OnFragmentInteractionListener,Content_List_Fragment.OnFragmentInteractionListener {
    private String currentFragmentTag = new List_view_fragment().getTag();
    private Toolbar toolbar;
    public static ProgressBar mprogress;
    private static View appbarmain ;
    private Content_List_Fragment currentfrag;

    //Search bar
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
         mprogress = (ProgressBar)findViewById(R.id.loading_spinner);
         toolbar.setTitle("Top Film");




        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)

        .build();
        ImageLoader.getInstance().init(config);





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        List_view_fragment fragment = new List_view_fragment();
        android.app.FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(isSearchOpened){
            handleMenuSearch();
            return;
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        mSearchAction = menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_search){
            handleMenuSearch();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        toolbar.setTitle(item.getTitle());
        if (id == R.id.nav_Top_film) {
            List_view_fragment fragment = new List_view_fragment();
            android.app.FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
            mSearchAction.setVisible(false);

        } else if (id == R.id.nav_Films) {
            Content_List_Fragment fragment = Content_List_Fragment.newInstance("FILMS");
            currentfrag = fragment;
            android.app.FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
            mSearchAction.setVisible(true);

        } else if (id == R.id.nav_Series) {
            Content_List_Fragment fragment = Content_List_Fragment.newInstance("SERIES");
            currentfrag = fragment;
            android.app.FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
            mSearchAction.setVisible(true);

        } else if (id == R.id.nav_Musique) {
            Content_List_Fragment fragment = Content_List_Fragment.newInstance("MUSIQUES");
            currentfrag = fragment;
            android.app.FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
            mSearchAction.setVisible(true);
        } else if (id == R.id.nav_Ebooks) {
            Content_List_Fragment fragment = Content_List_Fragment.newInstance("EBOOKS");
            currentfrag = fragment;
            android.app.FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
            mSearchAction.setVisible(true);
        } else if (id == R.id.nav_logiciels) {
            Content_List_Fragment fragment = Content_List_Fragment.newInstance("LOGICIELS");
            currentfrag = fragment;
            android.app.FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
            mSearchAction.setVisible(true);
        }else if (id == R.id.nav_Jeux_console) {
            Content_List_Fragment fragment = Content_List_Fragment.newInstance("JEUX_CONSOLES");
            currentfrag = fragment;
            android.app.FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
            mSearchAction.setVisible(true);
        }else if (id == R.id.nav_Jeux_Pc) {
            Content_List_Fragment fragment = Content_List_Fragment.newInstance("JEUX_PC");
            currentfrag = fragment;
            android.app.FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
            mSearchAction.setVisible(true);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if (isSearchOpened){
            final ActionBar action = getSupportActionBar(); //get the actionbar
            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true);
            //action.setTitle(toolbar.getTitle());



            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);

            //add the search icon in the action bar
            mSearchAction.setIcon(getResources().getDrawable(android.support.v7.appcompat.R.drawable.abc_ic_search_api_mtrl_alpha));

            isSearchOpened = false;
        }
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onFragmentInteractionHome(Uri uri) {

    }

    @Override
    public void openHome(View view) {

    }

    protected void handleMenuSearch(){
        final ActionBar action = getSupportActionBar(); //get the actionbar

        if(isSearchOpened){ //test if the search is open

            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true);
            action.setTitle(toolbar.getTitle());

//show the title in the action bar

            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);

            //add the search icon in the action bar
            mSearchAction.setIcon(getResources().getDrawable(android.support.v7.appcompat.R.drawable.abc_ic_search_api_mtrl_alpha));

            isSearchOpened = false;
        } else { //open the search entry

            action.setDisplayShowCustomEnabled(true); //enable it to display a
            // custom view in the action bar.
            action.setCustomView(R.layout.search_bar);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title

            edtSeach = (EditText)action.getCustomView().findViewById(R.id.edtSearch); //the text editor

            //this is a listener to do a search when the user clicks on search button
            edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        //doSearch();
                        currentfrag.search(v.getText().toString());
                        action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
                        action.setDisplayShowTitleEnabled(true);
                        action.setTitle(v.getText().toString());//show the title in the action bar
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);
                        return true;
                    }
                    return false;
                }
            });


            edtSeach.requestFocus();

            //open the keyboard focused in the edtSearch
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);


            //add the close icon
            mSearchAction.setIcon(getResources().getDrawable(android.support.v7.appcompat.R.drawable.abc_ic_search_api_mtrl_alpha));

            isSearchOpened = true;
        }
    }
}
