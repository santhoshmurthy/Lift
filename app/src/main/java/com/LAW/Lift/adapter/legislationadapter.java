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
import com.LAW.Lift.model.legislationcard;

import java.util.ArrayList;
import java.util.List;


public class legislationadapter extends ArrayAdapter<legislationcard> {
    private static final String TAG = "CardArrayAdapter";
    private List<legislationcard> cardList = new ArrayList<legislationcard>();

    String bus_id_item;
    int textViewResourceId;

    //Map<String, String> addToCartMap = new HashMap<>();

    public legislationadapter(Context context, int textViewResourceId) {

        super(context, textViewResourceId);
        this.textViewResourceId = textViewResourceId;
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
        viewHolder.line9.setText(legislationcard.gettamilfulltext());
        viewHolder.line1.setSelected(true);
        viewHolder.line2.setSelected(true);
        viewHolder.line3.setSelected(true);
        viewHolder.line4.setSelected(true);
        viewHolder.line5.setSelected(true);
        viewHolder.line6.setSelected(true);
        viewHolder.line7.setSelected(true);
        viewHolder.line8.setSelected(true);
        viewHolder.line9.setSelected(true);
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
}



