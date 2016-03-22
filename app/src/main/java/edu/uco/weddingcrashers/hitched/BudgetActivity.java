package edu.uco.weddingcrashers.hitched;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class BudgetActivity extends AppCompatActivity implements
        BudgetUpdateFragment.BudgetUpdateListener {

    private ArrayList<BudgetItem> budgetItems;

    private double budget;
    private double used;

    TextView budgetView;
    TextView budgetUsedView;
    ListView budgetListView;
    BudgetItemAdapter budgetItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        //replaced with database population
        budget = 10000;
        used = 2000;

        budgetView =  (TextView) findViewById(R.id.budgetValueText);
        budgetUsedView =  (TextView) findViewById(R.id.budgetUsedText);

        budgetView.setText(NumberFormat.getCurrencyInstance().format(budget));
        budgetUsedView.setText(NumberFormat.getCurrencyInstance().format(used));

        budgetItems = new ArrayList<>();

        //will be replaced with database population
        BudgetItem budgetItem = new BudgetItem("Dress", 5000, 0);
        BudgetItem budgetItem2 = new BudgetItem("Cake", 1000, 0);

        budgetItems.add(budgetItem);
        budgetItems.add(budgetItem2);

        budgetItemAdapter = new BudgetItemAdapter(this, budgetItems);

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

    public void updateBudgetAndUsed(double budget, double used, int position) {

        if(position == -1) {
            this.budget = budget;
            this.used = used;

            budgetView.setText(NumberFormat.getCurrencyInstance().format(budget));
            budgetUsedView.setText(NumberFormat.getCurrencyInstance().format(used));
        } else {
            budgetItems.get(position).setValue(budget);
            budgetItems.get(position).setUsed(used);
        }

        removeDialog();
    }

    protected void removeDialog() {
        Fragment dialog = getFragmentManager().findFragmentByTag("dialog");
        if (dialog != null) ((DialogFragment) dialog).dismiss();
        budgetItemAdapter.notifyDataSetChanged();
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

        DialogFragment newFragment = BudgetUpdateFragment.newInstance(budget, used, -1, null);

        newFragment.show(ft, "dialog");


    }

    public void deleteItem(int pos) {
        budgetItems.remove(pos);
    }

    public void addItem(BudgetItem item) {
        budgetItems.add(item);
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

}
