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
import com.LAW.Lift.model.Card;
import com.LAW.Lift.model.forumcard;

import java.util.ArrayList;
import java.util.List;


public class forumadapter extends ArrayAdapter<forumcard> {
    private static final String TAG = "CardArrayAdapter";
    private List<forumcard> cardList = new ArrayList<forumcard>();

    String bus_id_item;
    int textViewResourceId;

    //Map<String, String> addToCartMap = new HashMap<>();

    public forumadapter(Context context, int textViewResourceId) {

        super(context, textViewResourceId);
        this.textViewResourceId = textViewResourceId;
    }

    @Override
    public void add(forumcard object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public forumcard getItem(int index) {
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
            viewHolder.line1 = (TextView) row.findViewById(R.id.thoughttext);
            viewHolder.line2 = (TextView) row.findViewById(R.id.juniortext);
            viewHolder.line3 = (TextView) row.findViewById(R.id.authors);


            //viewHolder.line2 = (TextView) row.findViewById(R.id.bus_location);
            //viewHolder.line3 = (TextView) row.findViewById(R.id.bus_time);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder) row.getTag();
        }

        forumcard forumcard = getItem(position);
        viewHolder.line1.setText(forumcard.getthoughttext());
        viewHolder.line2.setText(forumcard.getjuniortext());
        viewHolder.line3.setText(forumcard.getauthor());

        viewHolder.line1.setSelected(true);
        viewHolder.line2.setSelected(true);
        viewHolder.line3.setSelected(true);
        if (row != null) {
            TextView line1 = (TextView) row.findViewById(R.id.thoughttext);


            if (line1 != null)
                line1.setText(Html.fromHtml(forumcard.getthoughttext()).toString());



        }


        if (row != null) {
            TextView line2 = (TextView) row.findViewById(R.id.juniortext);

            if (line2 != null)
                line2.setText(Html.fromHtml(forumcard.getjuniortext()).toString());
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

    }
}

