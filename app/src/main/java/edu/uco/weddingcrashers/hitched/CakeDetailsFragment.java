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
public class CakeDetailsFragment extends Fragment {

    private RecyclerView mVendorRecycleView;
    private CakeV mCakeV;
    private VendorAdapter mAdapter;
    private String mState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getActivity().getIntent();
        mState = i.getStringExtra("STATE");
//        mState = i.getStringExtra("city");
//        Log.i("TAG","The State Is !!! :"+mState);
        mCakeV = new CakeV();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_cake,container,false);
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
        CakeList cakeList = CakeList.get(getActivity());
        List<CakeV> cakeVs = cakeList.getVendors();
        if(mAdapter == null) {
            mAdapter = new VendorAdapter(cakeVs);
            mVendorRecycleView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    private class VendorHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CakeV mCakeV;
        public TextView mTextView;
        public ImageView mImageView;
        public VendorHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTextView = (TextView)itemView.findViewById(R.id.list_item_vendor_name_text_view);
            mImageView = (ImageView)itemView.findViewById(R.id.vendorListImageView);
        }
        public void bindVendor(CakeV cakeV){
            mCakeV = cakeV;
            mTextView.setText(mCakeV.getVendorName());
           // mImageView.setImageResource(R.drawable.a);
        }

        @Override
        public void onClick(View view) {
            Intent intent = CakeDetailsPagerActivity.newIntent(getActivity(), mCakeV.getVendorID());
            startActivity(intent);
        }
    }

    private class VendorAdapter extends RecyclerView.Adapter<VendorHolder>{
        private List<CakeV> mCakeVs;

        public VendorAdapter(List<CakeV> cakeVs){
            mCakeVs = cakeVs;
        }

        @Override
        public VendorHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_cake_details,viewGroup,false);
            return new VendorHolder(view);
        }

        @Override
        public void onBindViewHolder(VendorHolder vendorHolder, int i) {
            CakeV cakeV = mCakeVs.get(i);
            vendorHolder.bindVendor(cakeV);
        }

        @Override
        public int getItemCount() {
            return mCakeVs.size();
        }

    }


}
