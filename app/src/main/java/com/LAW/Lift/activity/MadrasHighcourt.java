package com.LAW.Lift.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.LAW.Lift.R;
import com.LAW.Lift.adapter.highcourtadapter;
import com.LAW.Lift.adapter.supremeadapter;
import com.LAW.Lift.app.MyVolley;
import com.LAW.Lift.common.AlertDialogManager;
import com.LAW.Lift.common.ConnectionDetector;
import com.LAW.Lift.model.MyTextviewWhite;
import com.LAW.Lift.model.highcourtcard;
import com.LAW.Lift.model.supremecard;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MadrasHighcourt extends Activity {
    ImageView back;
    TextView madrastext;
    public static String[] get_legislation;
    public static String[] book_name;
    public static String[] book_icon;
    public static String[] year;
    public static String[] month;
    public static String[] name;
    public static String[] court;
    public static String[] title;
    public static String[] context;
    public static String[] question;
    public static String[] answer;
    public static String[] reference;
    public static String[] url;
    String jsonResponse;
    String highpara, contexthigh, questionhigh, answerhigh, referencehigh, texthigh;;
    ProgressDialog pDialog;
    AlertDialogManager alert = new AlertDialogManager();
    ConnectionDetector cd;
    String urlJsonArry = "http://www.lawinfingertips.com/webservice/Lift_Final/get_case_law.php?book_id=";
    String high ="&type=hc&court_id=115";
    ListView listView;
    String bookid;
    String months;

    private highcourtadapter ride2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.madraslist);

        this.getActionBar().setDisplayShowCustomEnabled(true);
        this.getActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        madrastext=(TextView)findViewById(R.id.madrastext);
        View v = inflator.inflate(R.layout.titleview, null);
        ((MyTextviewWhite) v.findViewById(R.id.title)).setText(this.getTitle());
        this.getActionBar().setCustomView(v);
        back=(ImageView)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent open = new Intent(MadrasHighcourt.this, MainActivity.class);
                startActivity(open);*/
                finish();

            }
        });




        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //bus_names=extras.getStringArray("BusNames");

            bookid = extras.getString("book_id");
            months=extras.getString("month");
            Log.d("high",bookid  + months);
            //Toast.makeText(Booking.this,sname+"\n"+slat+"\n"+slong, Toast.LENGTH_SHORT).show();
        }
        madrastext.setText(months);
        cd = new ConnectionDetector(getApplicationContext());
        listView = (ListView)findViewById(R.id.listView4);

        ride2 = new highcourtadapter(MadrasHighcourt.this, R.layout.madras);
        listView.setAdapter(ride2);


        if (!cd.isConnectingToInternet()) {
            alert.showAlertDialog(getApplicationContext(),
                    "Internet Connection Lost",
                    "Please connect to Internet and Try again..", false);
        } else {

            pDialog = new ProgressDialog(MadrasHighcourt.this);
            pDialog.setMessage("Loading....");
            pDialog.setCancelable(false);
            pDialog.show();

            RequestQueue queue = MyVolley.getRequestQueue();

            JsonObjectRequest req = new JsonObjectRequest(urlJsonArry+bookid+high,null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                Log.e("History", "response.get_case_law" + response);

                                JSONObject jobject =response;

                                JSONArray jsonMainArr = jobject.getJSONArray("get_case_law");

                                book_name = new String[jsonMainArr.length()];
                                book_icon = new String[jsonMainArr.length()];
                                year = new String[jsonMainArr.length()];
                                month = new String[jsonMainArr.length()];
                                court = new String[jsonMainArr.length()];
                                title = new String[jsonMainArr.length()];
                                context = new String[jsonMainArr.length()];
                                question = new String[jsonMainArr.length()];
                                answer = new String[jsonMainArr.length()];
                                reference = new String[jsonMainArr.length()];
                                url = new String[jsonMainArr.length()];


                                for (int i = 0; i < jsonMainArr.length(); i++) {

                                    JSONObject person= (JSONObject) jsonMainArr.get(i);

                                    book_name[i]=person.getString("book_name");
                                    book_icon[i] = person.getString("book_icon");
                                    year[i] = person.getString("year");
                                    month[i] = person.getString("month");
                                    court[i] = person.getString("court");
                                    title[i]=person.getString("title");
                                    context[i] = person.getString("context");
                                    question[i] = person.getString("question");
                                    answer[i] = person.getString("answer");
                                    reference[i] = person.getString("reference");
                                    url[i]=person.getString("url");


                                    highpara = person.getString("title");
                                    contexthigh = person.getString("context");
                                    questionhigh = person.getString("question");
                                    answerhigh = person.getString("answer");
                                    referencehigh= person.getString("reference");
                                    texthigh= person.getString("url");
                                    months=person.getString("month");

                                    highcourtcard highcourtcard = new highcourtcard(highpara, contexthigh, questionhigh, answerhigh, referencehigh, texthigh);
                                    ride2.add(highcourtcard);

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
            listView.setAdapter(ride2);
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
       /* Intent in = new Intent(MadrasHighcourt.this, MainActivity.class);
        startActivity(in);*/
        finish();
    }
}


