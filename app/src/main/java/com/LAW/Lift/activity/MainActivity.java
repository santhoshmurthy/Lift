package com.LAW.Lift.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.LAW.Lift.R;
import com.LAW.Lift.adapter.CardArrayAdapter;
import com.LAW.Lift.adapter.mainadapter;
import com.LAW.Lift.app.MyVolley;
import com.LAW.Lift.fragments.AboutFragment;
import com.LAW.Lift.fragments.BookingFragment;
import com.LAW.Lift.fragments.ContactusFragment;
import com.LAW.Lift.fragments.DisclaimerFragment;
import com.LAW.Lift.fragments.HowtouseFragment;
import com.LAW.Lift.model.Card;
import com.LAW.Lift.model.MainCard;
import com.LAW.Lift.model.MyTextviewWhite;
import com.LAW.Lift.navdrawer.NavDrawerItem;
import com.LAW.Lift.navdrawer.NavDrawerListAdapter;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private Button central,Tamil,supreme,Madras,forum;
    private TextView mon;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    ListView listView;
    public static String[] get_legislation;
    public static String[] Id;
    public static String[] Book_Name;
    public static String[] Url;
    public static String[] Published_Month;
    public static String[] month;
    private mainadapter adaps;
    String months,bookid,yearss;
    ProgressDialog pDialog;
    String urlJsonArry = "http://www.lawinfingertips.com/webservice/Lift_Final/lift_of_the_month.php?id=1";
    private RelativeLayout mDrawerRelativeLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    /* Calendar mcurrentTime = Calendar.getInstance();
     final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
     final int minute = mcurrentTime.get(Calendar.MINUTE);
     final int seconds=mcurrentTime.get(Calendar.SECOND);
     final int milliseconds=mcurrentTime.get(Calendar.MILLISECOND);
     final int date=mcurrentTime.get(Calendar.DATE);*/
    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.app.ActionBar ab = getActionBar();
        central = (Button) findViewById(R.id.central);
        Tamil = (Button) findViewById(R.id.Tamil);
        supreme = (Button) findViewById(R.id.supreme);
        Madras = (Button) findViewById(R.id.Madras);
        forum = (Button) findViewById(R.id.forum);
        mon=(TextView)findViewById(R.id.mon);
        this.getActionBar().setDisplayShowCustomEnabled(true);
        this.getActionBar().setDisplayShowTitleEnabled(false);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {


            bookid = extras.getString("book_id");
            months=extras.getString("month");
            mon.setText(months);
            Log.d("Mainactivity",bookid+months);

        }else{
            RequestQueue queue = MyVolley.getRequestQueue();

            JsonObjectRequest req = new JsonObjectRequest(urlJsonArry, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                Log.e("History", "response.month_of_book" + response);

                                JSONObject jobject = response;

                                JSONArray jsonMainArr = jobject.getJSONArray("month_of_book");

                                Id = new String[jsonMainArr.length()];
                                Book_Name = new String[jsonMainArr.length()];
                                Url = new String[jsonMainArr.length()];
                                Published_Month = new String[jsonMainArr.length()];
                                month = new String[jsonMainArr.length()];

                                for (int i = 0; i < jsonMainArr.length(); i++) {

                                    JSONObject person = (JSONObject) jsonMainArr.get(i);

                                    Id[i] = person.getString("Id");
                                    Book_Name[i] = person.getString("Book_Name");
                                    Url[i] = person.getString("Url");
                                    Published_Month[i] = person.getString("Published_Month");
                                    month[i] = person.getString("month");

                                    bookid=person.getString("Id");
                                    months = person.getString("month");
                                    mon.setText(months);
                                    Log.d("MainActivity",bookid);
                                }


                                //mTvResult.setText(jsonResponse);

                            } catch (JSONException e) {
                                e.printStackTrace();


                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.e("Hiistory", "error  " + error);
                    // listView.setVisibility(View.GONE);
                }


            });
            queue.add(req);

        }

        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.title_view, null);
        ((MyTextviewWhite) v.findViewById(R.id.title)).setText(this.getTitle());
        this.getActionBar().setCustomView(v);

        ((Button) v.findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        mTitle = mDrawerTitle = getTitle();


        central.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openIntent = new Intent(MainActivity.this, CentralLegislation.class);
                openIntent.putExtra("book_id", bookid);
                openIntent.putExtra("month",months);
                startActivity(openIntent);
                MainActivity.this.finish();


            }
        });

        Tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openIntent = new Intent(MainActivity.this, TamilLegislation.class);
                openIntent.putExtra("book_id", bookid);
                openIntent.putExtra("month",months);
                startActivity(openIntent);
                MainActivity.this.finish();


            }
        });

        supreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openIntent = new Intent(MainActivity.this, Supremecourt.class);
                openIntent.putExtra("book_id", bookid);
                openIntent.putExtra("month",months);
                startActivity(openIntent);
                MainActivity.this.finish();


            }
        });
        Madras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openIntent = new Intent(MainActivity.this, MadrasHighcourt.class);
                openIntent.putExtra("book_id", bookid);
                openIntent.putExtra("month",months);
                startActivity(openIntent);
                MainActivity.this.finish();


            }
        });
        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openIntent = new Intent(MainActivity.this, Forum.class);
                openIntent.putExtra("book_id", bookid);
                openIntent.putExtra("month",months);
                startActivity(openIntent);
                MainActivity.this.finish();


            }
        });


        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
       navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerRelativeLayout = (RelativeLayout) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));

        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
       /* getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);*/

        /*mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close){*/
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.menu_icon, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                hideKeyboard();
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);


        }



    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // toggle nav drawer on selecting action bar app icon/title
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
            // Handle action bar actions click
		/*switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}*/
            return super.onOptionsItemSelected(item);
        }

    /* *
     * Called when invalidateOptionsMenu() is triggered
     */
        @Override
        public boolean onPrepareOptionsMenu (Menu menu){
            // if nav drawer is opened, hide the action items
            // boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
            boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerRelativeLayout);
            //menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
            return super.onPrepareOptionsMenu(menu);
        }


        /**
         * Diplaying fragment view for selected nav drawer list item
         */
    private void displayView(int position) {
        // update the main content by replacing fragments
        android.support.v4.app.Fragment fragment = null;

        switch (position) {
            case 0:

                break;

            case 1:

                Intent i=new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
            case 2:

                Bundle bundles=new Bundle();
                fragment = new ContactusFragment();
                fragment.setArguments(bundles);
                break;

            case 3 :

                Bundle bundle=new Bundle();
                fragment = new AboutFragment();
                fragment.setArguments(bundle);
                break;

            case 4:

                Bundle bundle1=new Bundle();
                fragment = new HowtouseFragment();
                fragment.setArguments(bundle1);
                break;


                case 5:
                    Bundle bundle2 = new Bundle();
                    fragment = new DisclaimerFragment();
                    fragment.setArguments(bundle2);
                    break;


            case 6:
                Intent l=new Intent(MainActivity.this,Library.class);

                startActivity(l);
                finish();
                break;
                default:
                    break;
            }

           {
        }


        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().addToBackStack("Frag1")
                    .replace(R.id.frame_container, fragment).commit();


            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            //mDrawerLayout.closeDrawer(mDrawerList);
            mDrawerLayout.closeDrawer(mDrawerRelativeLayout);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Slide menu item click listener
     */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            hideKeyboard();
            displayView(position);
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().findFragmentByTag("Frag1") != null) {
            getFragmentManager().popBackStackImmediate("Frag1",0);
            finish();
        } else {
            super.onBackPressed();
            Intent in = new Intent(MainActivity.this,MainActivity.class);
            startActivity(in);
            finish();
        }
    }
    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager)  this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }


}




