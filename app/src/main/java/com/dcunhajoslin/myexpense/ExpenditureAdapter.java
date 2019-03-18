package com.dcunhajoslin.myexpense;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpenditureAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ExpenseModel> items;

    public ExpenditureAdapter(Context context, ArrayList<ExpenseModel> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public int getCount() {

        return items.size();
    }

    @Override
    public Object getItem(int position) {

        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {


        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.expenditure_today_item, parent, false);
        }


        ExpenseModel currentItem = (ExpenseModel) getItem(position);
        TextView  exp_name, amount;


        exp_name = (TextView) view.findViewById(R.id.exp_name_today);
        amount = (TextView) view.findViewById(R.id.amount_today);


        exp_name.setText(currentItem.getExpense_name());
        amount.setText(currentItem.getAmount());

        return view;
    }


}
