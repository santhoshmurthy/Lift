package com.LAW.Lift.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.LAW.Lift.R;
import com.LAW.Lift.activity.TamilLegislation;
import com.LAW.Lift.model.legislationcard;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class legislationadapter extends ArrayAdapter<legislationcard> {
    private static final String TAG = "CardArrayAdapter";
    private List<legislationcard> cardList = new ArrayList<legislationcard>();

    String bus_id_item;
    int textViewResourceId;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private Button startBtn;
    private ProgressDialog mProgressDialog;

    Context mcontext;
    String Link;
    //Map<String, String> addToCartMap = new HashMap<>();
    File pdfFile = new File(Environment
            .getExternalStorageDirectory(),"lift.pdf");
    //Map<String, String> addToCartMap = new HashMap<>();



    public legislationadapter(Context context, int textViewResourceId) {

        super(context, textViewResourceId);
        this.textViewResourceId = textViewResourceId;
        this.mcontext=context;
    }

    @Override
    public void add(legislationcard object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public legislationcard getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(textViewResourceId, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.line1 = (TextView) row.findViewById(R.id.tamilname);
            viewHolder.line2 = (TextView) row.findViewById(R.id.tamilactno);
            viewHolder.line3 = (TextView) row.findViewById(R.id.tamilenactedby);
            viewHolder.line4 = (TextView) row.findViewById(R.id.tamilreceived);
            viewHolder.line5 = (TextView) row.findViewById(R.id.tamilpublished);
            viewHolder.line6 = (TextView) row.findViewById(R.id.tamilcame);
            viewHolder.line7 = (TextView) row.findViewById(R.id.tamilsalient);
            viewHolder.line8 = (TextView) row.findViewById(R.id.tamilbrief);
           viewHolder.line9 = (TextView) row.findViewById(R.id.tamilfulltext);
            mProgressDialog = new ProgressDialog(mcontext);
            mProgressDialog.setMessage("Downloading file..");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            //viewHolder.line2 = (TextView) row.findViewById(R.id.bus_location);
            //viewHolder.line3 = (TextView) row.findViewById(R.id.bus_time);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder) row.getTag();
        }

        legislationcard legislationcard = getItem(position);
        viewHolder.line1.setText(legislationcard.gettamilname());
        viewHolder.line2.setText(legislationcard.gettamilactno());
        viewHolder.line3.setText(legislationcard.gettamilenactedby());
        viewHolder.line4.setText(legislationcard.gettamilreceived());
        viewHolder.line5.setText(legislationcard.gettamilpublished());
        viewHolder.line6.setText(legislationcard.gettamilcame());
        viewHolder.line7.setText(legislationcard.gettamilsalient());
        viewHolder.line8.setText(legislationcard.gettamilbrief());
        //viewHolder.line9.setText(legislationcard.gettamilfulltext());
        viewHolder.line1.setSelected(true);
        viewHolder.line2.setSelected(true);
        viewHolder.line3.setSelected(true);
        viewHolder.line4.setSelected(true);
        viewHolder.line5.setSelected(true);
        viewHolder.line6.setSelected(true);
        viewHolder.line7.setSelected(true);
        viewHolder.line8.setSelected(true);
        viewHolder.line9.setSelected(true);



        if(legislationcard.gettamilname().equals("")){
            viewHolder.line1.setText("Content not uploaded");
        }else {
            viewHolder.line1.setText(legislationcard.gettamilname());
        }

        if(legislationcard.gettamilactno().equals("")){
            viewHolder.line2.setText("Content not uploaded");
        }else {
            viewHolder.line2.setText(legislationcard.gettamilactno());
        }

        if(legislationcard.gettamilenactedby().equals("")){
            viewHolder.line3.setText("Content not uploaded");
        }else {
            viewHolder.line3.setText(legislationcard.gettamilenactedby());
        }


        if(legislationcard.gettamilreceived().equals("")){
            viewHolder.line4.setText("Content not uploaded");
        }else {
            viewHolder.line4.setText(legislationcard.gettamilreceived());
        }

        if(legislationcard.gettamilpublished().equals("")){
            viewHolder.line5.setText("Content not uploaded");
        }else {
            viewHolder.line5.setText(legislationcard.gettamilpublished());
        }

        if(legislationcard.gettamilcame().equals("")){
            viewHolder.line6.setText("Content not uploaded");
        }else {
            viewHolder.line6.setText(legislationcard.gettamilcame());
        }

        if(legislationcard.gettamilsalient().equals("")){
            viewHolder.line7.setText("Content not uploaded");
        }else {
            viewHolder.line7.setText(legislationcard.gettamilsalient());
        }

        if(legislationcard.gettamilbrief().equals("")){
            viewHolder.line8.setText("Content not uploaded");
        }else {
            viewHolder.line8.setText(legislationcard.gettamilbrief());
        }


       /* if(legislationcard.gettamilfulltext().equals("")){
            viewHolder.line9.setText("Content not uploaded");
        }else {
            viewHolder.line9.setText(legislationcard.gettamilfulltext());
        }*/




        if (row != null) {
            TextView line7 = (TextView) row.findViewById(R.id.tamilsalient);

            if (line7 != null)
                line7.setText(Html.fromHtml(legislationcard.gettamilsalient()).toString());



        }


        if (row != null) {
            TextView line8 = (TextView) row.findViewById(R.id.tamilbrief);

            if (line8 != null)
                line8.setText(Html.fromHtml(legislationcard.gettamilsalient()).toString());
        }



        viewHolder.line9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Link = TamilLegislation.ref_url[position];
                startDownload();
            }
        });
        return row;
    }







    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }



    static class CardViewHolder {
        TextView line1;
        TextView line2;
        TextView line3;
        TextView line4;
        TextView line5;
        TextView line6;
        TextView line7;
        TextView line8;
        TextView line9;
    }

    private void startDownload() {
        //String url = "http://farm1.static.flickr.com/114/298125983_0e4bf66782_b.jpg";
        new DownloadFileAsync().execute(Link);
    }

    class DownloadFileAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
            mProgressDialog.setProgress(DIALOG_DOWNLOAD_PROGRESS);
        }

        @Override
        protected String doInBackground(String... aurl) {
            int count;

            try {

                URL url = new URL(aurl[0]);
                URLConnection conexion = url.openConnection();
                conexion.connect();

                int lenghtOfFile = conexion.getContentLength();
                Log.d("ANDRO_ASYNC", "Length of file: " + lenghtOfFile);

                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream("/sdcard/" + "lift.pdf");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
            }
            return null;

        }

        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC", progress[0]);
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String unused) {
            mProgressDialog.dismiss();


            Uri path = Uri.fromFile(pdfFile);
            Intent objIntent = new Intent(Intent.ACTION_VIEW);
            objIntent.setDataAndType(path, "application/pdf");
            objIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mcontext.startActivity(objIntent);


        }
    }
}



