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
import com.LAW.Lift.model.supremecard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santhoshis on 2/10/2016.
 */
public class supremeadapter  extends ArrayAdapter<supremecard> {
    private static final String TAG = "CardArrayAdapter";
    private List<supremecard> cardList = new ArrayList<supremecard>();

    String bus_id_item;
    int textViewResourceId;

    //Map<String, String> addToCartMap = new HashMap<>();

    public supremeadapter(Context context, int textViewResourceId) {

        super(context, textViewResourceId);
        this.textViewResourceId = textViewResourceId;
    }

    @Override
    public void add(supremecard object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public supremecard getItem(int index) {
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
            viewHolder.line1 = (TextView) row.findViewById(R.id.titlepara);
            viewHolder.line2 = (TextView) row.findViewById(R.id.contextpara);
            viewHolder.line3 = (TextView) row.findViewById(R.id.questionpara);
            viewHolder.line4 = (TextView) row.findViewById(R.id.answerpara);
            viewHolder.line5 = (TextView) row.findViewById(R.id.referencepara);
            viewHolder.line6 = (TextView) row.findViewById(R.id.texttouch);


            //viewHolder.line2 = (TextView) row.findViewById(R.id.bus_location);
            //viewHolder.line3 = (TextView) row.findViewById(R.id.bus_time);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder) row.getTag();
        }

        supremecard supremecard = getItem(position);
        viewHolder.line1.setText(supremecard.gettitlepara());
        viewHolder.line2.setText(supremecard.getcontextpara());
        viewHolder.line3.setText(supremecard.getquestionpara());
        viewHolder.line4.setText(supremecard.getanswerpara());
        viewHolder.line5.setText(supremecard.getreferencepara());
        viewHolder.line6.setText(supremecard.gettexttouch());

        viewHolder.line1.setSelected(true);
        viewHolder.line2.setSelected(true);
        viewHolder.line3.setSelected(true);
        viewHolder.line4.setSelected(true);
        viewHolder.line5.setSelected(true);
        viewHolder.line6.setSelected(true);



        if(supremecard.gettitlepara().equals("")){
            viewHolder.line1.setText("Content not uploaded");
        }else {
            viewHolder.line1.setText(supremecard.gettitlepara());
        }
        if(supremecard.getcontextpara().equals("")){
            viewHolder.line2.setText("Content not uploaded");
        }else {
            viewHolder.line2.setText(supremecard.getcontextpara());
        }
        if(supremecard.getquestionpara().equals("")){
            viewHolder.line3.setText("Content not uploaded");
        }else {
            viewHolder.line3.setText(supremecard.getquestionpara());
        }
        if(supremecard.getanswerpara().equals("")){
            viewHolder.line4.setText("Content not uploaded");
        }else {
            viewHolder.line4.setText(supremecard.getanswerpara());
        }
        if(supremecard.getreferencepara().equals("")){
            viewHolder.line5.setText("Content not uploaded");
        }else {
            viewHolder.line5.setText(supremecard.getreferencepara());
        }

        if(supremecard.gettexttouch().equals("")){
            viewHolder.line6.setText("Content not uploaded");
        }else {
            viewHolder.line6.setText(supremecard.gettexttouch());
        }


        if (row != null) {
            TextView line3 = (TextView) row.findViewById(R.id.questionpara);

            if (line3 != null)
                line3.setText(Html.fromHtml(supremecard.getquestionpara()).toString());



        }


        if (row != null) {
            TextView line4 = (TextView) row.findViewById(R.id.answerpara);

            if (line4 != null)
                line4.setText(Html.fromHtml(supremecard.getquestionpara()).toString());
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


