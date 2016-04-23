package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tung Nguyen on 2/4/2016.
 */
public class PhotographerDetailsFragment extends Fragment {

    private RecyclerView mVendorRecycleView;
    private PhotographerV mPhotographerV;
    private VendorAdapter mAdapter;
    private String mState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getActivity().getIntent();
        mState = i.getStringExtra("STATE");
//        mState = i.getStringExtra("city");
//        Log.i("TAG","The State Is !!! :"+mState);
        mPhotographerV = new PhotographerV();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_photographer,container,false);
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
        PhotographerList photographerList = PhotographerList.get(getActivity());
        List<PhotographerV> photographerVs = photographerList.getVendors();
        if(mAdapter == null) {
            mAdapter = new VendorAdapter(photographerVs);
            mVendorRecycleView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    private class VendorHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private PhotographerV mPhotographerV;
        public TextView mTextView;
        public ImageView mImageView;
        public VendorHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTextView = (TextView)itemView.findViewById(R.id.list_item_vendor_name_text_view);
            mImageView = (ImageView)itemView.findViewById(R.id.vendorListImageView);
        }
        public void bindVendor(PhotographerV photographerV){
            mPhotographerV = photographerV;
            mTextView.setText(mPhotographerV.getVendorName());
           // mImageView.setImageResource(R.drawable.a);
        }

        @Override
        public void onClick(View view) {
            Intent intent = PhotographerDetailsPagerActivity.newIntent(getActivity(), mPhotographerV.getVendorID());
            startActivity(intent);
        }
    }

    private class VendorAdapter extends RecyclerView.Adapter<VendorHolder>{
        private List<PhotographerV> mPhotographerVs;

        public VendorAdapter(List<PhotographerV> photographerVs){
            mPhotographerVs = photographerVs;
        }

        @Override
        public VendorHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_photographer_details,viewGroup,false);
            return new VendorHolder(view);
        }

        @Override
        public void onBindViewHolder(VendorHolder vendorHolder, int i) {
            PhotographerV photographerV = mPhotographerVs.get(i);
            vendorHolder.bindVendor(photographerV);
        }

        @Override
        public int getItemCount() {
            return mPhotographerVs.size();
        }

    }


}
