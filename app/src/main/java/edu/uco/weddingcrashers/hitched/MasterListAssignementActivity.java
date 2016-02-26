package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MasterListAssignementActivity extends AppCompatActivity {

    private Intent intent;
    private MasterListItem task;
    private ArrayList<GuestListItem> assignedTo;
    private ArrayList<GuestListItem> guestTo;
    private ListView assListView, guestListView;
    private ArrayList<String> guests;
    private ArrayList<String> assigned;
    ArrayAdapter guestArrayAdapter;
    ArrayAdapter assArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_list_assignement);
        intent = getIntent();
        task = (MasterListItem) intent.getSerializableExtra("task");
        assignedTo = task.getAssignedTo();
        assListView = (ListView) findViewById(R.id.masterListAssignedToListView);

        assigned = new ArrayList<>();
        guests = new ArrayList<>();

        ParseQuery<GuestListItem> query = ParseQuery.getQuery(GuestListItem.class);

        query.findInBackground(new FindCallback<GuestListItem>() {
            @Override
            public void done(List<GuestListItem> objects, ParseException e) {
                guestTo = new ArrayList<GuestListItem>(objects);
                if (guestTo.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "There was no data found",
                            Toast.LENGTH_SHORT);
                }

                guestListView = (ListView) findViewById(R.id.masterListGuestListView);
                guestArrayAdapter = new MasterListAssignementAdapter(MasterListAssignementActivity.this, guestTo);
                guestListView.setAdapter(guestArrayAdapter);
                UtilityFunctions.setListViewHeightBasedOnChildren(guestListView);

                guestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        assignedTo.add(guestTo.get(position));
                        guestTo.remove(position);
                        guestArrayAdapter.notifyDataSetChanged();
                        assArrayAdapter.notifyDataSetChanged();
                        UtilityFunctions.setListViewHeightBasedOnChildren(assListView);
                        UtilityFunctions.setListViewHeightBasedOnChildren(guestListView);
                    }
                });
            }
        });

        assArrayAdapter = new MasterListAssignementAdapter(MasterListAssignementActivity.this, assignedTo);
        assListView.setAdapter(assArrayAdapter);

        /*
        for (int x = 0; x < assignedTo.size(); x++) {
            assigned.add(x, assignedTo.get(x).getName());
        }

        assArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                assigned);


        assListView.setAdapter(assArrayAdapter);
        */

        UtilityFunctions.setListViewHeightBasedOnChildren(assListView);


        assListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                guestTo.add(assignedTo.get(position));
                assignedTo.remove(position);
                guestArrayAdapter.notifyDataSetChanged();
                assArrayAdapter.notifyDataSetChanged();
                UtilityFunctions.setListViewHeightBasedOnChildren(assListView);
                UtilityFunctions.setListViewHeightBasedOnChildren(guestListView);
            }
        });
    }
}
