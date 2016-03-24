package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class BudgetActivity extends Activity implements
        BudgetUpdateFragment.BudgetUpdateListener {

    private ArrayList<BudgetItem> budgetItems;

    private BudgetItem mainBudget;
    private double totalUsedAccounted;

    TextView budgetView;
    TextView budgetUsedView;
    ListView budgetListView;
    BudgetItemAdapter budgetItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        //replaced with database population

        ParseQuery<BudgetItem> budgetQuery = ParseQuery.getQuery(BudgetItem.class);
        budgetQuery.whereEqualTo(BudgetItem.TITLE, BudgetItem.MAIN_TITLE);
        budgetQuery.findInBackground(new FindCallback<BudgetItem>() {
            @Override
            public void done(List<BudgetItem> objects, ParseException e) {
                if (objects.isEmpty() || objects == null) {
                    //TODO: custom dialog to set budget if none exists;
                    mainBudget = new BudgetItem();
                    mainBudget.setTitle(BudgetItem.MAIN_TITLE);
                    mainBudget.saveInBackground();
                } else {
                    mainBudget = objects.get(0);
                }

                budgetView.setText(NumberFormat.getCurrencyInstance().format(
                        mainBudget.getValue()));
                budgetUsedView.setText(NumberFormat.getCurrencyInstance().format(
                        mainBudget.getUsed()));
            }
        });

        budgetView =  (TextView) findViewById(R.id.budgetValueText);
        budgetUsedView =  (TextView) findViewById(R.id.budgetUsedText);


        budgetItems = new ArrayList<>();

        ParseQuery<BudgetItem> arrayQuery = ParseQuery.getQuery(BudgetItem.class);
        arrayQuery.whereNotEqualTo(BudgetItem.TITLE, BudgetItem.MAIN_TITLE);

        arrayQuery.findInBackground(new FindCallback<BudgetItem>() {
            @Override
            public void done(List<BudgetItem> objects, ParseException e) {
                budgetItems = new ArrayList<BudgetItem>(objects);
                caculateListUsed();
                budgetItemAdapter = new BudgetItemAdapter(getApplicationContext(), budgetItems);

                budgetListView = (ListView) findViewById(R.id.itemizedBudgetList);

                budgetListView.setAdapter(budgetItemAdapter);
                budgetListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
                                                   long id) {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                        if (prev != null) {
                            ft.remove(prev);
                        }
                        ft.addToBackStack(null);
                        DialogFragment newFragment = BudgetUpdateFragment.newInstance(
                                budgetItems.get(position).getValue(), budgetItems.get(position).getUsed(),
                                position, budgetItems.get(position).getTitle());
                        newFragment.show(ft, "dialog");
                        return false;
                    }
                });
            }
        });







    }

    public void updateBudgetAndUsed(double budget, double used, int position) {

        if(position == -1) {
            mainBudget.setValue(budget);
            mainBudget.setUsed(used);
            mainBudget.saveInBackground();

            budgetView.setText(NumberFormat.getCurrencyInstance().format(budget));
            budgetUsedView.setText(NumberFormat.getCurrencyInstance().format(used));
        } else {
            budgetItems.get(position).setValue(budget);
            budgetItems.get(position).setUsed(used);
            budgetItems.get(position).saveInBackground();
        }


        removeDialog();
    }

    protected void removeDialog() {
        Fragment dialog = getFragmentManager().findFragmentByTag("dialog");
        if (dialog != null) ((DialogFragment) dialog).dismiss();
        budgetItemAdapter.notifyDataSetChanged();
        caculateListUsed();
    }

    public void launchNewItemDialog(View v){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment newFragment = BudgetUpdateFragment.newInstance(0, 0, -2,
                BudgetUpdateFragment.NEW_FLAG);

        newFragment.show(ft, "dialog");
    }

    public void launchUpdateDialog(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment newFragment = BudgetUpdateFragment.newInstance(mainBudget.getValue(),
                mainBudget.getUsed(), -1, null);

        newFragment.show(ft, "dialog");


    }

    public void deleteItem(int pos) {
        budgetItems.get(pos).deleteInBackground();
        budgetItems.remove(pos);
        budgetItemAdapter.notifyDataSetChanged();

    }

    public void addItem(BudgetItem item) {
        budgetItems.add(item);
        item.saveInBackground();
        budgetItemAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_addbudgettoolbar) { //Rehana Added this 2/11/15

        }
        if (id == R.id.action_updatebudgettool) { //Rehana Added this 2/11/15

        }


        return super.onOptionsItemSelected(item);
    }

    public void caculateListUsed(){
        double used = 0;
        for(BudgetItem item: budgetItems) {
            used += item.getUsed();
        }

        ((TextView) findViewById(R.id.textCalculateUsed)).setText(
                NumberFormat.getCurrencyInstance().format(used));
    }

}
