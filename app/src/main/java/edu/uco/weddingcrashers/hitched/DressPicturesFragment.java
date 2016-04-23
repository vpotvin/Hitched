package edu.uco.weddingcrashers.hitched;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class DressPicturesFragment extends Fragment {

    private WebView webView;
    private ProgressDialog progDailog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venue_pictures,container,false);
        webView = (WebView) view.findViewById(R.id.web_tutorial_web_view);
        progDailog = ProgressDialog.show(getActivity(), "Loading", "Please wait...", true);
        progDailog.setCancelable(false);



        webView = (WebView) view.findViewById(R.id.web_tutorial_web_view);



        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progDailog.show();
                view.loadUrl(url);

                return true;
            }
            @Override
            public void onPageFinished(WebView view, final String url) {
                progDailog.dismiss();
            }
        });

        webView.loadUrl("http://www.impressionbridalstore.com/");

        return view;
    }
}
