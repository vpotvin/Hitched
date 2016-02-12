package edu.uco.weddingcrashers.hitched;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by PC User on 2/11/2016.
 */
public class VendorsDetailFragment extends Fragment {
    private static final String ARG_URI = "https://www.google.com/search?q=catering+wedding+vendor&oq=catering+wedding+vendor&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=catering+wedding+vendor+in+okc&istate=lrl:xpd";
    private Uri mUri;
    private WebView mWebView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mWebView.loadUrl(ARG_URI.toString());
        return view;
    }
}
