package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tung Nguyen on 2/4/2016.
 */
public class DetailsFragment extends Fragment {

    private RecyclerView mVendorRecycleView;
    private Vendor mVendor;
    private VendorAdapter mAdapter;
    private String mState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mVendor = new Vendor();

//        FragmentManager manager = getFragmentManager();
//        SaveVendorFragment dialog = new SaveVendorFragment();
//        dialog.show(manager,"StateDialog");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail,container,false);
        mVendorRecycleView = (RecyclerView) view.findViewById(R.id.detail_vendor_recycle_view);
        mVendorRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        VendorList vendorList = VendorList.get(getActivity());
        List<Vendor> vendors = vendorList.getVendors();
        for(int i = 0;i<vendors.size();i++){
            int index = i+1;
            int drawableID = getResources().getIdentifier("@drawable/a"+index,"drawable",getActivity().getPackageName());
            Log.i("Drawable","ID: " + drawableID);
            Drawable d = getActivity().getResources().getDrawable(drawableID);
            vendors.get(i).setImage(d);
        }
        if(mAdapter == null) {
            mAdapter = new VendorAdapter(vendors);
            mVendorRecycleView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    private class VendorHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Vendor mVendor;
        public TextView mTextView;
        public ImageView mImageView;
        public VendorHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTextView = (TextView)itemView.findViewById(R.id.list_item_vendor_name_text_view);
            mImageView = (ImageView)itemView.findViewById(R.id.vendorListImageView);
        }
        public void bindVendor(Vendor vendor){
            mVendor = vendor;
            mTextView.setText(mVendor.getVendorName());
            mImageView.setImageDrawable(mVendor.getImage());
        }

        @Override
        public void onClick(View view) {
            Intent intent = VendorDetailsPagerActivity.newIntent(getActivity(),mVendor.getVendorID());
            startActivity(intent);
        }
    }

    private class VendorAdapter extends RecyclerView.Adapter<VendorHolder>{
        private List<Vendor> mVendors;

        public VendorAdapter(List<Vendor> vendors){
            mVendors = vendors;
        }

        @Override
        public VendorHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_vendor_details,viewGroup,false);
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
