package edu.uco.weddingcrashers.hitched;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
}
