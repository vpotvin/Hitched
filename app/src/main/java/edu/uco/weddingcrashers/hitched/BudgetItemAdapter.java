package edu.uco.weddingcrashers.hitched;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Author: Victoria Potvin
 */
public class BudgetItemAdapter extends ArrayAdapter<BudgetItem> {
    public BudgetItemAdapter(Context context, ArrayList<BudgetItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BudgetItem budgetItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.budget_item, parent, false);
        }

        TextView titleView = (TextView) convertView.findViewById(R.id.budgetItemTitle);
        TextView budgetView = (TextView) convertView.findViewById(R.id.budgetItemValue);
        TextView usedView = (TextView) convertView.findViewById(R.id.budgetItemUsed);

        titleView.setText(budgetItem.getTitle());
        budgetView.setText(NumberFormat.getCurrencyInstance().format(budgetItem.getValue()));
        usedView.setText(NumberFormat.getCurrencyInstance().format(budgetItem.getUsed()));

        return convertView;
    }
}
