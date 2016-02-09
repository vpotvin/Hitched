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

import java.text.NumberFormat;
import java.util.ArrayList;

public class BudgetActivity extends Activity implements
        BudgetUpdateFragment.BudgetUpdateListener {

    private ArrayList<BudgetItem> budgetItems;

    private double budget;
    private double used;

    TextView budgetView;
    TextView budgetUsedView;
    ListView budgetListView;


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

        BudgetItemAdapter budgetItemAdapter = new BudgetItemAdapter(this, budgetItems);

        budgetListView = (ListView) findViewById(R.id.itemizedBudgetList);

        budgetListView.setAdapter(budgetItemAdapter);
        budgetListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
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
    }

    public void launchUpdateDialog(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment newFragment;

        if(v.getId() == R.id.updateBudgetBtn) newFragment =
                BudgetUpdateFragment.newInstance(budget, used, -1);
        else {
            int pos = ((ListView) v).getCheckedItemPosition();
            newFragment = BudgetUpdateFragment.newInstance(budgetItems.get(pos).getValue(),
                    budgetItems.get(pos).getUsed(), pos);
        }

        newFragment.show(ft, "dialog");


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

        return super.onOptionsItemSelected(item);
    }

}
