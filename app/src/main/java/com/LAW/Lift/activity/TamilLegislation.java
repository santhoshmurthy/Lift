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
import com.LAW.Lift.adapter.CardArrayAdapter;
import com.LAW.Lift.adapter.legislationadapter;
import com.LAW.Lift.app.MyVolley;
import com.LAW.Lift.common.AlertDialogManager;
import com.LAW.Lift.common.ConnectionDetector;
import com.LAW.Lift.model.Card;
import com.LAW.Lift.model.MyTextviewWhite;
import com.LAW.Lift.model.legislationcard;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TamilLegislation extends Activity {
    TextView tamilfeb;
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
    String tamilname, tamilactno, tamilenactedby, tamilreceived,tamilpublished,tamilcame,tamilsalient,tamilbrief,tamilfulltext;
    ProgressDialog pDialog;
    AlertDialogManager alert = new AlertDialogManager();
    ConnectionDetector cd;
    String urlJsonArry = "http://www.lawinfingertips.com/webservice/Lift_Final/get_legislation.php?book_id=";
    String tamil="&type=state&state_id=1000";
    ListView listView2;
    String bookid;
    String months;

    private legislationadapter rideadapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tamil);

        this.getActionBar().setDisplayShowCustomEnabled(true);
        this.getActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.titleview, null);
        tamilfeb=(TextView)findViewById(R.id.tamilfeb);
        ((MyTextviewWhite) v.findViewById(R.id.title)).setText(this.getTitle());
        this.getActionBar().setCustomView(v);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //bus_names=extras.getStringArray("BusNames");

            bookid = extras.getString("book_id");
            months=extras.getString("month");
            Log.d("tamil",bookid +months);
            //Toast.makeText(Booking.this,sname+"\n"+slat+"\n"+slong, Toast.LENGTH_SHORT).show();
        }
        tamilfeb.setText(months);
        cd = new ConnectionDetector(getApplicationContext());
        listView2 = (ListView)findViewById(R.id.listView2);

        rideadapter2 = new legislationadapter(TamilLegislation.this, R.layout.tamillegislation);
        listView2.setAdapter(rideadapter2);


        if (!cd.isConnectingToInternet()) {
            alert.showAlertDialog(getApplicationContext(),
                    "Internet Connection Lost",
                    "Please connect to Internet and Try again..", false);
        } else {

            pDialog = new ProgressDialog(TamilLegislation.this);
            pDialog.setMessage("Loading....");
            pDialog.setCancelable(false);
            pDialog.show();

            RequestQueue queue = MyVolley.getRequestQueue();

            JsonObjectRequest req = new JsonObjectRequest(urlJsonArry+bookid+tamil,null,
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

                                    JSONObject person= (JSONObject) jsonMainArr.get(i);

                                    book_id[i]=person.getString("book_id");
                                    state_id[i] = person.getString("state_id");
                                    state[i] = person.getString("state");
                                    book_name[i] = person.getString("book_name");
                                    book_icon[i] = person.getString("book_icon");
                                    published_month[i]=person.getString("published_month");
                                    month[i] = person.getString("month");
                                    name[i] = person.getString("name");
                                    act_no[i] = person.getString("act_no");
                                    enacted_by[i] = person.getString("enacted_by");
                                    receiver_by_president_on[i]=person.getString("receiver_by_president_on");
                                    published_in[i] = person.getString("published_in");
                                    came_in_force[i] = person.getString("came_in_force");
                                    salient_features[i] = person.getString("salient_features");
                                    brief_deatils[i] = person.getString("brief_deatils");
                                    ref_url[i]=person.getString("ref_url");

                                    tamilname = person.getString("name");
                                    tamilactno = person.getString("act_no");
                                    tamilenactedby = person.getString("enacted_by");
                                    tamilreceived = person.getString("receiver_by_president_on");
                                    tamilpublished= person.getString("published_in");
                                    tamilcame= person.getString("came_in_force");
                                    tamilsalient=person.getString("salient_features");
                                    tamilbrief=person.getString("brief_deatils");
                                    tamilfulltext=person.getString("ref_url");


                                    months=person.getString("month");


                                    legislationcard legislationcard = new legislationcard(tamilname, tamilactno, tamilenactedby, tamilreceived,tamilpublished,tamilcame,tamilsalient,tamilbrief,tamilfulltext);
                                    rideadapter2.add(legislationcard);

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
            listView2.setAdapter(rideadapter2);
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
        Intent in = new Intent(TamilLegislation.this, MainActivity.class);
        startActivity(in);
        finish();
    }
}

