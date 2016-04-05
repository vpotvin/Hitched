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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menu = (ListView) findViewById(R.id.menuListView);

        ParseQuery<MenuItem> query = ParseQuery.getQuery(MenuItem.class);

        query.findInBackground(new FindCallback<MenuItem>() {
            @Override
            public void done(List<MenuItem> objects, ParseException e) {
                values = new ArrayList<MenuItem>(objects);
            }
        });
        menu.setLongClickable(true);
        final ArrayAdapter<MenuItem> adapter = new ArrayAdapter<MenuItem>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
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
                        values.add(newItem);

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
                values.remove(pos);
                adapter.notifyDataSetChanged();

                return true;
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
