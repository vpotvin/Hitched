package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

/**
 * Created by PC User on 3/17/2016.
 */
public class VendorDealFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Button searchButton;
    private Spinner dealSpinner;
    private List<Vendor> mVendorList;
    private String dealCategory;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vendor_deal,container,false);
        searchButton = (Button)v.findViewById(R.id.vendor_deal_button);
        dealSpinner = (Spinner)v.findViewById(R.id.vendor_deal_spinner);
        VendorList vendorList = VendorList.get(getActivity());
        mVendorList = vendorList.getVendors();
        ArrayAdapter<Vendor> adapter = new ArrayAdapter<Vendor>(getActivity(),android.R.layout.simple_spinner_item,mVendorList);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
       dealSpinner.setAdapter(adapter);
        dealSpinner.setOnItemSelectedListener(this);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),DealDetailActivity.class);
                i.putExtra("CATEGORY",dealCategory);
                startActivity(i);
            }
        });
        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        dealCategory = mVendorList.get(pos).toString();
        Toast.makeText(getActivity(),dealCategory,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getActivity(),"Please choose category",Toast.LENGTH_SHORT).show();
    }


}
