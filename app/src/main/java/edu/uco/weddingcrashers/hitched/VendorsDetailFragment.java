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
    private WebView mWebView;
    private Vendor mVendor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID vendorID = (UUID)getActivity().getIntent().getSerializableExtra(VendorsDetailActivity.EXTRA_VENDOR_ID);
        mVendor = VendorList.get(getActivity()).getVendor(vendorID);
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
