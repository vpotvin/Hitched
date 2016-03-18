package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PC User on 3/17/2016.
 */
public class DealDetailFragment extends Fragment {
    private List<Coupon> mCouponList;
    private String category;
    private TextView mCouponTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deal_detail,container,false);
        mCouponTextView = (TextView)view.findViewById(R.id.deal_detail_text_view);
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
            if(coupons.size() > 0) {
                for (int i = 0; i < coupons.size(); i++) {
                    Coupon c = coupons.get(i);
                    mCouponTextView.append("\n");
                    mCouponTextView.append("Business Name: " + c.getBusinessName());
                    mCouponTextView.append("\n");
                    mCouponTextView.append("Address: " + c.getCouponAddress());
                    mCouponTextView.append("\n");
                    mCouponTextView.append("Phone: " + c.getCouponPhone());
                    mCouponTextView.append("\n");
                    mCouponTextView.append("Coupon Title: " + c.getCouponTitle());
                    mCouponTextView.append("\n");
                    mCouponTextView.append("Coupon Name: " + c.getCouponName());
                    mCouponTextView.append("\n");
                    mCouponTextView.append("Coupon Description: " + c.getCouponDescription());
                    mCouponTextView.append("\n");
                    mCouponTextView.append("Coupon Disclaimer: " + c.getCouponDisclaimer());
                    mCouponTextView.append("\n");
                    mCouponTextView.append("Start Date: " + c.getCouponStartDate());
                    mCouponTextView.append("\n");
                    mCouponTextView.append("Expiration Date: " + c.getCouponEndDate());
                    mCouponTextView.append("\n");
                    mCouponTextView.append("URL: " + c.getCouponURL());
                }
            }
            else {
                mCouponTextView.append("Sorry ! No coupon availabe for this category at the moment");
            }
        }
    }
}
