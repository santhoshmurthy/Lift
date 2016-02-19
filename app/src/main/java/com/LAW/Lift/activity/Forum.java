package com.LAW.Lift.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.LAW.Lift.R;
import com.LAW.Lift.adapter.forumadapter;
import com.LAW.Lift.adapter.supremeadapter;
import com.LAW.Lift.app.MyVolley;
import com.LAW.Lift.common.AlertDialogManager;
import com.LAW.Lift.common.ConnectionDetector;
import com.LAW.Lift.model.MyTextview;
import com.LAW.Lift.model.MyTextviewWhite;
import com.LAW.Lift.model.forumcard;
import com.LAW.Lift.model.supremecard;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Forum extends Activity {


MyTextview febtext;
    public static String[] get_legislation;
    public static String[] book_name;
    public static String[] book_icon;
    public static String[] year;
    public static String[] month;
    public static String[] name;
    public static String[] id;
    public static String[] title;
    public static String[] content;
    public static String[] author;
    String jsonResponse;
    String thoughttext, juniortext, authors;
    ProgressDialog pDialog;
    AlertDialogManager alert = new AlertDialogManager();
    ConnectionDetector cd;
    String urlJsonArry = "http://www.lawinfingertips.com/webservice/Lift_Final/get_thoughts.php?book_id=";
    ListView listView;
String  bookid;
    String months;
    private forumadapter ride3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forumlist);

        this.getActionBar().setDisplayShowCustomEnabled(true);
        this.getActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.titleview, null);
        febtext=(MyTextview)findViewById(R.id.febtext);
        ((MyTextviewWhite) v.findViewById(R.id.title)).setText(this.getTitle());
        this.getActionBar().setCustomView(v);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //bus_names=extras.getStringArray("BusNames");

            bookid = extras.getString("book_id");
            months=extras.getString("month");
            Log.d("forum",bookid);
            //Toast.makeText(Booking.this,sname+"\n"+slat+"\n"+slong, Toast.LENGTH_SHORT).show();
        }
        cd = new ConnectionDetector(getApplicationContext());
        listView = (ListView)findViewById(R.id.listView5);

        ride3 = new forumadapter(Forum.this, R.layout.forum);
        listView.setAdapter(ride3);


        if (!cd.isConnectingToInternet()) {
            alert.showAlertDialog(getApplicationContext(),
                    "Internet Connection Lost",
                    "Please connect to Internet and Try again..", false);
        } else {

            pDialog = new ProgressDialog(Forum.this);
            pDialog.setMessage("Loading....");
            pDialog.setCancelable(false);
            pDialog.show();

            RequestQueue queue = MyVolley.getRequestQueue();

            JsonObjectRequest req = new JsonObjectRequest(urlJsonArry+bookid,null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                Log.e("History", "response.get_year" + response);

                                JSONObject jobject =response;

                                JSONArray jsonMainArr = jobject.getJSONArray("get_year");

                                book_name = new String[jsonMainArr.length()];
                                book_icon = new String[jsonMainArr.length()];
                                year = new String[jsonMainArr.length()];
                                month = new String[jsonMainArr.length()];
                                id = new String[jsonMainArr.length()];
                                title = new String[jsonMainArr.length()];
                                content = new String[jsonMainArr.length()];
                                author = new String[jsonMainArr.length()];



                                for (int i = 0; i < jsonMainArr.length(); i++) {

                                    JSONObject person= (JSONObject) jsonMainArr.get(i);

                                    book_name[i]=person.getString("book_name");
                                    book_icon[i] = person.getString("book_icon");
                                    year[i] = person.getString("year");
                                    month[i] = person.getString("month");
                                    id[i] = person.getString("id");
                                    title[i]=person.getString("title");
                                    content[i] = person.getString("content");
                                    author[i] = person.getString("author");



                                    thoughttext = person.getString("title");
                                    juniortext = person.getString("content");
                                    authors = person.getString("author");


                                    months=person.getString("month");
                                    febtext.setText(months);
                                    forumcard forumcard = new forumcard(thoughttext, juniortext, authors);
                                    ride3.add(forumcard);

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
            queue.add(req);
            listView.setAdapter(ride3);
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (pDialog.isShowing())
            pDialog.dismiss();
        Intent in = new Intent(Forum.this, MainActivity.class);
        startActivity(in);
        finish();
    }
}


