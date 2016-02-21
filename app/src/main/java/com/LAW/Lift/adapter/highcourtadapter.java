package com.LAW.Lift.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.LAW.Lift.R;
import com.LAW.Lift.model.highcourtcard;
import com.LAW.Lift.model.legislationcard;
import com.LAW.Lift.model.supremecard;

import java.util.ArrayList;
import java.util.List;

public class highcourtadapter extends ArrayAdapter<highcourtcard> {
    private static final String TAG = "CardArrayAdapter";
    private List<highcourtcard> cardList = new ArrayList<highcourtcard>();

    String bus_id_item;
    int textViewResourceId;

    //Map<String, String> addToCartMap = new HashMap<>();

    public highcourtadapter(Context context, int textViewResourceId) {

        super(context, textViewResourceId);
        this.textViewResourceId = textViewResourceId;
    }

    @Override
    public void add(highcourtcard object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public highcourtcard getItem(int index) {
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
            viewHolder.line1 = (TextView) row.findViewById(R.id.highpara);
            viewHolder.line2 = (TextView) row.findViewById(R.id.contexthigh);
            viewHolder.line3 = (TextView) row.findViewById(R.id.questionhigh);
            viewHolder.line4 = (TextView) row.findViewById(R.id.answerhigh);
            viewHolder.line5 = (TextView) row.findViewById(R.id.referencehigh);
            viewHolder.line6 = (TextView) row.findViewById(R.id.texthigh);


            //viewHolder.line2 = (TextView) row.findViewById(R.id.bus_location);
            //viewHolder.line3 = (TextView) row.findViewById(R.id.bus_time);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder) row.getTag();
        }

        highcourtcard highcourtcard = getItem(position);
        viewHolder.line1.setText(highcourtcard.gethighpara());
        viewHolder.line2.setText(highcourtcard.getcontexthigh());
        viewHolder.line3.setText(highcourtcard.getquestionhigh());
        viewHolder.line4.setText(highcourtcard.getanswerhigh());
        viewHolder.line5.setText(highcourtcard.getreferencehigh());
        viewHolder.line6.setText(highcourtcard.gettexthigh());

        viewHolder.line1.setSelected(true);
        viewHolder.line2.setSelected(true);
        viewHolder.line3.setSelected(true);
        viewHolder.line4.setSelected(true);
        viewHolder.line5.setSelected(true);
        viewHolder.line6.setSelected(true);



        if(highcourtcard.gethighpara().equals("")){
            viewHolder.line1.setText("Content not uploaded");
        }else {
            viewHolder.line1.setText(highcourtcard.gethighpara());
        }


        if(highcourtcard.getcontexthigh().equals("")){
            viewHolder.line2.setText("Content not uploaded");
        }else {
            viewHolder.line2.setText(highcourtcard.getcontexthigh());
        }

        if(highcourtcard.getquestionhigh().equals("")){
            viewHolder.line3.setText("Content not uploaded");
        }else {
            viewHolder.line3.setText(highcourtcard.getquestionhigh());
        }

        if(highcourtcard.getanswerhigh().equals("")){
            viewHolder.line4.setText("Content not uploaded");
        }else {
            viewHolder.line4.setText(highcourtcard.getanswerhigh());
        }

        if(highcourtcard.getreferencehigh().equals("")){
            viewHolder.line5.setText("Content not uploaded");
        }else {
            viewHolder.line5.setText(highcourtcard.getreferencehigh());
        }
        if(highcourtcard.gettexthigh().equals("")){
            viewHolder.line6.setText("Content not uploaded");
        }else {
            viewHolder.line6.setText(highcourtcard.gettexthigh());
        }


        if (row != null) {
            TextView line3 = (TextView) row.findViewById(R.id.questionhigh);

            if (line3 != null)
                line3.setText(Html.fromHtml(highcourtcard.getquestionhigh()).toString());



        }


        if (row != null) {
            TextView line4 = (TextView) row.findViewById(R.id.answerhigh);

            if (line4 != null)
                line4.setText(Html.fromHtml(highcourtcard.getquestionhigh()).toString());
        }
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

    }
}


