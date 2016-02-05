package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class MasterWeddingList extends Activity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<MasterListItem> theList;
    private RecViewAdapter masterListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_wedding_list);

        recyclerView = (RecyclerView) findViewById(R.id.masterListRecView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        theList = new ArrayList<MasterListItem>();

        theList.add(new MasterListItem("Test Item1",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item2",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item3",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item4",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item5",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item6",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item7",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item8",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item9",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item10",new Date(),new Date(),"here are some notes",false));

        masterListAdapter = new RecViewAdapter(this,theList);
        recyclerView.setAdapter(masterListAdapter);

    }
}
