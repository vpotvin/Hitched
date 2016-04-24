package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity {
    EditText editText , editText1;

    Button addButton;
    ListView listView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        Intent intent = getIntent();
        editText = (EditText) findViewById(R.id.editText);
        editText1 = (EditText) findViewById(R.id.editText1);
        addButton = (Button) findViewById(R.id.addItem);
        listView = (ListView) findViewById(R.id.listView);
        listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        updateUIS();
        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                listItems.add("Table " + editText.getText().toString() + " - " + editText1.getText().toString());
                adapter.notifyDataSetChanged();
                ParseObject tables = new ParseObject("TableList");
                tables.put("TableNumber", editText.getText().toString());
                tables.put("TableName", editText1.getText().toString());
                tables.saveInBackground();
                editText.setText("");
                editText1.setText("");
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> a, View v, int position,
                                           long id) {
                Object thistoRemove = adapter.getItem(position);
                adapter.remove(thistoRemove.toString());
                Toast.makeText(TableActivity.this, "Selected table has been removed.", Toast.LENGTH_LONG)
                        .show();

                ;
           return true; }
        });
    }

    public void updateUIS() {
        ParseQuery query = new ParseQuery("TableList");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> items, ParseException e) {
                if (e == null) {
//                    Log.d("FavoriteMusicList", "Retrieved " + items.size() + " vendors");
                    listItems.clear();
                    for (int i = 0; i < items.size(); i++) {
//                        Song song = new Song();
                        String one = items.get(i).getString("TableName");
                        String two = items.get(i).getString("TableNumber");
                        listItems.add(one + " - " + two);
//                        adapter.notifyDataSetChanged();
                    }
                    if (adapter == null) {
                    } else {
                        adapter.notifyDataSetChanged();
                    }

                } else {
                    Log.d("FavoriteVendor", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tableactivitymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_main) {
            Intent main = new Intent(TableActivity.this, MainActivity.class);
            startActivity(main);

        }

        return super.onOptionsItemSelected(item);
    }




}
