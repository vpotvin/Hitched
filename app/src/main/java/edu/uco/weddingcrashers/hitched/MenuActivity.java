package edu.uco.weddingcrashers.hitched;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private ListView menu;
    private Button addItem;
    ArrayList<MenuItem> values = new ArrayList<>();
    ArrayList<String> valuesStrings = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menu = (ListView) findViewById(R.id.menuListView);

        ParseQuery<MenuItem> query = ParseQuery.getQuery(MenuItem.class);


        menu.setLongClickable(true);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, valuesStrings);
        menu.setAdapter(adapter);
        addItem = (Button) findViewById(R.id.addMenuItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                builder.setTitle("Add Item");
                final EditText input = new EditText(MenuActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MenuItem newItem = new MenuItem(input.getText().toString());
                        newItem.saveInBackground();
                        valuesStrings.add(input.getText().toString());

                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        menu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                MenuItem toDelete = values.get(pos);
                toDelete.deleteEventually();
                valuesStrings.remove(pos);
                adapter.notifyDataSetChanged();

                return true;
            }
        });

        query.findInBackground(new FindCallback<MenuItem>() {
            @Override
            public void done(List<MenuItem> objects, ParseException e) {
                values = new ArrayList<MenuItem>(objects);
                for (int x = 0; x < values.size(); x++) {
                    valuesStrings.add(values.get(x).getItem());
                }
                adapter.notifyDataSetChanged();
            }
        });

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


    }

}
