package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by PC User on 3/17/2016.
 */
public class DealDetailFragment extends Fragment {
    private List<Coupon> mCouponList;
    private String category;
    //private TextView mCouponTextView;
    private DealAdapter mAdapter;
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deal_detail,container,false);
       // mCouponTextView = (TextView)view.findViewById(R.id.deal_detail_text_view);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.detail_deal_recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Intent intent = getActivity().getIntent();
        category = intent.getStringExtra("CATEGORY");
        new FetchDealTask().execute();
        return view;
    }

    private class FetchDealTask extends AsyncTask<Void,Void,List<Coupon>> {

        @Override
        protected List<Coupon> doInBackground(Void... voids) {
            return new PlaceFetchr().fetchCoupon(category,((ParseDatabase)getActivity().getApplication()).getUserState());

        }

        @Override
        protected void onPostExecute(List<Coupon> coupons) {
            mCouponList = coupons;
            if(mAdapter == null) {
                mAdapter = new DealAdapter(mCouponList);
                mRecyclerView.setAdapter(mAdapter);
            }else{
                mAdapter.notifyDataSetChanged();
            }
//            if(coupons.size() > 0) {
//                for (int i = 0; i < coupons.size(); i++) {
//                    Coupon c = coupons.get(i);
//                    mCouponTextView.append((i+1)+".\n");
//                    mCouponTextView.append("Business Name: " + c.getBusinessName());
//                    mCouponTextView.append("\n");
//                    mCouponTextView.append("Address: " + c.getCouponAddress());
//                    mCouponTextView.append("\n");
//                    mCouponTextView.append("Phone: " + c.getCouponPhone());
//                    mCouponTextView.append("\n");
//                    mCouponTextView.append("Coupon Title: " + c.getCouponTitle());
//                    mCouponTextView.append("\n");
//                    mCouponTextView.append("Coupon Name: " + c.getCouponName());
//                    mCouponTextView.append("\n");
//                    mCouponTextView.append("Coupon Description: " + c.getCouponDescription());
//                    mCouponTextView.append("\n");
//                    mCouponTextView.append("Coupon Disclaimer: " + c.getCouponDisclaimer());
//                    mCouponTextView.append("\n");
//                    mCouponTextView.append("Start Date: " + c.getCouponStartDate());
//                    mCouponTextView.append("\n");
//                    mCouponTextView.append("Expiration Date: " + c.getCouponEndDate());
//                    mCouponTextView.append("\n");
//                    mCouponTextView.append("URL: " + c.getCouponURL());
//                    mCouponTextView.append("\n");
//                    mCouponTextView.append("\n");
//
//                }
//            }
//            else {
//                mCouponTextView.append("Sorry ! No coupon availabe for this category at the moment");
//            }
        }

    }

    private class DealHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public Coupon mCoupon;
        public TextView mTitle,mDescription,mExpiration;
        public ImageView mImageView;
        public DealHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitle = (TextView)itemView.findViewById(R.id.deal_title);
            mDescription = (TextView)itemView.findViewById(R.id.deal_description);
            mExpiration = (TextView)itemView.findViewById(R.id.deal_expiration);
            mImageView = (ImageView)itemView.findViewById(R.id.deal_image);


        }
        public void bindDeal(Coupon c){

            mCoupon = c;
            if(c.getCouponTitle() != "")
                mTitle.setText(c.getBusinessName());
            else {
                mTitle.setText("No Title");
            }
            if(c.getCouponDescription() != "")
                mDescription.setText(new StringBuilder().append(c.getCouponTitle()).append("\n").append(c.getCouponDescription()).toString());
            if(c.getCouponEndDate() != "")
                mExpiration.setText("Expiration: " + c.getCouponEndDate());
            c.setLogoURL(null);
            Picasso.with(getActivity())
                    .load(c.getLogoURL())
                    .placeholder(R.drawable.error)
                    .error(R.drawable.error)
                    .into(mImageView);
            //mImageView.setImageResource(R.drawable.a);
        }

        @Override
        public void onClick(View view) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mCoupon.getCouponURL()));
            startActivity(browserIntent);

        }
    }

    private class DealAdapter extends RecyclerView.Adapter<DealHolder>{
        private List<Coupon> mCoupons;

        public DealAdapter(List<Coupon> coupons){
            mCoupons = coupons;
        }

        @Override
        public DealHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_deal_details,viewGroup,false);
            return new DealHolder(view);
        }

        @Override
        public void onBindViewHolder(DealHolder dealHolder, int i) {
            Coupon mCoupon = mCoupons.get(i);
            dealHolder.bindDeal(mCoupon);
        }

        @Override
        public int getItemCount() {
            return mCoupons.size();
        }

    }
}
