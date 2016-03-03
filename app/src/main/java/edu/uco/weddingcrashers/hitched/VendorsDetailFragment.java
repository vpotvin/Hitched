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
public class VendorsDetailFragment extends Fragment {
    private static final String ARG_VENDOR_ID = "vendorID";
    private static final String DIALOG_SAVE_VENDOR = "DialogSaveVendor";
//    private WebView mWebView;
    private Vendor mVendor;
    private RecyclerView mVendorPlaceRecycleView;
    private List<VendorPlace> mVendorPlaces = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FetchItemsTask().execute();

        UUID vendorID = (UUID)getArguments().getSerializable(ARG_VENDOR_ID);
        mVendor = VendorList.get(getActivity()).getVendor(vendorID);
    }

    public static VendorsDetailFragment newInstance(UUID vendorID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_VENDOR_ID,vendorID);

        VendorsDetailFragment fragment = new VendorsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail,container,false);
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
//        mWebView.loadUrl(mVendor.getVendorWebsite());
        updateUI();
        return view;
    }


    private class FetchItemsTask extends AsyncTask<Void,Void,List<VendorPlace>>{

        @Override
        protected List<VendorPlace> doInBackground(Void... voids) {
            return new PlaceFetchr().fetchItems(mVendor.getQuery());

        }

        @Override
        protected void onPostExecute(List<VendorPlace> vendorPlaces) {
            mVendorPlaces = vendorPlaces;
            updateUI();
        }
    }

    private class VendorPlaceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private VendorPlace mVendor;
        public TextView mNameTextView,mAddressTextView,mPriveLevelTextView,mRatingTextView;
        public VendorPlaceHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = (TextView)itemView.findViewById(R.id.vendor_name_text_view);
            mAddressTextView = (TextView)itemView.findViewById(R.id.vendor_address_text_view);
            mPriveLevelTextView = (TextView)itemView.findViewById(R.id.vendor_price_level_text_view);
            mRatingTextView=(TextView)itemView.findViewById(R.id.vendor_rating_text_view);
        }
        public void bindVendor(VendorPlace vendor){
            mVendor = vendor;
            mNameTextView.setText(mVendor.getName());
            mAddressTextView.setText(mVendor.getAddress());
          //  mPriveLevelTextView.setText(mVendor.getPriceLevel());
          //  mRatingTextView.setText(mVendor.getRating());

            // mImageView.setImageResource(R.drawable.a);
        }

        @Override
        public void onClick(View view) {
           Intent intent = VendorActivity.newIntent(getActivity(),mVendor.getID(),mVendor.getIconURL());
            startActivity(intent);
        }
    }

    private class VendorPlaceAdapter extends RecyclerView.Adapter<VendorPlaceHolder>{
        private List<VendorPlace> mVendors;

        public VendorPlaceAdapter(List<VendorPlace> vendors){
            mVendors = vendors;
        }

        @Override
        public VendorPlaceHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_vendors_detail,viewGroup,false);
            return new VendorPlaceHolder(view);
        }

        @Override
        public void onBindViewHolder(VendorPlaceHolder vendorHolder, int i) {
            VendorPlace vendor = mVendors.get(i);
            vendorHolder.bindVendor(vendor);
        }

        @Override
        public int getItemCount() {
            return mVendors.size();
        }

    }
    private void updateUI(){
       if(isAdded()){
           mVendorPlaceRecycleView.setAdapter(new VendorPlaceAdapter(mVendorPlaces));
       }
    }


}
