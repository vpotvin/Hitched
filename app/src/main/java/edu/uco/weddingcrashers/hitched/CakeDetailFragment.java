package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by PC User on 2/11/2016.
 */
public class CakeDetailFragment extends Fragment {
    private static final String ARG_VENDOR_ID = "vendorID";
    private static final String DIALOG_SAVE_VENDOR = "DialogSaveVendor";
//    private WebView mWebView;
    private CakeV mCakeV;
    private RecyclerView mVendorPlaceRecycleView;
    private List<CakePlace> mCakePlaces = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FetchItemsTask().execute();

        UUID vendorID = (UUID)getArguments().getSerializable(ARG_VENDOR_ID);
        mCakeV = CakeList.get(getActivity()).getVendor(vendorID);
    }

    public static CakeDetailFragment newInstance(UUID vendorID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_VENDOR_ID,vendorID);

        CakeDetailFragment fragment = new CakeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_cake,container,false);
        mVendorPlaceRecycleView = (RecyclerView) view.findViewById(R.id.detail_vendor_recycle_view);
        mVendorPlaceRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        mWebView = (WebView)view.findViewById(R.id.web_view);
//        mWebView = new WebView(getActivity());
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//        });
//        mWebView.loadUrl(mCakeV.getVendorWebsite());
        updateUI();
        return view;
    }


    private class FetchItemsTask extends AsyncTask<Void,Void,List<CakePlace>>{

        @Override
        protected List<CakePlace> doInBackground(Void... voids) {
//            return new CakePlaceFetchr().fetchItems(mCakeV.getQuery()+ ((ParseDatabase)getActivity().getApplication()).getUserState());
            return new CakePlaceFetchr().fetchItems(mCakeV.getQuery());

        }

        @Override
        protected void onPostExecute(List<CakePlace> cakePlaces) {
            mCakePlaces = cakePlaces;
            updateUI();
        }
    }

    private class VendorPlaceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CakePlace mVendor;
        public TextView mNameTextView,mAddressTextView,mPriveLevelTextView,mRatingTextView;
        public VendorPlaceHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = (TextView)itemView.findViewById(R.id.vendor_name_text_view);
            mAddressTextView = (TextView)itemView.findViewById(R.id.vendor_address_text_view);
            mPriveLevelTextView = (TextView)itemView.findViewById(R.id.vendor_price_level_text_view);
            mRatingTextView=(TextView)itemView.findViewById(R.id.vendor_rating_text_view);
        }
        public void bindVendor(CakePlace vendor){
            mVendor = vendor;
            mNameTextView.setText(mVendor.getName());
            mAddressTextView.setText(mVendor.getAddress());
          //  mPriveLevelTextView.setText(mCakeV.getPriceLevel());
          //  mRatingTextView.setText(mCakeV.getRating());

            // mImageView.setImageResource(R.drawable.a);
        }

        @Override
        public void onClick(View view) {
           Intent intent = CakeVActivity.newIntent(getActivity(), mVendor.getID(), mVendor.getIconURL());
            startActivity(intent);
        }
    }

    private class VendorPlaceAdapter extends RecyclerView.Adapter<VendorPlaceHolder>{
        private List<CakePlace> mVendors;

        public VendorPlaceAdapter(List<CakePlace> vendors){
            mVendors = vendors;
        }

        @Override
        public VendorPlaceHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_cake_detail,viewGroup,false);
            return new VendorPlaceHolder(view);
        }

        @Override
        public void onBindViewHolder(VendorPlaceHolder vendorHolder, int i) {
            CakePlace vendor = mVendors.get(i);
            vendorHolder.bindVendor(vendor);
        }

        @Override
        public int getItemCount() {
            return mVendors.size();
        }

    }
    private void updateUI(){
       if(isAdded()){
           mVendorPlaceRecycleView.setAdapter(new VendorPlaceAdapter(mCakePlaces));
       }
    }


}
