package com.LAW.Lift.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.LAW.Lift.R;
import com.LAW.Lift.adapter.CardArrayAdapter;
import com.LAW.Lift.app.MyVolley;
import com.LAW.Lift.common.AlertDialogManager;
import com.LAW.Lift.common.ConnectionDetector;
import com.LAW.Lift.model.Card;
import com.LAW.Lift.model.MyTextview;
import com.LAW.Lift.model.MyTextviewWhite;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

public class CentralLegislation extends Activity {
    public static String[] get_legislation;
    public static String[] book_id;
    public static String[] state_id;
    public static String[] state;
    public static String[] book_name;
    public static String[] book_icon;
    public static String[] published_month;
    public static String[] month;
    public static String[] name;
    public static String[] act_no;
    public static String[] enacted_by;
    public static String[] receiver_by_president_on;
    public static String[] published_in;
    public static String[] came_in_force;
    public static String[] salient_features;
    public static String[] brief_deatils;
    public static String[] ref_url;
    String jsonResponse;
    String lawname, lawactno, lawenactedby, lawreceived,lawpublished,lawcame,lawsalient,lawbrief,lawfulltext;
    ProgressDialog pDialog;
    AlertDialogManager alert = new AlertDialogManager();
    ConnectionDetector cd;
    String urlJsonArry = "http://www.lawinfingertips.com/webservice/Lift_Final/get_legislation.php?book_id=";
   String half ="&type=central";
    ListView listView;
    String bookid;
     MyTextview febcentral;
String months;
    private CardArrayAdapter rideadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.central);

        this.getActionBar().setDisplayShowCustomEnabled(true);
        this.getActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.titleview, null);
        ((MyTextviewWhite) v.findViewById(R.id.title)).setText(this.getTitle());
        this.getActionBar().setCustomView(v);
        febcentral=(MyTextview)findViewById(R.id.febcentral);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //bus_names=extras.getStringArray("BusNames");

            bookid = extras.getString("book_id");
            months=extras.getString("month");

            Log.d("central",bookid+months);
            //Toast.makeText(Booking.this,sname+"\n"+slat+"\n"+slong, Toast.LENGTH_SHORT).show();
        }


        febcentral.setText(months);
        cd = new ConnectionDetector(getApplicationContext());
        listView = (ListView)findViewById(R.id.listView);

        rideadapter = new CardArrayAdapter(CentralLegislation.this, R.layout.centrallegistation);
        listView.setAdapter(rideadapter);




        if (!cd.isConnectingToInternet()) {
            alert.showAlertDialog(getApplicationContext(),
                    "Internet Connection Lost",
                    "Please connect to Internet and Try again..", false);
        } else {

            pDialog = new ProgressDialog(CentralLegislation.this);
            pDialog.setMessage("Loading....");
            pDialog.setCancelable(false);
            pDialog.show();



            RequestQueue queue = MyVolley.getRequestQueue();

            JsonObjectRequest req = new JsonObjectRequest(urlJsonArry+bookid+half,null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                Log.e("History", "response.get_legislation" + response);

                                JSONObject jobject =response;

                                JSONArray jsonMainArr = jobject.getJSONArray("get_legislation");

                                book_id = new String[jsonMainArr.length()];
                                state_id = new String[jsonMainArr.length()];
                                state = new String[jsonMainArr.length()];
                                book_name = new String[jsonMainArr.length()];
                                book_icon = new String[jsonMainArr.length()];
                                published_month = new String[jsonMainArr.length()];
                                month = new String[jsonMainArr.length()];
                                name = new String[jsonMainArr.length()];
                                act_no = new String[jsonMainArr.length()];
                                enacted_by = new String[jsonMainArr.length()];
                                receiver_by_president_on = new String[jsonMainArr.length()];
                                published_in = new String[jsonMainArr.length()];
                                came_in_force = new String[jsonMainArr.length()];
                                salient_features = new String[jsonMainArr.length()];
                                brief_deatils = new String[jsonMainArr.length()];
                                ref_url = new String[jsonMainArr.length()];

                                for (int i = 0; i < jsonMainArr.length(); i++) {

                                    JSONObject person = (JSONObject) jsonMainArr.get(i);

                                    book_id[i] = person.getString("book_id");
                                    state_id[i] = person.getString("state_id");
                                    state[i] = person.getString("state");
                                    book_name[i] = person.getString("book_name");
                                    book_icon[i] = person.getString("book_icon");
                                    published_month[i] = person.getString("published_month");
                                    month[i] = person.getString("month");
                                    name[i] = person.getString("name");
                                    act_no[i] = person.getString("act_no");
                                    enacted_by[i] = person.getString("enacted_by");
                                    receiver_by_president_on[i] = person.getString("receiver_by_president_on");
                                    published_in[i] = person.getString("published_in");
                                    came_in_force[i] = person.getString("came_in_force");
                                    salient_features[i] = person.getString("salient_features");
                                    brief_deatils[i] = person.getString("brief_deatils");
                                    ref_url[i] = person.getString("ref_url");

                                    lawname = person.getString("name");
                                    lawactno = person.getString("act_no");
                                    lawenactedby = person.getString("enacted_by");
                                    lawreceived = person.getString("receiver_by_president_on");
                                    lawpublished = person.getString("published_in");
                                    lawcame = person.getString("came_in_force");
                                    lawsalient = person.getString("salient_features");
                                    lawbrief = person.getString("brief_deatils");
                                    lawfulltext = person.getString("ref_url");


                                    months=person.getString("month");


                                    Card card = new Card(lawname, lawactno, lawenactedby, lawreceived, lawpublished, lawcame, lawsalient, lawbrief, lawfulltext);
                                    rideadapter.add(card);


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
            listView.setAdapter(rideadapter);
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
        Intent in = new Intent(CentralLegislation.this, MainActivity.class);
        startActivity(in);
        finish();
    }
}
