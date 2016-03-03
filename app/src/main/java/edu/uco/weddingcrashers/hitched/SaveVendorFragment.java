package edu.uco.weddingcrashers.hitched;


import android.app.Dialog;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


/**
 * Created by PC User on 2/18/2016.
 */
public class SaveVendorFragment extends DialogFragment {
    private static final String ARG_URL = "url";
    private WebView mWebView;
    private String mUrl;
    public static SaveVendorFragment newInstance(String url){
        Bundle args = new Bundle();
        args.putString(ARG_URL,url);

        SaveVendorFragment fragment = new SaveVendorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_save_vendor,null);
        mUrl = getArguments().getString(ARG_URL);
        mWebView = (WebView) view.findViewById(R.id.ratingWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false;
            }
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // Ignore SSL certificate errors
            }
        });
        mWebView.loadUrl(mUrl);
        Toast.makeText(getActivity(),"url: " + mUrl,Toast.LENGTH_SHORT).show();
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Rate Vendor")
                .setPositiveButton("Rate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(),"Rate Successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Discard",null)
                .create();
    }
}
