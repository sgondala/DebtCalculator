package com.example.sgondala.debtcalculator;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgondala on 24/7/15.
 */
public class MyAdapter extends ArrayAdapter<Pair> {

    private List<Pair> peopleList = new ArrayList<Pair>();
    private TextView name_text;
    private TextView number_text;
    Context ctx;

    public MyAdapter(Context context, int resource) {
        super(context, resource);
        ctx = context;
    }

    @Override
    public void add(Pair object){
        peopleList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return peopleList.size();
    }

    @Override
    public Pair getItem(int position) {
        return peopleList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.single_list_item,
                    parent, false);
        }

        name_text = (TextView) convertView.findViewById(R.id.singleItemName);
        number_text = (TextView) convertView.findViewById(R.id.singleItemCost);
        String Name;
        int Cost;
        boolean POSITION;
        Pair pair = getItem(position);
        Name = pair.name;
        Cost = pair.cost;
        name_text.setText(Name);
        number_text.setText(Integer.toString(Cost));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        name_text.setLayoutParams(params);
        number_text.setLayoutParams(params);
        return convertView;
    }

    public void removeItem(int position){
        peopleList.remove(position);
        notifyDataSetChanged();
    }
}
