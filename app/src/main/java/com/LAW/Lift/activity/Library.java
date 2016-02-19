package com.LAW.Lift.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.LAW.Lift.R;
import com.LAW.Lift.adapter.CardArrayAdapter;
import com.LAW.Lift.adapter.LibraryAdapter;
import com.LAW.Lift.app.MyVolley;
import com.LAW.Lift.common.AlertDialogManager;
import com.LAW.Lift.common.ConnectionDetector;
import com.LAW.Lift.model.Card;
import com.LAW.Lift.model.MyTextviewWhite;
import com.LAW.Lift.model.librarycard;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Library extends Activity {
    public static String[] get_legislation;
    public static String[] Id;
    public static String[] Book_Name;
    public static String[] Url;
    public static String[] Published_Month;
    public static String[] month;
    public String[] year;
    String years;
    String urlJsonArry = "http://www.lawinfingertips.com/webservice/Lift_Final/get_all_books.php?year=2016";
    String jsonPorturl = "http://www.lawinfingertips.com/webservice/Lift_Final/get_year.php?id=1";
    Spinner spinner1;
    ProgressDialog pDialog;
    AlertDialogManager alert = new AlertDialogManager();
    ConnectionDetector cd;
    public ArrayAdapter<String> adapter;
    private LibraryAdapter libraryAdapter;
    GridView gridView;
    String retimag,monthtext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.librarylist);

        this.getActionBar().setDisplayShowCustomEnabled(true);
        this.getActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.titleview, null);
        ((MyTextviewWhite) v.findViewById(R.id.title)).setText(this.getTitle());
        this.getActionBar().setCustomView(v);



        cd = new ConnectionDetector(getApplicationContext());
        gridView = (GridView) findViewById(R.id.gridView);
        libraryAdapter = new LibraryAdapter(Library.this, R.layout.library);
        gridView.setAdapter(libraryAdapter);


        spinner1 = (Spinner) findViewById(R.id.spinner1);
        RequestQueue queue = MyVolley.getRequestQueue();

        JsonObjectRequest req = new JsonObjectRequest(jsonPorturl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.e("History", "response.get_year" + response);

                            JSONObject jobject = response;

                            JSONArray jsonMainArr = jobject.getJSONArray("get_year");

                            year = new String[jsonMainArr.length()];


                            for (int i = 0; i < jsonMainArr.length(); i++) {

                                JSONObject person = (JSONObject) jsonMainArr.get(i);

                                year[i] = person.getString("year");

                                years = person.getString("year");
                                year[i] = years;

                                Log.d("Library", years);
                            }


                            //mTvResult.setText(jsonResponse);
                            new PortNumber().execute();
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

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                years = spinner1.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        if (!cd.isConnectingToInternet()) {
            alert.showAlertDialog(getApplicationContext(),
                    "Internet Connection Lost",
                    "Please connect to Internet and Try again..", false);
        } else {

            pDialog = new ProgressDialog(Library.this);
            pDialog.setMessage("Loading....");
            pDialog.setCancelable(false);
            pDialog.show();


        }



        RequestQueue queue1 = MyVolley.getRequestQueue();

        JsonObjectRequest req1 = new JsonObjectRequest(urlJsonArry,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.e("History", "response.get_all_book" + response);

                            JSONObject jobject =response;

                            JSONArray jsonMainArr = jobject.getJSONArray("get_all_book");

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

                                retimag = person.getString("Url");
                                monthtext = person.getString("month");






                                librarycard librarycard = new librarycard(retimag, monthtext);
                                libraryAdapter.add(librarycard);


                            }
                            if (pDialog.isShowing())
                                pDialog.dismiss();
                            //mTvResult.setText(jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();

                            if (pDialog.isShowing())
                                pDialog.dismiss();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pDialog.isShowing())
                    pDialog.dismiss();
                Log.e("Hiistory","error  "+error);
                // listView.setVisibility(View.GONE);
            }


        });
        queue1.add(req1);
        gridView.setAdapter(libraryAdapter);

}






    public class PortNumber extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.e("AddServer", "pre port_name " + year);

        }

        @Override
        protected String[] doInBackground(String... params) {

            Log.e("AddServer", "return port_name " + year);
            return year;
        }


        @Override
        protected void onPostExecute(String[] s) {

            Log.e("AddServer", "port_name " + s);

            super.onPostExecute(s);
            adapter = new ArrayAdapter<String>(Library.this, R.layout.spinner_item, s);
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            spinner1.setAdapter(adapter);
        }

    }
}






