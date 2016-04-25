package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * 作者：Created by rush_yu on 2016/4/5 09:42
 * 邮箱：yu.hacker.rush@gmail.com
 * version 1.0
 */
public class Flight extends Activity {
    private WebView mWebView;
    private boolean isFirstIn =true;
    private TextView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight);

        initUi();
    }

    private void initUi() {
        mWebView = (WebView) findViewById(R.id.webView);
        WebSettings s = mWebView.getSettings();
       // mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        s.setJavaScriptEnabled(false);
        s.setRenderPriority(WebSettings.RenderPriority.HIGH);
        mBack = (TextView) findViewById(R.id.back);
        mWebView.loadUrl("https://vacation.hotwire.com/Flights-Search?bid=B379784&sid=S3&tmid=9331598111&trip=RoundTrip&leg1=from:HNL,to:SHA,departure:04/13/2016TANYT&leg2=from:SHA,to:HNL,departure:04/15/2016TANYT&passengers=children:0,adults:1,seniors:0,infantinlap:Y&options=sortby:price&mode=search&paandi=true");
       mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //mWebView.loadUrl("http://www.hongkongairport.com/flightinfo/eng/chkfltarr.html");
                mWebView.loadUrl(url);
                return  true;
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flight.this.finish();
            }
        });
       // s.setBuiltInZoomControls(true);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(true);
        s.setRenderPriority(WebSettings.RenderPriority.HIGH);
        s.setJavaScriptEnabled(true);     // enable navigator.geolocation
        // s.setGeolocationEnabled(true);
        //s.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
        //mWebView.setWebChromeClient(new WebChromeClient());// enable Web Storage: localStorage, sessionStorage     s.setDomStorageEnabled(true);  wb.requestFocus();  wb.setScrollBarStyle(0);
        //s.setJavaScriptEnabled(true);
        //s.setBuiltInZoomControls(true);
        //s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //s.setUseWideViewPort(true);
        // s.setLoadWithOverviewMode(true);
        //s.setSavePassword(true);
        //s.setSaveFormData(true);
        // s.setJavaScriptEnabled(true);
        // enable navigator.geolocation
        //s.setGeolocationEnabled(true);
        //s.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
        // enable Web Storage: localStorage, sessionStorage
        // s.setDomStorageEnabled(true);
        mWebView.requestFocus();
        final SVProgressHUD svProgressHUD = new SVProgressHUD(this);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(isFirstIn){
                    isFirstIn = false;
                    svProgressHUD.getProgressBar().setProgress(newProgress);
                    svProgressHUD.showWithProgress("progress " + newProgress + "%", SVProgressHUD.SVProgressHUDMaskType.Black);
                }
                else if(newProgress>80)
                    svProgressHUD.dismiss();
                else {
                    svProgressHUD.getProgressBar().setProgress(newProgress);
                    svProgressHUD.setText("progress "+newProgress+"%");
                }
            }
        });

        //mWebView.setScrollBarStyle(0);
        //http://www.bookingbuddy.com/c/lander/fancy.html?fsOpener=true&mode=hotel&af=11207187&currency=USD&locale=en_US#rooms=1&location2=189433&source=55821&request_id=0d7da4dd-01b1-43d9-9f3c-5571c186d036&travelers=2&traqparam=USNews_SearchLess_Leavebehind_Chrome_Auth&pm_template=o-lBLl1iQcKz3EizTMBxfQ
        mWebView.setWebViewClient(new WebViewClient(){
                                      public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                          // TODO Auto-generated method stub
                                          //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                                          view.loadUrl(url);
                                          //iv.setVisibility(View.GONE);

                                          return true;
                                      }

                                  }

        );
    }
}
