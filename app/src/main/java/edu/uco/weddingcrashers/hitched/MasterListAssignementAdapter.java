package edu.uco.weddingcrashers.hitched;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by drenf on 2/25/2016.
 */
public class MasterListAssignementAdapter extends ArrayAdapter<GuestListItem> {
    public MasterListAssignementAdapter(Context context, ArrayList<GuestListItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        GuestListItem item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.master_list_guest_item,
                    parent, false);
        }

        TextView nameText = (TextView) convertView.findViewById(R.id.MasterListGuestName);
        TextView phoneText = (TextView) convertView.findViewById(R.id.MasterListGuestPhoneNumber);
        TextView addressText = (TextView) convertView.findViewById(R.id.MasterListGuestAddress);
        TextView cityText = (TextView) convertView.findViewById(R.id.MasterListGuestCityStateZip);
        TextView roleText = (TextView) convertView.findViewById(R.id.MasterListGuestRole);
        CheckBox weddingPartyCheck = (CheckBox) convertView.findViewById(R.id.MasterListGuestWeddingParty);

        nameText.setText(item.getName());
        phoneText.setText(item.getPhoneNumber());
        addressText.setText(item.getAddress());
        cityText.setText(item.getCity() + "," + item.getState() + " " + item.getZipcode());
        roleText.setText(item.getRole());
        weddingPartyCheck.setChecked(item.isWeddingParty());
        return convertView;
    }


}