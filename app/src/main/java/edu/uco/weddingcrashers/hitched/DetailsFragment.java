package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Tung Nguyen on 2/4/2016.
 */
public class DetailsFragment extends Fragment {

    private RecyclerView mVendorRecycleView;
    private Vendor mVendor;
    private VendorAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVendor = new Vendor();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail,container,false);
        mVendorRecycleView = (RecyclerView) view.findViewById(R.id.detail_vendor_recycle_view);
        mVendorRecycleView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        updateUI();
        return view;
    }

    private void updateUI(){
        VendorList vendorList = VendorList.get(getActivity());
        List<Vendor> vendors = vendorList.getVendors();

        mAdapter = new VendorAdapter(vendors);
        mVendorRecycleView.setAdapter(mAdapter);
    }

    private class VendorHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Vendor mVendor;
        private TextView mNameTextView;
        private ImageView mImageView;

        public VendorHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mImageView = (ImageView)itemView.findViewById(R.id.vendorListImageView);
            mNameTextView = (TextView)itemView.findViewById(R.id.list_item_vendor_name_text_view)  ;

        }
        public void bindVendor(Vendor vendor){
            mVendor = vendor;
            mNameTextView.setText(mVendor.getVendorName());
            mImageView.setImageResource(R.drawable.a);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),"clicked",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(),VendorActivity.class);
            startActivity(intent);
        }
    }

    private class VendorAdapter extends RecyclerView.Adapter<VendorHolder>{
        private List<Vendor> mVendors;
        public VendorAdapter(List<Vendor> vendors) {
            mVendors = vendors;
        }

        @Override
        public VendorHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_vendor_details, viewGroup,false);
            return new VendorHolder(view);
        }

        @Override
        public void onBindViewHolder(VendorHolder vendorHolder, int i) {
            Vendor vendor = mVendors.get(i);
            vendorHolder.bindVendor(vendor);
        }

        @Override
        public int getItemCount() {
            return mVendors.size();
        }
    }
}
