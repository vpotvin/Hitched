package edu.uco.weddingcrashers.hitched;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by PC User on 3/18/2016.
 */
public class WebTutorialFragment extends Fragment {
    private WebView mWebView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_tutorial,container,false);
        mWebView = (WebView) view.findViewById(R.id.web_tutorial_web_view);
        mWebView = new WebView(getActivity());
        mWebView.getSettings().setJavaScriptEnabled(true);
        //mWebView.getSettings().setBuiltInZoomControls(true);
        //mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //mWebView.setInitialScale(50);
        mWebView.loadUrl("http://www.google.com");
        return view;
    }
}
