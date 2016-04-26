package edu.uco.weddingcrashers.hitched;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drenf on 2/10/2016.
 */
public class GuestListRecViewAdapter extends
        RecyclerView.Adapter<GuestListRecViewAdapter.MyViewHolder> {

    private ArrayList<GuestListItem> theList;
    //private LayoutInflater inflater;

    public GuestListRecViewAdapter() {

        ParseQuery<GuestListItem> query = ParseQuery.getQuery(GuestListItem.class);

        query.findInBackground(new FindCallback<GuestListItem>() {
            @Override
            public void done(List<GuestListItem> objects, ParseException e) {
                theList = new ArrayList<GuestListItem>(objects);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guest_list_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(theList != null)
        {

            holder.name.setText(theList.get(position).getName());
            holder.phoneNumber.setText(theList.get(position).getPhoneNumber());
            holder.address.setText(theList.get(position).getAddress());
            holder.role.setText(theList.get(position).getRole());
            holder.weddingParty.setChecked(theList.get(position).isWeddingParty());
            holder.checkRSVP.setChecked(theList.get(position).getRSVP());

            final int pos = position;

            holder.checkRSVP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                theList.get(pos).setRSVP(isChecked);
                theList.get(pos).saveInBackground();
            }
        });

            holder.weddingParty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    theList.get(pos).setWeddingParty(isChecked);
                    theList.get(pos).saveInBackground();
                }
            });

        String csz = theList.get(position).getAddress() + "," + theList.get(position).getState() + " " + theList.get(position).getZipcode();

            holder.cityStateZip.setText(csz);
        }

    }


    @Override
    public int getItemCount() {
        if (theList != null)
        {
            return theList.size();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void updateTheList()
    {
        ParseQuery<GuestListItem> query = ParseQuery.getQuery(GuestListItem.class);

        query.findInBackground(new FindCallback<GuestListItem>() {
            @Override
            public void done(List<GuestListItem> objects, ParseException e) {
                theList = new ArrayList<GuestListItem>(objects);
                notifyDataSetChanged();
            }
        });
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView phoneNumber;
        private TextView address;
        private TextView cityStateZip;
        private TextView role;
        private CheckBox weddingParty;
        private CheckBox checkRSVP;
        public MyViewHolder(View itemView){
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.GuestListName);
            this.phoneNumber = (TextView) itemView.findViewById(R.id.GuestListPhoneNumber);
            this.address = (TextView) itemView.findViewById(R.id.GuestListAddress);
            this.cityStateZip = (TextView) itemView.findViewById(R.id.GuestListCityStateZip);
            this.role = (TextView) itemView.findViewById(R.id.GuestListRole);
            this.weddingParty = (CheckBox) itemView.findViewById(R.id.GuestListWeddingParty);
            this.checkRSVP = (CheckBox) itemView.findViewById(R.id.checkRSVP);
        }
    }


}
