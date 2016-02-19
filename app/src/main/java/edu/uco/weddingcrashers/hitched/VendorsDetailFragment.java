package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.UUID;

/**
 * Created by PC User on 2/11/2016.
 */
public class VendorsDetailFragment extends Fragment {
    private static final String ARG_VENDOR_ID = "vendorID";
    private WebView mWebView;
    private Vendor mVendor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        View view = inflater.inflate(R.layout.fragment_vendors_detail,container,false);
        mWebView = (WebView)view.findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        mWebView.loadUrl(mVendor.getVendorWebsite());
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_vendors_detail,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_vendor:
                Vendor vendor = new Vendor();
                VendorList.get(getActivity()).addVendor(vendor);
                Intent intent = VendorDetailsPagerActivity.newIntent(getActivity(),vendor.getVendorID());
                startActivity(intent);
                return true;
            default:return super.onOptionsItemSelected(item);
        }

    }
}
